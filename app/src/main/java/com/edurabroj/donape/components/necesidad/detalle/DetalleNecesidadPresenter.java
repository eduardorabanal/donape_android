package com.edurabroj.donape.components.necesidad.detalle;

import com.edurabroj.donape.shared.entidades.Necesidad;

class DetalleNecesidadPresenter implements DetalleNecesidadContract.Presenter, DetalleNecesidadContract.Interactor.OnDetalleLoadFinished {
    DetalleNecesidadContract.View view;
    DetalleNecesidadContract.Interactor interactor;

    DetalleNecesidadPresenter(DetalleNecesidadContract.View view, DetalleNecesidadContract.Interactor interactor) {
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
