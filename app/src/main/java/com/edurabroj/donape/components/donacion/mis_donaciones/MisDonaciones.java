package com.edurabroj.donape.components.donacion.mis_donaciones;

import com.edurabroj.donape.shared.entidades.Donacion;

import io.reactivex.Observable;

public interface MisDonaciones {
    interface View{
        void mostrarLoading();
        void ocultarLoading();
        void adjuntarDonacion(Donacion donacion);
        void limpiarLista();
        void mostrarErrorServidor();
    }
    interface Presenter {
        void setView(MisDonaciones.View view);
        void refrescarLista();
        void solicitarMisDonaciones();
        void rxUnsubscribe();
    }
    interface Interactor {
        Observable<Donacion> obtenerMisDonaciones();
    }
}
