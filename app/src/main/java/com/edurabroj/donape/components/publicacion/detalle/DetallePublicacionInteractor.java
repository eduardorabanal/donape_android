package com.edurabroj.donape.components.publicacion.detalle;

import com.edurabroj.donape.shared.entidades.Publicacion;

import io.reactivex.Observable;

public class DetallePublicacionInteractor implements DetallePublicacion.Interactor {
    private RepoDetallePublicacion repoDetallePublicacion;

    public DetallePublicacionInteractor(RepoDetallePublicacion repoDetallePublicacion) {
        this.repoDetallePublicacion = repoDetallePublicacion;
    }

    @Override
    public Observable<Publicacion> getDetallePublicacionData(int id) {
        return repoDetallePublicacion.getPublicacionDetalleData(id);
    }
}
