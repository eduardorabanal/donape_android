package com.edurabroj.donape.mvp;

public interface BasePresenter<V> {
    void attach(V view);
    void detach();
}
