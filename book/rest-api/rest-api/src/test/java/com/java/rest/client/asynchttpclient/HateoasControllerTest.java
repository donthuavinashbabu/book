package com.java.rest.client.asynchttpclient;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import com.java.model.Student;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;
import java.util.List;
import org.asynchttpclient.AsyncHttpClient;
import org.asynchttpclient.Dsl;
import org.asynchttpclient.Response;

/**
 * Rest client with {@link org.asynchttpclient.AsyncHttpClient}
 * to {@link com.java.controller.impl.HateoasControllerImpl}
 */
@Slf4j
public class HateoasControllerTest {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void findAllStudents() throws Exception {
        try (AsyncHttpClient client = Dsl.asyncHttpClient()) {
            Response response = client.prepareGet("http://localhost:9000/hateoas/api/v1/students")
                    .execute().toCompletableFuture().join();
            List<Student> students = objectMapper.readValue(response.getResponseBody(), new TypeReference<List<Student>>() {});
            log.info("students={}", students);
        }
    }
    @Test
    void findStudentById() throws Exception {
        try (AsyncHttpClient client = Dsl.asyncHttpClient()) {
            Response response = client.prepareGet("http://localhost:9000/hateoas/api/v1/students/1")
                    .execute().toCompletableFuture().join();
            Student student = objectMapper.readValue(response.getResponseBody(), Student.class);
            log.info("student={}", student);
        }
    }
    @Test
    void saveStudent() throws Exception {
        Student requestBody = Student.builder().name("a").book("b1").build();
        try (AsyncHttpClient client = Dsl.asyncHttpClient()) {
            Response response = client.preparePost("http://localhost:9000/hateoas/api/v1/students")
                    .setHeader("Content-Type", "application/json")
                    .setBody(objectMapper.writeValueAsString(requestBody))
                    .execute().toCompletableFuture().join();
            log.info("status={}, location={}", response.getStatusCode(), response.getHeader("Location"));
        }
    }
    @Test
    void saveStudents2() throws Exception {
        Student requestBody = Student.builder().name("a").book("b1").build();
        try (AsyncHttpClient client = Dsl.asyncHttpClient()) {
            Response response = client.preparePost("http://localhost:9000/hateoas/api/v2/students")
                    .setHeader("Content-Type", "application/json")
                    .setBody(objectMapper.writeValueAsString(requestBody))
                    .execute().toCompletableFuture().join();
            log.info("body={}", response.getResponseBody());
        }
    }

}
