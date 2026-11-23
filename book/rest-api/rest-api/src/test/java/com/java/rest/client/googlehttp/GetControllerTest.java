package com.java.rest.client.googlehttp;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import com.java.model.Student;
import com.java.model.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;
import java.util.List;
import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestFactory;
import com.google.api.client.http.HttpResponse;
import com.google.api.client.http.javanet.NetHttpTransport;

/**
 * Rest client with {@link com.google.api.client.http.HttpRequestFactory}
 * to {@link com.java.controller.impl.GetControllerImpl}
 */
@Slf4j
public class GetControllerTest {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void helloWorld() throws Exception {
        HttpRequestFactory requestFactory = new NetHttpTransport().createRequestFactory();
        HttpRequest request = requestFactory.buildGetRequest(new GenericUrl("http://localhost:9000/get/api/v1/hello-world"));
        HttpResponse response = request.execute();
        try {
            log.info("response={}", response.parseAsString());
        } finally {
            response.disconnect();
        }
    }
    @Test
    void studentsV1() throws Exception {
        HttpRequestFactory requestFactory = new NetHttpTransport().createRequestFactory();
        HttpRequest request = requestFactory.buildGetRequest(new GenericUrl("http://localhost:9000/get/api/v1/students"));
        HttpResponse response = request.execute();
        try {
            Student student = objectMapper.readValue(response.parseAsString(), Student.class);
            log.info("student={}", student);
        } finally {
            response.disconnect();
        }
    }
    @Test
    void studentsV2() throws Exception {
        HttpRequestFactory requestFactory = new NetHttpTransport().createRequestFactory();
        HttpRequest request = requestFactory.buildGetRequest(new GenericUrl("http://localhost:9000/get/api/v2/students"));
        HttpResponse response = request.execute();
        try {
            Student student = objectMapper.readValue(response.parseAsString(), Student.class);
            log.info("status={}, student={}", response.getStatusCode(), student);
        } finally {
            response.disconnect();
        }
    }
    @Test
    void studentsV3() throws Exception {
        HttpRequestFactory requestFactory = new NetHttpTransport().createRequestFactory();
        HttpRequest request = requestFactory.buildGetRequest(new GenericUrl("http://localhost:9000/get/api/v3/students"));
        HttpResponse response = request.execute();
        try {
            List<Student> students = objectMapper.readValue(response.parseAsString(), new TypeReference<List<Student>>() {});
            log.info("students={}", students);
        } finally {
            response.disconnect();
        }
    }
    @Test
    void studentsV4() throws Exception {
        HttpRequestFactory requestFactory = new NetHttpTransport().createRequestFactory();
        HttpRequest request = requestFactory.buildGetRequest(new GenericUrl("http://localhost:9000/get/api/v4/students"));
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
    void studentsV5() throws Exception {
        HttpRequestFactory requestFactory = new NetHttpTransport().createRequestFactory();
        HttpRequest request = requestFactory.buildGetRequest(new GenericUrl("http://localhost:9000/get/api/v5/students?name=a&book=b1"));
        HttpResponse response = request.execute();
        try {
            Student student = objectMapper.readValue(response.parseAsString(), Student.class);
            log.info("student={}", student);
        } finally {
            response.disconnect();
        }
    }
    @Test
    void studentsV6() throws Exception {
        HttpRequestFactory requestFactory = new NetHttpTransport().createRequestFactory();
        HttpRequest request = requestFactory.buildGetRequest(new GenericUrl("http://localhost:9000/get/api/v6/students/a/b1"));
        HttpResponse response = request.execute();
        try {
            Student student = objectMapper.readValue(response.parseAsString(), Student.class);
            log.info("student={}", student);
        } finally {
            response.disconnect();
        }
    }
    @Test
    void studentsV7() throws Exception {
        HttpRequestFactory requestFactory = new NetHttpTransport().createRequestFactory();
        HttpRequest request = requestFactory.buildGetRequest(new GenericUrl("http://localhost:9000/get/api/v7/students?page=0&size=2&sort=asc"));
        HttpResponse response = request.execute();
        try {
            List<Student> students = objectMapper.readValue(response.parseAsString(), new TypeReference<List<Student>>() {});
            log.info("students={}", students);
        } finally {
            response.disconnect();
        }
    }
    @Test
    void users1() throws Exception {
        HttpRequestFactory requestFactory = new NetHttpTransport().createRequestFactory();
        HttpRequest request = requestFactory.buildGetRequest(new GenericUrl("http://localhost:9000/get/api/v1/users"));
        HttpResponse response = request.execute();
        try {
            List<User> users = objectMapper.readValue(response.parseAsString(), new TypeReference<List<User>>() {});
            log.info("users={}", users);
        } finally {
            response.disconnect();
        }
    }

}
