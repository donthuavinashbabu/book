package com.java.rest.client.okhttp;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import com.java.model.Student;
import com.java.controller.PostController.Employee;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.io.ClassPathResource;
import java.nio.charset.StandardCharsets;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Rest client with {@link okhttp3.OkHttpClient}
 * to {@link com.java.controller.impl.PostControllerImpl}
 */
@Slf4j
public class PostControllerTest {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void studentV1() throws Exception {
        String studentJson = new ClassPathResource("student-v1.json").getContentAsString(StandardCharsets.UTF_8);
        Request request = new Request.Builder()
                .url("http://localhost:9000/post/api/v1/students")
                .post(RequestBody.create(studentJson, MediaType.get("text/plain")))
                .build();
        try (Response response = new OkHttpClient().newCall(request).execute()) {
            Student student = objectMapper.readValue(response.body().string(), Student.class);
            log.info("student={}", student);
        }
    }
    @Test
    void studentV2() throws Exception {
        Student requestBody = Student.builder().name("a").book("b1").build();
        Request request = new Request.Builder()
                .url("http://localhost:9000/post/api/v2/students")
                .post(RequestBody.create(objectMapper.writeValueAsString(requestBody), MediaType.get("application/json")))
                .build();
        try (Response response = new OkHttpClient().newCall(request).execute()) {
            Student student = objectMapper.readValue(response.body().string(), Student.class);
            log.info("status={}, student={}", response.code(), student);
        }
    }
    @Test
    void studentV3() throws Exception {
        Student requestBody = Student.builder().name("request-name").book("request-book").build();
        Request request = new Request.Builder()
                .url("http://localhost:9000/post/api/v3/students")
                .header("name", "a")
                .header("book", "b1")
                .post(RequestBody.create(objectMapper.writeValueAsString(requestBody), MediaType.get("application/json")))
                .build();
        try (Response response = new OkHttpClient().newCall(request).execute()) {
            Student student = objectMapper.readValue(response.body().string(), Student.class);
            log.info("student={}", student);
        }
    }
    @Test
    void studentV4() throws Exception {
        Student requestBody = Student.builder().name("request-name").book("request-book").build();
        Request request = new Request.Builder()
                .url("http://localhost:9000/post/api/v4/students?name=a&book=b1")
                .post(RequestBody.create(objectMapper.writeValueAsString(requestBody), MediaType.get("application/json")))
                .build();
        try (Response response = new OkHttpClient().newCall(request).execute()) {
            Student student = objectMapper.readValue(response.body().string(), Student.class);
            log.info("student={}", student);
        }
    }
    @Test
    void studentV5() throws Exception {
        Student requestBody = Student.builder().name("request-name").book("request-book").build();
        Request request = new Request.Builder()
                .url("http://localhost:9000/post/api/v5/students/a/b1")
                .post(RequestBody.create(objectMapper.writeValueAsString(requestBody), MediaType.get("application/json")))
                .build();
        try (Response response = new OkHttpClient().newCall(request).execute()) {
            Student student = objectMapper.readValue(response.body().string(), Student.class);
            log.info("student={}", student);
        }
    }
    @Test
    void studentV6() throws Exception {
        Student requestBody = Student.builder().name("request-name").book("request-book").build();
        Request request = new Request.Builder()
                .url("http://localhost:9000/post/api/v6/students/ABC123?book=b1")
                .header("name", "a")
                .post(RequestBody.create(objectMapper.writeValueAsString(requestBody), MediaType.get("application/json")))
                .build();
        try (Response response = new OkHttpClient().newCall(request).execute()) {
            Student student = objectMapper.readValue(response.body().string(), Student.class);
            log.info("student={}", student);
        }
    }
    @Test
    void employee() throws Exception {
        Employee requestBody = new Employee(1L, "a", "engineering");
        Request request = new Request.Builder()
                .url("http://localhost:9000/post/api/v1/employee")
                .post(RequestBody.create(objectMapper.writeValueAsString(requestBody), MediaType.get("application/json")))
                .build();
        try (Response response = new OkHttpClient().newCall(request).execute()) {
            Employee employee = objectMapper.readValue(response.body().string(), Employee.class);
            log.info("employee={}", employee);
        }
    }

}
