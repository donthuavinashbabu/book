package com.java.rest.client.retrofit;

import com.java.model.Student;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface PutClient {

    @PUT("/put/api/v1/students")
    Call<Student> studentV1(@Body String studentJson);

    @PUT("/put/api/v2/students")
    Call<Student> studentV2(@Body Student student);

    @PUT("/put/api/v3/students")
    Call<Student> studentV3(@Body Student student, @Header("name") String name, @Header("book") String book);

    @PUT("/put/api/v4/students")
    Call<Student> studentV4(@Body Student student, @Query("name") String name, @Query("book") String book);

    @PUT("/put/api/v5/students/{name}/{book}")
    Call<Student> studentV5(@Body Student student, @Path("name") String name, @Path("book") String book);

    @PUT("/put/api/v6/students/{id}")
    Call<Student> studentV6(@Body Student student, @Path("id") String id, @Header("name") String name, @Query("book") String book);

}
