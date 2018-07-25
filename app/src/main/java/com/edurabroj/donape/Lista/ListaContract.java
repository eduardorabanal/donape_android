package com.edurabroj.donape.Lista;

import com.edurabroj.donape.entidades.Solicitud;

import java.util.List;

public class ListaContract {
    public interface View {
        void mostrarProgress();
        void ocultarProgress();
        void llenarLista(List<Solicitud> list);
        void mostrarErrorRed();
        void mostrarErrorServidor();
    }

    public interface Presenter {
        void onCreate();
        void onRefreshLista();
    }

    public interface Interactor {
        interface OnLoadListaFinishedListener{
            void onLoadListaSuccess(List<Solicitud> data);
            void onLoadListaErrorServidor();
            void onLoadListaErrorRed(Throwable t);
        }

        void getLista(OnLoadListaFinishedListener onLoadListaFinishedListener);
    }
}
