package com.edurabroj.donape.components.publicacion.detalle;


import com.edurabroj.donape.shared.entidades.Necesidad;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

public class DetallePublicacionPresenterTest {
    private DetallePublicacionPresenter presenter;
    private DetallePublicacionContract.View view;
    private DetallePublicacionContract.Interactor interactor;

    @Before
    public void setUp() throws Exception {
        view = Mockito.mock(DetallePublicacionContract.View.class);
        interactor = Mockito.mock(DetallePublicacionContract.Interactor.class);
        presenter = new DetallePublicacionPresenter(view,interactor);
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

//    @Test
//    public void cuandoRecibeDetalleOcultaProgress() {
//        presenter.onDetalleLoadSuccess(new Necesidad());
//        Mockito.verify(view).ocultarProgress();
//    }

//    @Test
//    public void cuandoRecibeDetalleLoMuestra() {
//        Necesidad necesidad = new Necesidad();
//        presenter.onDetalleLoadSuccess(necesidad);
//        Mockito.verify(view).mostrarDetalle(necesidad);
//    }

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