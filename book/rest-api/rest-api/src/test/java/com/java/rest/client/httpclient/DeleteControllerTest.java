package com.java.rest.client.httpclient;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import com.java.model.Student;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.io.ClassPathResource;
import java.nio.charset.StandardCharsets;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

/**
 * Rest client with {@link java.net.http.HttpClient}
 * to {@link com.java.controller.impl.DeleteControllerImpl}
 */
@Slf4j
public class DeleteControllerTest {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void studentV1() throws Exception {
        String studentJson = new ClassPathResource("student-v1.json").getContentAsString(StandardCharsets.UTF_8);
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:9000/delete/api/v1/students"))
                .header("Content-Type", "text/plain")
                .method("DELETE", HttpRequest.BodyPublishers.ofString(studentJson))
                .build();
        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        Student student = objectMapper.readValue(response.body(), Student.class);
        log.info("student={}", student);
    }
    @Test
    void studentV2() throws Exception {
        Student requestBody = Student.builder().name("a").book("b1").build();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:9000/delete/api/v2/students"))
                .header("Content-Type", "application/json")
                .method("DELETE", HttpRequest.BodyPublishers.ofString(objectMapper.writeValueAsString(requestBody)))
                .build();
        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        Student student = objectMapper.readValue(response.body(), Student.class);
        log.info("status={}, student={}", response.statusCode(), student);
    }
    @Test
    void studentV3() throws Exception {
        Student requestBody = Student.builder().name("request-name").book("request-book").build();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:9000/delete/api/v3/students"))
                .header("name", "a")
                .header("book", "b1")
                .header("Content-Type", "application/json")
                .method("DELETE", HttpRequest.BodyPublishers.ofString(objectMapper.writeValueAsString(requestBody)))
                .build();
        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        Student student = objectMapper.readValue(response.body(), Student.class);
        log.info("student={}", student);
    }
    @Test
    void studentV4() throws Exception {
        Student requestBody = Student.builder().name("request-name").book("request-book").build();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:9000/delete/api/v4/students?name=a&book=b1"))
                .header("Content-Type", "application/json")
                .method("DELETE", HttpRequest.BodyPublishers.ofString(objectMapper.writeValueAsString(requestBody)))
                .build();
        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        Student student = objectMapper.readValue(response.body(), Student.class);
        log.info("student={}", student);
    }
    @Test
    void studentV5() throws Exception {
        Student requestBody = Student.builder().name("request-name").book("request-book").build();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:9000/delete/api/v5/students/a/b1"))
                .header("Content-Type", "application/json")
                .method("DELETE", HttpRequest.BodyPublishers.ofString(objectMapper.writeValueAsString(requestBody)))
                .build();
        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        Student student = objectMapper.readValue(response.body(), Student.class);
        log.info("student={}", student);
    }
    @Test
    void studentV6() throws Exception {
        Student requestBody = Student.builder().name("request-name").book("request-book").build();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:9000/delete/api/v6/students/ABC123?book=b1"))
                .header("name", "a")
                .header("Content-Type", "application/json")
                .method("DELETE", HttpRequest.BodyPublishers.ofString(objectMapper.writeValueAsString(requestBody)))
                .build();
        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        Student student = objectMapper.readValue(response.body(), Student.class);
        log.info("student={}", student);
    }

}
