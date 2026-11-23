package com.java.rest.client.openfeign;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

/**
 * Rest client with {@link org.springframework.cloud.openfeign.FeignClient}
 * to {@link com.java.controller.impl.GetControllerImpl}
 */
@Slf4j
public class GetControllerTest {

    @Test
    void helloWorld() {
        GetClient client = OpenFeignClients.target(GetClient.class);
        log.info("response={}", client.helloWorld());
    }

    @Test
    void studentsV1() {
        GetClient client = OpenFeignClients.target(GetClient.class);
        log.info("student={}", client.studentsV1());
    }

    @Test
    void studentsV2() {
        GetClient client = OpenFeignClients.target(GetClient.class);
        var response = client.studentsV2();
        log.info("status={}, student={}", response.getStatusCode(), response.getBody());
    }

    @Test
    void studentsV3() {
        GetClient client = OpenFeignClients.target(GetClient.class);
        log.info("students={}", client.studentsV3());
    }

    @Test
    void studentsV4() {
        GetClient client = OpenFeignClients.target(GetClient.class);
        log.info("student={}", client.studentsV4("a", "b1"));
    }

    @Test
    void studentsV5() {
        GetClient client = OpenFeignClients.target(GetClient.class);
        log.info("student={}", client.studentsV5("a", "b1"));
    }

    @Test
    void studentsV6() {
        GetClient client = OpenFeignClients.target(GetClient.class);
        log.info("student={}", client.studentsV6("a", "b1"));
    }

    @Test
    void studentsV7() {
        GetClient client = OpenFeignClients.target(GetClient.class);
        log.info("students={}", client.studentsV7(0, 2, "asc"));
    }

    @Test
    void users1() {
        GetClient client = OpenFeignClients.target(GetClient.class);
        log.info("users={}", client.users1());
    }

}
