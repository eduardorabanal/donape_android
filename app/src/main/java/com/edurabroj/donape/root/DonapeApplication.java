package com.edurabroj.donape.root;

import android.app.Application;

import com.edurabroj.donape.components.donacion.mis_donaciones.MisDonacionesModule;


public class DonapeApplication extends Application {

    private ApplicationComponent applicationComponent;

    @Override public void onCreate() {
        super.onCreate();
        applicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .misDonacionesModule(new MisDonacionesModule())
                .build();
    }

    public ApplicationComponent getComponent() {
        return applicationComponent;
    }
}