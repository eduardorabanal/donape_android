package com.edurabroj.donape.utils;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import static android.content.Context.MODE_PRIVATE;
import static com.edurabroj.donape.data.PreferencesData.PREFERENCIAS_NAME;

public class PreferencesUtils {
    public static String getStringPreference(Activity activity, String preferenceKey){
        return getPreferencias(activity).getString(preferenceKey,"");
    }
    public static String getStringPreference(Activity activity, String preferenceKey, String defaultValue){
        return getPreferencias(activity).getString(preferenceKey,defaultValue);
    }
    public static void setStringPreference(Activity activity, String preferenceKey, String value){
        getPreferencias(activity)
                .edit()
                .putString(preferenceKey,value)
                .apply();
    }
    public static void removeStringPreference(Activity activity, String preferenceKey){
        getPreferencias(activity)
                .edit()
                .remove(preferenceKey)
                .apply();
    }

    private static SharedPreferences getPreferencias(Context context){
        return context.getSharedPreferences(PREFERENCIAS_NAME, MODE_PRIVATE);
    }
}
