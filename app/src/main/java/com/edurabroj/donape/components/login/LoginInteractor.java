package com.edurabroj.donape.components.login;

import android.util.Log;

import com.edurabroj.donape.shared.servicio.IService;
import com.edurabroj.donape.shared.servicio.ServiceProvider;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginInteractor implements LoginContract.Interactor {
    @Override
    public void getLoginRespuesta(String user, String password, final OnLoginFinishedListener onLoginFinishedListener) {
        IService service = ServiceProvider.getService();
        Call<LoginRespuesta> call = service.Login(user,password);

        call.enqueue(new Callback<LoginRespuesta>() {
            @Override
            public void onResponse(Call<LoginRespuesta> call, Response<LoginRespuesta> response) {
                if(response.isSuccessful()){
                    onLoginFinishedListener.onSuccessLogin(response.body());
                }else{
                    try {
                        Log.e("ERR",response.errorBody().string());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    onLoginFinishedListener.onFailedLogin();
                }
            }

            @Override
            public void onFailure(Call<LoginRespuesta> call, Throwable t) {
                onLoginFinishedListener.onFailure(t);
            }
        });
    }
}
