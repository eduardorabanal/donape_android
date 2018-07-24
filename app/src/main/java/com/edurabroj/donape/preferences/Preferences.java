package com.edurabroj.donape.preferences;

import android.content.Context;
import android.content.SharedPreferences;

import static android.content.Context.MODE_PRIVATE;
import static com.edurabroj.donape.data.PreferencesData.PREFERENCIAS_NAME;

public class Preferences implements IPreferences {
    Context context;

    public Preferences(Context context) {
        this.context = context;
    }

    @Override
    public String getStringPreference(String preferenceKey) {
        return getPreferencias().getString(preferenceKey,"");
    }

    @Override
    public String getStringPreference(String preferenceKey, String defaultValue) {
        return getPreferencias().getString(preferenceKey,defaultValue);
    }

    @Override
    public void setStringPreference(String preferenceKey, String value) {
        getPreferencias()
                .edit()
                .putString(preferenceKey,value)
                .apply();
    }

    @Override
    public void removeStringPreference(String preferenceKey) {
        getPreferencias()
                .edit()
                .remove(preferenceKey)
                .apply();
    }

    private SharedPreferences getPreferencias(){
        return context.getSharedPreferences(PREFERENCIAS_NAME, MODE_PRIVATE);
    }
}
