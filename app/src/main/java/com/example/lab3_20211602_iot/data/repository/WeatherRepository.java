package com.example.lab3_20211602_iot.data.repository;

import com.example.lab3_20211602_iot.data.dto.ForecastDto;
import com.example.lab3_20211602_iot.data.dto.LocationDto;
import com.example.lab3_20211602_iot.data.dto.SportsDto;
import java.util.List;
import retrofit2.Call;

public interface WeatherRepository {
    Call<List<LocationDto>> searchLocations(String query);
    Call<ForecastDto> getForecast(long idLocation, int days);
    Call<SportsDto> getSports(String location);
}
