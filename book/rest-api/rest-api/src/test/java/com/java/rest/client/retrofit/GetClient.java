package com.java.rest.client.retrofit;

import com.java.model.Student;
import com.java.model.User;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;
import retrofit2.http.Query;

import java.util.List;

public interface GetClient {

    @GET("/get/api/v1/hello-world")
    Call<String> helloWorld();

    @GET("/get/api/v1/students")
    Call<Student> studentsV1();

    @GET("/get/api/v2/students")
    Call<Student> studentsV2();

    @GET("/get/api/v3/students")
    Call<List<Student>> studentsV3();

    @GET("/get/api/v4/students")
    Call<Student> studentsV4(@Header("name") String name, @Header("book") String book);

    @GET("/get/api/v5/students")
    Call<Student> studentsV5(@Query("name") String name, @Query("book") String book);

    @GET("/get/api/v6/students/{name}/{book}")
    Call<Student> studentsV6(@Path("name") String name, @Path("book") String book);

    @GET("/get/api/v7/students")
    Call<List<Student>> studentsV7(@Query("page") int page, @Query("size") int size, @Query("sort") String sort);

    @GET("/get/api/v1/users")
    Call<List<User>> users1();

}
