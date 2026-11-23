package com.java.rest.client.googlehttp;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import com.java.model.Student;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;
import java.util.List;
import java.nio.charset.StandardCharsets;
import com.google.api.client.http.ByteArrayContent;
import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestFactory;
import com.google.api.client.http.HttpResponse;
import com.google.api.client.http.javanet.NetHttpTransport;

/**
 * Rest client with {@link com.google.api.client.http.HttpRequestFactory}
 * to {@link com.java.controller.impl.HateoasControllerImpl}
 */
@Slf4j
public class HateoasControllerTest {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void findAllStudents() throws Exception {
        HttpRequestFactory requestFactory = new NetHttpTransport().createRequestFactory();
        HttpRequest request = requestFactory.buildGetRequest(new GenericUrl("http://localhost:9000/hateoas/api/v1/students"));
        HttpResponse response = request.execute();
        try {
            List<Student> students = objectMapper.readValue(response.parseAsString(), new TypeReference<List<Student>>() {});
            log.info("students={}", students);
        } finally {
            response.disconnect();
        }
    }
    @Test
    void findStudentById() throws Exception {
        HttpRequestFactory requestFactory = new NetHttpTransport().createRequestFactory();
        HttpRequest request = requestFactory.buildGetRequest(new GenericUrl("http://localhost:9000/hateoas/api/v1/students/1"));
        HttpResponse response = request.execute();
        try {
            Student student = objectMapper.readValue(response.parseAsString(), Student.class);
            log.info("student={}", student);
        } finally {
            response.disconnect();
        }
    }
    @Test
    void saveStudent() throws Exception {
        Student requestBody = Student.builder().name("a").book("b1").build();
        HttpRequestFactory requestFactory = new NetHttpTransport().createRequestFactory();
        ByteArrayContent content = new ByteArrayContent("application/json", objectMapper.writeValueAsString(requestBody).getBytes(StandardCharsets.UTF_8));
        HttpRequest request = requestFactory.buildPostRequest(new GenericUrl("http://localhost:9000/hateoas/api/v1/students"), content);
        HttpResponse response = request.execute();
        try {
            log.info("status={}, location={}", response.getStatusCode(), response.getHeaders().getLocation());
        } finally {
            response.disconnect();
        }
    }
    @Test
    void saveStudents2() throws Exception {
        Student requestBody = Student.builder().name("a").book("b1").build();
        HttpRequestFactory requestFactory = new NetHttpTransport().createRequestFactory();
        ByteArrayContent content = new ByteArrayContent("application/json", objectMapper.writeValueAsString(requestBody).getBytes(StandardCharsets.UTF_8));
        HttpRequest request = requestFactory.buildPostRequest(new GenericUrl("http://localhost:9000/hateoas/api/v2/students"), content);
        HttpResponse response = request.execute();
        try {
            log.info("body={}", response.parseAsString());
        } finally {
            response.disconnect();
        }
    }

}
