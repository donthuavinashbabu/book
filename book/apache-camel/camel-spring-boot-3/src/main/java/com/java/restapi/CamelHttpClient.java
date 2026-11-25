package com.java.restapi;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.java.restapi.model.CreatedResponse;
import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.ProducerTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriUtils;

import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.regex.Matcher;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.http.MediaType.TEXT_PLAIN_VALUE;

/**
 * Calls rest-api through the Camel HTTP component.
 * Query strings go in {@link Exchange#HTTP_QUERY} so Camel does not read them as endpoint options.
 */
@Component
public class CamelHttpClient {

    private final ProducerTemplate producerTemplate;
    private final ObjectMapper objectMapper;
    private final String endpointUri;
    private final String basePath;

    public CamelHttpClient(ProducerTemplate producerTemplate,
                           ObjectMapper objectMapper,
                           @Value("${app.rest-api.base-url:http://localhost:9000}") String baseUrl) {
        this.producerTemplate = producerTemplate;
        this.objectMapper = objectMapper;

        String normalized = stripTrailingSlash(baseUrl.trim());
        URI uri = URI.create(normalized);
        String path = uri.getRawPath() == null ? "" : uri.getRawPath();
        this.basePath = "/".equals(path) ? "" : path;
        this.endpointUri = uri.getScheme() + "://" + uri.getRawAuthority()
                + "?throwExceptionOnFailure=true&deleteWithBody=true";
    }

    public String path(String template, String... values) {
        String resolved = template;
        for (String value : values) {
            String encoded = UriUtils.encodePathSegment(value, StandardCharsets.UTF_8);
            resolved = resolved.replaceFirst("\\{[^}]+}", Matcher.quoteReplacement(encoded));
        }
        return resolved;
    }

    public String getText(String path, Map<String, String> headers) {
        return body(send("GET", path, null, null, TEXT_PLAIN_VALUE, headers, null));
    }

    public <T> T get(String path, Class<T> type, Map<String, String> headers, Map<String, String> query) {
        return get(path, type, APPLICATION_JSON_VALUE, headers, query);
    }

    public <T> T get(String path, Class<T> type, String accept, Map<String, String> headers, Map<String, String> query) {
        return read(body(send("GET", path, null, null, accept, headers, query)), type, path);
    }

    public <T> T get(String path, TypeReference<T> type, Map<String, String> headers, Map<String, String> query) {
        return read(body(send("GET", path, null, null, APPLICATION_JSON_VALUE, headers, query)), type, path);
    }

    public <T> T postJson(String path, Object requestBody, Class<T> type, Map<String, String> headers, Map<String, String> query) {
        return read(body(send("POST", path, write(requestBody), APPLICATION_JSON_VALUE, APPLICATION_JSON_VALUE, headers, query)), type, path);
    }

    public <T> T postJson(String path, Object requestBody, TypeReference<T> type, Map<String, String> headers, Map<String, String> query) {
        return read(body(send("POST", path, write(requestBody), APPLICATION_JSON_VALUE, APPLICATION_JSON_VALUE, headers, query)), type, path);
    }

    public <T> T postText(String path, String requestBody, Class<T> type) {
        return read(body(send("POST", path, requestBody, TEXT_PLAIN_VALUE, APPLICATION_JSON_VALUE, null, null)), type, path);
    }

    public CreatedResponse postCreated(String path, Object requestBody) {
        Exchange exchange = send("POST", path, write(requestBody), APPLICATION_JSON_VALUE, APPLICATION_JSON_VALUE, null, null);
        Message message = exchange.getMessage();
        Integer status = message.getHeader(Exchange.HTTP_RESPONSE_CODE, Integer.class);
        Object location = message.getHeader("Location");
        return new CreatedResponse(status == null ? 0 : status, location == null ? null : location.toString());
    }

    public <T> T putJson(String path, Object requestBody, Class<T> type, Map<String, String> headers, Map<String, String> query) {
        return read(body(send("PUT", path, write(requestBody), APPLICATION_JSON_VALUE, APPLICATION_JSON_VALUE, headers, query)), type, path);
    }

    public <T> T putText(String path, String requestBody, Class<T> type) {
        return read(body(send("PUT", path, requestBody, TEXT_PLAIN_VALUE, APPLICATION_JSON_VALUE, null, null)), type, path);
    }

    public <T> T deleteJson(String path, Object requestBody, Class<T> type, Map<String, String> headers, Map<String, String> query) {
        return read(body(send("DELETE", path, write(requestBody), APPLICATION_JSON_VALUE, APPLICATION_JSON_VALUE, headers, query)), type, path);
    }

    public <T> T deleteText(String path, String requestBody, Class<T> type) {
        return read(body(send("DELETE", path, requestBody, TEXT_PLAIN_VALUE, APPLICATION_JSON_VALUE, null, null)), type, path);
    }

    private Exchange send(String method,
                          String path,
                          String requestBody,
                          String contentType,
                          String accept,
                          Map<String, String> headers,
                          Map<String, String> query) {
        String requestPath = fullPath(path);
        Exchange exchange = producerTemplate.request(endpointUri, callback -> {
            Message in = callback.getIn();
            in.setHeader(Exchange.HTTP_METHOD, method);
            in.setHeader(Exchange.HTTP_PATH, requestPath);
            if (query != null && !query.isEmpty()) {
                in.setHeader(Exchange.HTTP_QUERY, encodeQuery(query));
            }
            if (headers != null) {
                headers.forEach(in::setHeader);
            }
            if (contentType != null) {
                in.setHeader(Exchange.CONTENT_TYPE, contentType);
            }
            if (accept != null) {
                in.setHeader("Accept", accept);
            }
            if (requestBody != null) {
                in.setBody(requestBody);
            }
        });
        return exchange;
    }

    private String fullPath(String path) {
        if (path == null || path.isBlank()) {
            throw new RestApiException("Request path is required");
        }
        String suffix = path.startsWith("/") ? path : "/" + path;
        return basePath + suffix;
    }

    private String encodeQuery(Map<String, String> query) {
        StringBuilder builder = new StringBuilder();
        query.forEach((key, value) -> {
            if (value == null) {
                return;
            }
            if (!builder.isEmpty()) {
                builder.append('&');
            }
            builder.append(UriUtils.encodeQueryParam(key, StandardCharsets.UTF_8));
            builder.append('=');
            builder.append(UriUtils.encodeQueryParam(value, StandardCharsets.UTF_8));
        });
        return builder.toString();
    }

    private String write(Object value) {
        try {
            return objectMapper.writeValueAsString(value);
        } catch (JsonProcessingException exception) {
            throw new RestApiException("Failed to write request body", exception);
        }
    }

    private String body(Exchange exchange) {
        Object response = exchange.getMessage().getBody();
        if (response == null) {
            return "";
        }
        String text = exchange.getMessage().getBody(String.class);
        return text == null ? "" : text;
    }

    private <T> T read(String responseBody, Class<T> type, String path) {
        if (responseBody.isBlank()) {
            throw new RestApiException("Empty response body from " + path);
        }
        try {
            return objectMapper.readValue(responseBody, type);
        } catch (JsonProcessingException exception) {
            throw new RestApiException("Failed to read response from " + path + ": " + excerpt(responseBody), exception);
        }
    }

    private <T> T read(String responseBody, TypeReference<T> type, String path) {
        if (responseBody.isBlank()) {
            throw new RestApiException("Empty response body from " + path);
        }
        try {
            return objectMapper.readValue(responseBody, type);
        } catch (JsonProcessingException exception) {
            throw new RestApiException("Failed to read response from " + path + ": " + excerpt(responseBody), exception);
        }
    }

    private String excerpt(String responseBody) {
        return responseBody.length() <= 200 ? responseBody : responseBody.substring(0, 200);
    }

    private static String stripTrailingSlash(String value) {
        String normalized = value;
        while (normalized.endsWith("/")) {
            normalized = normalized.substring(0, normalized.length() - 1);
        }
        return normalized;
    }

}
