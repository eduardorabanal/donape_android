package com.edurabroj.donape.components.publicacion.lista;

import com.edurabroj.donape.shared.entidades.Publicacion;

import io.reactivex.Observable;

public interface ListaPublicacion {
    interface View {
        void mostrarLoading();
        void ocultarLoading();
        void limpiarLista();
        void adjuntarPublicacion(Publicacion publicacion);
        void mostrarErrorRed();
        void goToLogin();
        void goToMisDonaciones();
    }

    interface Presenter {
        void setView(View view);
        void onRefrescarLista();
        void onLogoutClick();
        void onMisDonacionesClick();
        void onRetryLoadClick();
        void solicitarPublicaciones();
        void rxUnsubscribe();
    }

    interface Interactor {
        Observable<Publicacion> obtenerPublicaciones();
        void logout();
    }
}
