package com.java.rest.client.retrofit;

import com.java.model.Student;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import retrofit2.Response;

import java.io.IOException;

/**
 * Rest client with {@link retrofit2.Retrofit}
 * to {@link com.java.controller.impl.HateoasControllerImpl}
 */
@Slf4j
public class HateoasControllerTest {

    @Test
    void findAllStudents() throws IOException {
        HateoasClient client = RetrofitClients.target(HateoasClient.class);
        log.info("students={}", client.findAllStudents().execute().body());
    }

    @Test
    void findStudentById() throws IOException {
        HateoasClient client = RetrofitClients.target(HateoasClient.class);
        log.info("student={}", client.findStudentById("1").execute().body());
    }

    @Test
    void saveStudent() throws IOException {
        HateoasClient client = RetrofitClients.target(HateoasClient.class);
        Student request = Student.builder().name("a").book("b1").build();
        Response<Void> response = client.saveStudent(request).execute();
        log.info("status={}, location={}", response.code(), response.headers().get("Location"));
    }

    @Test
    void saveStudents2() throws IOException {
        HateoasClient client = RetrofitClients.target(HateoasClient.class);
        Student request = Student.builder().name("a").book("b1").build();
        log.info("body={}", client.saveStudents2(request).execute().body());
    }

}
