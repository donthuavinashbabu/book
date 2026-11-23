package com.java.rest.client.httpclient;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

/**
 * Rest client with {@link java.net.http.HttpClient}
 * to {@link com.java.controller.impl.I18NControllerImpl}
 */
@Slf4j
public class I18NControllerTest {

    @Test
    void helloWorld() throws Exception {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:9000/i18n/api/v1/hello-world"))
                .GET()
                .build();
        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        log.info("english={}", response.body());

        HttpRequest request1 = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:9000/i18n/api/v1/hello-world"))
                .header("Accept-Language", "fr")
                .GET()
                .build();
        HttpResponse<String> response1 = HttpClient.newHttpClient().send(request1, HttpResponse.BodyHandlers.ofString());
        log.info("french={}", response1.body());
    }
    @Test
    void helloWorldWithName() throws Exception {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:9000/i18n/api/v1/hello-world/a"))
                .GET()
                .build();
        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        log.info("english={}", response.body());

        HttpRequest request1 = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:9000/i18n/api/v1/hello-world/a"))
                .header("Accept-Language", "fr")
                .GET()
                .build();
        HttpResponse<String> response1 = HttpClient.newHttpClient().send(request1, HttpResponse.BodyHandlers.ofString());
        log.info("french={}", response1.body());
    }

}
