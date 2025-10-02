package com.example.lab3_20211602_iot.data.dto;

import java.util.List;

public class SportsDto {
    public List<Football> football;

    public static class Football {
        public String start;
        public String stadium;
        public String country;
        public String tournament;
        public String match;
        public String home;
        public String away;
    }
}
