package com.edurabroj.donape.components.publicacion.lista;

import android.util.Log;

import com.apollographql.apollo.ApolloCall;
import com.apollographql.apollo.ApolloClient;
import com.apollographql.apollo.exception.ApolloException;
import com.edurabroj.donape.ListarPublicaciones;
import com.edurabroj.donape.shared.entidades.Necesidad;
import com.edurabroj.donape.shared.graphql.ClienteApolloProvider;
import com.edurabroj.donape.shared.preferences.IPreferences;
import com.edurabroj.donape.shared.servicio.IService;
import com.edurabroj.donape.shared.servicio.ServiceProvider;

import java.io.IOException;
import java.util.List;

import javax.annotation.Nonnull;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.edurabroj.donape.shared.data.PreferencesData.TOKEN_KEY;
import static com.edurabroj.donape.shared.data.PreferencesData.TOKEN_PREV;

public class ListaPublicacionInteractor implements ListaPublicacionContract.Interactor {
    IPreferences preferences;

    public ListaPublicacionInteractor(IPreferences preferences) {
        this.preferences = preferences;
    }

    @Override
    public void getLista(final OnLoadListaFinishedListener onLoadListaFinishedListener) {
        ApolloClient cliente = ClienteApolloProvider.getClient();
        cliente.query(ListarPublicaciones.builder().build()).enqueue(new ApolloCall.Callback<ListarPublicaciones.Data>() {
            @Override
            public void onResponse(@Nonnull com.apollographql.apollo.api.Response<ListarPublicaciones.Data> response) {
                onLoadListaFinishedListener.onLoadListaSuccess(response.data());
            }

            @Override
            public void onFailure(@Nonnull ApolloException e) {
                onLoadListaFinishedListener.onLoadListaErrorServidor();
            }
        });

//        IService service = ServiceProvider.getService();
//        String authToken = preferences.getStringPreference(TOKEN_KEY);
//
//        Call<List<Necesidad>> call = service.ObtenerSolicitudesByGrupo(TOKEN_PREV + authToken, 1);
//
//        call.enqueue(new Callback<List<Necesidad>>() {
//            @Override
//            public void onResponse(Call<List<Necesidad>> call, Response<List<Necesidad>> response) {
//                if(response.isSuccessful()){
//                    onLoadListaFinishedListener.onLoadListaSuccess(response.body());
//                }else{
//                    try {
//                        Log.e("ERROR",response.errorBody().string());
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//                    onLoadListaFinishedListener.onLoadListaErrorServidor();
//                }
//            }
//
//            @Override
//            public void onFailure(Call<List<Necesidad>> call, Throwable t) {
//                onLoadListaFinishedListener.onLoadListaErrorRed(t);
//            }
//        });
    }
}
