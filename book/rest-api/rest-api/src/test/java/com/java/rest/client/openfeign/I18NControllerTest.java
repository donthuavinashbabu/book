package com.java.rest.client.openfeign;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

/**
 * Rest client with {@link org.springframework.cloud.openfeign.FeignClient}
 * to {@link com.java.controller.impl.I18NControllerImpl}
 */
@Slf4j
public class I18NControllerTest {

    @Test
    void helloWorld() {
        I18NClient client = OpenFeignClients.target(I18NClient.class);
        log.info("english={}", client.helloWorld());
        log.info("french={}", client.helloWorld("fr"));
    }

    @Test
    void helloWorldWithName() {
        I18NClient client = OpenFeignClients.target(I18NClient.class);
        log.info("english={}", client.helloWorldWithName("a"));
        log.info("french={}", client.helloWorldWithName("a", "fr"));
    }

}
