package com.example.lab3_20211602_iot;

import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkCapabilities;
import android.os.Bundle;
import android.provider.Settings;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import com.example.lab3_20211602_iot.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.btnIngresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isOnline()) {
                    startActivity(new Intent(MainActivity.this, AppActivity.class));
                } else {
                    new AlertDialog.Builder(MainActivity.this)
                            .setTitle("Sin conexión")
                            .setMessage("Activa Internet para continuar")
                            .setPositiveButton("Configuración", (d, w) -> startActivity(new Intent(Settings.ACTION_SETTINGS)))
                            .setNegativeButton("Cancelar", null)
                            .show();
                }
            }
        });
    }

    private boolean isOnline() {
        ConnectivityManager cm = getSystemService(ConnectivityManager.class);
        if (cm == null || cm.getActiveNetwork() == null) return false;
        NetworkCapabilities caps = cm.getNetworkCapabilities(cm.getActiveNetwork());
        return caps != null && (caps.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)
                || caps.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)
                || caps.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET));
    }
}
