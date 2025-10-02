package com.example.lab3_20211602_iot.ui.location;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.example.lab3_20211602_iot.R;
import com.example.lab3_20211602_iot.databinding.FragmentLocationBinding;
import com.example.lab3_20211602_iot.domain.model.LocationItem;
import com.example.lab3_20211602_iot.storage.Preferences;
import com.example.lab3_20211602_iot.ui.common.UiState;
import com.example.lab3_20211602_iot.ui.location.adapter.LocationAdapter;
import java.util.List;

public class LocationFragment extends Fragment {
    private FragmentLocationBinding binding;
    private LocationViewModel vm;
    private LocationAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentLocationBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        vm = new ViewModelProvider(this).get(LocationViewModel.class);
        adapter = new LocationAdapter(new LocationAdapter.OnClick() {
            @Override
            public void onItem(LocationItem item) {
                Preferences.setLastLocationId(requireContext(), item.id);
                Preferences.setLastLocationName(requireContext(), item.name);
                Bundle b = new Bundle();
                b.putLong("idLocation", item.id);
                b.putString("name", item.name);
                NavController nc = NavHostFragment.findNavController(LocationFragment.this);
                nc.navigate(R.id.forecastFragment, b);
            }
        });
        binding.rvLocations.setLayoutManager(new LinearLayoutManager(requireContext()));
        binding.rvLocations.setAdapter(adapter);

        String last = Preferences.getLastQuery(requireContext());
        binding.etQuery.setText(last);

        binding.btnBuscar.setOnClickListener(v -> {
            String q = binding.etQuery.getText().toString().trim();
            Preferences.setLastQuery(requireContext(), q);
            vm.search(q);
        });

        vm.getState().observe(getViewLifecycleOwner(), s -> render(s));
    }

    private void render(UiState<List<LocationItem>> s) {
        binding.progress.setVisibility(View.GONE);
        binding.tvEmpty.setVisibility(View.GONE);
        binding.tvError.setVisibility(View.GONE);
        binding.rvLocations.setVisibility(View.GONE);
        if (s.status == UiState.Status.LOADING) {
            binding.progress.setVisibility(View.VISIBLE);
        } else if (s.status == UiState.Status.SUCCESS) {
            adapter.submit(s.data);
            binding.rvLocations.setVisibility(View.VISIBLE);
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
