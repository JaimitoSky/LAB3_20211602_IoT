package com.example.lab3_20211602_iot.ui.sports;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.example.lab3_20211602_iot.databinding.FragmentSportsBinding;
import com.example.lab3_20211602_iot.domain.model.MatchItem;
import com.example.lab3_20211602_iot.ui.common.UiState;
import com.example.lab3_20211602_iot.ui.sports.adapter.SportsAdapter;
import java.util.List;

public class SportsFragment extends Fragment {
    private FragmentSportsBinding binding;
    private SportsViewModel vm;
    private SportsAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentSportsBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        vm = new ViewModelProvider(this).get(SportsViewModel.class);
        adapter = new SportsAdapter();
        binding.rvSports.setLayoutManager(new LinearLayoutManager(requireContext()));
        binding.rvSports.setAdapter(adapter);

        binding.btnBuscarSports.setOnClickListener(v -> vm.search(binding.etCity.getText().toString().trim()));

        vm.getState().observe(getViewLifecycleOwner(), s -> render(s));
    }

    private void render(UiState<List<MatchItem>> s) {
        binding.progress.setVisibility(View.GONE);
        binding.tvEmpty.setVisibility(View.GONE);
        binding.tvError.setVisibility(View.GONE);
        binding.rvSports.setVisibility(View.GONE);
        if (s.status == UiState.Status.LOADING) {
            binding.progress.setVisibility(View.VISIBLE);
        } else if (s.status == UiState.Status.SUCCESS) {
            adapter.submit(s.data);
            binding.rvSports.setVisibility(View.VISIBLE);
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
