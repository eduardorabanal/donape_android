package com.edurabroj.donape.Lista;

import com.edurabroj.donape.entidades.Necesidad;

import java.util.List;

public class ListaContract {
    public interface View {
        void mostrarProgress();
        void ocultarProgress();
        void llenarLista(List<Necesidad> list);
        void mostrarErrorRed();
        void mostrarErrorServidor();
    }

    public interface Presenter {
        void onCreate();
        void onRefreshLista();
    }

    public interface Interactor {
        interface OnLoadListaFinishedListener{
            void onLoadListaSuccess(List<Necesidad> data);
            void onLoadListaErrorServidor();
            void onLoadListaErrorRed(Throwable t);
        }

        void getLista(OnLoadListaFinishedListener onLoadListaFinishedListener);
    }
}
