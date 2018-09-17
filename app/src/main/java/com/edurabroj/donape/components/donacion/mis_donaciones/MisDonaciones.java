package com.edurabroj.donape.components.donacion.mis_donaciones;

import com.edurabroj.donape.shared.entidades.Donacion;

import java.util.List;

public interface MisDonaciones {
    interface View{
        void mostrarLoading();
        void ocultarLoading();
        void mostrarDonaciones(List<Donacion> donaciones);
        void mostrarErrorRed();
        void mostrarErrorServidor();
    }
    interface Presenter extends Interactor.CallbackInteractor {
        void setView(MisDonaciones.View view);
        void refrescarLista();
        void solicitarMisDonaciones();
    }
    interface Interactor {
        void obtenerMisDonaciones();
        void setCallback(Interactor.CallbackInteractor callback);

        interface CallbackInteractor{
            void onMisDonacionesSuccess(List<Donacion> donaciones);
            void onMisDonacionesError();
        }
    }
}
