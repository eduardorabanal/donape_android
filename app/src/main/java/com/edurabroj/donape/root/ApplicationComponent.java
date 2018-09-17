package com.edurabroj.donape.root;

import com.edurabroj.donape.components.donacion.mis_donaciones.MisDonacionesActivity;
import com.edurabroj.donape.components.donacion.mis_donaciones.MisDonacionesModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {
        ApplicationModule.class,
        MisDonacionesModule.class
})
public interface ApplicationComponent {
    void inject(MisDonacionesActivity activity);
}
