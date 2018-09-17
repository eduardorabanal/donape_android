package com.edurabroj.donape.components.donacion.mis_donaciones;

import com.edurabroj.donape.shared.entidades.Donacion;

import java.util.List;

import javax.annotation.Nullable;

public class MisDonacionesPresenter implements MisDonaciones.Presenter{
    @Nullable
    private MisDonaciones.View view;
    private MisDonaciones.Interactor interactor;

    public MisDonacionesPresenter(MisDonaciones.Interactor interactor) {
        interactor.setCallback(this);
        this.interactor = interactor;
    }

    @Override
    public void setView(MisDonaciones.View view) {
        this.view = view;
    }

    @Override
    public void refrescarLista() {
        solicitarMisDonaciones();
    }

    @Override
    public void solicitarMisDonaciones() {
        interactor.obtenerMisDonaciones();
    }

    @Override
    public void onMisDonacionesSuccess(List<Donacion> donaciones) {
        if(view!=null){
            view.ocultarLoading();
            view.mostrarDonaciones(donaciones);
        }
    }

    @Override
    public void onMisDonacionesError() {
        if(view!=null){
            view.ocultarLoading();
            view.mostrarErrorServidor();
        }
    }
}
