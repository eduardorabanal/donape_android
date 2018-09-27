package com.edurabroj.donape.components.donacion.donar;

import com.edurabroj.donape.shared.entidades.Donacion;
import com.edurabroj.donape.shared.entidades.Necesidad;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class DonarPresenter implements DonarMVP.Presenter {
    private DonarMVP.View view;
    private DonarMVP.Interactor interactor;

    private Disposable
        disposableGuardarDonacion,
        disposableDetalleNecesidad;

    public DonarPresenter(DonarMVP.Interactor interactor) {
        this.interactor = interactor;
    }

    @Override
    public void onGuardarDonacionClicked() {
        this.guardarDonacion();
    }

    @Override
    public void onRefreshDetalleDonacion() {
        this.solicitarDetalleNecesidad();
    }

    @Override
    public void solicitarDetalleNecesidad() {
        int necesidadId = 0;
        if(view!=null){
            view.mostrarLoadingDetalleNecesidad();
            necesidadId = view.getNecesidadId();
        }
        disposableDetalleNecesidad = interactor.getNecesidadData(necesidadId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableObserver<Necesidad>(){
                    @Override
                    public void onNext(Necesidad necesidad) {
                        if(view!=null){
                            view.mostrarDetalleNecesidad(necesidad);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        if(view!=null){
                            view.ocultarLoadingDetalleNecesidad();
                            view.mostrarErrorAlObtenerDetalleNecesidad();
                        }
                    }

                    @Override
                    public void onComplete() {
                        if(view!=null){
                            view.ocultarLoadingDetalleNecesidad();
                        }
                    }
                });
    }

    @Override
    public void onRetrySolicitarDetalleNecesidadClicked() {
        this.solicitarDetalleNecesidad();
    }

    @Override
    public void guardarDonacion() {
        int necesidadId = 0;
        float cantidad = 0;

        if(view!=null){
            necesidadId = view.getNecesidadId();
            cantidad = view.getCantidadADonar();
            view.mostrarLoadingGuardarDonacion();
        }

        disposableGuardarDonacion = interactor.saveDonacionData(necesidadId,cantidad)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableObserver<Donacion>() {
                    @Override
                    public void onNext(Donacion donacion) {

                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        if(view!=null){
                            view.ocultarLoadingGuardarDonacion();
                            view.mostrarErrorAlGuardarDonacion();
                        }
                    }

                    @Override
                    public void onComplete() {
                        if(view!=null){
                            view.ocultarLoadingGuardarDonacion();
                            view.mostrarMensajeAlGuardarDonacionCorrectamente();
                        }
                    }
                });
    }

    @Override
    public void attach(DonarMVP.View view) {
        this.view=view;
    }

    @Override
    public void detach() {
        if(disposableGuardarDonacion !=null && !disposableGuardarDonacion.isDisposed()){
            disposableGuardarDonacion.dispose();
        }
        if(disposableDetalleNecesidad !=null && !disposableDetalleNecesidad.isDisposed()){
            disposableDetalleNecesidad.dispose();
        }
    }
}
