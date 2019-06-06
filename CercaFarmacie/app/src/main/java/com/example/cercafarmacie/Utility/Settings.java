package com.example.cercafarmacie.Utility;


import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class Settings {
    public static final String FIRST_TIME = "first_time"; // Used to remember if is the first time that the user open the app


    public static void save(Context context, String key, boolean value){

        SharedPreferences preferences = PreferenceManager
                .getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean(key, value);
        editor.apply();
    }


    public static boolean loadBoolean(Context context, String key, boolean fallback){

        SharedPreferences preferences = PreferenceManager
                .getDefaultSharedPreferences(context);
        return preferences.getBoolean(key, fallback);
    }
}

