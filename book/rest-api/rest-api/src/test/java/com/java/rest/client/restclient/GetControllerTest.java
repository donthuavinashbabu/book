package com.java.rest.client.restclient;

import com.java.model.Student;
import com.java.model.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClient;

import java.util.List;

/**
 * Rest client with {@link org.springframework.web.client.RestClient}
 * to {@link com.java.controller.impl.GetControllerImpl}
 */
@Slf4j
public class GetControllerTest {

    @Test
    void helloWorld() {
        RestClient restClient = RestClient.create();
        String response = restClient.get().uri("http://localhost:9000/get/api/v1/hello-world")
                .retrieve().body(String.class);
        log.info("response={}", response);
    }

    @Test
    void studentsV1() {
        RestClient restClient = RestClient.create();
        Student student = restClient.get().uri("http://localhost:9000/get/api/v1/students")
                .retrieve().body(Student.class);
        log.info("student={}", student);
    }

    @Test
    void studentsV2() {
        RestClient restClient = RestClient.create();
        ResponseEntity<Student> response = restClient.get().uri("http://localhost:9000/get/api/v2/students")
                .retrieve().toEntity(Student.class);
        log.info("status={}, student={}", response.getStatusCode(), response.getBody());
    }

    @Test
    void studentsV3() {
        RestClient restClient = RestClient.create();
        List<Student> students = restClient.get().uri("http://localhost:9000/get/api/v3/students")
                .retrieve().body(new ParameterizedTypeReference<>() {});
        log.info("students={}", students);
    }

    @Test
    void studentsV4() {
        RestClient restClient = RestClient.create();
        Student student = restClient.get().uri("http://localhost:9000/get/api/v4/students")
                .header("name", "a")
                .header("book", "b1")
                .retrieve().body(Student.class);
        log.info("student={}", student);
    }

    @Test
    void studentsV5() {
        RestClient restClient = RestClient.create();
        Student student = restClient.get().uri("http://localhost:9000/get/api/v5/students?name=a&book=b1")
                .retrieve().body(Student.class);
        log.info("student={}", student);
    }

    @Test
    void studentsV6() {
        RestClient restClient = RestClient.create();
        Student student = restClient.get().uri("http://localhost:9000/get/api/v6/students/{name}/{book}", "a", "b1")
                .retrieve().body(Student.class);
        log.info("student={}", student);
    }

    @Test
    void studentsV7() {
        RestClient restClient = RestClient.create();
        List<Student> students = restClient.get().uri("http://localhost:9000/get/api/v7/students?page=0&size=2&sort=asc")
                .retrieve().body(new ParameterizedTypeReference<>() {});
        log.info("students={}", students);
    }

    @Test
    void users1() {
        RestClient restClient = RestClient.create();
        List<User> users = restClient.get().uri("http://localhost:9000/get/api/v1/users")
                .retrieve().body(new ParameterizedTypeReference<>() {});
        log.info("users={}", users);
    }

}
