package com.java.rest.client.apache;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import com.java.model.Student;
import com.java.controller.PostController.Employee;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.io.ClassPathResource;
import java.nio.charset.StandardCharsets;
import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.ClassicHttpResponse;
import org.apache.hc.core5.http.ContentType;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.apache.hc.core5.http.io.entity.StringEntity;

/**
 * Rest client with {@link org.apache.hc.client5.http.impl.classic.CloseableHttpClient}
 * to {@link com.java.controller.impl.PostControllerImpl}
 */
@Slf4j
public class PostControllerTest {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void studentV1() throws Exception {
        String studentJson = new ClassPathResource("student-v1.json").getContentAsString(StandardCharsets.UTF_8);
        HttpPost request = new HttpPost("http://localhost:9000/post/api/v1/students");
        request.setEntity(new StringEntity(studentJson, ContentType.TEXT_PLAIN));
        try (CloseableHttpClient client = HttpClients.createDefault();
             ClassicHttpResponse response = client.execute(request)) {
            String responseBody = response.getEntity() == null ? "" : EntityUtils.toString(response.getEntity(), StandardCharsets.UTF_8);
            Student student = objectMapper.readValue(responseBody, Student.class);
            log.info("student={}", student);
        }
    }
    @Test
    void studentV2() throws Exception {
        Student requestBody = Student.builder().name("a").book("b1").build();
        HttpPost request = new HttpPost("http://localhost:9000/post/api/v2/students");
        request.setEntity(new StringEntity(objectMapper.writeValueAsString(requestBody), ContentType.APPLICATION_JSON));
        try (CloseableHttpClient client = HttpClients.createDefault();
             ClassicHttpResponse response = client.execute(request)) {
            String responseBody = response.getEntity() == null ? "" : EntityUtils.toString(response.getEntity(), StandardCharsets.UTF_8);
            Student student = objectMapper.readValue(responseBody, Student.class);
            log.info("status={}, student={}", response.getCode(), student);
        }
    }
    @Test
    void studentV3() throws Exception {
        Student requestBody = Student.builder().name("request-name").book("request-book").build();
        HttpPost request = new HttpPost("http://localhost:9000/post/api/v3/students");
        request.setHeader("name", "a");
        request.setHeader("book", "b1");
        request.setEntity(new StringEntity(objectMapper.writeValueAsString(requestBody), ContentType.APPLICATION_JSON));
        try (CloseableHttpClient client = HttpClients.createDefault();
             ClassicHttpResponse response = client.execute(request)) {
            String responseBody = response.getEntity() == null ? "" : EntityUtils.toString(response.getEntity(), StandardCharsets.UTF_8);
            Student student = objectMapper.readValue(responseBody, Student.class);
            log.info("student={}", student);
        }
    }
    @Test
    void studentV4() throws Exception {
        Student requestBody = Student.builder().name("request-name").book("request-book").build();
        HttpPost request = new HttpPost("http://localhost:9000/post/api/v4/students?name=a&book=b1");
        request.setEntity(new StringEntity(objectMapper.writeValueAsString(requestBody), ContentType.APPLICATION_JSON));
        try (CloseableHttpClient client = HttpClients.createDefault();
             ClassicHttpResponse response = client.execute(request)) {
            String responseBody = response.getEntity() == null ? "" : EntityUtils.toString(response.getEntity(), StandardCharsets.UTF_8);
            Student student = objectMapper.readValue(responseBody, Student.class);
            log.info("student={}", student);
        }
    }
    @Test
    void studentV5() throws Exception {
        Student requestBody = Student.builder().name("request-name").book("request-book").build();
        HttpPost request = new HttpPost("http://localhost:9000/post/api/v5/students/a/b1");
        request.setEntity(new StringEntity(objectMapper.writeValueAsString(requestBody), ContentType.APPLICATION_JSON));
        try (CloseableHttpClient client = HttpClients.createDefault();
             ClassicHttpResponse response = client.execute(request)) {
            String responseBody = response.getEntity() == null ? "" : EntityUtils.toString(response.getEntity(), StandardCharsets.UTF_8);
            Student student = objectMapper.readValue(responseBody, Student.class);
            log.info("student={}", student);
        }
    }
    @Test
    void studentV6() throws Exception {
        Student requestBody = Student.builder().name("request-name").book("request-book").build();
        HttpPost request = new HttpPost("http://localhost:9000/post/api/v6/students/ABC123?book=b1");
        request.setHeader("name", "a");
        request.setEntity(new StringEntity(objectMapper.writeValueAsString(requestBody), ContentType.APPLICATION_JSON));
        try (CloseableHttpClient client = HttpClients.createDefault();
             ClassicHttpResponse response = client.execute(request)) {
            String responseBody = response.getEntity() == null ? "" : EntityUtils.toString(response.getEntity(), StandardCharsets.UTF_8);
            Student student = objectMapper.readValue(responseBody, Student.class);
            log.info("student={}", student);
        }
    }
    @Test
    void employee() throws Exception {
        Employee requestBody = new Employee(1L, "a", "engineering");
        HttpPost request = new HttpPost("http://localhost:9000/post/api/v1/employee");
        request.setEntity(new StringEntity(objectMapper.writeValueAsString(requestBody), ContentType.APPLICATION_JSON));
        try (CloseableHttpClient client = HttpClients.createDefault();
             ClassicHttpResponse response = client.execute(request)) {
            String responseBody = response.getEntity() == null ? "" : EntityUtils.toString(response.getEntity(), StandardCharsets.UTF_8);
            Employee employee = objectMapper.readValue(responseBody, Employee.class);
            log.info("employee={}", employee);
        }
    }

}
