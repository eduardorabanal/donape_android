package com.edurabroj.donape.root;

import android.app.Application;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;

import com.edurabroj.donape.shared.preferences.IPreferences;
import com.edurabroj.donape.shared.preferences.Preferences;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class ApplicationModule {
    private Application application;

    public ApplicationModule(Application application) {
        this.application = application;
    }

    @Provides
    @Singleton
    public Context providesApplicationContext(){
        return this.application;
    }

    @Provides
    @Singleton
    Handler providesHandler(){
        return new Handler(Looper.getMainLooper());
    }

    @Provides
    @Singleton
    IPreferences providePreferences(Context context){
        return new Preferences(context);
    }
}
