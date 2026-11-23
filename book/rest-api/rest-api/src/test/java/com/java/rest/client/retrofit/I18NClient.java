package com.java.rest.client.retrofit;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;

public interface I18NClient {

    @GET("/i18n/api/v1/hello-world")
    Call<String> helloWorld();

    @GET("/i18n/api/v1/hello-world")
    Call<String> helloWorld(@Header("Accept-Language") String language);

    @GET("/i18n/api/v1/hello-world/{name}")
    Call<String> helloWorldWithName(@Path("name") String name);

    @GET("/i18n/api/v1/hello-world/{name}")
    Call<String> helloWorldWithName(@Path("name") String name, @Header("Accept-Language") String language);

}
