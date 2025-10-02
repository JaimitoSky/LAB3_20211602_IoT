package com.example.lab3_20211602_iot.ui.location.adapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.lab3_20211602_iot.R;
import com.example.lab3_20211602_iot.domain.model.LocationItem;
import java.util.ArrayList;
import java.util.List;

public class LocationAdapter extends RecyclerView.Adapter<LocationAdapter.VH> {
    public interface OnClick { void onItem(LocationItem item); }
    private final List<LocationItem> data = new ArrayList<>();
    private final OnClick listener;

    public LocationAdapter(OnClick l) { this.listener = l; }

    @SuppressLint("NotifyDataSetChanged")
    public void submit(List<LocationItem> d) {
        data.clear();
        if (d != null) data.addAll(d);
        notifyDataSetChanged();
    }

    @NonNull @Override public VH onCreateViewHolder(@NonNull ViewGroup p, int v) {
        View view = LayoutInflater.from(p.getContext()).inflate(R.layout.item_location, p, false);
        return new VH(view);
    }

    @Override public void onBindViewHolder(@NonNull VH h, int pos) {
        LocationItem it = data.get(pos);
        h.tv1.setText(String.valueOf(it.id));
        h.tv2.setText(it.name);
        h.tv3.setText(it.region);
        h.tv4.setText(it.country);
        h.tv5.setText(String.valueOf(it.lat));
        h.tv6.setText(String.valueOf(it.lon));
        h.itemView.setOnClickListener(v -> listener.onItem(it));
    }

    @Override public int getItemCount() { return data.size(); }

    static class VH extends RecyclerView.ViewHolder {
        TextView tv1,tv2,tv3,tv4,tv5,tv6;
        VH(@NonNull View itemView) {
            super(itemView);
            tv1 = itemView.findViewById(R.id.tvId);
            tv2 = itemView.findViewById(R.id.tvName);
            tv3 = itemView.findViewById(R.id.tvRegion);
            tv4 = itemView.findViewById(R.id.tvCountry);
            tv5 = itemView.findViewById(R.id.tvLat);
            tv6 = itemView.findViewById(R.id.tvLon);
        }
    }
}
