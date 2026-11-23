package com.java.rest.client.asynchttpclient;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import com.java.model.Student;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.asynchttpclient.AsyncHttpClient;
import org.asynchttpclient.Dsl;
import org.asynchttpclient.Response;

/**
 * Rest client with {@link org.asynchttpclient.AsyncHttpClient}
 * to {@link com.java.controller.impl.VersioningControllerImpl}
 */
@Slf4j
public class VersioningControllerTest {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void urlVersioning1() throws Exception {
        try (AsyncHttpClient client = Dsl.asyncHttpClient()) {
            Response response = client.prepareGet("http://localhost:9000/versioning/api/v1/students")
                    .execute().toCompletableFuture().join();
            Student student = objectMapper.readValue(response.getResponseBody(), Student.class);
            log.info("student={}", student);
        }
    }
    @Test
    void urlVersioning2() throws Exception {
        try (AsyncHttpClient client = Dsl.asyncHttpClient()) {
            Response response = client.prepareGet("http://localhost:9000/versioning/api/v2/students")
                    .execute().toCompletableFuture().join();
            Student student = objectMapper.readValue(response.getResponseBody(), Student.class);
            log.info("student={}", student);
        }
    }
    @Test
    void requestParamVersioning1() throws Exception {
        try (AsyncHttpClient client = Dsl.asyncHttpClient()) {
            Response response = client.prepareGet("http://localhost:9000/versioning/api/students?VERSION=1")
                    .execute().toCompletableFuture().join();
            Student student = objectMapper.readValue(response.getResponseBody(), Student.class);
            log.info("student={}", student);
        }
    }
    @Test
    void requestParamVersioning2() throws Exception {
        try (AsyncHttpClient client = Dsl.asyncHttpClient()) {
            Response response = client.prepareGet("http://localhost:9000/versioning/api/students?VERSION=2")
                    .execute().toCompletableFuture().join();
            Student student = objectMapper.readValue(response.getResponseBody(), Student.class);
            log.info("student={}", student);
        }
    }
    @Test
    void headerVersioning1() throws Exception {
        try (AsyncHttpClient client = Dsl.asyncHttpClient()) {
            Response response = client.prepareGet("http://localhost:9000/versioning/api/students")
                    .setHeader("X-API-VERSION", "1")
                    .setHeader("Accept", "application/json")
                    .execute().toCompletableFuture().join();
            Student student = objectMapper.readValue(response.getResponseBody(), Student.class);
            log.info("student={}", student);
        }
    }
    @Test
    void headerVersioning2() throws Exception {
        try (AsyncHttpClient client = Dsl.asyncHttpClient()) {
            Response response = client.prepareGet("http://localhost:9000/versioning/api/students")
                    .setHeader("X-API-VERSION", "2")
                    .setHeader("Accept", "application/json")
                    .execute().toCompletableFuture().join();
            Student student = objectMapper.readValue(response.getResponseBody(), Student.class);
            log.info("student={}", student);
        }
    }
    @Test
    void mediaTypeVersioning1() throws Exception {
        try (AsyncHttpClient client = Dsl.asyncHttpClient()) {
            Response response = client.prepareGet("http://localhost:9000/versioning/api/students")
                    .setHeader("Accept", "application/my-company-v1+json")
                    .execute().toCompletableFuture().join();
            Student student = objectMapper.readValue(response.getResponseBody(), Student.class);
            log.info("student={}", student);
        }
    }
    @Test
    void mediaTypeVersioning2() throws Exception {
        try (AsyncHttpClient client = Dsl.asyncHttpClient()) {
            Response response = client.prepareGet("http://localhost:9000/versioning/api/students")
                    .setHeader("Accept", "application/my-company-v2+json")
                    .execute().toCompletableFuture().join();
            Student student = objectMapper.readValue(response.getResponseBody(), Student.class);
            log.info("student={}", student);
        }
    }

}
