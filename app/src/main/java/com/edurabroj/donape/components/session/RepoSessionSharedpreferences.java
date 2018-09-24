package com.edurabroj.donape.components.session;

import com.edurabroj.donape.shared.preferences.IPreferences;

import static com.edurabroj.donape.shared.data.PreferencesData.TOKEN_KEY;

public class RepoSessionSharedpreferences implements RepoSession {
    private IPreferences preferences;

    public RepoSessionSharedpreferences(IPreferences preferences) {
        this.preferences = preferences;
    }

    @Override
    public void doLogout() {
        preferences.removeStringPreference(TOKEN_KEY);
    }
}
