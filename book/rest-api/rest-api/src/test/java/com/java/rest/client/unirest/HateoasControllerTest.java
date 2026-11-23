package com.java.rest.client.unirest;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import com.java.model.Student;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;
import java.util.List;
import kong.unirest.HttpResponse;
import kong.unirest.Unirest;
import org.junit.jupiter.api.AfterAll;

/**
 * Rest client with {@link kong.unirest.Unirest}
 * to {@link com.java.controller.impl.HateoasControllerImpl}
 */
@Slf4j
public class HateoasControllerTest {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @AfterAll
    static void shutdown() {
        Unirest.shutDown();
    }

    @Test
    void findAllStudents() throws Exception {
        HttpResponse<String> response = Unirest.get("http://localhost:9000/hateoas/api/v1/students")
                .asString();
        List<Student> students = objectMapper.readValue(response.getBody(), new TypeReference<List<Student>>() {});
        log.info("students={}", students);
    }
    @Test
    void findStudentById() throws Exception {
        HttpResponse<String> response = Unirest.get("http://localhost:9000/hateoas/api/v1/students/1")
                .asString();
        Student student = objectMapper.readValue(response.getBody(), Student.class);
        log.info("student={}", student);
    }
    @Test
    void saveStudent() throws Exception {
        Student requestBody = Student.builder().name("a").book("b1").build();
        HttpResponse<String> response = Unirest.post("http://localhost:9000/hateoas/api/v1/students")
                .header("Content-Type", "application/json")
                .body(objectMapper.writeValueAsString(requestBody))
                .asString();
        log.info("status={}, location={}", response.getStatus(), response.getHeaders().getFirst("Location"));
    }
    @Test
    void saveStudents2() throws Exception {
        Student requestBody = Student.builder().name("a").book("b1").build();
        HttpResponse<String> response = Unirest.post("http://localhost:9000/hateoas/api/v2/students")
                .header("Content-Type", "application/json")
                .body(objectMapper.writeValueAsString(requestBody))
                .asString();
        log.info("body={}", response.getBody());
    }

}
