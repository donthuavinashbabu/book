package com.java.rest.client.restclient;

import com.java.controller.PostController.Employee;
import com.java.model.Student;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClient;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * Rest client with {@link org.springframework.web.client.RestClient}
 * to {@link com.java.controller.impl.PostControllerImpl}
 */
@Slf4j
public class PostControllerTest {

    @Test
    void studentV1() throws IOException {
        RestClient restClient = RestClient.create();
        String studentJson = new ClassPathResource("student-v1.json").getContentAsString(StandardCharsets.UTF_8);

        Student student = restClient.post().uri("http://localhost:9000/post/api/v1/students")
                .contentType(MediaType.TEXT_PLAIN)
                .body(studentJson)
                .retrieve().body(Student.class);
        log.info("student={}", student);
    }

    @Test
    void studentV2() {
        RestClient restClient = RestClient.create();
        Student request = Student.builder().name("a").book("b1").build();

        ResponseEntity<Student> response = restClient.post().uri("http://localhost:9000/post/api/v2/students")
                .contentType(MediaType.APPLICATION_JSON)
                .body(request)
                .retrieve().toEntity(Student.class);
        log.info("status={}, student={}", response.getStatusCode(), response.getBody());
    }

    @Test
    void studentV3() {
        RestClient restClient = RestClient.create();
        Student request = Student.builder().name("request-name").book("request-book").build();

        Student student = restClient.post().uri("http://localhost:9000/post/api/v3/students")
                .contentType(MediaType.APPLICATION_JSON)
                .header("name", "a")
                .header("book", "b1")
                .body(request)
                .retrieve().body(Student.class);
        log.info("student={}", student);
    }

    @Test
    void studentV4() {
        RestClient restClient = RestClient.create();
        Student request = Student.builder().name("request-name").book("request-book").build();

        Student student = restClient.post().uri("http://localhost:9000/post/api/v4/students?name={name}&book={book}", "a", "b1")
                .contentType(MediaType.APPLICATION_JSON)
                .body(request)
                .retrieve().body(Student.class);
        log.info("student={}", student);
    }

    @Test
    void studentV5() {
        RestClient restClient = RestClient.create();
        Student request = Student.builder().name("request-name").book("request-book").build();

        Student student = restClient.post().uri("http://localhost:9000/post/api/v5/students/{name}/{book}", "a", "b1")
                .contentType(MediaType.APPLICATION_JSON)
                .body(request)
                .retrieve().body(Student.class);
        log.info("student={}", student);
    }

    @Test
    void studentV6() {
        RestClient restClient = RestClient.create();
        Student request = Student.builder().name("request-name").book("request-book").build();

        Student student = restClient.post().uri("http://localhost:9000/post/api/v6/students/{id}?book={book}", "ABC123", "b1")
                .contentType(MediaType.APPLICATION_JSON)
                .header("name", "a")
                .body(request)
                .retrieve().body(Student.class);
        log.info("student={}", student);
    }

    @Test
    void employee() {
        RestClient restClient = RestClient.create();
        Employee request = new Employee(1L, "a", "engineering");

        Employee employee = restClient.post().uri("http://localhost:9000/post/api/v1/employee")
                .contentType(MediaType.APPLICATION_JSON)
                .body(request)
                .retrieve().body(Employee.class);
        log.info("employee={}", employee);
    }

}
