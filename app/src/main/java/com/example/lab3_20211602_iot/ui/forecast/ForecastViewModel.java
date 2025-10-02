package com.example.lab3_20211602_iot.ui.forecast;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.example.lab3_20211602_iot.core.Result;
import com.example.lab3_20211602_iot.core.SimpleCallback;
import com.example.lab3_20211602_iot.data.ServiceLocator;
import com.example.lab3_20211602_iot.domain.model.ForecastDayItem;
import com.example.lab3_20211602_iot.ui.common.UiState;
import java.util.ArrayList;
import java.util.List;

public class ForecastViewModel extends ViewModel {
    private final MutableLiveData<UiState<List<ForecastDayItem>>> state = new MutableLiveData<>(UiState.empty());
    private List<ForecastDayItem> lastLoaded = new ArrayList<>();

    public LiveData<UiState<List<ForecastDayItem>>> getState() { return state; }

    public void load(long idLocation, int days) {
        state.setValue(UiState.loading());
        ServiceLocator.getForecast().execute(idLocation, days, new SimpleCallback<List<ForecastDayItem>>() {
            @Override
            public void onResult(Result<List<ForecastDayItem>> result) {
                if (result.isSuccess) {
                    List<ForecastDayItem> d = result.data;
                    lastLoaded = d == null ? new ArrayList<>() : new ArrayList<>(d);
                    state.postValue(d == null || d.isEmpty() ? UiState.empty() : UiState.success(d));
                } else {
                    state.postValue(UiState.error(result.error != null ? result.error.getMessage() : "Error"));
                }
            }
        });
    }

    public void clearCurrent() {
        state.setValue(UiState.empty());
    }

    public void restoreLast() {
        if (lastLoaded == null || lastLoaded.isEmpty()) state.setValue(UiState.empty());
        else state.setValue(UiState.success(new ArrayList<>(lastLoaded)));
    }
}
