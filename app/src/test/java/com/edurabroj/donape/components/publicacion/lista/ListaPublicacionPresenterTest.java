package com.edurabroj.donape.components.publicacion.lista;

import com.edurabroj.donape.shared.entidades.Publicacion;
import com.edurabroj.donape.testRules.FakeSchedulerRule;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class ListaPublicacionPresenterTest {
    @Rule
    public final FakeSchedulerRule fakeSchedulerRule = new FakeSchedulerRule();

    private ListaPublicacionPresenter presenter;
    private ListaPublicacion.Interactor interactor;
    private ListaPublicacion.View view;

    private List<Publicacion> publicaciones = new ArrayList<Publicacion>(){{
        add(new Publicacion(){{
            setTitulo("Ayuda");
        }});
        add(new Publicacion(){{
            setTitulo("Ayuda2");
        }});
    }};

    @Before
    public void setUp() {

        interactor = mock(ListaPublicacion.Interactor.class);
        view = mock(ListaPublicacion.View.class);

        presenter = spy(new ListaPublicacionPresenter(interactor));
        presenter.setView(view);

        when(interactor.obtenerPublicaciones())
                .thenReturn(Observable.fromIterable(publicaciones));
    }

    @Test
    public void cuandoSolicitaDatos_muestraLoadingYLlamaInteractor() {
        presenter.solicitarPublicaciones();
        verify(view).mostrarLoading();
        verify(interactor).obtenerPublicaciones();
    }

    @Test
    public void cuandoObtieneTodosLosDatos_losMuestraEnLaVistaYOcultaLoading() {
        presenter.solicitarPublicaciones();
        verify(view,times(publicaciones.size())).adjuntarPublicacion(any());
        verify(view).ocultarLoading();
    }

    @Test
    public void cuandoHayError_ocultaLoadingYMuestraMensaje() {
        when(interactor.obtenerPublicaciones()).thenReturn(Observable.error(new Exception("error")));
        presenter.solicitarPublicaciones();
        verify(view).ocultarLoading();
        verify(view).mostrarErrorRed();
    }

    @Test
    public void cuandoRefrescaLista_limpiaListaYsolicitaPublicaciones() {
        presenter.onRefrescarLista();
        verify(view).limpiarLista();
        verify(presenter).solicitarPublicaciones();
    }

    @Test
    public void cuandoReintentarClicked_limpiaListaYsolicitaPublicaciones() {
        presenter.onRetryLoadClick();
        verify(view).limpiarLista();
        verify(presenter).solicitarPublicaciones();
    }

    @Test
    public void cuandoLogoutClicked_llamaInteractorYNavegaALogin() {
        presenter.onLogoutClick();
        verify(interactor).logout();
        verify(view).goToLogin();
    }

    @Test
    public void cuandoMisDonacionesClicked_NavegaAMisDonaciones() {
        presenter.onMisDonacionesClick();
        verify(view).goToMisDonaciones();
    }
}