package com.java.rest.client.webclient;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpHeaders;
import org.springframework.web.reactive.function.client.WebClient;

/**
 * Rest client with {@link org.springframework.web.reactive.function.client.WebClient}
 * to {@link com.java.controller.impl.I18NControllerImpl}
 */
@Slf4j
public class I18NControllerTest {

    @Test
    void helloWorld() {
        WebClient webClient = WebClient.create("http://localhost:9000");
        String english = webClient.get().uri("/i18n/api/v1/hello-world")
                .retrieve().bodyToMono(String.class).block();
        log.info("english={}", english);

        String french = webClient.get().uri("/i18n/api/v1/hello-world")
                .header(HttpHeaders.ACCEPT_LANGUAGE, "fr")
                .retrieve().bodyToMono(String.class).block();
        log.info("french={}", french);
    }

    @Test
    void helloWorldWithName() {
        WebClient webClient = WebClient.create("http://localhost:9000");
        String english = webClient.get().uri("/i18n/api/v1/hello-world/{name}", "a")
                .retrieve().bodyToMono(String.class).block();
        log.info("english={}", english);

        String french = webClient.get().uri("/i18n/api/v1/hello-world/{name}", "a")
                .header(HttpHeaders.ACCEPT_LANGUAGE, "fr")
                .retrieve().bodyToMono(String.class).block();
        log.info("french={}", french);
    }

}
