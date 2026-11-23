package com.java.rest.client.openfeign;

import com.java.model.Student;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.http.MediaType.TEXT_PLAIN_VALUE;

@FeignClient(name = "putClient", url = "http://localhost:9000")
public interface PutClient {

    @PutMapping(value = "/put/api/v1/students", consumes = TEXT_PLAIN_VALUE)
    Student studentV1(@RequestBody String studentJson);

    @PutMapping(value = "/put/api/v2/students", consumes = APPLICATION_JSON_VALUE)
    ResponseEntity<Student> studentV2(@RequestBody Student student);

    @PutMapping(value = "/put/api/v3/students", consumes = APPLICATION_JSON_VALUE)
    Student studentV3(@RequestBody Student student, @RequestHeader("name") String name, @RequestHeader("book") String book);

    @PutMapping(value = "/put/api/v4/students", consumes = APPLICATION_JSON_VALUE)
    Student studentV4(@RequestBody Student student, @RequestParam("name") String name, @RequestParam("book") String book);

    @PutMapping(value = "/put/api/v5/students/{name}/{book}", consumes = APPLICATION_JSON_VALUE)
    Student studentV5(@RequestBody Student student, @PathVariable("name") String name, @PathVariable("book") String book);

    @PutMapping(value = "/put/api/v6/students/{id}", consumes = APPLICATION_JSON_VALUE)
    Student studentV6(@RequestBody Student student, @PathVariable("id") String id, @RequestHeader("name") String name, @RequestParam("book") String book);

}
