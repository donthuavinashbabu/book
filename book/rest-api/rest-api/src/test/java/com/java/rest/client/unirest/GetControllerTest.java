package com.java.rest.client.unirest;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import com.java.model.Student;
import com.java.model.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;
import java.util.List;
import kong.unirest.HttpResponse;
import kong.unirest.Unirest;
import org.junit.jupiter.api.AfterAll;

/**
 * Rest client with {@link kong.unirest.Unirest}
 * to {@link com.java.controller.impl.GetControllerImpl}
 */
@Slf4j
public class GetControllerTest {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @AfterAll
    static void shutdown() {
        Unirest.shutDown();
    }

    @Test
    void helloWorld() throws Exception {
        HttpResponse<String> response = Unirest.get("http://localhost:9000/get/api/v1/hello-world")
                .asString();
        log.info("response={}", response.getBody());
    }
    @Test
    void studentsV1() throws Exception {
        HttpResponse<String> response = Unirest.get("http://localhost:9000/get/api/v1/students")
                .asString();
        Student student = objectMapper.readValue(response.getBody(), Student.class);
        log.info("student={}", student);
    }
    @Test
    void studentsV2() throws Exception {
        HttpResponse<String> response = Unirest.get("http://localhost:9000/get/api/v2/students")
                .asString();
        Student student = objectMapper.readValue(response.getBody(), Student.class);
        log.info("status={}, student={}", response.getStatus(), student);
    }
    @Test
    void studentsV3() throws Exception {
        HttpResponse<String> response = Unirest.get("http://localhost:9000/get/api/v3/students")
                .asString();
        List<Student> students = objectMapper.readValue(response.getBody(), new TypeReference<List<Student>>() {});
        log.info("students={}", students);
    }
    @Test
    void studentsV4() throws Exception {
        HttpResponse<String> response = Unirest.get("http://localhost:9000/get/api/v4/students")
                .header("name", "a")
                .header("book", "b1")
                .asString();
        Student student = objectMapper.readValue(response.getBody(), Student.class);
        log.info("student={}", student);
    }
    @Test
    void studentsV5() throws Exception {
        HttpResponse<String> response = Unirest.get("http://localhost:9000/get/api/v5/students?name=a&book=b1")
                .asString();
        Student student = objectMapper.readValue(response.getBody(), Student.class);
        log.info("student={}", student);
    }
    @Test
    void studentsV6() throws Exception {
        HttpResponse<String> response = Unirest.get("http://localhost:9000/get/api/v6/students/a/b1")
                .asString();
        Student student = objectMapper.readValue(response.getBody(), Student.class);
        log.info("student={}", student);
    }
    @Test
    void studentsV7() throws Exception {
        HttpResponse<String> response = Unirest.get("http://localhost:9000/get/api/v7/students?page=0&size=2&sort=asc")
                .asString();
        List<Student> students = objectMapper.readValue(response.getBody(), new TypeReference<List<Student>>() {});
        log.info("students={}", students);
    }
    @Test
    void users1() throws Exception {
        HttpResponse<String> response = Unirest.get("http://localhost:9000/get/api/v1/users")
                .asString();
        List<User> users = objectMapper.readValue(response.getBody(), new TypeReference<List<User>>() {});
        log.info("users={}", users);
    }

}
