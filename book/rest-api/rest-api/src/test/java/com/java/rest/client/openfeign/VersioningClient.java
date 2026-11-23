package com.java.rest.client.openfeign;

import com.java.model.Student;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@FeignClient(name = "versioningClient", url = "http://localhost:9000")
public interface VersioningClient {

    @GetMapping("/versioning/api/v1/students")
    Student urlVersioning1();

    @GetMapping("/versioning/api/v2/students")
    Student urlVersioning2();

    @GetMapping("/versioning/api/students")
    Student requestParamVersioning(@RequestParam("VERSION") String version);

    @GetMapping(value = "/versioning/api/students", produces = APPLICATION_JSON_VALUE)
    Student headerVersioning(@RequestHeader("X-API-VERSION") String version);

    @GetMapping(value = "/versioning/api/students", produces = "application/my-company-v1+json")
    Student mediaTypeVersioning1();

    @GetMapping(value = "/versioning/api/students", produces = "application/my-company-v2+json")
    Student mediaTypeVersioning2();

}
