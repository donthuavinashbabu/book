package com.java.rest.client.okhttp;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import com.java.model.Student;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;
import java.util.List;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Rest client with {@link okhttp3.OkHttpClient}
 * to {@link com.java.controller.impl.HateoasControllerImpl}
 */
@Slf4j
public class HateoasControllerTest {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void findAllStudents() throws Exception {
        Request request = new Request.Builder()
                .url("http://localhost:9000/hateoas/api/v1/students")
                .get()
                .build();
        try (Response response = new OkHttpClient().newCall(request).execute()) {
            List<Student> students = objectMapper.readValue(response.body().string(), new TypeReference<List<Student>>() {});
            log.info("students={}", students);
        }
    }
    @Test
    void findStudentById() throws Exception {
        Request request = new Request.Builder()
                .url("http://localhost:9000/hateoas/api/v1/students/1")
                .get()
                .build();
        try (Response response = new OkHttpClient().newCall(request).execute()) {
            Student student = objectMapper.readValue(response.body().string(), Student.class);
            log.info("student={}", student);
        }
    }
    @Test
    void saveStudent() throws Exception {
        Student requestBody = Student.builder().name("a").book("b1").build();
        Request request = new Request.Builder()
                .url("http://localhost:9000/hateoas/api/v1/students")
                .post(RequestBody.create(objectMapper.writeValueAsString(requestBody), MediaType.get("application/json")))
                .build();
        try (Response response = new OkHttpClient().newCall(request).execute()) {
            log.info("status={}, location={}", response.code(), response.header("Location"));
        }
    }
    @Test
    void saveStudents2() throws Exception {
        Student requestBody = Student.builder().name("a").book("b1").build();
        Request request = new Request.Builder()
                .url("http://localhost:9000/hateoas/api/v2/students")
                .post(RequestBody.create(objectMapper.writeValueAsString(requestBody), MediaType.get("application/json")))
                .build();
        try (Response response = new OkHttpClient().newCall(request).execute()) {
            log.info("body={}", response.body().string());
        }
    }

}
