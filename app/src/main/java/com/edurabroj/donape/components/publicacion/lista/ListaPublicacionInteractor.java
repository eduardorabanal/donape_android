package com.edurabroj.donape.components.publicacion.lista;

import com.edurabroj.donape.shared.entidades.Publicacion;
import com.edurabroj.donape.components.session.RepoSession;

import io.reactivex.Observable;

public class ListaPublicacionInteractor implements ListaPublicacion.Interactor {
    private RepoListaPublicacion repoListaPublicacion;
    private RepoSession repoSession;

    public ListaPublicacionInteractor(
            RepoListaPublicacion repoListaPublicacion,
            RepoSession repoSession
    ) {
        this.repoListaPublicacion = repoListaPublicacion;
        this.repoSession = repoSession;
    }

    @Override
    public Observable<Publicacion> obtenerPublicaciones() {
        return repoListaPublicacion.getPublicacionesData();
    }

    @Override
    public void logout() {
        repoSession.doLogout();
    }
}
