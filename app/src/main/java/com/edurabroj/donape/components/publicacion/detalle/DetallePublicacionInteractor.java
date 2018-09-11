package com.edurabroj.donape.components.publicacion.detalle;

import android.os.Handler;
import android.os.Looper;

import com.apollographql.apollo.ApolloCall;
import com.apollographql.apollo.ApolloCallback;
import com.apollographql.apollo.ApolloClient;
import com.apollographql.apollo.exception.ApolloException;
import com.edurabroj.donape.PublicacionQuery;
import com.edurabroj.donape.shared.entidades.Necesidad;
import com.edurabroj.donape.shared.graphql.ClienteApolloProvider;
import com.edurabroj.donape.shared.preferences.IPreferences;
import com.edurabroj.donape.shared.servicio.IService;
import com.edurabroj.donape.shared.servicio.ServiceProvider;

import javax.annotation.Nonnull;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.edurabroj.donape.shared.data.PreferencesData.TOKEN_KEY;
import static com.edurabroj.donape.shared.data.PreferencesData.TOKEN_PREV;

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
