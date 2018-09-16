package com.edurabroj.donape.shared.servicio;

import com.edurabroj.donape.components.login.LoginRespuesta;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface IService {
    @POST("login")
    @FormUrlEncoded
    Call<LoginRespuesta> Login(@Field("email") String email, @Field("password") String password);
}
