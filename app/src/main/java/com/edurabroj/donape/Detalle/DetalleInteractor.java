package com.edurabroj.donape.Detalle;

import com.edurabroj.donape.entidades.Solicitud;
import com.edurabroj.donape.preferences.IPreferences;
import com.edurabroj.donape.servicio.IService;
import com.edurabroj.donape.servicio.ServiceProvider;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.edurabroj.donape.data.PreferencesData.TOKEN_KEY;
import static com.edurabroj.donape.data.PreferencesData.TOKEN_PREV;

public class DetalleInteractor implements DetalleContract.Interactor {
    private IPreferences preferences;

    public DetalleInteractor(IPreferences preferences) {
        this.preferences = preferences;
    }

    @Override
    public void loadDetalle(String id, final OnDetalleLoadFinished onDetalleLoadFinished) {
        IService service = ServiceProvider.getService();
        String authToken = preferences.getStringPreference(TOKEN_KEY);

        Call<Solicitud> call = service.ObtenerSolicitudById(TOKEN_PREV + authToken, id);

        call.enqueue(new Callback<Solicitud>() {
            @Override
            public void onResponse(Call<Solicitud> call, Response<Solicitud> response) {
                if(response.isSuccessful()){
                    onDetalleLoadFinished.onDetalleLoadSuccess(response.body());
                }else {
                    onDetalleLoadFinished.onDetalleLoadErrorServidor();
                }
            }

            @Override
            public void onFailure(Call<Solicitud> call, Throwable t) {
                onDetalleLoadFinished.onDetalleLoadErrorRed();
            }
        });
    }
}
