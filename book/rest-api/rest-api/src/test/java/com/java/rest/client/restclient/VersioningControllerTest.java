package com.java.rest.client.restclient;

import com.java.model.Student;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestClient;

/**
 * Rest client with {@link org.springframework.web.client.RestClient}
 * to {@link com.java.controller.impl.VersioningControllerImpl}
 */
@Slf4j
public class VersioningControllerTest {

    @Test
    void urlVersioning1() {
        RestClient restClient = RestClient.create();
        Student student = restClient.get().uri("http://localhost:9000/versioning/api/v1/students")
                .retrieve().body(Student.class);
        log.info("student={}", student);
    }

    @Test
    void urlVersioning2() {
        RestClient restClient = RestClient.create();
        Student student = restClient.get().uri("http://localhost:9000/versioning/api/v2/students")
                .retrieve().body(Student.class);
        log.info("student={}", student);
    }

    @Test
    void requestParamVersioning1() {
        RestClient restClient = RestClient.create();
        Student student = restClient.get().uri("http://localhost:9000/versioning/api/students?VERSION=1")
                .retrieve().body(Student.class);
        log.info("student={}", student);
    }

    @Test
    void requestParamVersioning2() {
        RestClient restClient = RestClient.create();
        Student student = restClient.get().uri("http://localhost:9000/versioning/api/students?VERSION=2")
                .retrieve().body(Student.class);
        log.info("student={}", student);
    }

    @Test
    void headerVersioning1() {
        RestClient restClient = RestClient.create();
        Student student = restClient.get().uri("http://localhost:9000/versioning/api/students")
                .header("X-API-VERSION", "1")
                .accept(MediaType.APPLICATION_JSON)
                .retrieve().body(Student.class);
        log.info("student={}", student);
    }

    @Test
    void headerVersioning2() {
        RestClient restClient = RestClient.create();
        Student student = restClient.get().uri("http://localhost:9000/versioning/api/students")
                .header("X-API-VERSION", "2")
                .accept(MediaType.APPLICATION_JSON)
                .retrieve().body(Student.class);
        log.info("student={}", student);
    }

    @Test
    void mediaTypeVersioning1() {
        RestClient restClient = RestClient.create();
        Student student = restClient.get().uri("http://localhost:9000/versioning/api/students")
                .accept(MediaType.parseMediaType("application/my-company-v1+json"))
                .retrieve().body(Student.class);
        log.info("student={}", student);
    }

    @Test
    void mediaTypeVersioning2() {
        RestClient restClient = RestClient.create();
        Student student = restClient.get().uri("http://localhost:9000/versioning/api/students")
                .accept(MediaType.parseMediaType("application/my-company-v2+json"))
                .retrieve().body(Student.class);
        log.info("student={}", student);
    }

}
