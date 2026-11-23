package com.java.rest.client.webclient;

import com.java.model.Student;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.reactive.function.client.WebClient;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

/**
 * Rest client with {@link org.springframework.web.reactive.function.client.WebClient}
 * to {@link com.java.controller.impl.DeleteControllerImpl}
 *
 * {@link WebClient#delete} cannot send a request body, so these calls use
 * {@link WebClient#method} with {@link HttpMethod#DELETE}.
 */
@Slf4j
public class DeleteControllerTest {

    @Test
    void studentV1() throws IOException {
        WebClient webClient = WebClient.create("http://localhost:9000");
        String studentJson = new ClassPathResource("student-v1.json").getContentAsString(StandardCharsets.UTF_8);

        Student student = webClient.method(HttpMethod.DELETE).uri("/delete/api/v1/students")
                .contentType(MediaType.TEXT_PLAIN)
                .bodyValue(studentJson)
                .retrieve().bodyToMono(Student.class).block();
        log.info("student={}", student);
    }

    @Test
    void studentV2() {
        WebClient webClient = WebClient.create("http://localhost:9000");
        Student request = Student.builder().name("a").book("b1").build();

        ResponseEntity<Student> response = webClient.method(HttpMethod.DELETE).uri("/delete/api/v2/students")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(request)
                .retrieve().toEntity(Student.class).block();
        Objects.requireNonNull(response);
        log.info("status={}, student={}", response.getStatusCode(), response.getBody());
    }

    @Test
    void studentV3() {
        WebClient webClient = WebClient.create("http://localhost:9000");
        Student request = Student.builder().name("request-name").book("request-book").build();

        Student student = webClient.method(HttpMethod.DELETE).uri("/delete/api/v3/students")
                .contentType(MediaType.APPLICATION_JSON)
                .header("name", "a")
                .header("book", "b1")
                .bodyValue(request)
                .retrieve().bodyToMono(Student.class).block();
        log.info("student={}", student);
    }

    @Test
    void studentV4() {
        WebClient webClient = WebClient.create("http://localhost:9000");
        Student request = Student.builder().name("request-name").book("request-book").build();

        Student student = webClient.method(HttpMethod.DELETE)
                .uri("/delete/api/v4/students?name={name}&book={book}", "a", "b1")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(request)
                .retrieve().bodyToMono(Student.class).block();
        log.info("student={}", student);
    }

    @Test
    void studentV5() {
        WebClient webClient = WebClient.create("http://localhost:9000");
        Student request = Student.builder().name("request-name").book("request-book").build();

        Student student = webClient.method(HttpMethod.DELETE)
                .uri("/delete/api/v5/students/{name}/{book}", "a", "b1")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(request)
                .retrieve().bodyToMono(Student.class).block();
        log.info("student={}", student);
    }

    @Test
    void studentV6() {
        WebClient webClient = WebClient.create("http://localhost:9000");
        Student request = Student.builder().name("request-name").book("request-book").build();

        Student student = webClient.method(HttpMethod.DELETE)
                .uri("/delete/api/v6/students/{id}?book={book}", "ABC123", "b1")
                .contentType(MediaType.APPLICATION_JSON)
                .header("name", "a")
                .bodyValue(request)
                .retrieve().bodyToMono(Student.class).block();
        log.info("student={}", student);
    }

}
