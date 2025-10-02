package com.example.lab3_20211602_iot.ui.forecast.adapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.lab3_20211602_iot.R;
import com.example.lab3_20211602_iot.domain.model.ForecastDayItem;
import java.util.ArrayList;
import java.util.List;

public class ForecastAdapter extends RecyclerView.Adapter<ForecastAdapter.VH> {
    private final List<ForecastDayItem> data = new ArrayList<>();

    @SuppressLint("NotifyDataSetChanged")
    public void submit(List<ForecastDayItem> d) {
        data.clear();
        if (d != null) data.addAll(d);
        notifyDataSetChanged();
    }

    @NonNull @Override public VH onCreateViewHolder(@NonNull ViewGroup p, int v) {
        View view = LayoutInflater.from(p.getContext()).inflate(R.layout.item_forecast, p, false);
        return new VH(view);
    }

    @Override public void onBindViewHolder(@NonNull VH h, int pos) {
        ForecastDayItem it = data.get(pos);
        h.tvDate.setText(it.date);
        h.tvMax.setText(String.valueOf(it.maxTempC));
        h.tvMin.setText(String.valueOf(it.minTempC));
        h.tvCond.setText(it.conditionText);
    }

    @Override public int getItemCount() { return data.size(); }

    static class VH extends RecyclerView.ViewHolder {
        TextView tvDate,tvMax,tvMin,tvCond;
        VH(@NonNull View itemView) {
            super(itemView);
            tvDate = itemView.findViewById(R.id.tvDate);
            tvMax = itemView.findViewById(R.id.tvMax);
            tvMin = itemView.findViewById(R.id.tvMin);
            tvCond = itemView.findViewById(R.id.tvCond);
        }
    }
}
