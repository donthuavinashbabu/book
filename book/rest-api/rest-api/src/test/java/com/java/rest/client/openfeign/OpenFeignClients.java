package com.java.rest.client.openfeign;

import feign.Feign;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.cloud.openfeign.support.ResponseEntityDecoder;
import org.springframework.cloud.openfeign.support.SpringDecoder;
import org.springframework.cloud.openfeign.support.SpringEncoder;
import org.springframework.cloud.openfeign.support.SpringMvcContract;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

import java.nio.charset.StandardCharsets;

/**
 * Builds a Spring Cloud OpenFeign client without starting a Spring context.
 * The interface still uses {@link org.springframework.cloud.openfeign.FeignClient}
 * and Spring MVC mapping annotations.
 */
public final class OpenFeignClients {

    private OpenFeignClients() {
    }

    public static <T> T target(Class<T> type) {
        ObjectFactory<HttpMessageConverters> converters = () -> new HttpMessageConverters(
                new StringHttpMessageConverter(StandardCharsets.UTF_8),
                new MappingJackson2HttpMessageConverter());
        return Feign.builder()
                .contract(new SpringMvcContract())
                .encoder(new SpringEncoder(converters))
                .decoder(new ResponseEntityDecoder(new SpringDecoder(converters)))
                .target(type, "http://localhost:9000");
    }

}
