package com.java.rest.client.googlehttp;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import com.java.model.Student;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.io.ClassPathResource;
import java.nio.charset.StandardCharsets;
import com.google.api.client.http.ByteArrayContent;
import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestFactory;
import com.google.api.client.http.HttpResponse;
import com.google.api.client.http.javanet.NetHttpTransport;

/**
 * Rest client with {@link com.google.api.client.http.HttpRequestFactory}
 * to {@link com.java.controller.impl.DeleteControllerImpl}
 */
@Slf4j
public class DeleteControllerTest {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void studentV1() throws Exception {
        String studentJson = new ClassPathResource("student-v1.json").getContentAsString(StandardCharsets.UTF_8);
        HttpRequestFactory requestFactory = new NetHttpTransport().createRequestFactory();
        ByteArrayContent content = new ByteArrayContent("text/plain", studentJson.getBytes(StandardCharsets.UTF_8));
        HttpRequest request = requestFactory.buildRequest("DELETE", new GenericUrl("http://localhost:9000/delete/api/v1/students"), content);
        HttpResponse response = request.execute();
        try {
            Student student = objectMapper.readValue(response.parseAsString(), Student.class);
            log.info("student={}", student);
        } finally {
            response.disconnect();
        }
    }
    @Test
    void studentV2() throws Exception {
        Student requestBody = Student.builder().name("a").book("b1").build();
        HttpRequestFactory requestFactory = new NetHttpTransport().createRequestFactory();
        ByteArrayContent content = new ByteArrayContent("application/json", objectMapper.writeValueAsString(requestBody).getBytes(StandardCharsets.UTF_8));
        HttpRequest request = requestFactory.buildRequest("DELETE", new GenericUrl("http://localhost:9000/delete/api/v2/students"), content);
        HttpResponse response = request.execute();
        try {
            Student student = objectMapper.readValue(response.parseAsString(), Student.class);
            log.info("status={}, student={}", response.getStatusCode(), student);
        } finally {
            response.disconnect();
        }
    }
    @Test
    void studentV3() throws Exception {
        Student requestBody = Student.builder().name("request-name").book("request-book").build();
        HttpRequestFactory requestFactory = new NetHttpTransport().createRequestFactory();
        ByteArrayContent content = new ByteArrayContent("application/json", objectMapper.writeValueAsString(requestBody).getBytes(StandardCharsets.UTF_8));
        HttpRequest request = requestFactory.buildRequest("DELETE", new GenericUrl("http://localhost:9000/delete/api/v3/students"), content);
        request.getHeaders().set("name", "a");
        request.getHeaders().set("book", "b1");
        HttpResponse response = request.execute();
        try {
            Student student = objectMapper.readValue(response.parseAsString(), Student.class);
            log.info("student={}", student);
        } finally {
            response.disconnect();
        }
    }
    @Test
    void studentV4() throws Exception {
        Student requestBody = Student.builder().name("request-name").book("request-book").build();
        HttpRequestFactory requestFactory = new NetHttpTransport().createRequestFactory();
        ByteArrayContent content = new ByteArrayContent("application/json", objectMapper.writeValueAsString(requestBody).getBytes(StandardCharsets.UTF_8));
        HttpRequest request = requestFactory.buildRequest("DELETE", new GenericUrl("http://localhost:9000/delete/api/v4/students?name=a&book=b1"), content);
        HttpResponse response = request.execute();
        try {
            Student student = objectMapper.readValue(response.parseAsString(), Student.class);
            log.info("student={}", student);
        } finally {
            response.disconnect();
        }
    }
    @Test
    void studentV5() throws Exception {
        Student requestBody = Student.builder().name("request-name").book("request-book").build();
        HttpRequestFactory requestFactory = new NetHttpTransport().createRequestFactory();
        ByteArrayContent content = new ByteArrayContent("application/json", objectMapper.writeValueAsString(requestBody).getBytes(StandardCharsets.UTF_8));
        HttpRequest request = requestFactory.buildRequest("DELETE", new GenericUrl("http://localhost:9000/delete/api/v5/students/a/b1"), content);
        HttpResponse response = request.execute();
        try {
            Student student = objectMapper.readValue(response.parseAsString(), Student.class);
            log.info("student={}", student);
        } finally {
            response.disconnect();
        }
    }
    @Test
    void studentV6() throws Exception {
        Student requestBody = Student.builder().name("request-name").book("request-book").build();
        HttpRequestFactory requestFactory = new NetHttpTransport().createRequestFactory();
        ByteArrayContent content = new ByteArrayContent("application/json", objectMapper.writeValueAsString(requestBody).getBytes(StandardCharsets.UTF_8));
        HttpRequest request = requestFactory.buildRequest("DELETE", new GenericUrl("http://localhost:9000/delete/api/v6/students/ABC123?book=b1"), content);
        request.getHeaders().set("name", "a");
        HttpResponse response = request.execute();
        try {
            Student student = objectMapper.readValue(response.parseAsString(), Student.class);
            log.info("student={}", student);
        } finally {
            response.disconnect();
        }
    }

}
