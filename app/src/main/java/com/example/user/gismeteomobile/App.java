package com.example.user.gismeteomobile;

import android.app.Application;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class App extends Application {

    private Retrofit retrofit;
    private static GismeteoApi gismeteoApi;

    private final String BASE_URL = "https://api.worldweatheronline.com/premium/v1/";


    public static GismeteoApi getGismeteoApi() {
        return gismeteoApi;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        gismeteoApi = retrofit.create(GismeteoApi.class);



    }
}
