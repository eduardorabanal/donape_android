package com.edurabroj.donape.servicio;

import com.edurabroj.donape.entidades.LoginRespuesta;
import com.edurabroj.donape.entidades.Solicitud;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface IService {
    @Headers({
        "Authorization: Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiI1YWJmY2UwNGE0ODM2NzAwMTRjNDlmNzIiLCJleHAiOjE1MzIyOTE4ODh9.3r0zQGj-uNqem-CbaxJ_oZArr7zehGQC8Oq-ppWGF3E"
    })
    @GET("request")
    Call<List<Solicitud>> ObtenerPartidos();

    @POST("login")
    @FormUrlEncoded
    Call<LoginRespuesta> Login(@Field("email") String email, @Field("password") String password);
}
