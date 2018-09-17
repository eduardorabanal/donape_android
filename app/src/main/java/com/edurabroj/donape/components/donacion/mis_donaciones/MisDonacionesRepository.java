package com.edurabroj.donape.components.donacion.mis_donaciones;

import com.edurabroj.donape.shared.entidades.Donacion;

import java.util.List;

public interface MisDonacionesRepository {
    void obtenerMisDonaciones();
    void setCallback(CallbackRepository callback);

    interface CallbackRepository{
        void onMisDonacionesSuccess(List<Donacion> donaciones);
        void onMisDonacionesError();
    }
}
