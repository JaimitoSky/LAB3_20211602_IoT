package com.example.lab3_20211602_iot.data.mapper;

import com.example.lab3_20211602_iot.data.dto.ForecastDto;
import com.example.lab3_20211602_iot.domain.model.ForecastDayItem;
import java.util.ArrayList;
import java.util.List;

public class ForecastMapper {
    public static List<ForecastDayItem> map(ForecastDto src) {
        List<ForecastDayItem> out = new ArrayList<>();
        if (src == null || src.forecast == null || src.forecast.forecastday == null) return out;
        for (ForecastDto.ForecastDay f : src.forecast.forecastday) {
            String text = f.day != null && f.day.condition != null ? f.day.condition.text : null;
            String icon = f.day != null && f.day.condition != null ? f.day.condition.icon : null;
            out.add(new ForecastDayItem(f.date, f.day.maxtemp_c, f.day.mintemp_c, text, icon));
        }
        return out;
    }
}
