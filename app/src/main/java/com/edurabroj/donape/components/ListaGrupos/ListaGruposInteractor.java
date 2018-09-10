package com.edurabroj.donape.components.ListaGrupos;

import android.util.Log;

import com.edurabroj.donape.shared.entidades.Necesidad;
import com.edurabroj.donape.shared.preferences.IPreferences;
import com.edurabroj.donape.shared.servicio.IService;
import com.edurabroj.donape.shared.servicio.ServiceProvider;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.edurabroj.donape.shared.data.PreferencesData.TOKEN_KEY;
import static com.edurabroj.donape.shared.data.PreferencesData.TOKEN_PREV;

public class ListaGruposInteractor implements ListaGruposContract.Interactor {
    IPreferences preferences;

    public ListaGruposInteractor(IPreferences preferences) {
        this.preferences = preferences;
    }

    @Override
    public void getLista(final OnLoadListaFinishedListener onLoadListaFinishedListener) {
        IService service = ServiceProvider.getService();
        String authToken = preferences.getStringPreference(TOKEN_KEY);

        Call<List<Necesidad>> call = service.ObtenerSolicitudesByGrupo(TOKEN_PREV + authToken, 1);

        call.enqueue(new Callback<List<Necesidad>>() {
            @Override
            public void onResponse(Call<List<Necesidad>> call, Response<List<Necesidad>> response) {
                if(response.isSuccessful()){
                    onLoadListaFinishedListener.onLoadListaSuccess(response.body());
                }else{
                    try {
                        Log.e("ERROR",response.errorBody().string());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    onLoadListaFinishedListener.onLoadListaErrorServidor();
                }
            }

            @Override
            public void onFailure(Call<List<Necesidad>> call, Throwable t) {
                onLoadListaFinishedListener.onLoadListaErrorRed(t);
            }
        });
    }
}
