package com.edurabroj.donape.components.publicacion.detalle;

import com.edurabroj.donape.shared.entidades.Publicacion;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

class DetallePublicacionPresenter implements DetallePublicacion.Presenter {
    private DetallePublicacion.View view;
    private DetallePublicacion.Interactor interactor;

    private Disposable subscription;

    DetallePublicacionPresenter(DetallePublicacion.Interactor interactor) {
        this.interactor = interactor;
    }

    @Override
    public void setView(DetallePublicacion.View view) {
        this.view = view;
    }

    @Override
    public void rxUnsubscribe() {
        if(subscription !=null && !subscription.isDisposed()){
            subscription.dispose();
        }
    }

    @Override
    public void onRefresh() {
        solicitarDetalle();
    }

    @Override
    public void onRetryLoadClick() {
        solicitarDetalle();
    }

    @Override
    public void solicitarDetalle() {
        String id = null;
        if(view!=null){
            view.mostrarLoading();
            id = view.getPublicacionId();
        }
        subscription = interactor.getDetallePublicacionData(Integer.parseInt(id))
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeWith(new DisposableObserver<Publicacion>(){
                            @Override
                            public void onNext(Publicacion publicacion) {
                                if(view!=null){
                                    view.mostrarDetalle(publicacion);
                                }
                            }

                            @Override
                            public void onError(Throwable e) {
                                e.printStackTrace();
                                if(view!=null){
                                    view.ocultarLoading();
                                    view.mostrarError();
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
}
