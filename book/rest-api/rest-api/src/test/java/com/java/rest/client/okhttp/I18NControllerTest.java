package com.java.rest.client.okhttp;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Rest client with {@link okhttp3.OkHttpClient}
 * to {@link com.java.controller.impl.I18NControllerImpl}
 */
@Slf4j
public class I18NControllerTest {

    @Test
    void helloWorld() throws Exception {
        Request request = new Request.Builder()
                .url("http://localhost:9000/i18n/api/v1/hello-world")
                .get()
                .build();
        try (Response response = new OkHttpClient().newCall(request).execute()) {
            log.info("english={}", response.body().string());
        }

        Request request1 = new Request.Builder()
                .url("http://localhost:9000/i18n/api/v1/hello-world")
                .header("Accept-Language", "fr")
                .get()
                .build();
        try (Response response = new OkHttpClient().newCall(request1).execute()) {
            log.info("french={}", response.body().string());
        }
    }
    @Test
    void helloWorldWithName() throws Exception {
        Request request = new Request.Builder()
                .url("http://localhost:9000/i18n/api/v1/hello-world/a")
                .get()
                .build();
        try (Response response = new OkHttpClient().newCall(request).execute()) {
            log.info("english={}", response.body().string());
        }

        Request request1 = new Request.Builder()
                .url("http://localhost:9000/i18n/api/v1/hello-world/a")
                .header("Accept-Language", "fr")
                .get()
                .build();
        try (Response response = new OkHttpClient().newCall(request1).execute()) {
            log.info("french={}", response.body().string());
        }
    }

}
