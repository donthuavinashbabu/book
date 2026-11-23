package com.java.rest.client.restclient;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpHeaders;
import org.springframework.web.client.RestClient;

/**
 * Rest client with {@link org.springframework.web.client.RestClient}
 * to {@link com.java.controller.impl.I18NControllerImpl}
 */
@Slf4j
public class I18NControllerTest {

    @Test
    void helloWorld() {
        RestClient restClient = RestClient.create();
        String english = restClient.get().uri("http://localhost:9000/i18n/api/v1/hello-world")
                .retrieve().body(String.class);
        log.info("english={}", english);

        String french = restClient.get().uri("http://localhost:9000/i18n/api/v1/hello-world")
                .header(HttpHeaders.ACCEPT_LANGUAGE, "fr")
                .retrieve().body(String.class);
        log.info("french={}", french);
    }

    @Test
    void helloWorldWithName() {
        RestClient restClient = RestClient.create();
        String english = restClient.get().uri("http://localhost:9000/i18n/api/v1/hello-world/{name}", "a")
                .retrieve().body(String.class);
        log.info("english={}", english);

        String french = restClient.get().uri("http://localhost:9000/i18n/api/v1/hello-world/{name}", "a")
                .header(HttpHeaders.ACCEPT_LANGUAGE, "fr")
                .retrieve().body(String.class);
        log.info("french={}", french);
    }

}
