package com.java.rest.client.openfeign;

import com.java.controller.PostController.Employee;
import com.java.model.Student;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * Rest client with {@link org.springframework.cloud.openfeign.FeignClient}
 * to {@link com.java.controller.impl.PostControllerImpl}
 */
@Slf4j
public class PostControllerTest {

    @Test
    void studentV1() throws IOException {
        PostClient client = OpenFeignClients.target(PostClient.class);
        String studentJson = new ClassPathResource("student-v1.json").getContentAsString(StandardCharsets.UTF_8);
        log.info("student={}", client.studentV1(studentJson));
    }

    @Test
    void studentV2() {
        PostClient client = OpenFeignClients.target(PostClient.class);
        Student request = Student.builder().name("a").book("b1").build();
        var response = client.studentV2(request);
        log.info("status={}, student={}", response.getStatusCode(), response.getBody());
    }

    @Test
    void studentV3() {
        PostClient client = OpenFeignClients.target(PostClient.class);
        Student request = Student.builder().name("request-name").book("request-book").build();
        log.info("student={}", client.studentV3(request, "a", "b1"));
    }

    @Test
    void studentV4() {
        PostClient client = OpenFeignClients.target(PostClient.class);
        Student request = Student.builder().name("request-name").book("request-book").build();
        log.info("student={}", client.studentV4(request, "a", "b1"));
    }

    @Test
    void studentV5() {
        PostClient client = OpenFeignClients.target(PostClient.class);
        Student request = Student.builder().name("request-name").book("request-book").build();
        log.info("student={}", client.studentV5(request, "a", "b1"));
    }

    @Test
    void studentV6() {
        PostClient client = OpenFeignClients.target(PostClient.class);
        Student request = Student.builder().name("request-name").book("request-book").build();
        log.info("student={}", client.studentV6(request, "ABC123", "a", "b1"));
    }

    @Test
    void employee() {
        PostClient client = OpenFeignClients.target(PostClient.class);
        log.info("employee={}", client.employee(new Employee(1L, "a", "engineering")));
    }

}
