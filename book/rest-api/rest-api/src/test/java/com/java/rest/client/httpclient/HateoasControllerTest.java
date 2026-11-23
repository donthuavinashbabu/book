package com.java.rest.client.httpclient;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import com.java.model.Student;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;
import java.util.List;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

/**
 * Rest client with {@link java.net.http.HttpClient}
 * to {@link com.java.controller.impl.HateoasControllerImpl}
 */
@Slf4j
public class HateoasControllerTest {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void findAllStudents() throws Exception {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:9000/hateoas/api/v1/students"))
                .GET()
                .build();
        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        List<Student> students = objectMapper.readValue(response.body(), new TypeReference<List<Student>>() {});
        log.info("students={}", students);
    }
    @Test
    void findStudentById() throws Exception {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:9000/hateoas/api/v1/students/1"))
                .GET()
                .build();
        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        Student student = objectMapper.readValue(response.body(), Student.class);
        log.info("student={}", student);
    }
    @Test
    void saveStudent() throws Exception {
        Student requestBody = Student.builder().name("a").book("b1").build();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:9000/hateoas/api/v1/students"))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(objectMapper.writeValueAsString(requestBody)))
                .build();
        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        log.info("status={}, location={}", response.statusCode(), response.headers().firstValue("location").orElse(null));
    }
    @Test
    void saveStudents2() throws Exception {
        Student requestBody = Student.builder().name("a").book("b1").build();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:9000/hateoas/api/v2/students"))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(objectMapper.writeValueAsString(requestBody)))
                .build();
        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        log.info("body={}", response.body());
    }

}
