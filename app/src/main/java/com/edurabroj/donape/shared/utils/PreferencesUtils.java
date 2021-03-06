package com.edurabroj.donape.shared.utils;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import static android.content.Context.MODE_PRIVATE;
import static com.edurabroj.donape.shared.data.PreferencesData.PREFERENCIAS_NAME;

public class PreferencesUtils {
    public static String getStringPreference(Context context, String preferenceKey){
        return getPreferencias(context).getString(preferenceKey,"");
    }
    public static String getStringPreference(Context context, String preferenceKey, String defaultValue){
        return getPreferencias(context).getString(preferenceKey,defaultValue);
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
