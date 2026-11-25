package com.java.restapi;

import com.java.restapi.model.CreatedResponse;
import com.java.restapi.model.Employee;
import com.java.restapi.model.EmployeeEntity;
import com.java.restapi.model.Student;
import com.java.restapi.model.StudentResource;
import com.java.restapi.model.User;
import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpServer;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedDeque;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(properties = {
        "server.port=0",
        "app.route1=false",
        "app.route2=false",
        "app.route3=false",
        "app.route4=false",
        "app.rest.route=false",
        "app.kafka.route=false",
        "app.kafka.route.list=false"
})
class RestApiClientTest {

    private static final String STUDENT_JSON = "{\"id\":\"1\",\"name\":\"n\",\"book\":\"b\"}";
    private static final String STUDENT_LIST_JSON = "[" + STUDENT_JSON + "]";
    private static final ConcurrentLinkedDeque<Received> REQUESTS = new ConcurrentLinkedDeque<>();

    private static HttpServer server;

    @Autowired
    private GetControllerClient getClient;
    @Autowired
    private PostControllerClient postClient;
    @Autowired
    private PutControllerClient putClient;
    @Autowired
    private DeleteControllerClient deleteClient;
    @Autowired
    private HateoasControllerClient hateoasClient;
    @Autowired
    private I18NControllerClient i18nClient;
    @Autowired
    private VersioningControllerClient versioningClient;
    @Autowired
    private H2ControllerClient h2Client;

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
                        header(requestHeaders, "Accept"),
                        header(requestHeaders, "Content-Type"),
                        header(requestHeaders, "Accept-Language"),
                        header(requestHeaders, "name"),
                        header(requestHeaders, "book"),
                        header(requestHeaders, "X-API-VERSION"),
                        body));

                byte[] response = responseFor(method, path);
                Headers responseHeaders = exchange.getResponseHeaders();
                if ("POST".equals(method) && "/hateoas/api/v1/students".equals(path)) {
                    responseHeaders.add("Location", "http://localhost/hateoas/api/v1/students/1");
                    exchange.sendResponseHeaders(201, -1);
                    exchange.close();
                    return;
                }
                responseHeaders.add("Content-Type", path.startsWith("/i18n/") || "/get/api/v1/hello-world".equals(path)
                        ? "text/plain" : "application/json");
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

    @BeforeEach
    void clearRequests() {
        REQUESTS.clear();
    }

    @Test
    void getController() {
        assertThat(getClient.helloWorld()).isEqualTo("hello");
        assertCall("GET", "/get/api/v1/hello-world", null, "text/plain", null, null);

        assertThat(getClient.studentsV1()).isEqualTo(sampleStudent());
        assertCall("GET", "/get/api/v1/students", null, "application/json", null, null);

        assertThat(getClient.studentsV2().getId()).isEqualTo("1");
        assertCall("GET", "/get/api/v2/students", null, "application/json", null, null);

        assertThat(getClient.studentsV3()).containsExactly(sampleStudent());
        assertCall("GET", "/get/api/v3/students", null, "application/json", null, null);

        assertThat(getClient.studentsV4("Ann", "Java")).isEqualTo(sampleStudent());
        Received headers = next();
        assertThat(headers.method()).isEqualTo("GET");
        assertThat(headers.path()).isEqualTo("/get/api/v4/students");
        assertThat(headers.name()).isEqualTo("Ann");
        assertThat(headers.book()).isEqualTo("Java");

        assertThat(getClient.studentsV5("Ann", "Java Book")).isEqualTo(sampleStudent());
        Received query = next();
        assertThat(query.path()).isEqualTo("/get/api/v5/students");
        assertThat(query.query()).contains("name=Ann").contains("book=Java%20Book");

        assertThat(getClient.studentsV6("Ann Lee", "Java")).isEqualTo(sampleStudent());
        assertThat(next().path()).isEqualTo("/get/api/v6/students/Ann%20Lee/Java");

        assertThat(getClient.studentsV7(0, 2, "name,asc")).containsExactly(sampleStudent());
        assertThat(next().query()).contains("page=0").contains("size=2").contains("sort=name,asc");

        List<User> users = getClient.users1();
        assertThat(users).containsExactly(User.builder().username("u").firstName("f").lastName("l").build());
        assertThat(next().path()).isEqualTo("/get/api/v1/users");
    }

    @Test
    void postController() {
        Student request = Student.builder().name("a").book("b1").build();

        assertThat(postClient.studentV1("{\"name\":\"a\"}").getName()).isEqualTo("n");
        Received text = next();
        assertThat(text.method()).isEqualTo("POST");
        assertThat(text.path()).isEqualTo("/post/api/v1/students");
        assertThat(text.contentType()).startsWith("text/plain");
        assertThat(text.body()).isEqualTo("{\"name\":\"a\"}");

        assertThat(postClient.studentV2(request)).isEqualTo(sampleStudent());
        Received json = next();
        assertThat(json.path()).isEqualTo("/post/api/v2/students");
        assertThat(json.contentType()).startsWith("application/json");
        assertThat(json.body()).contains("\"name\":\"a\"").contains("\"book\":\"b1\"");

        postClient.studentV3(request, "Ann", "Java");
        Received headerCall = next();
        assertThat(headerCall.path()).isEqualTo("/post/api/v3/students");
        assertThat(headerCall.name()).isEqualTo("Ann");
        assertThat(headerCall.book()).isEqualTo("Java");

        postClient.studentV4(request, "Ann", "Java");
        assertThat(next().query()).contains("name=Ann").contains("book=Java");

        postClient.studentV5(request, "Ann", "Java");
        assertThat(next().path()).isEqualTo("/post/api/v5/students/Ann/Java");

        postClient.studentV6(request, "10", "Ann", "Java");
        Received mixed = next();
        assertThat(mixed.path()).isEqualTo("/post/api/v6/students/10");
        assertThat(mixed.name()).isEqualTo("Ann");
        assertThat(mixed.query()).contains("book=Java");

        Employee employee = postClient.employee(new Employee(7L, "Ann", "dev"));
        assertThat(employee).isEqualTo(new Employee(1L, "n", "d"));
        assertThat(next().path()).isEqualTo("/post/api/v1/employee");
    }

    @Test
    void putController() {
        Student request = Student.builder().name("a").book("b1").build();

        putClient.studentV1("{\"name\":\"a\"}");
        Received text = next();
        assertThat(text.method()).isEqualTo("PUT");
        assertThat(text.path()).isEqualTo("/put/api/v1/students");
        assertThat(text.contentType()).startsWith("text/plain");

        putClient.studentV2(request);
        assertThat(next().path()).isEqualTo("/put/api/v2/students");

        putClient.studentV3(request, "Ann", "Java");
        assertThat(next().name()).isEqualTo("Ann");

        putClient.studentV4(request, "Ann", "Java");
        assertThat(next().query()).contains("book=Java");

        putClient.studentV5(request, "Ann", "Java");
        assertThat(next().path()).isEqualTo("/put/api/v5/students/Ann/Java");

        putClient.studentV6(request, "10", "Ann", "Java");
        Received mixed = next();
        assertThat(mixed.path()).isEqualTo("/put/api/v6/students/10");
        assertThat(mixed.query()).contains("book=Java");
    }

    @Test
    void deleteController() {
        Student request = Student.builder().name("a").book("b1").build();

        deleteClient.studentV1("{\"name\":\"a\"}");
        Received text = next();
        assertThat(text.method()).isEqualTo("DELETE");
        assertThat(text.path()).isEqualTo("/delete/api/v1/students");
        assertThat(text.contentType()).startsWith("text/plain");
        assertThat(text.body()).isEqualTo("{\"name\":\"a\"}");

        deleteClient.studentV2(request);
        Received json = next();
        assertThat(json.path()).isEqualTo("/delete/api/v2/students");
        assertThat(json.body()).contains("\"name\":\"a\"");

        deleteClient.studentV3(request, "Ann", "Java");
        assertThat(next().book()).isEqualTo("Java");

        deleteClient.studentV4(request, "Ann", "Java");
        assertThat(next().query()).contains("name=Ann");

        deleteClient.studentV5(request, "Ann", "Java");
        assertThat(next().path()).isEqualTo("/delete/api/v5/students/Ann/Java");

        deleteClient.studentV6(request, "10", "Ann", "Java");
        Received mixed = next();
        assertThat(mixed.path()).isEqualTo("/delete/api/v6/students/10");
        assertThat(mixed.name()).isEqualTo("Ann");
    }

    @Test
    void hateoasController() {
        assertThat(hateoasClient.findAllStudents()).containsExactly(sampleStudent());
        assertThat(next().path()).isEqualTo("/hateoas/api/v1/students");

        assertThat(hateoasClient.findStudentById("10").getId()).isEqualTo("1");
        assertThat(next().path()).isEqualTo("/hateoas/api/v1/students/10");

        CreatedResponse created = hateoasClient.saveStudent(Student.builder().name("a").book("b1").build());
        assertThat(created.statusCode()).isEqualTo(201);
        assertThat(created.location()).isEqualTo("http://localhost/hateoas/api/v1/students/1");
        Received save = next();
        assertThat(save.method()).isEqualTo("POST");
        assertThat(save.path()).isEqualTo("/hateoas/api/v1/students");

        StudentResource resource = hateoasClient.saveStudents2(Student.builder().name("a").book("b1").build());
        assertThat(resource.getLinks().get("all-students").getHref()).isEqualTo("http://localhost/all");
        assertThat(next().path()).isEqualTo("/hateoas/api/v2/students");
    }

    @Test
    void i18nController() {
        assertThat(i18nClient.helloWorld()).isEqualTo("hello");
        Received english = next();
        assertThat(english.path()).isEqualTo("/i18n/api/v1/hello-world");
        assertThat(english.acceptLanguage()).isNull();

        assertThat(i18nClient.helloWorld("fr")).isEqualTo("hello");
        assertThat(next().acceptLanguage()).isEqualTo("fr");

        assertThat(i18nClient.helloWorldWithName("Ann")).isEqualTo("hello");
        assertThat(next().path()).isEqualTo("/i18n/api/v1/hello-world/Ann");

        i18nClient.helloWorldWithName("Ann", "fr");
        Received french = next();
        assertThat(french.path()).isEqualTo("/i18n/api/v1/hello-world/Ann");
        assertThat(french.acceptLanguage()).isEqualTo("fr");
    }

    @Test
    void versioningController() {
        versioningClient.urlVersioning1();
        assertThat(next().path()).isEqualTo("/versioning/api/v1/students");

        versioningClient.urlVersioning2();
        assertThat(next().path()).isEqualTo("/versioning/api/v2/students");

        versioningClient.requestParamVersioning1();
        assertThat(next().query()).contains("VERSION=1");

        versioningClient.requestParamVersioning2();
        assertThat(next().query()).contains("VERSION=2");

        versioningClient.headerVersioning1();
        assertThat(next().apiVersion()).isEqualTo("1");

        versioningClient.headerVersioning2();
        assertThat(next().apiVersion()).isEqualTo("2");

        versioningClient.mediaTypeVersioning1();
        assertThat(next().accept()).isEqualTo("application/my-company-v1+json");

        versioningClient.mediaTypeVersioning2();
        assertThat(next().accept()).isEqualTo("application/my-company-v2+json");
    }

    @Test
    void h2Controller() {
        EmployeeEntity employee = EmployeeEntity.builder().name("Ann").joiningDate("24112026").build();

        assertThat(h2Client.saveEmployee(employee).getJoiningDate()).isEqualTo("24112026");
        Received save = next();
        assertThat(save.method()).isEqualTo("POST");
        assertThat(save.path()).isEqualTo("/h2/apis/v1/employees");
        assertThat(save.body()).contains("\"joiningDate\":\"24112026\"");

        assertThat(h2Client.saveEmployeeEntityList(List.of(employee))).hasSize(1);
        assertThat(next().path()).isEqualTo("/h2/apis/v2/employees");

        assertThat(h2Client.findAllEmployeeEntities()).extracting(EmployeeEntity::getName).containsExactly("n");
        Received find = next();
        assertThat(find.method()).isEqualTo("GET");
        assertThat(find.path()).isEqualTo("/h2/apis/v1/employees");
    }

    private static Student sampleStudent() {
        return Student.builder().id("1").name("n").book("b").build();
    }

    private static void assertCall(String method, String path, String queryPart, String accept, String name, String book) {
        Received received = next();
        assertThat(received.method()).isEqualTo(method);
        assertThat(received.path()).isEqualTo(path);
        if (queryPart != null) {
            assertThat(received.query()).contains(queryPart);
        }
        if (accept != null) {
            assertThat(received.accept()).startsWith(accept);
        }
        if (name != null) {
            assertThat(received.name()).isEqualTo(name);
        }
        if (book != null) {
            assertThat(received.book()).isEqualTo(book);
        }
    }

    private static Received next() {
        Received received = REQUESTS.poll();
        assertThat(received).as("expected an HTTP call").isNotNull();
        return received;
    }

    private static String header(Headers headers, String name) {
        return headers.getFirst(name);
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
        if ("/get/api/v3/students".equals(path) || "/get/api/v7/students".equals(path) || ("GET".equals(method) && "/hateoas/api/v1/students".equals(path))) {
            return STUDENT_LIST_JSON.getBytes(StandardCharsets.UTF_8);
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
