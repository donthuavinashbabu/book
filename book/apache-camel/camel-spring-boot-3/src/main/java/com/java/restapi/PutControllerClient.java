package com.java.restapi;

import com.java.restapi.model.Student;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class PutControllerClient {

    private final CamelHttpClient http;

    public PutControllerClient(CamelHttpClient http) {
        this.http = http;
    }

    public Student studentV1(String studentJsonAsText) {
        return http.putText("/put/api/v1/students", studentJsonAsText, Student.class);
    }

    public Student studentV2(Student student) {
        return http.putJson("/put/api/v2/students", student, Student.class, null, null);
    }

    public Student studentV3(Student student, String name, String book) {
        return http.putJson("/put/api/v3/students", student, Student.class, Map.of("name", name, "book", book), null);
    }

    public Student studentV4(Student student, String name, String book) {
        return http.putJson("/put/api/v4/students", student, Student.class, null, Map.of("name", name, "book", book));
    }

    public Student studentV5(Student student, String name, String book) {
        return http.putJson(http.path("/put/api/v5/students/{name}/{book}", name, book), student, Student.class, null, null);
    }

    public Student studentV6(Student student, String id, String name, String book) {
        return http.putJson(http.path("/put/api/v6/students/{id}", id), student, Student.class,
                Map.of("name", name), Map.of("book", book));
    }

}
