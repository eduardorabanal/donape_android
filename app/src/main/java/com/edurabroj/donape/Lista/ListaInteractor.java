package com.edurabroj.donape.Lista;

import android.util.Log;

import com.edurabroj.donape.entidades.LoginRespuesta;
import com.edurabroj.donape.entidades.Solicitud;
import com.edurabroj.donape.preferences.IPreferences;
import com.edurabroj.donape.servicio.IService;
import com.edurabroj.donape.servicio.ServiceProvider;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.edurabroj.donape.data.PreferencesData.TOKEN_KEY;
import static com.edurabroj.donape.data.PreferencesData.TOKEN_PREV;

public class ListaInteractor implements ListaContract.Interactor {
    IPreferences preferences;

    public ListaInteractor(IPreferences preferences) {
        this.preferences = preferences;
    }

    @Override
    public void getLista(final OnLoadListaFinishedListener onLoadListaFinishedListener) {
        IService service = ServiceProvider.getService();
        String authToken = preferences.getStringPreference(TOKEN_KEY);

        Call<List<Solicitud>> call = service.ObtenerSolicitudes(TOKEN_PREV + authToken);

        call.enqueue(new Callback<List<Solicitud>>() {
            @Override
            public void onResponse(Call<List<Solicitud>> call, Response<List<Solicitud>> response) {
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
            public void onFailure(Call<List<Solicitud>> call, Throwable t) {
                onLoadListaFinishedListener.onLoadListaErrorRed(t);
            }
        });
    }
}
