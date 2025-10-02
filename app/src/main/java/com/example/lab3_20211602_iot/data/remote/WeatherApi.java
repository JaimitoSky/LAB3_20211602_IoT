package com.example.lab3_20211602_iot.data.remote;

import com.example.lab3_20211602_iot.data.dto.ForecastDto;
import com.example.lab3_20211602_iot.data.dto.LocationDto;
import com.example.lab3_20211602_iot.data.dto.SportsDto;
import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface WeatherApi {
    @GET("v1/search.json")
    Call<List<LocationDto>> searchLocations(@Query("key") String key, @Query("q") String query);

    @GET("v1/forecast.json")
    Call<ForecastDto> getForecast(@Query("key") String key, @Query("q") String qParam, @Query("days") int days);

    @GET("v1/sports.json")
    Call<SportsDto> getSports(@Query("key") String key, @Query("q") String location);
}
