package com.java.rest.client.openfeign;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

/**
 * Rest client with {@link org.springframework.cloud.openfeign.FeignClient}
 * to {@link com.java.controller.impl.VersioningControllerImpl}
 */
@Slf4j
public class VersioningControllerTest {

    @Test
    void urlVersioning1() {
        VersioningClient client = OpenFeignClients.target(VersioningClient.class);
        log.info("student={}", client.urlVersioning1());
    }

    @Test
    void urlVersioning2() {
        VersioningClient client = OpenFeignClients.target(VersioningClient.class);
        log.info("student={}", client.urlVersioning2());
    }

    @Test
    void requestParamVersioning1() {
        VersioningClient client = OpenFeignClients.target(VersioningClient.class);
        log.info("student={}", client.requestParamVersioning("1"));
    }

    @Test
    void requestParamVersioning2() {
        VersioningClient client = OpenFeignClients.target(VersioningClient.class);
        log.info("student={}", client.requestParamVersioning("2"));
    }

    @Test
    void headerVersioning1() {
        VersioningClient client = OpenFeignClients.target(VersioningClient.class);
        log.info("student={}", client.headerVersioning("1"));
    }

    @Test
    void headerVersioning2() {
        VersioningClient client = OpenFeignClients.target(VersioningClient.class);
        log.info("student={}", client.headerVersioning("2"));
    }

    @Test
    void mediaTypeVersioning1() {
        VersioningClient client = OpenFeignClients.target(VersioningClient.class);
        log.info("student={}", client.mediaTypeVersioning1());
    }

    @Test
    void mediaTypeVersioning2() {
        VersioningClient client = OpenFeignClients.target(VersioningClient.class);
        log.info("student={}", client.mediaTypeVersioning2());
    }

}
