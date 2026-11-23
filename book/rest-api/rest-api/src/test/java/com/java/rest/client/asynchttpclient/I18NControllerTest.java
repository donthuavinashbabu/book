package com.java.rest.client.asynchttpclient;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.asynchttpclient.AsyncHttpClient;
import org.asynchttpclient.Dsl;
import org.asynchttpclient.Response;

/**
 * Rest client with {@link org.asynchttpclient.AsyncHttpClient}
 * to {@link com.java.controller.impl.I18NControllerImpl}
 */
@Slf4j
public class I18NControllerTest {

    @Test
    void helloWorld() throws Exception {
        try (AsyncHttpClient client = Dsl.asyncHttpClient()) {
            Response response = client.prepareGet("http://localhost:9000/i18n/api/v1/hello-world")
                    .execute().toCompletableFuture().join();
            log.info("english={}", response.getResponseBody());
        }

        try (AsyncHttpClient client = Dsl.asyncHttpClient()) {
            Response response = client.prepareGet("http://localhost:9000/i18n/api/v1/hello-world")
                    .setHeader("Accept-Language", "fr")
                    .execute().toCompletableFuture().join();
            log.info("french={}", response.getResponseBody());
        }
    }
    @Test
    void helloWorldWithName() throws Exception {
        try (AsyncHttpClient client = Dsl.asyncHttpClient()) {
            Response response = client.prepareGet("http://localhost:9000/i18n/api/v1/hello-world/a")
                    .execute().toCompletableFuture().join();
            log.info("english={}", response.getResponseBody());
        }

        try (AsyncHttpClient client = Dsl.asyncHttpClient()) {
            Response response = client.prepareGet("http://localhost:9000/i18n/api/v1/hello-world/a")
                    .setHeader("Accept-Language", "fr")
                    .execute().toCompletableFuture().join();
            log.info("french={}", response.getResponseBody());
        }
    }

}
