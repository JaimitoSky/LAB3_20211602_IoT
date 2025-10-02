package com.example.lab3_20211602_iot.ui.forecast;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.example.lab3_20211602_iot.databinding.FragmentForecastBinding;
import com.example.lab3_20211602_iot.domain.model.ForecastDayItem;
import com.example.lab3_20211602_iot.ui.common.UiState;
import com.example.lab3_20211602_iot.ui.forecast.adapter.ForecastAdapter;
import java.util.List;

public class ForecastFragment extends Fragment {
    private FragmentForecastBinding binding;
    private ForecastViewModel vm;
    private ForecastAdapter adapter;
    private long idLocation;
    private String name;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentForecastBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        vm = new ViewModelProvider(this).get(ForecastViewModel.class);
        adapter = new ForecastAdapter();
        binding.rvForecast.setLayoutManager(new LinearLayoutManager(requireContext()));
        binding.rvForecast.setAdapter(adapter);

        Bundle args = getArguments();
        idLocation = args != null ? args.getLong("idLocation", 0L) : 0L;
        name = args != null ? args.getString("name", "") : "";
        binding.tvLocation.setText(name);

        binding.btnCargar.setOnClickListener(v -> {
            String d = binding.etDays.getText().toString().trim();
            int days = TextUtils.isEmpty(d) ? 7 : Integer.parseInt(d);
            vm.load(idLocation, days);
        });

        vm.getState().observe(getViewLifecycleOwner(), s -> render(s));
    }

    private void render(UiState<List<ForecastDayItem>> s) {
        binding.progress.setVisibility(View.GONE);
        binding.tvEmpty.setVisibility(View.GONE);
        binding.tvError.setVisibility(View.GONE);
        binding.rvForecast.setVisibility(View.GONE);
        if (s.status == UiState.Status.LOADING) {
            binding.progress.setVisibility(View.VISIBLE);
        } else if (s.status == UiState.Status.SUCCESS) {
            adapter.submit(s.data);
            binding.rvForecast.setVisibility(View.VISIBLE);
        } else if (s.status == UiState.Status.EMPTY) {
            binding.tvEmpty.setVisibility(View.VISIBLE);
        } else if (s.status == UiState.Status.ERROR) {
            binding.tvError.setText(s.message == null ? "Error" : s.message);
            binding.tvError.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
