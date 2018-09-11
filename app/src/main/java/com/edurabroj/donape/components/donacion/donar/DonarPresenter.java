package com.edurabroj.donape.components.donacion.donar;

public class DonarPresenter implements DonarContract.Presenter {
    DonarContract.View view;
    DonarContract.Interactor interactor;

    public DonarPresenter(DonarContract.View view) {
        this.view = view;
        interactor = new DonarInteractor(this);
    }

    @Override
    public void mostrarDonacionCorrecta() {
        view.mostrarDonacionCorrecta();
    }

    @Override
    public void mostrarErrorDonacion() {
        view.mostrarErrorDonacion();
    }

    @Override
    public void guardarDonacion(float cantidad, int necesidadId) {
        interactor.guardarDonacion(cantidad,necesidadId);
    }
}
