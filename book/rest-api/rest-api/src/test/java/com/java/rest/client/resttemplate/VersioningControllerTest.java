package com.java.rest.client.resttemplate;

import com.java.model.Student;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * Rest client with {@link org.springframework.web.client.RestTemplate}
 * to {@link com.java.controller.impl.VersioningControllerImpl}
 */
@Slf4j
public class VersioningControllerTest {

    @Test
    void urlVersioning1() {
        RestTemplate restTemplate = new RestTemplate();
        Student student = restTemplate.getForObject("http://localhost:9000/versioning/api/v1/students", Student.class);
        log.info("student={}", student);
    }

    @Test
    void urlVersioning2() {
        RestTemplate restTemplate = new RestTemplate();
        Student student = restTemplate.getForObject("http://localhost:9000/versioning/api/v2/students", Student.class);
        log.info("student={}", student);
    }

    @Test
    void requestParamVersioning1() {
        RestTemplate restTemplate = new RestTemplate();
        Student student = restTemplate.getForObject("http://localhost:9000/versioning/api/students?VERSION=1", Student.class);
        log.info("student={}", student);
    }

    @Test
    void requestParamVersioning2() {
        RestTemplate restTemplate = new RestTemplate();
        Student student = restTemplate.getForObject("http://localhost:9000/versioning/api/students?VERSION=2", Student.class);
        log.info("student={}", student);
    }

    @Test
    void headerVersioning1() {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("X-API-VERSION", "1");
        httpHeaders.setAccept(List.of(MediaType.APPLICATION_JSON));
        HttpEntity<Void> entity = new HttpEntity<>(httpHeaders);

        ResponseEntity<Student> response = restTemplate.exchange("http://localhost:9000/versioning/api/students",
                HttpMethod.GET, entity, Student.class);
        log.info("student={}", response.getBody());
    }

    @Test
    void headerVersioning2() {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("X-API-VERSION", "2");
        httpHeaders.setAccept(List.of(MediaType.APPLICATION_JSON));
        HttpEntity<Void> entity = new HttpEntity<>(httpHeaders);

        ResponseEntity<Student> response = restTemplate.exchange("http://localhost:9000/versioning/api/students",
                HttpMethod.GET, entity, Student.class);
        log.info("student={}", response.getBody());
    }

    @Test
    void mediaTypeVersioning1() {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set(HttpHeaders.ACCEPT, "application/my-company-v1+json");
        HttpEntity<Void> entity = new HttpEntity<>(httpHeaders);

        ResponseEntity<Student> response = restTemplate.exchange("http://localhost:9000/versioning/api/students",
                HttpMethod.GET, entity, Student.class);
        log.info("student={}", response.getBody());
    }

    @Test
    void mediaTypeVersioning2() {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set(HttpHeaders.ACCEPT, "application/my-company-v2+json");
        HttpEntity<Void> entity = new HttpEntity<>(httpHeaders);

        ResponseEntity<Student> response = restTemplate.exchange("http://localhost:9000/versioning/api/students",
                HttpMethod.GET, entity, Student.class);
        log.info("student={}", response.getBody());
    }

}
