package com.java.rest.client.unirest;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import kong.unirest.HttpResponse;
import kong.unirest.Unirest;
import org.junit.jupiter.api.AfterAll;

/**
 * Rest client with {@link kong.unirest.Unirest}
 * to {@link com.java.controller.impl.I18NControllerImpl}
 */
@Slf4j
public class I18NControllerTest {

    @AfterAll
    static void shutdown() {
        Unirest.shutDown();
    }

    @Test
    void helloWorld() throws Exception {
        HttpResponse<String> response = Unirest.get("http://localhost:9000/i18n/api/v1/hello-world")
                .asString();
        log.info("english={}", response.getBody());

        HttpResponse<String> response1 = Unirest.get("http://localhost:9000/i18n/api/v1/hello-world")
                .header("Accept-Language", "fr")
                .asString();
        log.info("french={}", response1.getBody());
    }
    @Test
    void helloWorldWithName() throws Exception {
        HttpResponse<String> response = Unirest.get("http://localhost:9000/i18n/api/v1/hello-world/a")
                .asString();
        log.info("english={}", response.getBody());

        HttpResponse<String> response1 = Unirest.get("http://localhost:9000/i18n/api/v1/hello-world/a")
                .header("Accept-Language", "fr")
                .asString();
        log.info("french={}", response1.getBody());
    }

}
