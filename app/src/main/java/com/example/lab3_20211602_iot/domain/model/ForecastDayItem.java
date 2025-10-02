package com.example.lab3_20211602_iot.domain.model;

public class ForecastDayItem {
    public final String date;
    public final double maxTempC;
    public final double minTempC;
    public final String conditionText;
    public final String conditionIcon;

    public ForecastDayItem(String date, double maxTempC, double minTempC, String conditionText, String conditionIcon) {
        this.date = date;
        this.maxTempC = maxTempC;
        this.minTempC = minTempC;
        this.conditionText = conditionText;
        this.conditionIcon = conditionIcon;
    }
}
