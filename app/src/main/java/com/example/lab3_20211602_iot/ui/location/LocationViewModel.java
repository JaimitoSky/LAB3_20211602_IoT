package com.example.lab3_20211602_iot.ui.location;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.example.lab3_20211602_iot.core.Result;
import com.example.lab3_20211602_iot.core.SimpleCallback;
import com.example.lab3_20211602_iot.data.ServiceLocator;
import com.example.lab3_20211602_iot.domain.model.LocationItem;
import com.example.lab3_20211602_iot.ui.common.UiState;
import java.util.List;

public class LocationViewModel extends ViewModel {
    private final MutableLiveData<UiState<List<LocationItem>>> state = new MutableLiveData<>(UiState.empty());
    public LiveData<UiState<List<LocationItem>>> getState() { return state; }

    public void search(String q) {
        state.setValue(UiState.loading());
        ServiceLocator.searchLocations().execute(q, new SimpleCallback<List<LocationItem>>() {
            @Override
            public void onResult(Result<List<LocationItem>> result) {
                if (result.isSuccess) {
                    List<LocationItem> data = result.data;
                    state.postValue(data == null || data.isEmpty() ? UiState.empty() : UiState.success(data));
                } else {
                    state.postValue(UiState.error(result.error != null ? result.error.getMessage() : "Error"));
                }
            }
        });
    }
}
