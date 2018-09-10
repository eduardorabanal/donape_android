package com.edurabroj.donape.shared.servicio;

import com.edurabroj.donape.components.login.LoginRespuesta;
import com.edurabroj.donape.shared.entidades.Necesidad;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface IService {
    @GET("necesidad/{grupoId}")
    Call<List<Necesidad>> ObtenerSolicitudesByGrupo(@Header("Authorization") String authToken, @Path("grupoId") int grupoId);

    @GET("necesidad/{id}")
    Call<Necesidad> ObtenerSolicitudById(@Header("Authorization") String authToken, @Path("id") String id);

    @POST("auth/login")
    @FormUrlEncoded
    Call<LoginRespuesta> Login(@Field("email") String email, @Field("password") String password);
}
