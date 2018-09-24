package com.edurabroj.donape.components.publicacion.lista;

import com.edurabroj.donape.shared.entidades.Publicacion;

import io.reactivex.Observable;

public interface RepoListaPublicacion {
    Observable<Publicacion> getPublicacionesData();
}
