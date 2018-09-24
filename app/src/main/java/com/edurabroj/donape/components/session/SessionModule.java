package com.edurabroj.donape.components.session;

import com.edurabroj.donape.shared.preferences.IPreferences;

import dagger.Module;
import dagger.Provides;

@Module
public class SessionModule {
    @Provides
    public RepoSession provideRepoSession(IPreferences preferences){
        return new RepoSessionSharedpreferences(preferences);
    }
}
