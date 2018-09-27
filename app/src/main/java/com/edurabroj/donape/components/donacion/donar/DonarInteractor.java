package com.edurabroj.donape.components.donacion.donar;

import com.edurabroj.donape.shared.entidades.Donacion;
import com.edurabroj.donape.shared.entidades.Necesidad;

import io.reactivex.Observable;

public class DonarInteractor implements DonarMVP.Interactor {
    private RepoDonar repoDonar;
    private RepoNecesidad repoNecesidad;

    public DonarInteractor(RepoDonar repoDonar, RepoNecesidad repoNecesidad) {
        this.repoDonar = repoDonar;
        this.repoNecesidad = repoNecesidad;
    }

    @Override
    public Observable<Necesidad> getNecesidadData(int necesidadId) {
        return repoNecesidad.getNecesidadData(necesidadId);
    }

    @Override
    public Observable<Donacion> saveDonacionData(int necesidadId, float cantidad) {
        return repoDonar.saveDonacionData(necesidadId,cantidad);
    }
}
