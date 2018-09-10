package com.edurabroj.donape.components.necesidad.detalle;

import com.edurabroj.donape.shared.entidades.Necesidad;
import com.edurabroj.donape.shared.preferences.IPreferences;
import com.edurabroj.donape.shared.servicio.IService;
import com.edurabroj.donape.shared.servicio.ServiceProvider;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.edurabroj.donape.shared.data.PreferencesData.TOKEN_KEY;
import static com.edurabroj.donape.shared.data.PreferencesData.TOKEN_PREV;

public class DetalleNecesidadInteractor implements DetalleNecesidadContract.Interactor {
    private IPreferences preferences;

    public DetalleNecesidadInteractor(IPreferences preferences) {
        this.preferences = preferences;
    }

    @Override
    public void loadDetalle(String id, final OnDetalleLoadFinished onDetalleLoadFinished) {
        IService service = ServiceProvider.getService();
        String authToken = preferences.getStringPreference(TOKEN_KEY);

        Call<Necesidad> call = service.ObtenerSolicitudById(TOKEN_PREV + authToken, id);

        call.enqueue(new Callback<Necesidad>() {
            @Override
            public void onResponse(Call<Necesidad> call, Response<Necesidad> response) {
                if(response.isSuccessful()){
                    onDetalleLoadFinished.onDetalleLoadSuccess(response.body());
                }else {
                    onDetalleLoadFinished.onDetalleLoadErrorServidor();
                }
            }

            @Override
            public void onFailure(Call<Necesidad> call, Throwable t) {
                onDetalleLoadFinished.onDetalleLoadErrorRed();
            }
        });
    }
}
