package com.edurabroj.donape.Lista;

import com.edurabroj.donape.entidades.Solicitud;

import java.util.List;

public class ListaPresenter implements ListaContract.Presenter, ListaContract.Interactor.OnLoadListaFinishedListener {
    ListaContract.View view;
    ListaContract.Interactor interactor;

    public ListaPresenter(ListaContract.View view, ListaContract.Interactor interactor) {
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
    public void onLoadListaSuccess(List<Solicitud> data) {
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
