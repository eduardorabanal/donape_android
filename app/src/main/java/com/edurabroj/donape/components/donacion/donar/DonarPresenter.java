package com.edurabroj.donape.components.donacion.donar;

import android.content.Context;

public class DonarPresenter implements DonarContract.Presenter {
    DonarContract.View view;
    DonarContract.Interactor interactor;
    Context context;

    public DonarPresenter(DonarContract.View view, Context context) {
        this.view = view;
        interactor = new DonarInteractor(this);
        this.context=context;
    }

    public Context getContext() {
        return context;
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
