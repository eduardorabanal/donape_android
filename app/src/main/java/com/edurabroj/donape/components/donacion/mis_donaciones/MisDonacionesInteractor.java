package com.edurabroj.donape.components.donacion.mis_donaciones;

import com.edurabroj.donape.shared.entidades.Donacion;

import java.util.List;

public class MisDonacionesInteractor implements MisDonaciones.Interactor{
    MisDonacionesRepository repository;
    CallbackInteractor callback;

    public MisDonacionesInteractor(MisDonacionesRepository repository
    ) {
        repository.setCallback(this);
        this.repository = repository;
    }

    @Override
    public void obtenerMisDonaciones() {
        repository.obtenerMisDonaciones();
    }

    public void setCallback(CallbackInteractor callback) {
        this.callback = callback;
    }

    @Override
    public void onMisDonacionesSuccess(List<Donacion> donaciones) {
        callback.onMisDonacionesSuccess(donaciones);
    }

    @Override
    public void onMisDonacionesError() {
        callback.onMisDonacionesError();
    }
}
