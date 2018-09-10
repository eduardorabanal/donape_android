package com.edurabroj.donape.components.ListaGrupos;

import com.edurabroj.donape.shared.entidades.Necesidad;

import java.util.List;

public class ListaGruposContract {
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
