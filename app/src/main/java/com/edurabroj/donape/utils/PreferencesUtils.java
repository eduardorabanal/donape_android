package com.edurabroj.donape.utils;

import android.app.Activity;

import static android.content.Context.MODE_PRIVATE;

public class PreferencesUtils {
    public static String getStringPreference(Activity activity, String preferenceKey){
        return activity.getPreferences(MODE_PRIVATE).getString(preferenceKey,"");
    }
    public static String getStringPreference(Activity activity, String preferenceKey, String defaultValue){
        return activity.getPreferences(MODE_PRIVATE).getString(preferenceKey,defaultValue);
    }
    public static void setStringPreference(Activity activity, String preferenceKey, String value){
        activity.getPreferences(MODE_PRIVATE)
                .edit()
                .putString(preferenceKey,value)
                .apply();
    }
    public static void removeStringPreference(Activity activity, String preferenceKey){
        activity.getPreferences(MODE_PRIVATE)
                .edit()
                .remove(preferenceKey)
                .apply();
    }
}
