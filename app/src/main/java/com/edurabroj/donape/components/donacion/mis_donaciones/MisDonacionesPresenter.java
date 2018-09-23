package com.edurabroj.donape.components.donacion.mis_donaciones;

import com.edurabroj.donape.shared.entidades.Donacion;

import javax.annotation.Nullable;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class MisDonacionesPresenter implements MisDonaciones.Presenter{
    @Nullable
    private MisDonaciones.View view;
    private MisDonaciones.Interactor interactor;

    private Disposable subscription;

    MisDonacionesPresenter(MisDonaciones.Interactor interactor) {
        this.interactor = interactor;
    }

    @Override
    public void setView(MisDonaciones.View view) {
        this.view=view;
    }

    @Override
    public void refrescarLista() {
        if(view!=null){
            view.limpiarLista();
        }
        solicitarMisDonaciones();
    }

    @Override
    public void solicitarMisDonaciones() {
        if(view!=null){
            view.mostrarLoading();
        }
        subscription = interactor.obtenerMisDonaciones()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableObserver<Donacion>() {
                    @Override
                    public void onNext(Donacion donacion) {
                        if(view!=null){
                            view.adjuntarDonacion(donacion);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        if(view!=null){
                            view.ocultarLoading();
                            view.mostrarErrorServidor();
                        }
                    }

                    @Override
                    public void onComplete() {
                        if(view!=null){
                            view.ocultarLoading();
                        }
                    }
                });
    }

    @Override
    public void rxUnsubscribe() {
        if(subscription !=null && !subscription.isDisposed()){
            subscription.dispose();
        }
    }
}
