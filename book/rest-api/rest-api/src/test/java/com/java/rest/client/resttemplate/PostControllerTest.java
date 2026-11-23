package com.java.rest.client.resttemplate;

import com.java.controller.PostController.Employee;
import com.java.model.Student;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

/**
 * Rest client with {@link org.springframework.web.client.RestTemplate}
 * to {@link com.java.controller.impl.PostControllerImpl}
 */
@Slf4j
public class PostControllerTest {

    @Test
    void studentV1() throws IOException {
        RestTemplate restTemplate = new RestTemplate();
        String studentJson = new ClassPathResource("student-v1.json").getContentAsString(StandardCharsets.UTF_8);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.TEXT_PLAIN);
        HttpEntity<String> entity = new HttpEntity<>(studentJson, httpHeaders);

        Student student = restTemplate.postForObject("http://localhost:9000/post/api/v1/students", entity, Student.class);
        log.info("student={}", student);
    }

    @Test
    void studentV2() {
        RestTemplate restTemplate = new RestTemplate();
        Student request = Student.builder().name("a").book("b1").build();

        ResponseEntity<Student> response = restTemplate.postForEntity("http://localhost:9000/post/api/v2/students", request, Student.class);
        log.info("status={}, student={}", response.getStatusCode(), response.getBody());
    }

    @Test
    void studentV3() {
        RestTemplate restTemplate = new RestTemplate();
        Student request = Student.builder().name("request-name").book("request-book").build();

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.add("name", "a");
        httpHeaders.add("book", "b1");
        HttpEntity<Student> entity = new HttpEntity<>(request, httpHeaders);

        ResponseEntity<Student> response = restTemplate.exchange("http://localhost:9000/post/api/v3/students",
                HttpMethod.POST, entity, Student.class);
        log.info("student={}", response.getBody());
    }

    @Test
    void studentV4() {
        RestTemplate restTemplate = new RestTemplate();
        Student request = Student.builder().name("request-name").book("request-book").build();

        String url = "http://localhost:9000/post/api/v4/students?name={name}&book={book}";
        Map<String, String> queryParams = new HashMap<>();
        queryParams.put("name", "a");
        queryParams.put("book", "b1");

        Student student = restTemplate.postForObject(url, request, Student.class, queryParams);
        log.info("student={}", student);
    }

    @Test
    void studentV5() {
        RestTemplate restTemplate = new RestTemplate();
        Student request = Student.builder().name("request-name").book("request-book").build();

        String url = "http://localhost:9000/post/api/v5/students/{name}/{book}";
        Map<String, String> pathParams = new HashMap<>();
        pathParams.put("name", "a");
        pathParams.put("book", "b1");

        Student student = restTemplate.postForObject(url, request, Student.class, pathParams);
        log.info("student={}", student);
    }

    @Test
    void studentV6() {
        RestTemplate restTemplate = new RestTemplate();
        Student request = Student.builder().name("request-name").book("request-book").build();

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.add("name", "a");
        HttpEntity<Student> entity = new HttpEntity<>(request, httpHeaders);

        String url = "http://localhost:9000/post/api/v6/students/{id}?book={book}";
        Map<String, String> uriVariables = new HashMap<>();
        uriVariables.put("id", "ABC123");
        uriVariables.put("book", "b1");

        ResponseEntity<Student> response = restTemplate.exchange(url, HttpMethod.POST, entity, Student.class, uriVariables);
        log.info("student={}", response.getBody());
    }

    @Test
    void employee() {
        RestTemplate restTemplate = new RestTemplate();
        Employee request = new Employee(1L, "a", "engineering");

        Employee employee = restTemplate.postForObject("http://localhost:9000/post/api/v1/employee", request, Employee.class);
        log.info("employee={}", employee);
    }

}
