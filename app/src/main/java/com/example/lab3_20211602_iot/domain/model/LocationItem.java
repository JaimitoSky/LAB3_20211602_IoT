package com.example.lab3_20211602_iot.domain.model;

public class LocationItem {
    public final long id;
    public final String name;
    public final String region;
    public final String country;
    public final double lat;
    public final double lon;

    public LocationItem(long id, String name, String region, String country, double lat, double lon) {
        this.id = id;
        this.name = name;
        this.region = region;
        this.country = country;
        this.lat = lat;
        this.lon = lon;
    }
}
