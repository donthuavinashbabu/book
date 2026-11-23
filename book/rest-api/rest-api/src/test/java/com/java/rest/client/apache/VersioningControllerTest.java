package com.java.rest.client.apache;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import com.java.model.Student;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.nio.charset.StandardCharsets;
import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.ClassicHttpResponse;
import org.apache.hc.core5.http.io.entity.EntityUtils;

/**
 * Rest client with {@link org.apache.hc.client5.http.impl.classic.CloseableHttpClient}
 * to {@link com.java.controller.impl.VersioningControllerImpl}
 */
@Slf4j
public class VersioningControllerTest {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void urlVersioning1() throws Exception {
        HttpGet request = new HttpGet("http://localhost:9000/versioning/api/v1/students");
        try (CloseableHttpClient client = HttpClients.createDefault();
             ClassicHttpResponse response = client.execute(request)) {
            String responseBody = response.getEntity() == null ? "" : EntityUtils.toString(response.getEntity(), StandardCharsets.UTF_8);
            Student student = objectMapper.readValue(responseBody, Student.class);
            log.info("student={}", student);
        }
    }
    @Test
    void urlVersioning2() throws Exception {
        HttpGet request = new HttpGet("http://localhost:9000/versioning/api/v2/students");
        try (CloseableHttpClient client = HttpClients.createDefault();
             ClassicHttpResponse response = client.execute(request)) {
            String responseBody = response.getEntity() == null ? "" : EntityUtils.toString(response.getEntity(), StandardCharsets.UTF_8);
            Student student = objectMapper.readValue(responseBody, Student.class);
            log.info("student={}", student);
        }
    }
    @Test
    void requestParamVersioning1() throws Exception {
        HttpGet request = new HttpGet("http://localhost:9000/versioning/api/students?VERSION=1");
        try (CloseableHttpClient client = HttpClients.createDefault();
             ClassicHttpResponse response = client.execute(request)) {
            String responseBody = response.getEntity() == null ? "" : EntityUtils.toString(response.getEntity(), StandardCharsets.UTF_8);
            Student student = objectMapper.readValue(responseBody, Student.class);
            log.info("student={}", student);
        }
    }
    @Test
    void requestParamVersioning2() throws Exception {
        HttpGet request = new HttpGet("http://localhost:9000/versioning/api/students?VERSION=2");
        try (CloseableHttpClient client = HttpClients.createDefault();
             ClassicHttpResponse response = client.execute(request)) {
            String responseBody = response.getEntity() == null ? "" : EntityUtils.toString(response.getEntity(), StandardCharsets.UTF_8);
            Student student = objectMapper.readValue(responseBody, Student.class);
            log.info("student={}", student);
        }
    }
    @Test
    void headerVersioning1() throws Exception {
        HttpGet request = new HttpGet("http://localhost:9000/versioning/api/students");
        request.setHeader("X-API-VERSION", "1");
        request.setHeader("Accept", "application/json");
        try (CloseableHttpClient client = HttpClients.createDefault();
             ClassicHttpResponse response = client.execute(request)) {
            String responseBody = response.getEntity() == null ? "" : EntityUtils.toString(response.getEntity(), StandardCharsets.UTF_8);
            Student student = objectMapper.readValue(responseBody, Student.class);
            log.info("student={}", student);
        }
    }
    @Test
    void headerVersioning2() throws Exception {
        HttpGet request = new HttpGet("http://localhost:9000/versioning/api/students");
        request.setHeader("X-API-VERSION", "2");
        request.setHeader("Accept", "application/json");
        try (CloseableHttpClient client = HttpClients.createDefault();
             ClassicHttpResponse response = client.execute(request)) {
            String responseBody = response.getEntity() == null ? "" : EntityUtils.toString(response.getEntity(), StandardCharsets.UTF_8);
            Student student = objectMapper.readValue(responseBody, Student.class);
            log.info("student={}", student);
        }
    }
    @Test
    void mediaTypeVersioning1() throws Exception {
        HttpGet request = new HttpGet("http://localhost:9000/versioning/api/students");
        request.setHeader("Accept", "application/my-company-v1+json");
        try (CloseableHttpClient client = HttpClients.createDefault();
             ClassicHttpResponse response = client.execute(request)) {
            String responseBody = response.getEntity() == null ? "" : EntityUtils.toString(response.getEntity(), StandardCharsets.UTF_8);
            Student student = objectMapper.readValue(responseBody, Student.class);
            log.info("student={}", student);
        }
    }
    @Test
    void mediaTypeVersioning2() throws Exception {
        HttpGet request = new HttpGet("http://localhost:9000/versioning/api/students");
        request.setHeader("Accept", "application/my-company-v2+json");
        try (CloseableHttpClient client = HttpClients.createDefault();
             ClassicHttpResponse response = client.execute(request)) {
            String responseBody = response.getEntity() == null ? "" : EntityUtils.toString(response.getEntity(), StandardCharsets.UTF_8);
            Student student = objectMapper.readValue(responseBody, Student.class);
            log.info("student={}", student);
        }
    }

}
