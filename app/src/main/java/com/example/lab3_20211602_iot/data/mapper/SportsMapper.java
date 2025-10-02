package com.example.lab3_20211602_iot.data.mapper;

import com.example.lab3_20211602_iot.data.dto.SportsDto;
import com.example.lab3_20211602_iot.domain.model.MatchItem;
import java.util.ArrayList;
import java.util.List;

public class SportsMapper {
    public static List<MatchItem> map(SportsDto src) {
        List<MatchItem> out = new ArrayList<>();
        if (src == null || src.football == null) return out;
        for (SportsDto.Football f : src.football) {
            out.add(new MatchItem(f.start, f.stadium, f.tournament, f.home, f.away));
        }
        return out;
    }
}
