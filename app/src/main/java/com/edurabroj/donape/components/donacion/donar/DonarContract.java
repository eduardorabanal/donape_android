package com.edurabroj.donape.components.donacion.donar;

public interface DonarContract {
    interface View {
        void mostrarDonacionCorrecta();
        void mostrarErrorDonacion();
    }
    interface Presenter {
        void mostrarDonacionCorrecta();
        void mostrarErrorDonacion();
        void guardarDonacion(float cantidad, int necesidadId);
    }
    interface Interactor {
        void guardarDonacion(float cantidad, int necesidadId);
    }
}
