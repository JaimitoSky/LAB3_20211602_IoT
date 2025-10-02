package com.example.lab3_20211602_iot.domain.usecase;

import com.example.lab3_20211602_iot.core.Result;
import com.example.lab3_20211602_iot.core.SimpleCallback;
import com.example.lab3_20211602_iot.data.dto.LocationDto;
import com.example.lab3_20211602_iot.data.mapper.LocationMapper;
import com.example.lab3_20211602_iot.data.repository.WeatherRepository;
import com.example.lab3_20211602_iot.domain.model.LocationItem;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchLocationsUseCase {
    private final WeatherRepository repo;

    public SearchLocationsUseCase(WeatherRepository repo) {
        this.repo = repo;
    }

    public void execute(String query, SimpleCallback<List<LocationItem>> cb) {
        repo.searchLocations(query).enqueue(new Callback<List<LocationDto>>() {
            @Override
            public void onResponse(Call<List<LocationDto>> call, Response<List<LocationDto>> response) {
                if (response.isSuccessful()) {
                    cb.onResult(Result.success(LocationMapper.map(response.body())));
                } else {
                    cb.onResult(Result.failure(new RuntimeException("HTTP " + response.code())));
                }
            }

            @Override
            public void onFailure(Call<List<LocationDto>> call, Throwable t) {
                cb.onResult(Result.failure(t));
            }
        });
    }
}
