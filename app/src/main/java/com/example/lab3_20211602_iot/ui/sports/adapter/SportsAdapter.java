package com.example.lab3_20211602_iot.ui.sports.adapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.lab3_20211602_iot.R;
import com.example.lab3_20211602_iot.domain.model.MatchItem;
import java.util.ArrayList;
import java.util.List;

public class SportsAdapter extends RecyclerView.Adapter<SportsAdapter.VH> {
    private final List<MatchItem> data = new ArrayList<>();

    @SuppressLint("NotifyDataSetChanged")
    public void submit(List<MatchItem> d) {
        data.clear();
        if (d != null) data.addAll(d);
        notifyDataSetChanged();
    }

    @NonNull @Override public VH onCreateViewHolder(@NonNull ViewGroup p, int v) {
        View view = LayoutInflater.from(p.getContext()).inflate(R.layout.item_sport, p, false);
        return new VH(view);
    }

    @SuppressLint("SetTextI18n")
    @Override public void onBindViewHolder(@NonNull VH h, int pos) {
        MatchItem it = data.get(pos);
        h.tvStart.setText(it.start);
        h.tvTournament.setText(it.tournament);
        h.tvTeams.setText(it.home + " vs " + it.away);
        h.tvStadium.setText(it.stadium);
    }

    @Override public int getItemCount() { return data.size(); }

    static class VH extends RecyclerView.ViewHolder {
        TextView tvStart,tvTournament,tvTeams,tvStadium;
        VH(@NonNull View itemView) {
            super(itemView);
            tvStart = itemView.findViewById(R.id.tvStart);
            tvTournament = itemView.findViewById(R.id.tvTournament);
            tvTeams = itemView.findViewById(R.id.tvTeams);
            tvStadium = itemView.findViewById(R.id.tvStadium);
        }
    }
}
