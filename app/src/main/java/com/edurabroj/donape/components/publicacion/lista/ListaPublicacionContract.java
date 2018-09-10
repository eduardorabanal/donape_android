package com.edurabroj.donape.components.publicacion.lista;

import com.edurabroj.donape.ListarPublicaciones;

import java.util.List;

public class ListaPublicacionContract {
    public interface View {
        void mostrarProgress();
        void ocultarProgress();
        void llenarLista(List<ListarPublicaciones.Publicacione> list);
        void mostrarErrorRed();
        void mostrarErrorServidor();
    }

    public interface Presenter {
        void onCreate();
        void onRefreshLista();
    }

    public interface Interactor {
        interface OnLoadListaFinishedListener{
            void onLoadListaSuccess(ListarPublicaciones.Data data);
            void onLoadListaErrorServidor();
            void onLoadListaErrorRed(Throwable t);
        }

        void getLista(OnLoadListaFinishedListener onLoadListaFinishedListener);
    }
}
