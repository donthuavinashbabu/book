package com.java.restapi;

import com.fasterxml.jackson.core.type.TypeReference;
import com.java.restapi.model.CreatedResponse;
import com.java.restapi.model.Student;
import com.java.restapi.model.StudentResource;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class HateoasControllerClient {

    private final CamelHttpClient http;

    public HateoasControllerClient(CamelHttpClient http) {
        this.http = http;
    }

    public List<Student> findAllStudents() {
        return http.get("/hateoas/api/v1/students", new TypeReference<List<Student>>() {}, null, null);
    }

    public Student findStudentById(String id) {
        return http.get(http.path("/hateoas/api/v1/students/{id}", id), Student.class, null, null);
    }

    public CreatedResponse saveStudent(Student student) {
        return http.postCreated("/hateoas/api/v1/students", student);
    }

    public StudentResource saveStudents2(Student student) {
        return http.postJson("/hateoas/api/v2/students", student, StudentResource.class, null, null);
    }

}
