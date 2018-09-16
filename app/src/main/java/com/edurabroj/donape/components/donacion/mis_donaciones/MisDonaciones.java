package com.edurabroj.donape.components.donacion.mis_donaciones;

import android.content.Context;

import com.edurabroj.donape.shared.entidades.Donacion;

import java.util.List;

public interface MisDonaciones {
    interface View{
        void onCreate();
        void mostrarLoading();
        void ocultarLoading();
        void mostrarDonaciones(List<Donacion> donaciones);
        void mostrarErrorRed();
        void mostrarErrorServidor();
        void refrescarLista();
    }
    interface Presenter{
        void solicitarDonaciones();
        Context getContext();
    }
    interface Interactor{
        interface CallbackMisDonaciones{
            void onDonacionesSuccess(List<Donacion> donaciones);
            void onDonacionesNetworkError();
            void onDonacionesServerError();
        }
        void obtenerDonaciones(int usuarioId, CallbackMisDonaciones callback);
    }
}
