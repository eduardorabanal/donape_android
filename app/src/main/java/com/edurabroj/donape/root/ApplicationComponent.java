package com.edurabroj.donape.root;

import com.edurabroj.donape.components.donacion.mis_donaciones.MisDonacionesActivity;
import com.edurabroj.donape.components.donacion.mis_donaciones.ModuleMisDonaciones;
import com.edurabroj.donape.components.login.LoginActivity;
import com.edurabroj.donape.components.publicacion.lista.ListaPublicacionActivity;
import com.edurabroj.donape.components.publicacion.lista.ModuleListaPublicacion;
import com.edurabroj.donape.http.HttpModule;
import com.edurabroj.donape.components.session.SessionModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {
        ApplicationModule.class,
        SessionModule.class,
        HttpModule.class,
        ModuleMisDonaciones.class,
        ModuleListaPublicacion.class,
})
public interface ApplicationComponent {
    void inject(MisDonacionesActivity activity);
    void inject(LoginActivity activity);
    void inject(ListaPublicacionActivity activity);
}
