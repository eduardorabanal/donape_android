package com.edurabroj.donape.components.donacion.mis_donaciones;

import com.edurabroj.donape.shared.entidades.Donacion;

import io.reactivex.Observable;

public class MisDonacionesInteractor implements MisDonaciones.Interactor{
    private RepoMisDonaciones repository;

    MisDonacionesInteractor(RepoMisDonaciones repository) {
        this.repository = repository;
    }

    @Override
    public Observable<Donacion> obtenerMisDonaciones() {
        return repository.getMyDonationsData();
    }
}
