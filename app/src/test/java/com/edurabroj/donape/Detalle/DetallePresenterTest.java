package com.edurabroj.donape.Detalle;


import com.edurabroj.donape.entidades.Solicitud;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

public class DetallePresenterTest {
    private DetallePresenter presenter;
    private DetalleContract.View view;
    private DetalleContract.Interactor interactor;

    @Before
    public void setUp() throws Exception {
        view = Mockito.mock(DetalleContract.View.class);
        interactor = Mockito.mock(DetalleContract.Interactor.class);
        presenter = new DetallePresenter(view,interactor);
    }

    @Test
    public void cuandoIniciaMuestraProgress() {
        presenter.onCreate("1");
        Mockito.verify(view).mostrarProgress();
    }

    @Test
    public void cuandoIniciaSolicitaDetalle() {
        presenter.onCreate("1");
        Mockito.verify(interactor).loadDetalle("1",presenter);
    }

    @Test
    public void cuandoRefrescaMuestraProgress() {
        presenter.onRefresh("1");
        Mockito.verify(view).mostrarProgress();
    }

    @Test
    public void cuandoRefrescaSolicitaDetalle() {
        presenter.onRefresh("1");
        Mockito.verify(interactor).loadDetalle("1",presenter);
    }

    @Test
    public void cuandoRecibeDetalleOcultaProgress() {
        presenter.onDetalleLoadSuccess(new Solicitud());
        Mockito.verify(view).ocultarProgress();
    }

    @Test
    public void cuandoRecibeDetalleLoMuestra() {
        Solicitud solicitud = new Solicitud();
        presenter.onDetalleLoadSuccess(solicitud);
        Mockito.verify(view).mostrarDetalle(solicitud);
    }

    @Test
    public void cuandoRecibeErrorServidorOcultaProgress() {
        presenter.onDetalleLoadErrorServidor();
        Mockito.verify(view).ocultarProgress();
    }

    @Test
    public void cuandoRecibeErrorServidorMuestraMensaje() {
        presenter.onDetalleLoadErrorServidor();
        Mockito.verify(view).mostrarErrorServidor();
    }

    @Test
    public void cuandoRecibeErrorRedOcultaProgress() {
        presenter.onDetalleLoadErrorRed();
        Mockito.verify(view).ocultarProgress();
    }

    @Test
    public void cuandoRecibeErrorRedMuestraMensaje() {
        presenter.onDetalleLoadErrorRed();
        Mockito.verify(view).mostrarErrorRed();
    }
}