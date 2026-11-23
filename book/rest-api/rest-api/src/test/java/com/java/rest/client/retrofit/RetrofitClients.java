package com.java.rest.client.retrofit;

import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public final class RetrofitClients {

    private RetrofitClients() {
    }

    public static <T> T target(Class<T> type) {
        return new Retrofit.Builder()
                .baseUrl("http://localhost:9000")
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(JacksonConverterFactory.create())
                .build()
                .create(type);
    }

}
