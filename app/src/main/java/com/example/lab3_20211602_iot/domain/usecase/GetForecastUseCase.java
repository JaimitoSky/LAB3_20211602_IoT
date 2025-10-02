package com.example.lab3_20211602_iot.domain.usecase;

import com.example.lab3_20211602_iot.core.Result;
import com.example.lab3_20211602_iot.core.SimpleCallback;
import com.example.lab3_20211602_iot.data.dto.ForecastDto;
import com.example.lab3_20211602_iot.data.mapper.ForecastMapper;
import com.example.lab3_20211602_iot.data.repository.WeatherRepository;
import com.example.lab3_20211602_iot.domain.model.ForecastDayItem;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GetForecastUseCase {
    private final WeatherRepository repo;

    public GetForecastUseCase(WeatherRepository repo) {
        this.repo = repo;
    }

    public void execute(long idLocation, int days, SimpleCallback<List<ForecastDayItem>> cb) {
        int d = Math.max(1, Math.min(14, days));
        repo.getForecast(idLocation, d).enqueue(new Callback<ForecastDto>() {
            @Override
            public void onResponse(Call<ForecastDto> call, Response<ForecastDto> response) {
                if (response.isSuccessful()) {
                    cb.onResult(Result.success(ForecastMapper.map(response.body())));
                } else {
                    cb.onResult(Result.failure(new RuntimeException("HTTP " + response.code())));
                }
            }

            @Override
            public void onFailure(Call<ForecastDto> call, Throwable t) {
                cb.onResult(Result.failure(t));
            }
        });
    }
}
