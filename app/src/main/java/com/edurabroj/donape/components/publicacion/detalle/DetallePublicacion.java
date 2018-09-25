package com.edurabroj.donape.components.publicacion.detalle;

import com.edurabroj.donape.shared.entidades.Publicacion;

import io.reactivex.Observable;

public class DetallePublicacion {
    public interface View {
        void mostrarLoading();
        void ocultarLoading();
        void mostrarDetalle(Publicacion publicacion);
        void mostrarError();
        String getPublicacionId();
    }

    public interface Presenter {
        void setView(View view);
        void rxUnsubscribe();
        void onRefresh();
        void onRetryLoadClick();
        void solicitarDetalle();
    }

    public interface Interactor {
        Observable<Publicacion> getDetallePublicacionData(int id);
    }
}
