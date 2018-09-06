package com.edurabroj.donape.servicio;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServiceProvider {
    private static IService service;

    public static IService getService(){
        if(service==null){
            service = new Retrofit.Builder()
                    .baseUrl("http://192.168.0.15:8001/api/v1/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                    .create(IService.class);
        }
        return service;
    }
}
