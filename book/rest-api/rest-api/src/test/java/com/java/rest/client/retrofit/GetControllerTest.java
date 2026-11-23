package com.java.rest.client.retrofit;

import com.java.model.Student;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import retrofit2.Response;

import java.io.IOException;

/**
 * Rest client with {@link retrofit2.Retrofit}
 * to {@link com.java.controller.impl.GetControllerImpl}
 */
@Slf4j
public class GetControllerTest {

    @Test
    void helloWorld() throws IOException {
        GetClient client = RetrofitClients.target(GetClient.class);
        log.info("response={}", client.helloWorld().execute().body());
    }

    @Test
    void studentsV1() throws IOException {
        GetClient client = RetrofitClients.target(GetClient.class);
        log.info("student={}", client.studentsV1().execute().body());
    }

    @Test
    void studentsV2() throws IOException {
        GetClient client = RetrofitClients.target(GetClient.class);
        Response<Student> response = client.studentsV2().execute();
        log.info("status={}, student={}", response.code(), response.body());
    }

    @Test
    void studentsV3() throws IOException {
        GetClient client = RetrofitClients.target(GetClient.class);
        log.info("students={}", client.studentsV3().execute().body());
    }

    @Test
    void studentsV4() throws IOException {
        GetClient client = RetrofitClients.target(GetClient.class);
        log.info("student={}", client.studentsV4("a", "b1").execute().body());
    }

    @Test
    void studentsV5() throws IOException {
        GetClient client = RetrofitClients.target(GetClient.class);
        log.info("student={}", client.studentsV5("a", "b1").execute().body());
    }

    @Test
    void studentsV6() throws IOException {
        GetClient client = RetrofitClients.target(GetClient.class);
        log.info("student={}", client.studentsV6("a", "b1").execute().body());
    }

    @Test
    void studentsV7() throws IOException {
        GetClient client = RetrofitClients.target(GetClient.class);
        log.info("students={}", client.studentsV7(0, 2, "asc").execute().body());
    }

    @Test
    void users1() throws IOException {
        GetClient client = RetrofitClients.target(GetClient.class);
        log.info("users={}", client.users1().execute().body());
    }

}
