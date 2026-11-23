package com.java.rest.client.unirest;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import com.java.model.Student;
import com.fasterxml.jackson.databind.ObjectMapper;
import kong.unirest.HttpResponse;
import kong.unirest.Unirest;
import org.junit.jupiter.api.AfterAll;

/**
 * Rest client with {@link kong.unirest.Unirest}
 * to {@link com.java.controller.impl.VersioningControllerImpl}
 */
@Slf4j
public class VersioningControllerTest {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @AfterAll
    static void shutdown() {
        Unirest.shutDown();
    }

    @Test
    void urlVersioning1() throws Exception {
        HttpResponse<String> response = Unirest.get("http://localhost:9000/versioning/api/v1/students")
                .asString();
        Student student = objectMapper.readValue(response.getBody(), Student.class);
        log.info("student={}", student);
    }
    @Test
    void urlVersioning2() throws Exception {
        HttpResponse<String> response = Unirest.get("http://localhost:9000/versioning/api/v2/students")
                .asString();
        Student student = objectMapper.readValue(response.getBody(), Student.class);
        log.info("student={}", student);
    }
    @Test
    void requestParamVersioning1() throws Exception {
        HttpResponse<String> response = Unirest.get("http://localhost:9000/versioning/api/students?VERSION=1")
                .asString();
        Student student = objectMapper.readValue(response.getBody(), Student.class);
        log.info("student={}", student);
    }
    @Test
    void requestParamVersioning2() throws Exception {
        HttpResponse<String> response = Unirest.get("http://localhost:9000/versioning/api/students?VERSION=2")
                .asString();
        Student student = objectMapper.readValue(response.getBody(), Student.class);
        log.info("student={}", student);
    }
    @Test
    void headerVersioning1() throws Exception {
        HttpResponse<String> response = Unirest.get("http://localhost:9000/versioning/api/students")
                .header("X-API-VERSION", "1")
                .header("Accept", "application/json")
                .asString();
        Student student = objectMapper.readValue(response.getBody(), Student.class);
        log.info("student={}", student);
    }
    @Test
    void headerVersioning2() throws Exception {
        HttpResponse<String> response = Unirest.get("http://localhost:9000/versioning/api/students")
                .header("X-API-VERSION", "2")
                .header("Accept", "application/json")
                .asString();
        Student student = objectMapper.readValue(response.getBody(), Student.class);
        log.info("student={}", student);
    }
    @Test
    void mediaTypeVersioning1() throws Exception {
        HttpResponse<String> response = Unirest.get("http://localhost:9000/versioning/api/students")
                .header("Accept", "application/my-company-v1+json")
                .asString();
        Student student = objectMapper.readValue(response.getBody(), Student.class);
        log.info("student={}", student);
    }
    @Test
    void mediaTypeVersioning2() throws Exception {
        HttpResponse<String> response = Unirest.get("http://localhost:9000/versioning/api/students")
                .header("Accept", "application/my-company-v2+json")
                .asString();
        Student student = objectMapper.readValue(response.getBody(), Student.class);
        log.info("student={}", student);
    }

}
