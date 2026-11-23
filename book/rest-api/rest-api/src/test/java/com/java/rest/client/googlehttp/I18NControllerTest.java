package com.java.rest.client.googlehttp;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestFactory;
import com.google.api.client.http.HttpResponse;
import com.google.api.client.http.javanet.NetHttpTransport;

/**
 * Rest client with {@link com.google.api.client.http.HttpRequestFactory}
 * to {@link com.java.controller.impl.I18NControllerImpl}
 */
@Slf4j
public class I18NControllerTest {

    @Test
    void helloWorld() throws Exception {
        HttpRequestFactory requestFactory = new NetHttpTransport().createRequestFactory();
        HttpRequest request = requestFactory.buildGetRequest(new GenericUrl("http://localhost:9000/i18n/api/v1/hello-world"));
        HttpResponse response = request.execute();
        try {
            log.info("english={}", response.parseAsString());
        } finally {
            response.disconnect();
        }

        HttpRequestFactory requestFactory1 = new NetHttpTransport().createRequestFactory();
        HttpRequest request1 = requestFactory1.buildGetRequest(new GenericUrl("http://localhost:9000/i18n/api/v1/hello-world"));
        request1.getHeaders().set("Accept-Language", "fr");
        HttpResponse response1 = request1.execute();
        try {
            log.info("french={}", response1.parseAsString());
        } finally {
            response1.disconnect();
        }
    }
    @Test
    void helloWorldWithName() throws Exception {
        HttpRequestFactory requestFactory = new NetHttpTransport().createRequestFactory();
        HttpRequest request = requestFactory.buildGetRequest(new GenericUrl("http://localhost:9000/i18n/api/v1/hello-world/a"));
        HttpResponse response = request.execute();
        try {
            log.info("english={}", response.parseAsString());
        } finally {
            response.disconnect();
        }

        HttpRequestFactory requestFactory1 = new NetHttpTransport().createRequestFactory();
        HttpRequest request1 = requestFactory1.buildGetRequest(new GenericUrl("http://localhost:9000/i18n/api/v1/hello-world/a"));
        request1.getHeaders().set("Accept-Language", "fr");
        HttpResponse response1 = request1.execute();
        try {
            log.info("french={}", response1.parseAsString());
        } finally {
            response1.disconnect();
        }
    }

}
