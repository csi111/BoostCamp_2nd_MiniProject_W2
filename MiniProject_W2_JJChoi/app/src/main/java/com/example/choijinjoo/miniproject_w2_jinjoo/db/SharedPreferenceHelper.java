package com.example.choijinjoo.miniproject_w2_jinjoo.db;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by choijinjoo on 2017. 7. 14..
 */

public class SharedPreferenceHelper {
    private final static String PREF_FILE = "PREF";

    public static void putBooleanValue(Context context, String key, boolean value) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREF_FILE, 0);
        sharedPreferences.edit().putBoolean(key, value).apply();
    }

    public static boolean getBooleanValue(Context context, String key, boolean defValue) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREF_FILE, 0);
        return sharedPreferences.getBoolean(key, defValue);
    }

    public static void rearrange(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREF_FILE, 0);
        boolean staggered = sharedPreferences.getBoolean("rearrange_stggered", false);
        sharedPreferences.edit().putBoolean("rearrange_stggered", !staggered).apply();

    }
}
