package com.edurabroj.donape.components.publicacion.detalle;

import com.edurabroj.donape.shared.entidades.Publicacion;
import com.edurabroj.donape.testRules.FakeSchedulerRule;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import io.reactivex.Observable;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class DetallePublicacionPresenterTest {
    @Rule
    public final FakeSchedulerRule fakeSchedulerRule = new FakeSchedulerRule();

    private DetallePublicacionPresenter presenter;
    private DetallePublicacion.View view;
    private DetallePublicacion.Interactor interactor;

    private final String publicacionId = "12";
    private final Publicacion publicacion = new Publicacion(){{
        setId(Integer.parseInt(publicacionId));
    }};

    @Before
    public void setUp() throws Exception {
        view = mock(DetallePublicacion.View.class);
        interactor = mock(DetallePublicacion.Interactor.class);
        presenter = spy(new DetallePublicacionPresenter(interactor));
        presenter.setView(view);
        when(view.getPublicacionId()).thenReturn(publicacionId);
        when(interactor.getDetallePublicacionData(12)).thenReturn(Observable.just(publicacion));
    }

    @Test
    public void cuandoRefresca_solicitadetalle() {
        presenter.onRefresh();
        verify(presenter).solicitarDetalle();
    }

    @Test
    public void cuandoSolicitaDetalle_muestraLoading() {
        presenter.solicitarDetalle();
        verify(view).mostrarLoading();
    }
    @Test
    public void cuandoSolicitaDetalle_obtieneId() {
        presenter.solicitarDetalle();
        verify(view).getPublicacionId();
    }
    @Test
    public void cuandoSolicitaDetalle_solicitaDatosAInteractor() {
        presenter.solicitarDetalle();
        verify(interactor).getDetallePublicacionData(Integer.parseInt(publicacionId));
    }

    @Test
    public void cuandoObtieneDatos_losMuestra() {
        presenter.solicitarDetalle();
        verify(view).mostrarDetalle(publicacion);
    }

    @Test
    public void cuandoObtieneDatos_ocultaLoading() {
        presenter.solicitarDetalle();
        verify(view).ocultarLoading();
    }

    @Test
    public void cuandoHayError_loMuestraAlUsuario() {
        when(interactor.getDetallePublicacionData(12)).thenReturn(Observable.error(new Exception("Error")));
        presenter.solicitarDetalle();
        verify(view).mostrarError();
    }
    @Test
    public void cuandoHayError_ocultaLoading() {
        when(interactor.getDetallePublicacionData(12)).thenReturn(Observable.error(new Exception("Error")));
        presenter.solicitarDetalle();
        verify(view).ocultarLoading();
    }

    @Test
    public void cuandoReintentarClicked_solicitaDatos() {
        presenter.onRetryLoadClick();
        verify(presenter).solicitarDetalle();
    }
}