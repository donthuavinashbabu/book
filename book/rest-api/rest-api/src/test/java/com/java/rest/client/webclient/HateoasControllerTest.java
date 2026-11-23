package com.java.rest.client.webclient;

import com.java.model.Student;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.Objects;

/**
 * Rest client with {@link org.springframework.web.reactive.function.client.WebClient}
 * to {@link com.java.controller.impl.HateoasControllerImpl}
 */
@Slf4j
public class HateoasControllerTest {

    @Test
    void findAllStudents() {
        WebClient webClient = WebClient.create("http://localhost:9000");
        List<Student> students = webClient.get().uri("/hateoas/api/v1/students")
                .retrieve().bodyToFlux(Student.class).collectList().block();
        log.info("students={}", students);
    }

    @Test
    void findStudentById() {
        WebClient webClient = WebClient.create("http://localhost:9000");
        Student student = webClient.get().uri("/hateoas/api/v1/students/{id}", "1")
                .retrieve().bodyToMono(Student.class).block();
        log.info("student={}", student);
    }

    @Test
    void saveStudent() {
        WebClient webClient = WebClient.create("http://localhost:9000");
        Student request = Student.builder().name("a").book("b1").build();

        ResponseEntity<Student> response = webClient.post().uri("/hateoas/api/v1/students")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(request)
                .retrieve().toEntity(Student.class).block();
        Objects.requireNonNull(response);
        log.info("status={}, location={}, body={}", response.getStatusCode(), response.getHeaders().getLocation(), response.getBody());
    }

    @Test
    void saveStudents2() {
        WebClient webClient = WebClient.create("http://localhost:9000");
        Student request = Student.builder().name("a").book("b1").build();

        // Read as text so the HATEOAS _links are visible without a HATEOAS Jackson module
        String body = webClient.post().uri("/hateoas/api/v2/students")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(request)
                .retrieve().bodyToMono(String.class).block();
        log.info("body={}", body);
    }

}
