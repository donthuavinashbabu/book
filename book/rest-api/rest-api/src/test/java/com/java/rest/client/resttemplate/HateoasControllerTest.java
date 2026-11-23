package com.java.rest.client.resttemplate;

import com.java.model.Student;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * Rest client with {@link org.springframework.web.client.RestTemplate}
 * to {@link com.java.controller.impl.HateoasControllerImpl}
 */
@Slf4j
public class HateoasControllerTest {

    @Test
    void findAllStudents() {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<List<Student>> response = restTemplate.exchange("http://localhost:9000/hateoas/api/v1/students",
                HttpMethod.GET, null, new ParameterizedTypeReference<List<Student>>() {});
        log.info("students={}", response.getBody());
    }

    @Test
    void findStudentById() {
        RestTemplate restTemplate = new RestTemplate();
        Student student = restTemplate.getForObject("http://localhost:9000/hateoas/api/v1/students/{id}", Student.class, "1");
        log.info("student={}", student);
    }

    @Test
    void saveStudent() {
        RestTemplate restTemplate = new RestTemplate();
        Student request = Student.builder().name("a").book("b1").build();

        ResponseEntity<Student> response = restTemplate.postForEntity("http://localhost:9000/hateoas/api/v1/students", request, Student.class);
        log.info("status={}, location={}, body={}", response.getStatusCode(), response.getHeaders().getLocation(), response.getBody());
    }

    @Test
    void saveStudents2() {
        RestTemplate restTemplate = new RestTemplate();
        Student request = Student.builder().name("a").book("b1").build();

        // Read as text so the HATEOAS _links are visible without a HATEOAS Jackson module
        String body = restTemplate.postForObject("http://localhost:9000/hateoas/api/v2/students", request, String.class);
        log.info("body={}", body);
    }

}
