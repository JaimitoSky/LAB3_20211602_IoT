package com.example.lab3_20211602_iot.data.repository;

import com.example.lab3_20211602_iot.core.Constants;
import com.example.lab3_20211602_iot.data.dto.ForecastDto;
import com.example.lab3_20211602_iot.data.dto.LocationDto;
import com.example.lab3_20211602_iot.data.dto.SportsDto;
import com.example.lab3_20211602_iot.data.remote.NetworkClient;
import com.example.lab3_20211602_iot.data.remote.WeatherApi;
import java.util.List;
import retrofit2.Call;

public class WeatherRepositoryImpl implements WeatherRepository {
    private final WeatherApi api;

    public WeatherRepositoryImpl() {
        this.api = NetworkClient.get().create(WeatherApi.class);
    }

    @Override
    public Call<List<LocationDto>> searchLocations(String query) {
        return api.searchLocations(Constants.API_KEY, query);
    }

    @Override
    public Call<ForecastDto> getForecast(long idLocation, int days) {
        String q = "id:" + idLocation;
        return api.getForecast(Constants.API_KEY, q, days);
    }

    @Override
    public Call<SportsDto> getSports(String location) {
        return api.getSports(Constants.API_KEY, location);
    }
}
