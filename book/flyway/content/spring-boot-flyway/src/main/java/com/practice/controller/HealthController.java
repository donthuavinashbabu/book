package com.practice.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthController {

    record Status(String status){}

    @GetMapping(value = "/health", produces = MediaType.APPLICATION_JSON_VALUE)
    public Status health() {
        return new Status("UP");
    }
}