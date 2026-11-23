package com.java.rest.client.okhttp;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import com.java.model.Student;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Rest client with {@link okhttp3.OkHttpClient}
 * to {@link com.java.controller.impl.VersioningControllerImpl}
 */
@Slf4j
public class VersioningControllerTest {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void urlVersioning1() throws Exception {
        Request request = new Request.Builder()
                .url("http://localhost:9000/versioning/api/v1/students")
                .get()
                .build();
        try (Response response = new OkHttpClient().newCall(request).execute()) {
            Student student = objectMapper.readValue(response.body().string(), Student.class);
            log.info("student={}", student);
        }
    }
    @Test
    void urlVersioning2() throws Exception {
        Request request = new Request.Builder()
                .url("http://localhost:9000/versioning/api/v2/students")
                .get()
                .build();
        try (Response response = new OkHttpClient().newCall(request).execute()) {
            Student student = objectMapper.readValue(response.body().string(), Student.class);
            log.info("student={}", student);
        }
    }
    @Test
    void requestParamVersioning1() throws Exception {
        Request request = new Request.Builder()
                .url("http://localhost:9000/versioning/api/students?VERSION=1")
                .get()
                .build();
        try (Response response = new OkHttpClient().newCall(request).execute()) {
            Student student = objectMapper.readValue(response.body().string(), Student.class);
            log.info("student={}", student);
        }
    }
    @Test
    void requestParamVersioning2() throws Exception {
        Request request = new Request.Builder()
                .url("http://localhost:9000/versioning/api/students?VERSION=2")
                .get()
                .build();
        try (Response response = new OkHttpClient().newCall(request).execute()) {
            Student student = objectMapper.readValue(response.body().string(), Student.class);
            log.info("student={}", student);
        }
    }
    @Test
    void headerVersioning1() throws Exception {
        Request request = new Request.Builder()
                .url("http://localhost:9000/versioning/api/students")
                .header("X-API-VERSION", "1")
                .header("Accept", "application/json")
                .get()
                .build();
        try (Response response = new OkHttpClient().newCall(request).execute()) {
            Student student = objectMapper.readValue(response.body().string(), Student.class);
            log.info("student={}", student);
        }
    }
    @Test
    void headerVersioning2() throws Exception {
        Request request = new Request.Builder()
                .url("http://localhost:9000/versioning/api/students")
                .header("X-API-VERSION", "2")
                .header("Accept", "application/json")
                .get()
                .build();
        try (Response response = new OkHttpClient().newCall(request).execute()) {
            Student student = objectMapper.readValue(response.body().string(), Student.class);
            log.info("student={}", student);
        }
    }
    @Test
    void mediaTypeVersioning1() throws Exception {
        Request request = new Request.Builder()
                .url("http://localhost:9000/versioning/api/students")
                .header("Accept", "application/my-company-v1+json")
                .get()
                .build();
        try (Response response = new OkHttpClient().newCall(request).execute()) {
            Student student = objectMapper.readValue(response.body().string(), Student.class);
            log.info("student={}", student);
        }
    }
    @Test
    void mediaTypeVersioning2() throws Exception {
        Request request = new Request.Builder()
                .url("http://localhost:9000/versioning/api/students")
                .header("Accept", "application/my-company-v2+json")
                .get()
                .build();
        try (Response response = new OkHttpClient().newCall(request).execute()) {
            Student student = objectMapper.readValue(response.body().string(), Student.class);
            log.info("student={}", student);
        }
    }

}
