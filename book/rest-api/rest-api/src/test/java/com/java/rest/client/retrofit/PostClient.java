package com.java.rest.client.retrofit;

import com.java.controller.PostController.Employee;
import com.java.model.Student;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface PostClient {

    @POST("/post/api/v1/students")
    Call<Student> studentV1(@Body String studentJson);

    @POST("/post/api/v2/students")
    Call<Student> studentV2(@Body Student student);

    @POST("/post/api/v3/students")
    Call<Student> studentV3(@Body Student student, @Header("name") String name, @Header("book") String book);

    @POST("/post/api/v4/students")
    Call<Student> studentV4(@Body Student student, @Query("name") String name, @Query("book") String book);

    @POST("/post/api/v5/students/{name}/{book}")
    Call<Student> studentV5(@Body Student student, @Path("name") String name, @Path("book") String book);

    @POST("/post/api/v6/students/{id}")
    Call<Student> studentV6(@Body Student student, @Path("id") String id, @Header("name") String name, @Query("book") String book);

    @POST("/post/api/v1/employee")
    Call<Employee> employee(@Body Employee employee);

}
