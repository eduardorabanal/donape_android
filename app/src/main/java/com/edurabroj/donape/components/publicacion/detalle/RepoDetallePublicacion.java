package com.edurabroj.donape.components.publicacion.detalle;

import com.edurabroj.donape.shared.entidades.Publicacion;

import io.reactivex.Observable;

public interface RepoDetallePublicacion {
    Observable<Publicacion> getPublicacionDetalleData(int id);
}
