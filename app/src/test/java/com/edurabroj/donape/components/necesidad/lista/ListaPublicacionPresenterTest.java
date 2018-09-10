package com.edurabroj.donape.components.necesidad.lista;

import com.edurabroj.donape.shared.entidades.Necesidad;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;

public class ListaPublicacionPresenterTest {
    private ListaNecesidadPresenter presenter;
    private ListaNecesidadContract.View view;
    private ListaNecesidadContract.Interactor interactor;

    @Before
    public void setUp() throws Exception {
        view = Mockito.mock(ListaNecesidadContract.View.class);
        interactor = Mockito.mock(ListaNecesidadContract.Interactor.class);
        presenter = new ListaNecesidadPresenter(view, interactor);
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
        presenter.onLoadListaSuccess(new ArrayList<Necesidad>());
        Mockito.verify(view).ocultarProgress();
    }

    @Test
    public void cuandoObtieneListaMuestraElementos() {
        presenter.onLoadListaSuccess(new ArrayList<Necesidad>());
        Mockito.verify(view).llenarLista(new ArrayList<Necesidad>());
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