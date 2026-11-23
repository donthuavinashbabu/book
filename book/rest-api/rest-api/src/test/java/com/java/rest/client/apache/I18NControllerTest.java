package com.java.rest.client.apache;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import java.nio.charset.StandardCharsets;
import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.ClassicHttpResponse;
import org.apache.hc.core5.http.io.entity.EntityUtils;

/**
 * Rest client with {@link org.apache.hc.client5.http.impl.classic.CloseableHttpClient}
 * to {@link com.java.controller.impl.I18NControllerImpl}
 */
@Slf4j
public class I18NControllerTest {

    @Test
    void helloWorld() throws Exception {
        HttpGet request = new HttpGet("http://localhost:9000/i18n/api/v1/hello-world");
        try (CloseableHttpClient client = HttpClients.createDefault();
             ClassicHttpResponse response = client.execute(request)) {
            String responseBody = response.getEntity() == null ? "" : EntityUtils.toString(response.getEntity(), StandardCharsets.UTF_8);
            log.info("english={}", responseBody);
        }

        HttpGet request1 = new HttpGet("http://localhost:9000/i18n/api/v1/hello-world");
        request1.setHeader("Accept-Language", "fr");
        try (CloseableHttpClient client = HttpClients.createDefault();
             ClassicHttpResponse response = client.execute(request1)) {
            String responseBody = response.getEntity() == null ? "" : EntityUtils.toString(response.getEntity(), StandardCharsets.UTF_8);
            log.info("french={}", responseBody);
        }
    }
    @Test
    void helloWorldWithName() throws Exception {
        HttpGet request = new HttpGet("http://localhost:9000/i18n/api/v1/hello-world/a");
        try (CloseableHttpClient client = HttpClients.createDefault();
             ClassicHttpResponse response = client.execute(request)) {
            String responseBody = response.getEntity() == null ? "" : EntityUtils.toString(response.getEntity(), StandardCharsets.UTF_8);
            log.info("english={}", responseBody);
        }

        HttpGet request1 = new HttpGet("http://localhost:9000/i18n/api/v1/hello-world/a");
        request1.setHeader("Accept-Language", "fr");
        try (CloseableHttpClient client = HttpClients.createDefault();
             ClassicHttpResponse response = client.execute(request1)) {
            String responseBody = response.getEntity() == null ? "" : EntityUtils.toString(response.getEntity(), StandardCharsets.UTF_8);
            log.info("french={}", responseBody);
        }
    }

}
