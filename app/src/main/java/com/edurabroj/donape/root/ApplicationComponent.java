package com.edurabroj.donape.root;

import com.edurabroj.donape.components.donacion.mis_donaciones.MisDonacionesActivity;
import com.edurabroj.donape.components.donacion.mis_donaciones.MisDonacionesModule;
import com.edurabroj.donape.components.login.LoginActivity;
import com.edurabroj.donape.components.publicacion.lista.ListaPublicacionActivity;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {
        ApplicationModule.class,
        MisDonacionesModule.class
})
public interface ApplicationComponent {
    void inject(MisDonacionesActivity activity);
    void inject(LoginActivity activity);
    void inject(ListaPublicacionActivity activity);
}
