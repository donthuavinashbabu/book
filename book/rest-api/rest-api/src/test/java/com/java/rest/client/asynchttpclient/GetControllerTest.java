package com.java.rest.client.asynchttpclient;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import com.java.model.Student;
import com.java.model.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;
import java.util.List;
import org.asynchttpclient.AsyncHttpClient;
import org.asynchttpclient.Dsl;
import org.asynchttpclient.Response;

/**
 * Rest client with {@link org.asynchttpclient.AsyncHttpClient}
 * to {@link com.java.controller.impl.GetControllerImpl}
 */
@Slf4j
public class GetControllerTest {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void helloWorld() throws Exception {
        try (AsyncHttpClient client = Dsl.asyncHttpClient()) {
            Response response = client.prepareGet("http://localhost:9000/get/api/v1/hello-world")
                    .execute().toCompletableFuture().join();
            log.info("response={}", response.getResponseBody());
        }
    }
    @Test
    void studentsV1() throws Exception {
        try (AsyncHttpClient client = Dsl.asyncHttpClient()) {
            Response response = client.prepareGet("http://localhost:9000/get/api/v1/students")
                    .execute().toCompletableFuture().join();
            Student student = objectMapper.readValue(response.getResponseBody(), Student.class);
            log.info("student={}", student);
        }
    }
    @Test
    void studentsV2() throws Exception {
        try (AsyncHttpClient client = Dsl.asyncHttpClient()) {
            Response response = client.prepareGet("http://localhost:9000/get/api/v2/students")
                    .execute().toCompletableFuture().join();
            Student student = objectMapper.readValue(response.getResponseBody(), Student.class);
            log.info("status={}, student={}", response.getStatusCode(), student);
        }
    }
    @Test
    void studentsV3() throws Exception {
        try (AsyncHttpClient client = Dsl.asyncHttpClient()) {
            Response response = client.prepareGet("http://localhost:9000/get/api/v3/students")
                    .execute().toCompletableFuture().join();
            List<Student> students = objectMapper.readValue(response.getResponseBody(), new TypeReference<List<Student>>() {});
            log.info("students={}", students);
        }
    }
    @Test
    void studentsV4() throws Exception {
        try (AsyncHttpClient client = Dsl.asyncHttpClient()) {
            Response response = client.prepareGet("http://localhost:9000/get/api/v4/students")
                    .setHeader("name", "a")
                    .setHeader("book", "b1")
                    .execute().toCompletableFuture().join();
            Student student = objectMapper.readValue(response.getResponseBody(), Student.class);
            log.info("student={}", student);
        }
    }
    @Test
    void studentsV5() throws Exception {
        try (AsyncHttpClient client = Dsl.asyncHttpClient()) {
            Response response = client.prepareGet("http://localhost:9000/get/api/v5/students?name=a&book=b1")
                    .execute().toCompletableFuture().join();
            Student student = objectMapper.readValue(response.getResponseBody(), Student.class);
            log.info("student={}", student);
        }
    }
    @Test
    void studentsV6() throws Exception {
        try (AsyncHttpClient client = Dsl.asyncHttpClient()) {
            Response response = client.prepareGet("http://localhost:9000/get/api/v6/students/a/b1")
                    .execute().toCompletableFuture().join();
            Student student = objectMapper.readValue(response.getResponseBody(), Student.class);
            log.info("student={}", student);
        }
    }
    @Test
    void studentsV7() throws Exception {
        try (AsyncHttpClient client = Dsl.asyncHttpClient()) {
            Response response = client.prepareGet("http://localhost:9000/get/api/v7/students?page=0&size=2&sort=asc")
                    .execute().toCompletableFuture().join();
            List<Student> students = objectMapper.readValue(response.getResponseBody(), new TypeReference<List<Student>>() {});
            log.info("students={}", students);
        }
    }
    @Test
    void users1() throws Exception {
        try (AsyncHttpClient client = Dsl.asyncHttpClient()) {
            Response response = client.prepareGet("http://localhost:9000/get/api/v1/users")
                    .execute().toCompletableFuture().join();
            List<User> users = objectMapper.readValue(response.getResponseBody(), new TypeReference<List<User>>() {});
            log.info("users={}", users);
        }
    }

}
