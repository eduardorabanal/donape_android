package com.edurabroj.donape.components.donacion.donar;

import com.edurabroj.donape.shared.entidades.Donacion;
import com.edurabroj.donape.shared.entidades.Necesidad;
import com.edurabroj.donape.testRules.FakeSchedulerRule;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import io.reactivex.Observable;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class DonarPresenterTest {
    @Rule
    public final FakeSchedulerRule fakeSchedulerRule = new FakeSchedulerRule();

    private DonarPresenter presenter;
    private DonarMVP.Interactor interactor;
    private DonarMVP.View view;

    private float fakeCantidad = 12.5f;
    private int fakeNecesidadId = 101;
    private Donacion fakeDonacionGuardada = new Donacion(){{
        setCantidad(fakeCantidad);
        setId(1);
    }};
    private Necesidad fakeNecesidad = new Necesidad(){{
       setId(fakeNecesidadId);
       setArticulo("fake articulo");
    }};

    @Before
    public void setUp(){
        interactor = mock(DonarMVP.Interactor.class);
        view = mock(DonarMVP.View.class);

        presenter = spy(new DonarPresenter(interactor));
        presenter.attach(view);
        when(view.getCantidadADonar()).thenReturn(fakeCantidad);
        when(view.getNecesidadId()).thenReturn(fakeNecesidadId);
        when(interactor.saveDonacionData(fakeNecesidadId,fakeCantidad))
                .thenReturn( Observable.just(fakeDonacionGuardada) );
        when(interactor.getNecesidadData(fakeNecesidadId))
                .thenReturn(Observable.just(fakeNecesidad));
    }

    @Test
    public void cuandoGuardarDonacionClicked_guardaDonacion() {
        presenter.onGuardarDonacionClicked();
        verify(presenter).guardarDonacion();
    }

    @Test
    public void cuandoGuardaDonacion_muestraLoading() {
        presenter.guardarDonacion();
        verify(view).mostrarLoadingGuardarDonacion();
    }

    @Test
    public void cuandoGuardaDonacion_llamaInteractorConDatosCorrectos() {
        presenter.guardarDonacion();
        verify(view).getCantidadADonar();
        verify(view).getNecesidadId();
        verify(interactor).saveDonacionData(fakeNecesidadId,fakeCantidad);
    }

    @Test
    public void cuandoHayErrorAlGuardarDonacion_ocultaLoadingYMuestraMensajeAlUsuario() {
        when(interactor.saveDonacionData(fakeNecesidadId,fakeCantidad))
                .thenReturn(Observable.error(new Exception()));
        presenter.guardarDonacion();
        verify(view).ocultarLoadingGuardarDonacion();
        verify(view).mostrarErrorAlGuardarDonacion();
    }

    @Test
    public void cuandoGuardaDonacionCorrectamente_ocultaLoadingYmuestaMensajeAlUsuario() {
        presenter.guardarDonacion();
        verify(view).ocultarLoadingGuardarDonacion();
        verify(view).mostrarMensajeAlGuardarDonacionCorrectamente();
    }

    @Test
    public void cuandoSolicitaDetalle_muestraLoadingYSolicitaNecesidadId() {
        presenter.solicitarDetalleNecesidad();
        verify(view).mostrarLoadingDetalleNecesidad();
        verify(view).getNecesidadId();
    }

    @Test
    public void cuandoSolicitaDetalle_llamaInteractorConIdCorrecto() {
        presenter.solicitarDetalleNecesidad();
        verify(interactor).getNecesidadData(fakeNecesidadId);
    }

    @Test
    public void cuandoObtieneDetalle_ocultaLoadingYMuestraDetalle() {
        presenter.solicitarDetalleNecesidad();
        verify(view).ocultarLoadingDetalleNecesidad();
        verify(view).mostrarDetalleNecesidad(fakeNecesidad);
    }

    @Test
    public void cuandoHayErrorAlObtenerDetalle_ocultaLoadingYMuestraMensajeAlUsuario() {
        when(interactor.getNecesidadData(fakeNecesidadId)).thenReturn(Observable.error(new Exception()));
        presenter.solicitarDetalleNecesidad();
        verify(view).ocultarLoadingDetalleNecesidad();
        verify(view).mostrarErrorAlObtenerDetalleNecesidad();
    }

    @Test
    public void cuandoReintentarCargarDetalleClicked_solicitaDetalle() {
        presenter.onRetrySolicitarDetalleNecesidadClicked();
        verify(presenter).solicitarDetalleNecesidad();
    }
}