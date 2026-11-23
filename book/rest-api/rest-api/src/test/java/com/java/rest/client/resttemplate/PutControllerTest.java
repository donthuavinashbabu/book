package com.java.rest.client.resttemplate;

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
 * to {@link com.java.controller.impl.PutControllerImpl}
 *
 * {@link RestTemplate#put} returns void, so these calls use {@link RestTemplate#exchange}
 * to read the {@link Student} response body.
 */
@Slf4j
public class PutControllerTest {

    @Test
    void studentV1() throws IOException {
        RestTemplate restTemplate = new RestTemplate();
        String studentJson = new ClassPathResource("student-v1.json").getContentAsString(StandardCharsets.UTF_8);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.TEXT_PLAIN);
        HttpEntity<String> entity = new HttpEntity<>(studentJson, httpHeaders);

        ResponseEntity<Student> response = restTemplate.exchange("http://localhost:9000/put/api/v1/students",
                HttpMethod.PUT, entity, Student.class);
        log.info("student={}", response.getBody());
    }

    @Test
    void studentV2() {
        RestTemplate restTemplate = new RestTemplate();
        Student request = Student.builder().name("a").book("b1").build();
        HttpEntity<Student> entity = new HttpEntity<>(request);

        ResponseEntity<Student> response = restTemplate.exchange("http://localhost:9000/put/api/v2/students",
                HttpMethod.PUT, entity, Student.class);
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

        ResponseEntity<Student> response = restTemplate.exchange("http://localhost:9000/put/api/v3/students",
                HttpMethod.PUT, entity, Student.class);
        log.info("student={}", response.getBody());
    }

    @Test
    void studentV4() {
        RestTemplate restTemplate = new RestTemplate();
        Student request = Student.builder().name("request-name").book("request-book").build();
        HttpEntity<Student> entity = new HttpEntity<>(request);

        String url = "http://localhost:9000/put/api/v4/students?name={name}&book={book}";
        Map<String, String> queryParams = new HashMap<>();
        queryParams.put("name", "a");
        queryParams.put("book", "b1");

        ResponseEntity<Student> response = restTemplate.exchange(url, HttpMethod.PUT, entity, Student.class, queryParams);
        log.info("student={}", response.getBody());
    }

    @Test
    void studentV5() {
        RestTemplate restTemplate = new RestTemplate();
        Student request = Student.builder().name("request-name").book("request-book").build();
        HttpEntity<Student> entity = new HttpEntity<>(request);

        String url = "http://localhost:9000/put/api/v5/students/{name}/{book}";
        Map<String, String> pathParams = new HashMap<>();
        pathParams.put("name", "a");
        pathParams.put("book", "b1");

        ResponseEntity<Student> response = restTemplate.exchange(url, HttpMethod.PUT, entity, Student.class, pathParams);
        log.info("student={}", response.getBody());
    }

    @Test
    void studentV6() {
        RestTemplate restTemplate = new RestTemplate();
        Student request = Student.builder().name("request-name").book("request-book").build();

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.add("name", "a");
        HttpEntity<Student> entity = new HttpEntity<>(request, httpHeaders);

        String url = "http://localhost:9000/put/api/v6/students/{id}?book={book}";
        Map<String, String> uriVariables = new HashMap<>();
        uriVariables.put("id", "ABC123");
        uriVariables.put("book", "b1");

        ResponseEntity<Student> response = restTemplate.exchange(url, HttpMethod.PUT, entity, Student.class, uriVariables);
        log.info("student={}", response.getBody());
    }

}
