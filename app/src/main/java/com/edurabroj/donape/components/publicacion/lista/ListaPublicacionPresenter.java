package com.edurabroj.donape.components.publicacion.lista;

import com.edurabroj.donape.PublicacionesQuery;
import com.edurabroj.donape.PublicacionesQuery;
import com.edurabroj.donape.shared.entidades.Necesidad;

import java.util.List;

public class ListaPublicacionPresenter implements ListaPublicacionContract.Presenter, ListaPublicacionContract.Interactor.OnLoadListaFinishedListener {
    ListaPublicacionContract.View view;
    ListaPublicacionContract.Interactor interactor;

    public ListaPublicacionPresenter(ListaPublicacionContract.View view, ListaPublicacionContract.Interactor interactor) {
        this.view = view;
        this.interactor = interactor;
    }

    @Override
    public void onCreate() {
        view.mostrarProgress();
        interactor.getLista(this);
    }

    @Override
    public void onRefreshLista() {
        view.mostrarProgress();
        interactor.getLista(this);
    }

    @Override
    public void onLoadListaSuccess(PublicacionesQuery.Data data) {
        view.ocultarProgress();
        view.llenarLista(data.publicaciones());
    }

    @Override
    public void onLoadListaErrorServidor() {
        view.ocultarProgress();
        view.mostrarErrorServidor();
    }

    @Override
    public void onLoadListaErrorRed(Throwable t) {
        view.ocultarProgress();
        view.mostrarErrorRed();
    }
}
