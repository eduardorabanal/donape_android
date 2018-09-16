package com.edurabroj.donape.components.donacion.donar;

import android.content.Context;

public interface DonarContract {
    interface View {
        void mostrarDonacionCorrecta();
        void mostrarErrorDonacion();
    }
    interface Presenter {
        void mostrarDonacionCorrecta();
        void mostrarErrorDonacion();
        void guardarDonacion(float cantidad, int necesidadId);
        Context getContext();
    }
    interface Interactor {
        void guardarDonacion(float cantidad, int necesidadId);
    }
}
