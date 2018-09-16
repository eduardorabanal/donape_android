package com.edurabroj.donape.components.publicacion.lista;

import android.os.Handler;
import android.os.Looper;

import com.apollographql.apollo.ApolloCall;
import com.apollographql.apollo.ApolloCallback;
import com.apollographql.apollo.ApolloClient;
import com.apollographql.apollo.exception.ApolloException;
import com.edurabroj.donape.PublicacionesQuery;
import com.edurabroj.donape.shared.graphql.ClienteApolloProvider;
import com.edurabroj.donape.shared.preferences.IPreferences;

import javax.annotation.Nonnull;

public class ListaPublicacionInteractor implements ListaPublicacionContract.Interactor {
    IPreferences preferences;

    public ListaPublicacionInteractor(IPreferences preferences) {
        this.preferences = preferences;
    }

    @Override
    public void getLista(final OnLoadListaFinishedListener onLoadListaFinishedListener) {
        ApolloClient cliente = ClienteApolloProvider.getClient(null);
        cliente.query(
                PublicacionesQuery
                        .builder()
                        .build()
        ).enqueue(new ApolloCallback<>(new ApolloCall.Callback<PublicacionesQuery.Data>() {
            @Override
            public void onResponse(@Nonnull com.apollographql.apollo.api.Response<PublicacionesQuery.Data> response) {
                onLoadListaFinishedListener.onLoadListaSuccess(response.data());
            }

            @Override
            public void onFailure(@Nonnull ApolloException e) {
                onLoadListaFinishedListener.onLoadListaErrorServidor();
            }
        },new Handler(Looper.getMainLooper())));
    }
}
