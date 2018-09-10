package com.edurabroj.donape.components.publicacion.detalle;
import com.edurabroj.donape.PublicacionQuery;
public class DetallePublicacionContract {
    public interface View {
        void mostrarProgress();
        void ocultarProgress();
        void mostrarDetalle(PublicacionQuery.Publicacion publicacion);
        void mostrarErrorServidor();
        void mostrarErrorRed();
    }

    public interface Presenter {
        void onCreate(String id);
        void onRefresh(String id);
    }

    public interface Interactor {
        interface OnDetalleLoadFinished {
            void onDetalleLoadSuccess(PublicacionQuery.Data data);
            void onDetalleLoadErrorServidor();
            void onDetalleLoadErrorRed();
        }

        void loadDetalle(String id, OnDetalleLoadFinished onDetalleLoadFinished);
    }
}
