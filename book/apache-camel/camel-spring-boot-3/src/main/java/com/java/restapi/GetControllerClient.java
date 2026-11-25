package com.java.restapi;

import com.fasterxml.jackson.core.type.TypeReference;
import com.java.restapi.model.Student;
import com.java.restapi.model.User;
import org.springframework.stereotype.Component;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Component
public class GetControllerClient {

    private final CamelHttpClient http;

    public GetControllerClient(CamelHttpClient http) {
        this.http = http;
    }

    public String helloWorld() {
        return http.getText("/get/api/v1/hello-world", null);
    }

    public Student studentsV1() {
        return http.get("/get/api/v1/students", Student.class, null, null);
    }

    public Student studentsV2() {
        return http.get("/get/api/v2/students", Student.class, null, null);
    }

    public List<Student> studentsV3() {
        return http.get("/get/api/v3/students", new TypeReference<List<Student>>() {}, null, null);
    }

    public Student studentsV4(String name, String book) {
        return http.get("/get/api/v4/students", Student.class, Map.of("name", name, "book", book), null);
    }

    public Student studentsV5(String name, String book) {
        return http.get("/get/api/v5/students", Student.class, null, Map.of("name", name, "book", book));
    }

    public Student studentsV6(String name, String book) {
        return http.get(http.path("/get/api/v6/students/{name}/{book}", name, book), Student.class, null, null);
    }

    public List<Student> studentsV7(Integer page, Integer size, String sort) {
        Map<String, String> query = new LinkedHashMap<>();
        if (page != null) {
            query.put("page", page.toString());
        }
        if (size != null) {
            query.put("size", size.toString());
        }
        if (sort != null) {
            query.put("sort", sort);
        }
        return http.get("/get/api/v7/students", new TypeReference<List<Student>>() {}, null, query);
    }

    public List<User> users1() {
        return http.get("/get/api/v1/users", new TypeReference<List<User>>() {}, null, null);
    }

}
