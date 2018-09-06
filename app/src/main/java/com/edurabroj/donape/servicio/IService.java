package com.edurabroj.donape.servicio;

import com.edurabroj.donape.entidades.LoginRespuesta;
import com.edurabroj.donape.entidades.Necesidad;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface IService {
    @GET("necesidad")
    Call<List<Necesidad>> ObtenerSolicitudes(@Header("Authorization") String authToken);

    @GET("necesidad/{id}")
    Call<Necesidad> ObtenerSolicitudById(@Header("Authorization") String authToken, @Path("id") String id);

    @POST("usuario/login")
    @FormUrlEncoded
    Call<LoginRespuesta> Login(@Field("email") String email, @Field("password") String password);
}
