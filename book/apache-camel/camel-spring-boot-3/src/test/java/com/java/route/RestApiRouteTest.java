package com.java.route;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpServer;
import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.AdviceWith;
import org.apache.camel.test.spring.junit5.CamelSpringBootTest;
import org.apache.camel.test.spring.junit5.UseAdviceWith;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

@CamelSpringBootTest
@UseAdviceWith
@SpringBootTest(properties = {
        "server.port=0",
        "app.route1=false",
        "app.route2=false",
        "app.route3=false",
        "app.route4=false",
        "app.rest.route=false",
        "app.kafka.route=false",
        "app.kafka.route.list=false",
        "app.rest-api.get.route=true",
        "app.rest-api.post.route=true",
        "app.rest-api.put.route=true",
        "app.rest-api.delete.route=true",
        "app.rest-api.hateoas.route=true",
        "app.rest-api.i18n.route=true",
        "app.rest-api.versioning.route=true",
        "app.rest-api.h2.route=true"
})
class RestApiRouteTest {

    private static final String STUDENT_JSON = "{\"id\":\"1\",\"name\":\"n\",\"book\":\"b\"}";
    private static final ConcurrentLinkedDeque<Received> REQUESTS = new ConcurrentLinkedDeque<>();
    private static HttpServer server;

    @Autowired
    private CamelContext camelContext;

    @Autowired
    private ProducerTemplate producerTemplate;

    @DynamicPropertySource
    static void restApiBaseUrl(DynamicPropertyRegistry registry) throws IOException {
        server = HttpServer.create(new InetSocketAddress("localhost", 0), 0);
        server.createContext("/", exchange -> {
            try {
                String method = exchange.getRequestMethod();
                String path = exchange.getRequestURI().getRawPath();
                String query = exchange.getRequestURI().getRawQuery();
                Headers requestHeaders = exchange.getRequestHeaders();
                String body = new String(exchange.getRequestBody().readAllBytes(), StandardCharsets.UTF_8);
                REQUESTS.add(new Received(
                        method,
                        path,
                        query,
                        requestHeaders.getFirst("Accept"),
                        requestHeaders.getFirst("Content-Type"),
                        requestHeaders.getFirst("Accept-Language"),
                        requestHeaders.getFirst("name"),
                        requestHeaders.getFirst("book"),
                        requestHeaders.getFirst("X-API-VERSION"),
                        body));

                if ("POST".equals(method) && "/hateoas/api/v1/students".equals(path)) {
                    exchange.getResponseHeaders().add("Location", "http://localhost/hateoas/api/v1/students/1");
                    exchange.sendResponseHeaders(201, -1);
                    exchange.close();
                    return;
                }

                byte[] response = responseFor(method, path);
                exchange.getResponseHeaders().add("Content-Type",
                        path.startsWith("/i18n/") || "/get/api/v1/hello-world".equals(path) ? "text/plain" : "application/json");
                exchange.sendResponseHeaders(200, response.length);
                exchange.getResponseBody().write(response);
                exchange.close();
            } catch (Exception exception) {
                byte[] message = exception.toString().getBytes(StandardCharsets.UTF_8);
                exchange.sendResponseHeaders(500, message.length);
                exchange.getResponseBody().write(message);
                exchange.close();
            }
        });
        server.start();
        registry.add("app.rest-api.base-url", () -> "http://localhost:" + server.getAddress().getPort());
    }

    @AfterAll
    static void stopServer() {
        if (server != null) {
            server.stop(0);
        }
    }

    @Test
    void routesCallEachRestApi() throws Exception {
        for (String routeId : List.of(
                "getControllerRoute",
                "postControllerRoute",
                "putControllerRoute",
                "deleteControllerRoute",
                "hateoasControllerRoute",
                "i18nControllerRoute",
                "versioningControllerRoute",
                "h2ControllerRoute")) {
            AdviceWith.adviceWith(camelContext, routeId, advice -> advice.replaceFromWith("direct:" + routeId));
        }
        camelContext.start();

        producerTemplate.sendBody("direct:getControllerRoute", null);
        assertThat(calls()).containsExactly(
                "GET /get/api/v1/hello-world",
                "GET /get/api/v1/students",
                "GET /get/api/v2/students",
                "GET /get/api/v3/students",
                "GET /get/api/v4/students",
                "GET /get/api/v5/students?book=Java&name=Ann",
                "GET /get/api/v6/students/Ann/Java",
                "GET /get/api/v7/students?page=0&size=2&sort=name",
                "GET /get/api/v1/users");
        assertThat(REQUESTS.stream().filter(call -> "/get/api/v4/students".equals(call.path)).findFirst().orElseThrow().name())
                .isEqualTo("Ann");

        REQUESTS.clear();
        producerTemplate.sendBody("direct:postControllerRoute", null);
        assertThat(calls()).containsExactly(
                "POST /post/api/v1/students",
                "POST /post/api/v2/students",
                "POST /post/api/v3/students",
                "POST /post/api/v4/students?book=Java&name=Ann",
                "POST /post/api/v5/students/Ann/Java",
                "POST /post/api/v6/students/10?book=Java",
                "POST /post/api/v1/employee");
        Received postText = REQUESTS.stream().filter(call -> "/post/api/v1/students".equals(call.path)).findFirst().orElseThrow();
        assertThat(postText.contentType()).startsWith("text/plain");
        assertThat(postText.body()).isEqualTo("{\"name\":\"Ann\",\"book\":\"Java\"}");
        Received postJson = REQUESTS.stream().filter(call -> "/post/api/v2/students".equals(call.path)).findFirst().orElseThrow();
        assertThat(postJson.body()).contains("\"name\":\"Ann\"").contains("\"book\":\"Java\"");
        assertThat(REQUESTS.stream().filter(call -> "/post/api/v3/students".equals(call.path)).findFirst().orElseThrow().name())
                .isEqualTo("Ann");

        REQUESTS.clear();
        producerTemplate.sendBody("direct:putControllerRoute", null);
        assertThat(calls()).containsExactly(
                "PUT /put/api/v1/students",
                "PUT /put/api/v2/students",
                "PUT /put/api/v3/students",
                "PUT /put/api/v4/students?book=Java&name=Ann",
                "PUT /put/api/v5/students/Ann/Java",
                "PUT /put/api/v6/students/10?book=Java");

        REQUESTS.clear();
        producerTemplate.sendBody("direct:deleteControllerRoute", null);
        assertThat(calls()).containsExactly(
                "DELETE /delete/api/v1/students",
                "DELETE /delete/api/v2/students",
                "DELETE /delete/api/v3/students",
                "DELETE /delete/api/v4/students?book=Java&name=Ann",
                "DELETE /delete/api/v5/students/Ann/Java",
                "DELETE /delete/api/v6/students/10?book=Java");
        assertThat(REQUESTS.getFirst().body()).contains("\"name\":\"Ann\"");

        REQUESTS.clear();
        producerTemplate.sendBody("direct:hateoasControllerRoute", null);
        assertThat(calls()).containsExactly(
                "GET /hateoas/api/v1/students",
                "GET /hateoas/api/v1/students/1",
                "POST /hateoas/api/v1/students",
                "POST /hateoas/api/v2/students");

        REQUESTS.clear();
        producerTemplate.sendBody("direct:i18nControllerRoute", null);
        assertThat(calls()).containsExactly(
                "GET /i18n/api/v1/hello-world",
                "GET /i18n/api/v1/hello-world",
                "GET /i18n/api/v1/hello-world/Ann",
                "GET /i18n/api/v1/hello-world/Ann");
        List<Received> i18n = new ArrayList<>(REQUESTS);
        assertThat(i18n.get(0).acceptLanguage()).isNull();
        assertThat(i18n.get(1).acceptLanguage()).isEqualTo("fr");
        assertThat(i18n.get(3).acceptLanguage()).isEqualTo("fr");

        REQUESTS.clear();
        producerTemplate.sendBody("direct:versioningControllerRoute", null);
        assertThat(calls()).containsExactly(
                "GET /versioning/api/v1/students",
                "GET /versioning/api/v2/students",
                "GET /versioning/api/students?VERSION=1",
                "GET /versioning/api/students?VERSION=2",
                "GET /versioning/api/students",
                "GET /versioning/api/students",
                "GET /versioning/api/students",
                "GET /versioning/api/students");
        List<Received> versions = new ArrayList<>(REQUESTS);
        assertThat(versions.get(4).apiVersion()).isEqualTo("1");
        assertThat(versions.get(5).apiVersion()).isEqualTo("2");
        assertThat(versions.get(6).accept()).isEqualTo("application/my-company-v1+json");
        assertThat(versions.get(7).accept()).isEqualTo("application/my-company-v2+json");

        REQUESTS.clear();
        producerTemplate.sendBody("direct:h2ControllerRoute", null);
        assertThat(calls()).containsExactly(
                "POST /h2/apis/v1/employees",
                "POST /h2/apis/v2/employees",
                "GET /h2/apis/v1/employees");
        assertThat(REQUESTS.getFirst().body()).contains("\"joiningDate\":\"24112026\"");
    }

    private static List<String> calls() {
        return REQUESTS.stream().map(call -> {
            String target = call.method + " " + call.path;
            if (call.query == null) {
                return target;
            }
            String query = Arrays.stream(call.query.split("&")).sorted().collect(Collectors.joining("&"));
            return target + "?" + query;
        }).toList();
    }

    private static byte[] responseFor(String method, String path) {
        if ("/get/api/v1/hello-world".equals(path) || path.startsWith("/i18n/")) {
            return "hello".getBytes(StandardCharsets.UTF_8);
        }
        if ("/get/api/v1/users".equals(path)) {
            return "[{\"username\":\"u\",\"firstName\":\"f\",\"lastName\":\"l\"}]".getBytes(StandardCharsets.UTF_8);
        }
        if ("/post/api/v1/employee".equals(path)) {
            return "{\"id\":1,\"name\":\"n\",\"dept\":\"d\"}".getBytes(StandardCharsets.UTF_8);
        }
        if ("/hateoas/api/v2/students".equals(path)) {
            return "{\"id\":\"1\",\"name\":\"n\",\"book\":\"b\",\"_links\":{\"all-students\":{\"href\":\"http://localhost/all\"}}}"
                    .getBytes(StandardCharsets.UTF_8);
        }
        if (path.startsWith("/h2/apis/v2/") || ("GET".equals(method) && "/h2/apis/v1/employees".equals(path))) {
            return "[{\"id\":\"1\",\"name\":\"n\",\"joiningDate\":\"24112026\"}]".getBytes(StandardCharsets.UTF_8);
        }
        if (path.startsWith("/h2/apis/")) {
            return "{\"id\":\"1\",\"name\":\"n\",\"joiningDate\":\"24112026\"}".getBytes(StandardCharsets.UTF_8);
        }
        if ("/get/api/v3/students".equals(path)
                || "/get/api/v7/students".equals(path)
                || ("GET".equals(method) && "/hateoas/api/v1/students".equals(path))) {
            return ("[" + STUDENT_JSON + "]").getBytes(StandardCharsets.UTF_8);
        }
        return STUDENT_JSON.getBytes(StandardCharsets.UTF_8);
    }

    private record Received(String method,
                            String path,
                            String query,
                            String accept,
                            String contentType,
                            String acceptLanguage,
                            String name,
                            String book,
                            String apiVersion,
                            String body) {
    }
}
