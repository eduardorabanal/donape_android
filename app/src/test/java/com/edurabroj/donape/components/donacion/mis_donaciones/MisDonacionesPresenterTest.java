package com.edurabroj.donape.components.donacion.mis_donaciones;

import com.edurabroj.donape.shared.entidades.Donacion;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.plugins.RxAndroidPlugins;
import io.reactivex.schedulers.Schedulers;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class MisDonacionesPresenterTest {
    private MisDonacionesPresenter presenter;
    private MisDonaciones.Interactor interactor;
    private MisDonaciones.View view;

    private List<Donacion> misDonaciones = new ArrayList<Donacion>(){{
        add(new Donacion(){{
            setTitulo("Ayuda");
        }});
        add(new Donacion(){{
            setTitulo("Ayuda2");
        }});
    }};

    @BeforeClass
    public static void setupClass() {
        RxAndroidPlugins.setInitMainThreadSchedulerHandler(__ -> Schedulers.trampoline());
    }

    @Before
    public void setUp() {
        interactor = mock(MisDonaciones.Interactor.class);
        view = mock(MisDonaciones.View.class);

        presenter = spy(new MisDonacionesPresenter(interactor));
        presenter.setView(view);

        when(interactor.obtenerMisDonaciones())
                .thenReturn(Observable.fromIterable(misDonaciones));
    }

    @Test
    public void cuandoRefrescarLista_limpiaDatosDeRecycler() {
        presenter.refrescarLista();
        verify(view).limpiarLista();
    }

    @Test
    public void cuandoRefrescarLista_llamaSolicitarMisDonaciones() {
        presenter.refrescarLista();
        verify(presenter).solicitarMisDonaciones();
    }

    @Test
    public void cuandoSolicitaDonaciones_muestraLoading() {
        presenter.solicitarMisDonaciones();
        verify(view).mostrarLoading();
    }
    @Test
    public void cuandoSolicitarDonaciones_obtieneDatosDeInteractor() {
        presenter.solicitarMisDonaciones();
        verify(interactor).obtenerMisDonaciones();
    }

    @Test
    public void cuandoObtenieneDonaciones_adjuntaEnListaYOcultaLoading() {
        presenter.solicitarMisDonaciones();
        verify(view,times(2)).adjuntarDonacion(any());
        verify(view).ocultarLoading();
    }
}