package com.java.rest.client.retrofit;

import com.java.model.Student;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;

public interface VersioningClient {

    @GET("/versioning/api/v1/students")
    Call<Student> urlVersioning1();

    @GET("/versioning/api/v2/students")
    Call<Student> urlVersioning2();

    @GET("/versioning/api/students")
    Call<Student> requestParamVersioning(@Query("VERSION") String version);

    @GET("/versioning/api/students")
    Call<Student> headerVersioning(@Header("X-API-VERSION") String version, @Header("Accept") String accept);

    @GET("/versioning/api/students")
    Call<Student> mediaTypeVersioning(@Header("Accept") String accept);

}
