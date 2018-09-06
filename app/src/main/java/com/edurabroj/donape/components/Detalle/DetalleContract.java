package com.edurabroj.donape.components.Detalle;
import com.edurabroj.donape.shared.entidades.Necesidad;

public class DetalleContract {
    public interface View {
        void mostrarProgress();
        void ocultarProgress();
        void mostrarDetalle(Necesidad necesidad);
        void mostrarErrorServidor();
        void mostrarErrorRed();
    }

    public interface Presenter {
        void onCreate(String solicitudId);
        void onRefresh(String solicitudId);
    }

    public interface Interactor {
        interface OnDetalleLoadFinished {
            void onDetalleLoadSuccess(Necesidad necesidad);
            void onDetalleLoadErrorServidor();
            void onDetalleLoadErrorRed();
        }

        void loadDetalle(String id, OnDetalleLoadFinished onDetalleLoadFinished);
    }
}
