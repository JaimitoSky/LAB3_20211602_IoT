package com.example.lab3_20211602_iot.domain.model;

public class MatchItem {
    public final String start;
    public final String stadium;
    public final String tournament;
    public final String home;
    public final String away;

    public MatchItem(String start, String stadium, String tournament, String home, String away) {
        this.start = start;
        this.stadium = stadium;
        this.tournament = tournament;
        this.home = home;
        this.away = away;
    }
}
