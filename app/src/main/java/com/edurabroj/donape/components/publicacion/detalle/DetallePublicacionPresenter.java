package com.edurabroj.donape.components.publicacion.detalle;
import com.edurabroj.donape.PublicacionQuery;

class DetallePublicacionPresenter implements DetallePublicacionContract.Presenter, DetallePublicacionContract.Interactor.OnDetalleLoadFinished {
    DetallePublicacionContract.View view;
    DetallePublicacionContract.Interactor interactor;

    DetallePublicacionPresenter(DetallePublicacionContract.View view, DetallePublicacionContract.Interactor interactor) {
        this.view = view;
        this.interactor = interactor;
    }

    @Override
    public void onCreate(String id) {
        view.mostrarProgress();
        interactor.loadDetalle(id,this);
    }

    @Override
    public void onRefresh(String id) {
        view.mostrarProgress();
        interactor.loadDetalle(id,this);
    }

    @Override
    public void onDetalleLoadSuccess(PublicacionQuery.Data data) {
        view.ocultarProgress();
        view.mostrarDetalle(data.publicacion());
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
