package com.java.rest.client.openfeign;

import com.java.model.Student;
import feign.Response;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@FeignClient(name = "hateoasClient", url = "http://localhost:9000")
public interface HateoasClient {

    @GetMapping("/hateoas/api/v1/students")
    List<Student> findAllStudents();

    @GetMapping("/hateoas/api/v1/students/{id}")
    Student findStudentById(@PathVariable("id") String id);

    @PostMapping(value = "/hateoas/api/v1/students", consumes = APPLICATION_JSON_VALUE)
    Response saveStudent(@RequestBody Student student);

    @PostMapping(value = "/hateoas/api/v2/students", consumes = APPLICATION_JSON_VALUE)
    String saveStudents2(@RequestBody Student student);

}
