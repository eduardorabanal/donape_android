package com.edurabroj.donape.Detalle;

import com.edurabroj.donape.entidades.Solicitud;

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
    public void onDetalleLoadSuccess(Solicitud solicitud) {
        view.ocultarProgress();
        view.mostrarDetalle(solicitud);
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
