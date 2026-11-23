package com.java.rest.client.restclient;

import com.java.model.Student;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClient;

import java.util.List;

/**
 * Rest client with {@link org.springframework.web.client.RestClient}
 * to {@link com.java.controller.impl.HateoasControllerImpl}
 */
@Slf4j
public class HateoasControllerTest {

    @Test
    void findAllStudents() {
        RestClient restClient = RestClient.create();
        List<Student> students = restClient.get().uri("http://localhost:9000/hateoas/api/v1/students")
                .retrieve().body(new ParameterizedTypeReference<>() {});
        log.info("students={}", students);
    }

    @Test
    void findStudentById() {
        RestClient restClient = RestClient.create();
        Student student = restClient.get().uri("http://localhost:9000/hateoas/api/v1/students/{id}", "1")
                .retrieve().body(Student.class);
        log.info("student={}", student);
    }

    @Test
    void saveStudent() {
        RestClient restClient = RestClient.create();
        Student request = Student.builder().name("a").book("b1").build();

        ResponseEntity<Void> response = restClient.post().uri("http://localhost:9000/hateoas/api/v1/students")
                .contentType(MediaType.APPLICATION_JSON)
                .body(request)
                .retrieve().toBodilessEntity();
        log.info("status={}, location={}", response.getStatusCode(), response.getHeaders().getLocation());
    }

    @Test
    void saveStudents2() {
        RestClient restClient = RestClient.create();
        Student request = Student.builder().name("a").book("b1").build();

        String body = restClient.post().uri("http://localhost:9000/hateoas/api/v2/students")
                .contentType(MediaType.APPLICATION_JSON)
                .body(request)
                .retrieve().body(String.class);
        log.info("body={}", body);
    }

}
