package com.edurabroj.donape.components.donacion.donar;

import com.edurabroj.donape.mvp.BasePresenter;
import com.edurabroj.donape.shared.entidades.Donacion;
import com.edurabroj.donape.shared.entidades.Necesidad;

import io.reactivex.Observable;


public interface DonarMVP {
    interface View {
        int getNecesidadId();
        float getCantidadADonar();

        void mostrarLoadingDetalleNecesidad();
        void ocultarLoadingDetalleNecesidad();
        void mostrarErrorAlObtenerDetalleNecesidad();
        void mostrarDetalleNecesidad(Necesidad necesidad);

        void mostrarLoadingGuardarDonacion();
        void ocultarLoadingGuardarDonacion();
        void mostrarErrorAlGuardarDonacion();
        void mostrarMensajeAlGuardarDonacionCorrectamente();
    }
    interface Presenter extends BasePresenter<View>{
        void solicitarDetalleNecesidad();
        void onRetrySolicitarDetalleNecesidadClicked();
        void onGuardarDonacionClicked();
        void onRefreshDetalleDonacion();
        void guardarDonacion();
    }
    interface Interactor {
        Observable<Necesidad> getNecesidadData(int necesidadId);
        Observable<Donacion> saveDonacionData(int necesidadId,float cantidad);
    }
}
