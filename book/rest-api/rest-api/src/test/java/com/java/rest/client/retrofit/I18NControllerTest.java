package com.java.rest.client.retrofit;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.io.IOException;

/**
 * Rest client with {@link retrofit2.Retrofit}
 * to {@link com.java.controller.impl.I18NControllerImpl}
 */
@Slf4j
public class I18NControllerTest {

    @Test
    void helloWorld() throws IOException {
        I18NClient client = RetrofitClients.target(I18NClient.class);
        log.info("english={}", client.helloWorld().execute().body());
        log.info("french={}", client.helloWorld("fr").execute().body());
    }

    @Test
    void helloWorldWithName() throws IOException {
        I18NClient client = RetrofitClients.target(I18NClient.class);
        log.info("english={}", client.helloWorldWithName("a").execute().body());
        log.info("french={}", client.helloWorldWithName("a", "fr").execute().body());
    }

}
