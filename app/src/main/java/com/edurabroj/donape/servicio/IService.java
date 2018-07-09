package com.edurabroj.donape.servicio;

import com.edurabroj.donape.entidades.Solicitud;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;

public interface IService {
    @Headers({
        "Authorization: Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiI1YWJmY2UwNGE0ODM2NzAwMTRjNDlmNzIiLCJleHAiOjE1MzIyOTE4ODh9.3r0zQGj-uNqem-CbaxJ_oZArr7zehGQC8Oq-ppWGF3E"
    })
    @GET("request")
    Call<List<Solicitud>> ObtenerPartidos();
}
