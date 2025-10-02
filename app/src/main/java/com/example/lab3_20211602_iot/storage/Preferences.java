package com.example.lab3_20211602_iot.storage;

import android.content.Context;
import android.content.SharedPreferences;

public class Preferences {
    private static final String P = "tele_weather_prefs";
    private static SharedPreferences sp(Context c){ return c.getSharedPreferences(P, Context.MODE_PRIVATE); }

    public static void setLastQuery(Context c, String q){ sp(c).edit().putString("last_query", q).apply(); }
    public static String getLastQuery(Context c){ return sp(c).getString("last_query", ""); }

    public static void setLastCity(Context c, String q){ sp(c).edit().putString("last_city", q).apply(); }
    public static String getLastCity(Context c){ return sp(c).getString("last_city", ""); }

    public static void setLastLocationId(Context c, long id){ sp(c).edit().putLong("last_loc_id", id).apply(); }
    public static long getLastLocationId(Context c){ return sp(c).getLong("last_loc_id", 0L); }

    public static void setLastLocationName(Context c, String name){ sp(c).edit().putString("last_loc_name", name).apply(); }
    public static String getLastLocationName(Context c){ return sp(c).getString("last_loc_name", ""); }

    public static void setLastDays(Context c, int d){ sp(c).edit().putInt("last_days", d).apply(); }
    public static int getLastDays(Context c){ return sp(c).getInt("last_days", 7); }
}
