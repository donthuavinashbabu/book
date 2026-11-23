package com.java.rest.client.googlehttp;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import com.java.model.Student;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestFactory;
import com.google.api.client.http.HttpResponse;
import com.google.api.client.http.javanet.NetHttpTransport;

/**
 * Rest client with {@link com.google.api.client.http.HttpRequestFactory}
 * to {@link com.java.controller.impl.VersioningControllerImpl}
 */
@Slf4j
public class VersioningControllerTest {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void urlVersioning1() throws Exception {
        HttpRequestFactory requestFactory = new NetHttpTransport().createRequestFactory();
        HttpRequest request = requestFactory.buildGetRequest(new GenericUrl("http://localhost:9000/versioning/api/v1/students"));
        HttpResponse response = request.execute();
        try {
            Student student = objectMapper.readValue(response.parseAsString(), Student.class);
            log.info("student={}", student);
        } finally {
            response.disconnect();
        }
    }
    @Test
    void urlVersioning2() throws Exception {
        HttpRequestFactory requestFactory = new NetHttpTransport().createRequestFactory();
        HttpRequest request = requestFactory.buildGetRequest(new GenericUrl("http://localhost:9000/versioning/api/v2/students"));
        HttpResponse response = request.execute();
        try {
            Student student = objectMapper.readValue(response.parseAsString(), Student.class);
            log.info("student={}", student);
        } finally {
            response.disconnect();
        }
    }
    @Test
    void requestParamVersioning1() throws Exception {
        HttpRequestFactory requestFactory = new NetHttpTransport().createRequestFactory();
        HttpRequest request = requestFactory.buildGetRequest(new GenericUrl("http://localhost:9000/versioning/api/students?VERSION=1"));
        HttpResponse response = request.execute();
        try {
            Student student = objectMapper.readValue(response.parseAsString(), Student.class);
            log.info("student={}", student);
        } finally {
            response.disconnect();
        }
    }
    @Test
    void requestParamVersioning2() throws Exception {
        HttpRequestFactory requestFactory = new NetHttpTransport().createRequestFactory();
        HttpRequest request = requestFactory.buildGetRequest(new GenericUrl("http://localhost:9000/versioning/api/students?VERSION=2"));
        HttpResponse response = request.execute();
        try {
            Student student = objectMapper.readValue(response.parseAsString(), Student.class);
            log.info("student={}", student);
        } finally {
            response.disconnect();
        }
    }
    @Test
    void headerVersioning1() throws Exception {
        HttpRequestFactory requestFactory = new NetHttpTransport().createRequestFactory();
        HttpRequest request = requestFactory.buildGetRequest(new GenericUrl("http://localhost:9000/versioning/api/students"));
        request.getHeaders().set("X-API-VERSION", "1");
        request.getHeaders().setAccept("application/json");
        HttpResponse response = request.execute();
        try {
            Student student = objectMapper.readValue(response.parseAsString(), Student.class);
            log.info("student={}", student);
        } finally {
            response.disconnect();
        }
    }
    @Test
    void headerVersioning2() throws Exception {
        HttpRequestFactory requestFactory = new NetHttpTransport().createRequestFactory();
        HttpRequest request = requestFactory.buildGetRequest(new GenericUrl("http://localhost:9000/versioning/api/students"));
        request.getHeaders().set("X-API-VERSION", "2");
        request.getHeaders().setAccept("application/json");
        HttpResponse response = request.execute();
        try {
            Student student = objectMapper.readValue(response.parseAsString(), Student.class);
            log.info("student={}", student);
        } finally {
            response.disconnect();
        }
    }
    @Test
    void mediaTypeVersioning1() throws Exception {
        HttpRequestFactory requestFactory = new NetHttpTransport().createRequestFactory();
        HttpRequest request = requestFactory.buildGetRequest(new GenericUrl("http://localhost:9000/versioning/api/students"));
        request.getHeaders().setAccept("application/my-company-v1+json");
        HttpResponse response = request.execute();
        try {
            Student student = objectMapper.readValue(response.parseAsString(), Student.class);
            log.info("student={}", student);
        } finally {
            response.disconnect();
        }
    }
    @Test
    void mediaTypeVersioning2() throws Exception {
        HttpRequestFactory requestFactory = new NetHttpTransport().createRequestFactory();
        HttpRequest request = requestFactory.buildGetRequest(new GenericUrl("http://localhost:9000/versioning/api/students"));
        request.getHeaders().setAccept("application/my-company-v2+json");
        HttpResponse response = request.execute();
        try {
            Student student = objectMapper.readValue(response.parseAsString(), Student.class);
            log.info("student={}", student);
        } finally {
            response.disconnect();
        }
    }

}
