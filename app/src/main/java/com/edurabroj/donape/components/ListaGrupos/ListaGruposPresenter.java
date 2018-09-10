package com.edurabroj.donape.components.ListaGrupos;

import com.edurabroj.donape.shared.entidades.Necesidad;

import java.util.List;

public class ListaGruposPresenter implements ListaGruposContract.Presenter, ListaGruposContract.Interactor.OnLoadListaFinishedListener {
    ListaGruposContract.View view;
    ListaGruposContract.Interactor interactor;

    public ListaGruposPresenter(ListaGruposContract.View view, ListaGruposContract.Interactor interactor) {
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
    public void onLoadListaSuccess(List<Necesidad> data) {
        view.ocultarProgress();
        view.llenarLista(data);
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
