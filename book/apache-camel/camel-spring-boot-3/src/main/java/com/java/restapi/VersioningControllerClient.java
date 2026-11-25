package com.java.restapi;

import com.java.restapi.model.Student;
import org.springframework.stereotype.Component;

import java.util.Map;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Component
public class VersioningControllerClient {

    private static final String STUDENTS = "/versioning/api/students";

    private final CamelHttpClient http;

    public VersioningControllerClient(CamelHttpClient http) {
        this.http = http;
    }

    public Student urlVersioning1() {
        return http.get("/versioning/api/v1/students", Student.class, null, null);
    }

    public Student urlVersioning2() {
        return http.get("/versioning/api/v2/students", Student.class, null, null);
    }

    public Student requestParamVersioning1() {
        return http.get(STUDENTS, Student.class, APPLICATION_JSON_VALUE, null, Map.of("VERSION", "1"));
    }

    public Student requestParamVersioning2() {
        return http.get(STUDENTS, Student.class, APPLICATION_JSON_VALUE, null, Map.of("VERSION", "2"));
    }

    public Student headerVersioning1() {
        return http.get(STUDENTS, Student.class, APPLICATION_JSON_VALUE, Map.of("X-API-VERSION", "1"), null);
    }

    public Student headerVersioning2() {
        return http.get(STUDENTS, Student.class, APPLICATION_JSON_VALUE, Map.of("X-API-VERSION", "2"), null);
    }

    public Student mediaTypeVersioning1() {
        return http.get(STUDENTS, Student.class, "application/my-company-v1+json", null, null);
    }

    public Student mediaTypeVersioning2() {
        return http.get(STUDENTS, Student.class, "application/my-company-v2+json", null, null);
    }

}
