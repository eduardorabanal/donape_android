package com.edurabroj.donape.shared.preferences;

public interface IPreferences {
    String getStringPreference(String preferenceKey);
    String getStringPreference(String preferenceKey, String defaultValue);
    void setStringPreference(String preferenceKey, String value);
    void removeStringPreference(String preferenceKey);
}
