package com.edurabroj.donape.components.Detalle;

import com.edurabroj.donape.shared.entidades.Necesidad;

class DetallePresenter implements DetalleContract.Presenter, DetalleContract.Interactor.OnDetalleLoadFinished {
    DetalleContract.View view;
    DetalleContract.Interactor interactor;

    DetallePresenter(DetalleContract.View view, DetalleContract.Interactor interactor) {
        this.view = view;
        this.interactor = interactor;
    }

    @Override
    public void onCreate(String solicitudId) {
        view.mostrarProgress();
        interactor.loadDetalle(solicitudId,this);
    }

    @Override
    public void onRefresh(String solicitudId) {
        view.mostrarProgress();
        interactor.loadDetalle(solicitudId,this);
    }

    @Override
    public void onDetalleLoadSuccess(Necesidad necesidad) {
        view.ocultarProgress();
        view.mostrarDetalle(necesidad);
    }

    @Override
    public void onDetalleLoadErrorServidor() {
        view.ocultarProgress();
        view.mostrarErrorServidor();
    }

    @Override
    public void onDetalleLoadErrorRed() {
        view.ocultarProgress();
        view.mostrarErrorRed();
    }
}
