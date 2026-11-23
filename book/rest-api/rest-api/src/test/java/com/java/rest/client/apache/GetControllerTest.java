package com.java.rest.client.apache;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import com.java.model.Student;
import com.java.model.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;
import java.util.List;
import java.nio.charset.StandardCharsets;
import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.ClassicHttpResponse;
import org.apache.hc.core5.http.io.entity.EntityUtils;

/**
 * Rest client with {@link org.apache.hc.client5.http.impl.classic.CloseableHttpClient}
 * to {@link com.java.controller.impl.GetControllerImpl}
 */
@Slf4j
public class GetControllerTest {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void helloWorld() throws Exception {
        HttpGet request = new HttpGet("http://localhost:9000/get/api/v1/hello-world");
        try (CloseableHttpClient client = HttpClients.createDefault();
             ClassicHttpResponse response = client.execute(request)) {
            String responseBody = response.getEntity() == null ? "" : EntityUtils.toString(response.getEntity(), StandardCharsets.UTF_8);
            log.info("response={}", responseBody);
        }
    }
    @Test
    void studentsV1() throws Exception {
        HttpGet request = new HttpGet("http://localhost:9000/get/api/v1/students");
        try (CloseableHttpClient client = HttpClients.createDefault();
             ClassicHttpResponse response = client.execute(request)) {
            String responseBody = response.getEntity() == null ? "" : EntityUtils.toString(response.getEntity(), StandardCharsets.UTF_8);
            Student student = objectMapper.readValue(responseBody, Student.class);
            log.info("student={}", student);
        }
    }
    @Test
    void studentsV2() throws Exception {
        HttpGet request = new HttpGet("http://localhost:9000/get/api/v2/students");
        try (CloseableHttpClient client = HttpClients.createDefault();
             ClassicHttpResponse response = client.execute(request)) {
            String responseBody = response.getEntity() == null ? "" : EntityUtils.toString(response.getEntity(), StandardCharsets.UTF_8);
            Student student = objectMapper.readValue(responseBody, Student.class);
            log.info("status={}, student={}", response.getCode(), student);
        }
    }
    @Test
    void studentsV3() throws Exception {
        HttpGet request = new HttpGet("http://localhost:9000/get/api/v3/students");
        try (CloseableHttpClient client = HttpClients.createDefault();
             ClassicHttpResponse response = client.execute(request)) {
            String responseBody = response.getEntity() == null ? "" : EntityUtils.toString(response.getEntity(), StandardCharsets.UTF_8);
            List<Student> students = objectMapper.readValue(responseBody, new TypeReference<List<Student>>() {});
            log.info("students={}", students);
        }
    }
    @Test
    void studentsV4() throws Exception {
        HttpGet request = new HttpGet("http://localhost:9000/get/api/v4/students");
        request.setHeader("name", "a");
        request.setHeader("book", "b1");
        try (CloseableHttpClient client = HttpClients.createDefault();
             ClassicHttpResponse response = client.execute(request)) {
            String responseBody = response.getEntity() == null ? "" : EntityUtils.toString(response.getEntity(), StandardCharsets.UTF_8);
            Student student = objectMapper.readValue(responseBody, Student.class);
            log.info("student={}", student);
        }
    }
    @Test
    void studentsV5() throws Exception {
        HttpGet request = new HttpGet("http://localhost:9000/get/api/v5/students?name=a&book=b1");
        try (CloseableHttpClient client = HttpClients.createDefault();
             ClassicHttpResponse response = client.execute(request)) {
            String responseBody = response.getEntity() == null ? "" : EntityUtils.toString(response.getEntity(), StandardCharsets.UTF_8);
            Student student = objectMapper.readValue(responseBody, Student.class);
            log.info("student={}", student);
        }
    }
    @Test
    void studentsV6() throws Exception {
        HttpGet request = new HttpGet("http://localhost:9000/get/api/v6/students/a/b1");
        try (CloseableHttpClient client = HttpClients.createDefault();
             ClassicHttpResponse response = client.execute(request)) {
            String responseBody = response.getEntity() == null ? "" : EntityUtils.toString(response.getEntity(), StandardCharsets.UTF_8);
            Student student = objectMapper.readValue(responseBody, Student.class);
            log.info("student={}", student);
        }
    }
    @Test
    void studentsV7() throws Exception {
        HttpGet request = new HttpGet("http://localhost:9000/get/api/v7/students?page=0&size=2&sort=asc");
        try (CloseableHttpClient client = HttpClients.createDefault();
             ClassicHttpResponse response = client.execute(request)) {
            String responseBody = response.getEntity() == null ? "" : EntityUtils.toString(response.getEntity(), StandardCharsets.UTF_8);
            List<Student> students = objectMapper.readValue(responseBody, new TypeReference<List<Student>>() {});
            log.info("students={}", students);
        }
    }
    @Test
    void users1() throws Exception {
        HttpGet request = new HttpGet("http://localhost:9000/get/api/v1/users");
        try (CloseableHttpClient client = HttpClients.createDefault();
             ClassicHttpResponse response = client.execute(request)) {
            String responseBody = response.getEntity() == null ? "" : EntityUtils.toString(response.getEntity(), StandardCharsets.UTF_8);
            List<User> users = objectMapper.readValue(responseBody, new TypeReference<List<User>>() {});
            log.info("users={}", users);
        }
    }

}
