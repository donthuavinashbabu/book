package com.java.rest.client.openfeign;

import com.java.model.Student;
import feign.Response;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.io.IOException;

/**
 * Rest client with {@link org.springframework.cloud.openfeign.FeignClient}
 * to {@link com.java.controller.impl.HateoasControllerImpl}
 */
@Slf4j
public class HateoasControllerTest {

    @Test
    void findAllStudents() {
        HateoasClient client = OpenFeignClients.target(HateoasClient.class);
        log.info("students={}", client.findAllStudents());
    }

    @Test
    void findStudentById() {
        HateoasClient client = OpenFeignClients.target(HateoasClient.class);
        log.info("student={}", client.findStudentById("1"));
    }

    @Test
    void saveStudent() throws IOException {
        HateoasClient client = OpenFeignClients.target(HateoasClient.class);
        Student request = Student.builder().name("a").book("b1").build();
        try (Response response = client.saveStudent(request)) {
            log.info("status={}, location={}", response.status(), response.headers().get("location"));
        }
    }

    @Test
    void saveStudents2() {
        HateoasClient client = OpenFeignClients.target(HateoasClient.class);
        Student request = Student.builder().name("a").book("b1").build();
        log.info("body={}", client.saveStudents2(request));
    }

}
