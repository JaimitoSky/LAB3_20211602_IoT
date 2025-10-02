package com.example.lab3_20211602_iot.domain.usecase;

import com.example.lab3_20211602_iot.core.Result;
import com.example.lab3_20211602_iot.core.SimpleCallback;
import com.example.lab3_20211602_iot.data.dto.SportsDto;
import com.example.lab3_20211602_iot.data.mapper.SportsMapper;
import com.example.lab3_20211602_iot.data.repository.WeatherRepository;
import com.example.lab3_20211602_iot.domain.model.MatchItem;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GetFootballUseCase {
    private final WeatherRepository repo;

    public GetFootballUseCase(WeatherRepository repo) {
        this.repo = repo;
    }

    public void execute(String location, SimpleCallback<List<MatchItem>> cb) {
        repo.getSports(location).enqueue(new Callback<SportsDto>() {
            @Override
            public void onResponse(Call<SportsDto> call, Response<SportsDto> response) {
                if (response.isSuccessful()) {
                    cb.onResult(Result.success(SportsMapper.map(response.body())));
                } else {
                    cb.onResult(Result.failure(new RuntimeException("HTTP " + response.code())));
                }
            }

            @Override
            public void onFailure(Call<SportsDto> call, Throwable t) {
                cb.onResult(Result.failure(t));
            }
        });
    }
}
