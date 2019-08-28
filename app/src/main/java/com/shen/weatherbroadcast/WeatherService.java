package com.shen.weatherbroadcast;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface WeatherService {
    @GET("query")
    Call<Weather> getWeather(@Query("city") String city, @Query("key") String key);
}
