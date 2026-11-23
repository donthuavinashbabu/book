package com.java.rest.client.retrofit;

import com.java.model.Student;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

import java.util.List;

public interface HateoasClient {

    @GET("/hateoas/api/v1/students")
    Call<List<Student>> findAllStudents();

    @GET("/hateoas/api/v1/students/{id}")
    Call<Student> findStudentById(@Path("id") String id);

    @POST("/hateoas/api/v1/students")
    Call<Void> saveStudent(@Body Student student);

    @POST("/hateoas/api/v2/students")
    Call<String> saveStudents2(@Body Student student);

}
