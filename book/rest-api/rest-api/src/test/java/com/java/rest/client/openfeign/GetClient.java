package com.java.rest.client.openfeign;

import com.java.model.Student;
import com.java.model.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "getClient", url = "http://localhost:9000")
public interface GetClient {

    @GetMapping("/get/api/v1/hello-world")
    String helloWorld();

    @GetMapping("/get/api/v1/students")
    Student studentsV1();

    @GetMapping("/get/api/v2/students")
    ResponseEntity<Student> studentsV2();

    @GetMapping("/get/api/v3/students")
    List<Student> studentsV3();

    @GetMapping("/get/api/v4/students")
    Student studentsV4(@RequestHeader("name") String name, @RequestHeader("book") String book);

    @GetMapping("/get/api/v5/students")
    Student studentsV5(@RequestParam("name") String name, @RequestParam("book") String book);

    @GetMapping("/get/api/v6/students/{name}/{book}")
    Student studentsV6(@PathVariable("name") String name, @PathVariable("book") String book);

    @GetMapping("/get/api/v7/students")
    List<Student> studentsV7(@RequestParam("page") int page, @RequestParam("size") int size, @RequestParam("sort") String sort);

    @GetMapping("/get/api/v1/users")
    List<User> users1();

}
