package com.java.rest.client.httpclient;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import com.java.model.Student;
import com.java.model.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;
import java.util.List;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

/**
 * Rest client with {@link java.net.http.HttpClient}
 * to {@link com.java.controller.impl.GetControllerImpl}
 */
@Slf4j
public class GetControllerTest {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void helloWorld() throws Exception {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:9000/get/api/v1/hello-world"))
                .GET()
                .build();
        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        log.info("response={}", response.body());
    }
    @Test
    void studentsV1() throws Exception {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:9000/get/api/v1/students"))
                .GET()
                .build();
        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        Student student = objectMapper.readValue(response.body(), Student.class);
        log.info("student={}", student);
    }
    @Test
    void studentsV2() throws Exception {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:9000/get/api/v2/students"))
                .GET()
                .build();
        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        Student student = objectMapper.readValue(response.body(), Student.class);
        log.info("status={}, student={}", response.statusCode(), student);
    }
    @Test
    void studentsV3() throws Exception {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:9000/get/api/v3/students"))
                .GET()
                .build();
        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        List<Student> students = objectMapper.readValue(response.body(), new TypeReference<List<Student>>() {});
        log.info("students={}", students);
    }
    @Test
    void studentsV4() throws Exception {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:9000/get/api/v4/students"))
                .header("name", "a")
                .header("book", "b1")
                .GET()
                .build();
        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        Student student = objectMapper.readValue(response.body(), Student.class);
        log.info("student={}", student);
    }
    @Test
    void studentsV5() throws Exception {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:9000/get/api/v5/students?name=a&book=b1"))
                .GET()
                .build();
        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        Student student = objectMapper.readValue(response.body(), Student.class);
        log.info("student={}", student);
    }
    @Test
    void studentsV6() throws Exception {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:9000/get/api/v6/students/a/b1"))
                .GET()
                .build();
        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        Student student = objectMapper.readValue(response.body(), Student.class);
        log.info("student={}", student);
    }
    @Test
    void studentsV7() throws Exception {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:9000/get/api/v7/students?page=0&size=2&sort=asc"))
                .GET()
                .build();
        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        List<Student> students = objectMapper.readValue(response.body(), new TypeReference<List<Student>>() {});
        log.info("students={}", students);
    }
    @Test
    void users1() throws Exception {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:9000/get/api/v1/users"))
                .GET()
                .build();
        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        List<User> users = objectMapper.readValue(response.body(), new TypeReference<List<User>>() {});
        log.info("users={}", users);
    }

}
