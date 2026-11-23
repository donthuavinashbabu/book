package com.java.rest.client.okhttp;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import com.java.model.Student;
import com.java.model.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;
import java.util.List;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Rest client with {@link okhttp3.OkHttpClient}
 * to {@link com.java.controller.impl.GetControllerImpl}
 */
@Slf4j
public class GetControllerTest {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void helloWorld() throws Exception {
        Request request = new Request.Builder()
                .url("http://localhost:9000/get/api/v1/hello-world")
                .get()
                .build();
        try (Response response = new OkHttpClient().newCall(request).execute()) {
            log.info("response={}", response.body().string());
        }
    }
    @Test
    void studentsV1() throws Exception {
        Request request = new Request.Builder()
                .url("http://localhost:9000/get/api/v1/students")
                .get()
                .build();
        try (Response response = new OkHttpClient().newCall(request).execute()) {
            Student student = objectMapper.readValue(response.body().string(), Student.class);
            log.info("student={}", student);
        }
    }
    @Test
    void studentsV2() throws Exception {
        Request request = new Request.Builder()
                .url("http://localhost:9000/get/api/v2/students")
                .get()
                .build();
        try (Response response = new OkHttpClient().newCall(request).execute()) {
            Student student = objectMapper.readValue(response.body().string(), Student.class);
            log.info("status={}, student={}", response.code(), student);
        }
    }
    @Test
    void studentsV3() throws Exception {
        Request request = new Request.Builder()
                .url("http://localhost:9000/get/api/v3/students")
                .get()
                .build();
        try (Response response = new OkHttpClient().newCall(request).execute()) {
            List<Student> students = objectMapper.readValue(response.body().string(), new TypeReference<List<Student>>() {});
            log.info("students={}", students);
        }
    }
    @Test
    void studentsV4() throws Exception {
        Request request = new Request.Builder()
                .url("http://localhost:9000/get/api/v4/students")
                .header("name", "a")
                .header("book", "b1")
                .get()
                .build();
        try (Response response = new OkHttpClient().newCall(request).execute()) {
            Student student = objectMapper.readValue(response.body().string(), Student.class);
            log.info("student={}", student);
        }
    }
    @Test
    void studentsV5() throws Exception {
        Request request = new Request.Builder()
                .url("http://localhost:9000/get/api/v5/students?name=a&book=b1")
                .get()
                .build();
        try (Response response = new OkHttpClient().newCall(request).execute()) {
            Student student = objectMapper.readValue(response.body().string(), Student.class);
            log.info("student={}", student);
        }
    }
    @Test
    void studentsV6() throws Exception {
        Request request = new Request.Builder()
                .url("http://localhost:9000/get/api/v6/students/a/b1")
                .get()
                .build();
        try (Response response = new OkHttpClient().newCall(request).execute()) {
            Student student = objectMapper.readValue(response.body().string(), Student.class);
            log.info("student={}", student);
        }
    }
    @Test
    void studentsV7() throws Exception {
        Request request = new Request.Builder()
                .url("http://localhost:9000/get/api/v7/students?page=0&size=2&sort=asc")
                .get()
                .build();
        try (Response response = new OkHttpClient().newCall(request).execute()) {
            List<Student> students = objectMapper.readValue(response.body().string(), new TypeReference<List<Student>>() {});
            log.info("students={}", students);
        }
    }
    @Test
    void users1() throws Exception {
        Request request = new Request.Builder()
                .url("http://localhost:9000/get/api/v1/users")
                .get()
                .build();
        try (Response response = new OkHttpClient().newCall(request).execute()) {
            List<User> users = objectMapper.readValue(response.body().string(), new TypeReference<List<User>>() {});
            log.info("users={}", users);
        }
    }

}
