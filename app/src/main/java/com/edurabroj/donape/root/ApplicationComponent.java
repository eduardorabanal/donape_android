package com.edurabroj.donape.root;

import com.edurabroj.donape.components.donacion.donar.DonarActivity;
import com.edurabroj.donape.components.donacion.donar.ModuleDonar;
import com.edurabroj.donape.components.donacion.mis_donaciones.MisDonacionesActivity;
import com.edurabroj.donape.components.donacion.mis_donaciones.ModuleMisDonaciones;
import com.edurabroj.donape.components.login.LoginActivity;
import com.edurabroj.donape.components.publicacion.detalle.DetallePublicacionActivity;
import com.edurabroj.donape.components.publicacion.detalle.ModuleDetallePublicacion;
import com.edurabroj.donape.components.publicacion.lista.ListaPublicacionActivity;
import com.edurabroj.donape.components.publicacion.lista.ModuleListaPublicacion;
import com.edurabroj.donape.components.session.SessionModule;
import com.edurabroj.donape.http.HttpModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {
        ApplicationModule.class,
        SessionModule.class,
        HttpModule.class,
        ModuleMisDonaciones.class,
        ModuleListaPublicacion.class,
        ModuleDetallePublicacion.class,
        ModuleDonar.class
})
public interface ApplicationComponent {
    void inject(MisDonacionesActivity activity);
    void inject(LoginActivity activity);
    void inject(ListaPublicacionActivity activity);
    void inject(DetallePublicacionActivity activity);
    void inject(DonarActivity activity);
}
