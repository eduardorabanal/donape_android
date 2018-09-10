package com.edurabroj.donape.shared.servicio;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.edurabroj.donape.shared.data.ApiData.HOST;

public class ServiceProvider {
    private static IService service;

    public static IService getService(){
        if(service==null){
            service = new Retrofit.Builder()
                    .baseUrl(HOST)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                    .create(IService.class);
        }
        return service;
    }
}
