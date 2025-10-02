package com.example.lab3_20211602_iot.ui.sports;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.example.lab3_20211602_iot.core.Result;
import com.example.lab3_20211602_iot.core.SimpleCallback;
import com.example.lab3_20211602_iot.data.ServiceLocator;
import com.example.lab3_20211602_iot.domain.model.MatchItem;
import com.example.lab3_20211602_iot.ui.common.UiState;
import java.util.List;

public class SportsViewModel extends ViewModel {
    private final MutableLiveData<UiState<List<MatchItem>>> state = new MutableLiveData<>(UiState.empty());
    public LiveData<UiState<List<MatchItem>>> getState() { return state; }

    public void search(String loc) {
        state.setValue(UiState.loading());
        ServiceLocator.getFootball().execute(loc, new SimpleCallback<List<MatchItem>>() {
            @Override
            public void onResult(Result<List<MatchItem>> result) {
                if (result.isSuccess) {
                    List<MatchItem> data = result.data;
                    state.postValue(data == null || data.isEmpty() ? UiState.empty() : UiState.success(data));
                } else {
                    state.postValue(UiState.error(result.error != null ? result.error.getMessage() : "Error"));
                }
            }
        });
    }
}
