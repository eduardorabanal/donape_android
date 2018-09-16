package com.edurabroj.donape.components.donacion.mis_donaciones;

import android.content.Context;

import com.edurabroj.donape.shared.entidades.Donacion;

import java.util.List;

public class MisDonacionesPresenter implements MisDonaciones.Presenter, MisDonaciones.Interactor.CallbackMisDonaciones{
    MisDonaciones.View view;
    MisDonaciones.Interactor interactor;
    Context context;

    public MisDonacionesPresenter(MisDonaciones.View view, Context context) {
        this.view = view;
        interactor = new MisDonacionesInteractor(this);
        this.context = context;
    }

    @Override
    public void solicitarDonaciones() {
        view.mostrarLoading();
        interactor.obtenerDonaciones(1,this);
    }

    @Override
    public Context getContext() {
        return context;
    }

    @Override
    public void onDonacionesSuccess(List<Donacion> donaciones) {
        view.ocultarLoading();
        view.mostrarDonaciones(donaciones);
    }

    @Override
    public void onDonacionesNetworkError() {
        view.ocultarLoading();
        view.mostrarErrorRed();
    }

    @Override
    public void onDonacionesServerError() {
        view.ocultarLoading();
        view.mostrarErrorServidor();
    }
}
