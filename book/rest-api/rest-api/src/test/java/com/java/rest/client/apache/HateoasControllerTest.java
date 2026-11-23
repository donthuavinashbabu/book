package com.java.rest.client.apache;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import com.java.model.Student;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;
import java.util.List;
import java.nio.charset.StandardCharsets;
import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.ClassicHttpResponse;
import org.apache.hc.core5.http.ContentType;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.apache.hc.core5.http.io.entity.StringEntity;

/**
 * Rest client with {@link org.apache.hc.client5.http.impl.classic.CloseableHttpClient}
 * to {@link com.java.controller.impl.HateoasControllerImpl}
 */
@Slf4j
public class HateoasControllerTest {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void findAllStudents() throws Exception {
        HttpGet request = new HttpGet("http://localhost:9000/hateoas/api/v1/students");
        try (CloseableHttpClient client = HttpClients.createDefault();
             ClassicHttpResponse response = client.execute(request)) {
            String responseBody = response.getEntity() == null ? "" : EntityUtils.toString(response.getEntity(), StandardCharsets.UTF_8);
            List<Student> students = objectMapper.readValue(responseBody, new TypeReference<List<Student>>() {});
            log.info("students={}", students);
        }
    }
    @Test
    void findStudentById() throws Exception {
        HttpGet request = new HttpGet("http://localhost:9000/hateoas/api/v1/students/1");
        try (CloseableHttpClient client = HttpClients.createDefault();
             ClassicHttpResponse response = client.execute(request)) {
            String responseBody = response.getEntity() == null ? "" : EntityUtils.toString(response.getEntity(), StandardCharsets.UTF_8);
            Student student = objectMapper.readValue(responseBody, Student.class);
            log.info("student={}", student);
        }
    }
    @Test
    void saveStudent() throws Exception {
        Student requestBody = Student.builder().name("a").book("b1").build();
        HttpPost request = new HttpPost("http://localhost:9000/hateoas/api/v1/students");
        request.setEntity(new StringEntity(objectMapper.writeValueAsString(requestBody), ContentType.APPLICATION_JSON));
        try (CloseableHttpClient client = HttpClients.createDefault();
             ClassicHttpResponse response = client.execute(request)) {
            String responseBody = response.getEntity() == null ? "" : EntityUtils.toString(response.getEntity(), StandardCharsets.UTF_8);
            log.info("status={}, location={}", response.getCode(), response.getFirstHeader("Location") == null ? null : response.getFirstHeader("Location").getValue());
        }
    }
    @Test
    void saveStudents2() throws Exception {
        Student requestBody = Student.builder().name("a").book("b1").build();
        HttpPost request = new HttpPost("http://localhost:9000/hateoas/api/v2/students");
        request.setEntity(new StringEntity(objectMapper.writeValueAsString(requestBody), ContentType.APPLICATION_JSON));
        try (CloseableHttpClient client = HttpClients.createDefault();
             ClassicHttpResponse response = client.execute(request)) {
            String responseBody = response.getEntity() == null ? "" : EntityUtils.toString(response.getEntity(), StandardCharsets.UTF_8);
            log.info("body={}", responseBody);
        }
    }

}
