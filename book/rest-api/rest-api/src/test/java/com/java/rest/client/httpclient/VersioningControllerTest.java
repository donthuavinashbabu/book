package com.java.rest.client.httpclient;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import com.java.model.Student;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

/**
 * Rest client with {@link java.net.http.HttpClient}
 * to {@link com.java.controller.impl.VersioningControllerImpl}
 */
@Slf4j
public class VersioningControllerTest {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void urlVersioning1() throws Exception {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:9000/versioning/api/v1/students"))
                .GET()
                .build();
        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        Student student = objectMapper.readValue(response.body(), Student.class);
        log.info("student={}", student);
    }
    @Test
    void urlVersioning2() throws Exception {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:9000/versioning/api/v2/students"))
                .GET()
                .build();
        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        Student student = objectMapper.readValue(response.body(), Student.class);
        log.info("student={}", student);
    }
    @Test
    void requestParamVersioning1() throws Exception {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:9000/versioning/api/students?VERSION=1"))
                .GET()
                .build();
        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        Student student = objectMapper.readValue(response.body(), Student.class);
        log.info("student={}", student);
    }
    @Test
    void requestParamVersioning2() throws Exception {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:9000/versioning/api/students?VERSION=2"))
                .GET()
                .build();
        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        Student student = objectMapper.readValue(response.body(), Student.class);
        log.info("student={}", student);
    }
    @Test
    void headerVersioning1() throws Exception {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:9000/versioning/api/students"))
                .header("X-API-VERSION", "1")
                .header("Accept", "application/json")
                .GET()
                .build();
        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        Student student = objectMapper.readValue(response.body(), Student.class);
        log.info("student={}", student);
    }
    @Test
    void headerVersioning2() throws Exception {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:9000/versioning/api/students"))
                .header("X-API-VERSION", "2")
                .header("Accept", "application/json")
                .GET()
                .build();
        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        Student student = objectMapper.readValue(response.body(), Student.class);
        log.info("student={}", student);
    }
    @Test
    void mediaTypeVersioning1() throws Exception {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:9000/versioning/api/students"))
                .header("Accept", "application/my-company-v1+json")
                .GET()
                .build();
        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        Student student = objectMapper.readValue(response.body(), Student.class);
        log.info("student={}", student);
    }
    @Test
    void mediaTypeVersioning2() throws Exception {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:9000/versioning/api/students"))
                .header("Accept", "application/my-company-v2+json")
                .GET()
                .build();
        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        Student student = objectMapper.readValue(response.body(), Student.class);
        log.info("student={}", student);
    }

}
