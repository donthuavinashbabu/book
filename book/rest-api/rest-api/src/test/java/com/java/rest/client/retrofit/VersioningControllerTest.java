package com.java.rest.client.retrofit;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.io.IOException;

/**
 * Rest client with {@link retrofit2.Retrofit}
 * to {@link com.java.controller.impl.VersioningControllerImpl}
 */
@Slf4j
public class VersioningControllerTest {

    @Test
    void urlVersioning1() throws IOException {
        VersioningClient client = RetrofitClients.target(VersioningClient.class);
        log.info("student={}", client.urlVersioning1().execute().body());
    }

    @Test
    void urlVersioning2() throws IOException {
        VersioningClient client = RetrofitClients.target(VersioningClient.class);
        log.info("student={}", client.urlVersioning2().execute().body());
    }

    @Test
    void requestParamVersioning1() throws IOException {
        VersioningClient client = RetrofitClients.target(VersioningClient.class);
        log.info("student={}", client.requestParamVersioning("1").execute().body());
    }

    @Test
    void requestParamVersioning2() throws IOException {
        VersioningClient client = RetrofitClients.target(VersioningClient.class);
        log.info("student={}", client.requestParamVersioning("2").execute().body());
    }

    @Test
    void headerVersioning1() throws IOException {
        VersioningClient client = RetrofitClients.target(VersioningClient.class);
        log.info("student={}", client.headerVersioning("1", "application/json").execute().body());
    }

    @Test
    void headerVersioning2() throws IOException {
        VersioningClient client = RetrofitClients.target(VersioningClient.class);
        log.info("student={}", client.headerVersioning("2", "application/json").execute().body());
    }

    @Test
    void mediaTypeVersioning1() throws IOException {
        VersioningClient client = RetrofitClients.target(VersioningClient.class);
        log.info("student={}", client.mediaTypeVersioning("application/my-company-v1+json").execute().body());
    }

    @Test
    void mediaTypeVersioning2() throws IOException {
        VersioningClient client = RetrofitClients.target(VersioningClient.class);
        log.info("student={}", client.mediaTypeVersioning("application/my-company-v2+json").execute().body());
    }

}
