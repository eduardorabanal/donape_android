package com.edurabroj.donape.components.donacion.donar;

import android.os.Handler;
import android.os.Looper;

import com.apollographql.apollo.ApolloCall;
import com.apollographql.apollo.ApolloCallback;
import com.apollographql.apollo.ApolloClient;
import com.apollographql.apollo.api.Response;
import com.apollographql.apollo.exception.ApolloException;
import com.edurabroj.donape.DonacionCreateMutation;
import com.edurabroj.donape.shared.graphql.ClienteApolloProvider;

import javax.annotation.Nonnull;

public class DonarInteractor implements DonarContract.Interactor {
    DonarContract.Presenter presenter;

    public DonarInteractor(DonarContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void guardarDonacion(float cantidad, int necesidadId) {
        ApolloClient cliente = ClienteApolloProvider.getClient(presenter.getContext());
        cliente.mutate(DonacionCreateMutation.builder()
                .cantidad(cantidad)
                .necesidad(necesidadId)
                .build()).enqueue(new ApolloCallback<>(new ApolloCall.Callback<DonacionCreateMutation.Data>() {
            @Override
            public void onResponse(@Nonnull Response<DonacionCreateMutation.Data> response) {
                presenter.mostrarDonacionCorrecta();
            }

            @Override
            public void onFailure(@Nonnull ApolloException e) {
                presenter.mostrarErrorDonacion();
            }
        },new Handler(Looper.getMainLooper())));
    }
}
