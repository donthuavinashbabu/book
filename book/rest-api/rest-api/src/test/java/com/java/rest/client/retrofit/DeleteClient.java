package com.java.rest.client.retrofit;

import com.java.model.Student;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.HTTP;
import retrofit2.http.Header;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface DeleteClient {

    @HTTP(method = "DELETE", path = "/delete/api/v1/students", hasBody = true)
    Call<Student> studentV1(@Body String studentJson);

    @HTTP(method = "DELETE", path = "/delete/api/v2/students", hasBody = true)
    Call<Student> studentV2(@Body Student student);

    @HTTP(method = "DELETE", path = "/delete/api/v3/students", hasBody = true)
    Call<Student> studentV3(@Body Student student, @Header("name") String name, @Header("book") String book);

    @HTTP(method = "DELETE", path = "/delete/api/v4/students", hasBody = true)
    Call<Student> studentV4(@Body Student student, @Query("name") String name, @Query("book") String book);

    @HTTP(method = "DELETE", path = "/delete/api/v5/students/{name}/{book}", hasBody = true)
    Call<Student> studentV5(@Body Student student, @Path("name") String name, @Path("book") String book);

    @HTTP(method = "DELETE", path = "/delete/api/v6/students/{id}", hasBody = true)
    Call<Student> studentV6(@Body Student student, @Path("id") String id, @Header("name") String name, @Query("book") String book);

}
