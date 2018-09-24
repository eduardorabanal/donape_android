package com.edurabroj.donape.components.publicacion.lista;

import com.edurabroj.donape.shared.entidades.Publicacion;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class ListaPublicacionPresenter implements ListaPublicacion.Presenter{
    private ListaPublicacion.Interactor interactor;
    private ListaPublicacion.View view;

    private Disposable subscription;

    ListaPublicacionPresenter(ListaPublicacion.Interactor interactor) {
        this.interactor = interactor;
    }

    @Override
    public void setView(ListaPublicacion.View view) {
        this.view = view;
    }

    @Override
    public void onRefrescarLista() {
        if(view!=null){
            view.limpiarLista();
        }
        solicitarPublicaciones();
    }

    @Override
    public void onLogoutClick() {
        interactor.logout();
        if(view!=null){
            view.goToLogin();
        }
    }

    @Override
    public void onMisDonacionesClick() {
        if(view!=null){
            view.goToMisDonaciones();
        }
    }

    @Override
    public void onRetryLoadClick() {
        if(view!=null){
            view.limpiarLista();
        }
        solicitarPublicaciones();
    }

    @Override
    public void solicitarPublicaciones() {
        if(view!=null){
            view.mostrarLoading();
        }
        subscription = interactor.obtenerPublicaciones()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableObserver<Publicacion>(){
                    @Override
                    public void onNext(Publicacion publicacion) {
                        if(view!=null){
                            view.adjuntarPublicacion(publicacion);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        if(view!=null){
                            view.ocultarLoading();
                            view.mostrarErrorRed();
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
