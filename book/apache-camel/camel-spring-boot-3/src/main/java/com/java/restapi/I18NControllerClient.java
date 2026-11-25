package com.java.restapi;

import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class I18NControllerClient {

    private final CamelHttpClient http;

    public I18NControllerClient(CamelHttpClient http) {
        this.http = http;
    }

    public String helloWorld() {
        return helloWorld(null);
    }

    public String helloWorld(String acceptLanguage) {
        return http.getText("/i18n/api/v1/hello-world", languageHeader(acceptLanguage));
    }

    public String helloWorldWithName(String name) {
        return helloWorldWithName(name, null);
    }

    public String helloWorldWithName(String name, String acceptLanguage) {
        return http.getText(http.path("/i18n/api/v1/hello-world/{name}", name), languageHeader(acceptLanguage));
    }

    private Map<String, String> languageHeader(String acceptLanguage) {
        if (acceptLanguage == null) {
            return null;
        }
        return Map.of(HttpHeaders.ACCEPT_LANGUAGE, acceptLanguage);
    }

}
