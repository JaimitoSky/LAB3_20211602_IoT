package com.example.lab3_20211602_iot;

import android.os.Bundle;
import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;
import com.example.lab3_20211602_iot.databinding.ActivityAppBinding;

public class AppActivity extends AppCompatActivity {
    ActivityAppBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAppBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        NavController navController = Navigation.findNavController(this, R.id.nav_host);
        NavigationUI.setupWithNavController(binding.bottomNav, navController);

        getOnBackPressedDispatcher().addCallback(this, new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                if (!navController.popBackStack()) finish();
            }
        });
    }
}
