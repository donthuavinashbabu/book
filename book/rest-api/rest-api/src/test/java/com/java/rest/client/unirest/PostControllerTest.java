package com.java.rest.client.unirest;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import com.java.model.Student;
import com.java.controller.PostController.Employee;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.io.ClassPathResource;
import java.nio.charset.StandardCharsets;
import kong.unirest.HttpResponse;
import kong.unirest.Unirest;
import org.junit.jupiter.api.AfterAll;

/**
 * Rest client with {@link kong.unirest.Unirest}
 * to {@link com.java.controller.impl.PostControllerImpl}
 */
@Slf4j
public class PostControllerTest {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @AfterAll
    static void shutdown() {
        Unirest.shutDown();
    }

    @Test
    void studentV1() throws Exception {
        String studentJson = new ClassPathResource("student-v1.json").getContentAsString(StandardCharsets.UTF_8);
        HttpResponse<String> response = Unirest.post("http://localhost:9000/post/api/v1/students")
                .header("Content-Type", "text/plain")
                .body(studentJson)
                .asString();
        Student student = objectMapper.readValue(response.getBody(), Student.class);
        log.info("student={}", student);
    }
    @Test
    void studentV2() throws Exception {
        Student requestBody = Student.builder().name("a").book("b1").build();
        HttpResponse<String> response = Unirest.post("http://localhost:9000/post/api/v2/students")
                .header("Content-Type", "application/json")
                .body(objectMapper.writeValueAsString(requestBody))
                .asString();
        Student student = objectMapper.readValue(response.getBody(), Student.class);
        log.info("status={}, student={}", response.getStatus(), student);
    }
    @Test
    void studentV3() throws Exception {
        Student requestBody = Student.builder().name("request-name").book("request-book").build();
        HttpResponse<String> response = Unirest.post("http://localhost:9000/post/api/v3/students")
                .header("name", "a")
                .header("book", "b1")
                .header("Content-Type", "application/json")
                .body(objectMapper.writeValueAsString(requestBody))
                .asString();
        Student student = objectMapper.readValue(response.getBody(), Student.class);
        log.info("student={}", student);
    }
    @Test
    void studentV4() throws Exception {
        Student requestBody = Student.builder().name("request-name").book("request-book").build();
        HttpResponse<String> response = Unirest.post("http://localhost:9000/post/api/v4/students?name=a&book=b1")
                .header("Content-Type", "application/json")
                .body(objectMapper.writeValueAsString(requestBody))
                .asString();
        Student student = objectMapper.readValue(response.getBody(), Student.class);
        log.info("student={}", student);
    }
    @Test
    void studentV5() throws Exception {
        Student requestBody = Student.builder().name("request-name").book("request-book").build();
        HttpResponse<String> response = Unirest.post("http://localhost:9000/post/api/v5/students/a/b1")
                .header("Content-Type", "application/json")
                .body(objectMapper.writeValueAsString(requestBody))
                .asString();
        Student student = objectMapper.readValue(response.getBody(), Student.class);
        log.info("student={}", student);
    }
    @Test
    void studentV6() throws Exception {
        Student requestBody = Student.builder().name("request-name").book("request-book").build();
        HttpResponse<String> response = Unirest.post("http://localhost:9000/post/api/v6/students/ABC123?book=b1")
                .header("name", "a")
                .header("Content-Type", "application/json")
                .body(objectMapper.writeValueAsString(requestBody))
                .asString();
        Student student = objectMapper.readValue(response.getBody(), Student.class);
        log.info("student={}", student);
    }
    @Test
    void employee() throws Exception {
        Employee requestBody = new Employee(1L, "a", "engineering");
        HttpResponse<String> response = Unirest.post("http://localhost:9000/post/api/v1/employee")
                .header("Content-Type", "application/json")
                .body(objectMapper.writeValueAsString(requestBody))
                .asString();
        Employee employee = objectMapper.readValue(response.getBody(), Employee.class);
        log.info("employee={}", employee);
    }

}
