package com.java.rest.client.retrofit;

import com.java.model.Student;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.core.io.ClassPathResource;
import retrofit2.Response;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * Rest client with {@link retrofit2.Retrofit}
 * to {@link com.java.controller.impl.PutControllerImpl}
 */
@Slf4j
public class PutControllerTest {

    @Test
    void studentV1() throws IOException {
        PutClient client = RetrofitClients.target(PutClient.class);
        String studentJson = new ClassPathResource("student-v1.json").getContentAsString(StandardCharsets.UTF_8);
        log.info("student={}", client.studentV1(studentJson).execute().body());
    }

    @Test
    void studentV2() throws IOException {
        PutClient client = RetrofitClients.target(PutClient.class);
        Student request = Student.builder().name("a").book("b1").build();
        Response<Student> response = client.studentV2(request).execute();
        log.info("status={}, student={}", response.code(), response.body());
    }

    @Test
    void studentV3() throws IOException {
        PutClient client = RetrofitClients.target(PutClient.class);
        Student request = Student.builder().name("request-name").book("request-book").build();
        log.info("student={}", client.studentV3(request, "a", "b1").execute().body());
    }

    @Test
    void studentV4() throws IOException {
        PutClient client = RetrofitClients.target(PutClient.class);
        Student request = Student.builder().name("request-name").book("request-book").build();
        log.info("student={}", client.studentV4(request, "a", "b1").execute().body());
    }

    @Test
    void studentV5() throws IOException {
        PutClient client = RetrofitClients.target(PutClient.class);
        Student request = Student.builder().name("request-name").book("request-book").build();
        log.info("student={}", client.studentV5(request, "a", "b1").execute().body());
    }

    @Test
    void studentV6() throws IOException {
        PutClient client = RetrofitClients.target(PutClient.class);
        Student request = Student.builder().name("request-name").book("request-book").build();
        log.info("student={}", client.studentV6(request, "ABC123", "a", "b1").execute().body());
    }

}
