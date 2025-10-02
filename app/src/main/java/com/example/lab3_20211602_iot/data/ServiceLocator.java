package com.example.lab3_20211602_iot.data;

import com.example.lab3_20211602_iot.data.repository.WeatherRepository;
import com.example.lab3_20211602_iot.data.repository.WeatherRepositoryImpl;
import com.example.lab3_20211602_iot.domain.usecase.GetFootballUseCase;
import com.example.lab3_20211602_iot.domain.usecase.GetForecastUseCase;
import com.example.lab3_20211602_iot.domain.usecase.SearchLocationsUseCase;

public class ServiceLocator {
    private static WeatherRepository repo;
    private static SearchLocationsUseCase searchUC;
    private static GetForecastUseCase forecastUC;
    private static GetFootballUseCase footballUC;

    public static WeatherRepository repo() {
        if (repo == null) repo = new WeatherRepositoryImpl();
        return repo;
    }

    public static SearchLocationsUseCase searchLocations() {
        if (searchUC == null) searchUC = new SearchLocationsUseCase(repo());
        return searchUC;
    }

    public static GetForecastUseCase getForecast() {
        if (forecastUC == null) forecastUC = new GetForecastUseCase(repo());
        return forecastUC;
    }

    public static GetFootballUseCase getFootball() {
        if (footballUC == null) footballUC = new GetFootballUseCase(repo());
        return footballUC;
    }
}
