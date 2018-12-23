package com.example.user.gismeteomobile;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface GismeteoApi {
    @GET("weather.ashx")
    Call<Weather> getWeather(@Query("q") String place, @Query("key") String key, @Query("format") String format, @Query("num_of_days") String days);

}
