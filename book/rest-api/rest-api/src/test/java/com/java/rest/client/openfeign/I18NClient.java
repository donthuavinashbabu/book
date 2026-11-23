package com.java.rest.client.openfeign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "i18nClient", url = "http://localhost:9000")
public interface I18NClient {

    @GetMapping("/i18n/api/v1/hello-world")
    String helloWorld();

    @GetMapping("/i18n/api/v1/hello-world")
    String helloWorld(@RequestHeader("Accept-Language") String language);

    @GetMapping("/i18n/api/v1/hello-world/{name}")
    String helloWorldWithName(@PathVariable("name") String name);

    @GetMapping("/i18n/api/v1/hello-world/{name}")
    String helloWorldWithName(@PathVariable("name") String name, @RequestHeader("Accept-Language") String language);

}
