package com.java.rest.client.asynchttpclient;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import com.java.model.Student;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.io.ClassPathResource;
import java.nio.charset.StandardCharsets;
import org.asynchttpclient.AsyncHttpClient;
import org.asynchttpclient.Dsl;
import org.asynchttpclient.Response;

/**
 * Rest client with {@link org.asynchttpclient.AsyncHttpClient}
 * to {@link com.java.controller.impl.DeleteControllerImpl}
 */
@Slf4j
public class DeleteControllerTest {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void studentV1() throws Exception {
        String studentJson = new ClassPathResource("student-v1.json").getContentAsString(StandardCharsets.UTF_8);
        try (AsyncHttpClient client = Dsl.asyncHttpClient()) {
            Response response = client.prepareDelete("http://localhost:9000/delete/api/v1/students")
                    .setHeader("Content-Type", "text/plain")
                    .setBody(studentJson)
                    .execute().toCompletableFuture().join();
            Student student = objectMapper.readValue(response.getResponseBody(), Student.class);
            log.info("student={}", student);
        }
    }
    @Test
    void studentV2() throws Exception {
        Student requestBody = Student.builder().name("a").book("b1").build();
        try (AsyncHttpClient client = Dsl.asyncHttpClient()) {
            Response response = client.prepareDelete("http://localhost:9000/delete/api/v2/students")
                    .setHeader("Content-Type", "application/json")
                    .setBody(objectMapper.writeValueAsString(requestBody))
                    .execute().toCompletableFuture().join();
            Student student = objectMapper.readValue(response.getResponseBody(), Student.class);
            log.info("status={}, student={}", response.getStatusCode(), student);
        }
    }
    @Test
    void studentV3() throws Exception {
        Student requestBody = Student.builder().name("request-name").book("request-book").build();
        try (AsyncHttpClient client = Dsl.asyncHttpClient()) {
            Response response = client.prepareDelete("http://localhost:9000/delete/api/v3/students")
                    .setHeader("name", "a")
                    .setHeader("book", "b1")
                    .setHeader("Content-Type", "application/json")
                    .setBody(objectMapper.writeValueAsString(requestBody))
                    .execute().toCompletableFuture().join();
            Student student = objectMapper.readValue(response.getResponseBody(), Student.class);
            log.info("student={}", student);
        }
    }
    @Test
    void studentV4() throws Exception {
        Student requestBody = Student.builder().name("request-name").book("request-book").build();
        try (AsyncHttpClient client = Dsl.asyncHttpClient()) {
            Response response = client.prepareDelete("http://localhost:9000/delete/api/v4/students?name=a&book=b1")
                    .setHeader("Content-Type", "application/json")
                    .setBody(objectMapper.writeValueAsString(requestBody))
                    .execute().toCompletableFuture().join();
            Student student = objectMapper.readValue(response.getResponseBody(), Student.class);
            log.info("student={}", student);
        }
    }
    @Test
    void studentV5() throws Exception {
        Student requestBody = Student.builder().name("request-name").book("request-book").build();
        try (AsyncHttpClient client = Dsl.asyncHttpClient()) {
            Response response = client.prepareDelete("http://localhost:9000/delete/api/v5/students/a/b1")
                    .setHeader("Content-Type", "application/json")
                    .setBody(objectMapper.writeValueAsString(requestBody))
                    .execute().toCompletableFuture().join();
            Student student = objectMapper.readValue(response.getResponseBody(), Student.class);
            log.info("student={}", student);
        }
    }
    @Test
    void studentV6() throws Exception {
        Student requestBody = Student.builder().name("request-name").book("request-book").build();
        try (AsyncHttpClient client = Dsl.asyncHttpClient()) {
            Response response = client.prepareDelete("http://localhost:9000/delete/api/v6/students/ABC123?book=b1")
                    .setHeader("name", "a")
                    .setHeader("Content-Type", "application/json")
                    .setBody(objectMapper.writeValueAsString(requestBody))
                    .execute().toCompletableFuture().join();
            Student student = objectMapper.readValue(response.getResponseBody(), Student.class);
            log.info("student={}", student);
        }
    }

}
