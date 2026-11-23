package com.java.rest.client.resttemplate;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

/**
 * Rest client with {@link org.springframework.web.client.RestTemplate}
 * to {@link com.java.controller.impl.I18NControllerImpl}
 */
@Slf4j
public class I18NControllerTest {

    @Test
    void helloWorld() {
        RestTemplate restTemplate = new RestTemplate();
        String english = restTemplate.getForObject("http://localhost:9000/i18n/api/v1/hello-world", String.class);
        log.info("english={}", english);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(HttpHeaders.ACCEPT_LANGUAGE, "fr");
        HttpEntity<Void> entity = new HttpEntity<>(httpHeaders);
        ResponseEntity<String> french = restTemplate.exchange("http://localhost:9000/i18n/api/v1/hello-world",
                HttpMethod.GET, entity, String.class);
        log.info("french={}", french.getBody());
    }

    @Test
    void helloWorldWithName() {
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:9000/i18n/api/v1/hello-world/{name}";
        Map<String, String> pathParams = new HashMap<>();
        pathParams.put("name", "a");

        String english = restTemplate.getForObject(url, String.class, pathParams);
        log.info("english={}", english);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(HttpHeaders.ACCEPT_LANGUAGE, "fr");
        HttpEntity<Void> entity = new HttpEntity<>(httpHeaders);
        ResponseEntity<String> french = restTemplate.exchange(url, HttpMethod.GET, entity, String.class, pathParams);
        log.info("french={}", french.getBody());
    }

}
