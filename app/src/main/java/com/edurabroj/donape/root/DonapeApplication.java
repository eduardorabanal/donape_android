package com.edurabroj.donape.root;

import android.app.Application;

import com.edurabroj.donape.components.donacion.donar.ModuleDonar;
import com.edurabroj.donape.components.donacion.mis_donaciones.ModuleMisDonaciones;
import com.edurabroj.donape.components.publicacion.detalle.ModuleDetallePublicacion;
import com.edurabroj.donape.components.publicacion.lista.ModuleListaPublicacion;
import com.edurabroj.donape.http.HttpModule;


public class DonapeApplication extends Application {

    private ApplicationComponent applicationComponent;

    @Override public void onCreate() {
        super.onCreate();
        applicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .httpModule(new HttpModule())
                .moduleMisDonaciones(new ModuleMisDonaciones())
                .moduleListaPublicacion(new ModuleListaPublicacion())
                .moduleDetallePublicacion(new ModuleDetallePublicacion())
                .moduleDonar(new ModuleDonar())
                .build();
    }

    public ApplicationComponent getComponent() {
        return applicationComponent;
    }
}