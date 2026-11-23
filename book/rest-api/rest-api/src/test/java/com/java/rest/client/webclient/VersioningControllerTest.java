package com.java.rest.client.webclient;

import com.java.model.Student;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

/**
 * Rest client with {@link org.springframework.web.reactive.function.client.WebClient}
 * to {@link com.java.controller.impl.VersioningControllerImpl}
 */
@Slf4j
public class VersioningControllerTest {

    @Test
    void urlVersioning1() {
        WebClient webClient = WebClient.create("http://localhost:9000");
        Student student = webClient.get().uri("/versioning/api/v1/students")
                .retrieve().bodyToMono(Student.class).block();
        log.info("student={}", student);
    }

    @Test
    void urlVersioning2() {
        WebClient webClient = WebClient.create("http://localhost:9000");
        Student student = webClient.get().uri("/versioning/api/v2/students")
                .retrieve().bodyToMono(Student.class).block();
        log.info("student={}", student);
    }

    @Test
    void requestParamVersioning1() {
        WebClient webClient = WebClient.create("http://localhost:9000");
        Student student = webClient.get().uri("/versioning/api/students?VERSION=1")
                .retrieve().bodyToMono(Student.class).block();
        log.info("student={}", student);
    }

    @Test
    void requestParamVersioning2() {
        WebClient webClient = WebClient.create("http://localhost:9000");
        Student student = webClient.get().uri("/versioning/api/students?VERSION=2")
                .retrieve().bodyToMono(Student.class).block();
        log.info("student={}", student);
    }

    @Test
    void headerVersioning1() {
        WebClient webClient = WebClient.create("http://localhost:9000");
        Student student = webClient.get().uri("/versioning/api/students")
                .header("X-API-VERSION", "1")
                .accept(MediaType.APPLICATION_JSON)
                .retrieve().bodyToMono(Student.class).block();
        log.info("student={}", student);
    }

    @Test
    void headerVersioning2() {
        WebClient webClient = WebClient.create("http://localhost:9000");
        Student student = webClient.get().uri("/versioning/api/students")
                .header("X-API-VERSION", "2")
                .accept(MediaType.APPLICATION_JSON)
                .retrieve().bodyToMono(Student.class).block();
        log.info("student={}", student);
    }

    @Test
    void mediaTypeVersioning1() {
        WebClient webClient = WebClient.create("http://localhost:9000");
        Student student = webClient.get().uri("/versioning/api/students")
                .accept(MediaType.parseMediaType("application/my-company-v1+json"))
                .retrieve().bodyToMono(Student.class).block();
        log.info("student={}", student);
    }

    @Test
    void mediaTypeVersioning2() {
        WebClient webClient = WebClient.create("http://localhost:9000");
        Student student = webClient.get().uri("/versioning/api/students")
                .accept(MediaType.parseMediaType("application/my-company-v2+json"))
                .retrieve().bodyToMono(Student.class).block();
        log.info("student={}", student);
    }

}
