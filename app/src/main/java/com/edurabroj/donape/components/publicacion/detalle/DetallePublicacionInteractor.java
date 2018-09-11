package com.edurabroj.donape.components.publicacion.detalle;

import android.os.Handler;
import android.os.Looper;

import com.apollographql.apollo.ApolloCall;
import com.apollographql.apollo.ApolloCallback;
import com.apollographql.apollo.ApolloClient;
import com.apollographql.apollo.exception.ApolloException;
import com.edurabroj.donape.PublicacionQuery;
import com.edurabroj.donape.shared.graphql.ClienteApolloProvider;
import com.edurabroj.donape.shared.preferences.IPreferences;

import javax.annotation.Nonnull;

public class DetallePublicacionInteractor implements DetallePublicacionContract.Interactor {
    private IPreferences preferences;

    public DetallePublicacionInteractor(IPreferences preferences) {
        this.preferences = preferences;
    }

    @Override
    public void loadDetalle(String id, final OnDetalleLoadFinished onDetalleLoadFinished) {
        ApolloClient cliente = ClienteApolloProvider.getClient();
        cliente.query(
                PublicacionQuery.builder()
                .id(Integer.parseInt(id))
                .build())
        .enqueue(new ApolloCallback<>(new ApolloCall.Callback<PublicacionQuery.Data>() {
            @Override
            public void onResponse(@Nonnull com.apollographql.apollo.api.Response<PublicacionQuery.Data> response) {
                onDetalleLoadFinished.onDetalleLoadSuccess(response.data());
            }

            @Override
            public void onFailure(@Nonnull ApolloException e) {
                onDetalleLoadFinished.onDetalleLoadErrorServidor();
            }
        },new Handler(Looper.getMainLooper())));
    }
}
