package com.example.lab3_20211602_iot.data.mapper;

import com.example.lab3_20211602_iot.data.dto.LocationDto;
import com.example.lab3_20211602_iot.domain.model.LocationItem;
import java.util.ArrayList;
import java.util.List;

public class LocationMapper {
    public static List<LocationItem> map(List<LocationDto> src) {
        List<LocationItem> out = new ArrayList<>();
        if (src == null) return out;
        for (LocationDto d : src) {
            out.add(new LocationItem(d.id, d.name, d.region, d.country, d.lat, d.lon));
        }
        return out;
    }
}
