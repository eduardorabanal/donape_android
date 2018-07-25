package com.edurabroj.donape.Lista;

import com.edurabroj.donape.entidades.Solicitud;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class ListaPresenterTest {
    private ListaPresenter presenter;
    private ListaContract.View view;
    private ListaContract.Interactor interactor;

    @Before
    public void setUp() throws Exception {
        view = Mockito.mock(ListaContract.View.class);
        interactor = Mockito.mock(ListaContract.Interactor.class);
        presenter = new ListaPresenter(view, interactor);
    }

    @Test
    public void cuandoIniciaMuestraProgress() {
        presenter.onCreate();
        Mockito.verify(view).mostrarProgress();
    }

    @Test
    public void cuandoIniciaSolicitaLista() {
        presenter.onCreate();
        Mockito.verify(interactor).getLista(presenter);
    }

    @Test
    public void cuandoListaRefreshMuestraProgress() {
        presenter.onRefreshLista();
        Mockito.verify(view).mostrarProgress();
    }

    @Test
    public void cuandoListaRefreshSolicitaLista() {
        presenter.onRefreshLista();
        Mockito.verify(interactor).getLista(presenter);
    }

    @Test
    public void cuandoObtieneListaOcultaProgress() {
        presenter.onLoadListaSuccess(new ArrayList<Solicitud>());
        Mockito.verify(view).ocultarProgress();
    }

    @Test
    public void cuandoObtieneListaMuestraElementos() {
        presenter.onLoadListaSuccess(new ArrayList<Solicitud>());
        Mockito.verify(view).llenarLista(new ArrayList<Solicitud>());
    }

    @Test
    public void cuandoOcurreErrorServidorOcultaProgress() {
        presenter.onLoadListaErrorServidor();
        Mockito.verify(view).ocultarProgress();
    }

    @Test
    public void cuandoOcurreErrorServidorMuestraError() {
        presenter.onLoadListaErrorServidor();
        Mockito.verify(view).mostrarErrorServidor();
    }

    @Test
    public void cuandoOcurreErrorRedOcultaProgress() {
        presenter.onLoadListaErrorRed(new Throwable());
        Mockito.verify(view).ocultarProgress();
    }

    @Test
    public void cuandoOcurreErrorRedMuestraError() {
        presenter.onLoadListaErrorRed(new Throwable());
        Mockito.verify(view).mostrarErrorRed();
    }
}