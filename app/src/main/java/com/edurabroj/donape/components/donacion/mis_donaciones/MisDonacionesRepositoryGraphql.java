package com.edurabroj.donape.components.donacion.mis_donaciones;

import android.os.Handler;

import com.apollographql.apollo.ApolloCall;
import com.apollographql.apollo.ApolloCallback;
import com.apollographql.apollo.ApolloClient;
import com.apollographql.apollo.exception.ApolloException;
import com.edurabroj.donape.DonacionesByUsuarioQuery;
import com.edurabroj.donape.shared.entidades.Donacion;
import com.edurabroj.donape.shared.entidades.Estado;
import com.edurabroj.donape.shared.entidades.Imagen;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nonnull;

public class MisDonacionesRepositoryGraphql implements MisDonacionesRepository {
    private ApolloClient client;
    private Handler handler;
    CallbackRepository callback;

    public MisDonacionesRepositoryGraphql(
            ApolloClient client,
            Handler handler
    ) {
        this.client = client;
        this.handler = handler;
    }

    public void setCallback(CallbackRepository callback) {
        this.callback = callback;
    }

    @Override
    public void obtenerMisDonaciones() {
        client.query(
                DonacionesByUsuarioQuery.builder()
                        .build())
                .enqueue(new ApolloCallback<>(new ApolloCall.Callback<DonacionesByUsuarioQuery.Data>() {
                    @Override
                    public void onResponse(@Nonnull com.apollographql.apollo.api.Response<DonacionesByUsuarioQuery.Data> response) {
                        List<Donacion> lista = new ArrayList<>();
                        for(final DonacionesByUsuarioQuery.DonacionesByUsuario donacion : response.data().donacionesByUsuario()){
                            lista.add(new Donacion(){{
                                setId(donacion.id());
                                setFecha(donacion.fecha());
                                setCantidad(donacion.cantidad());
                                setTitulo(donacion.necesidad().publicacion().titulo());
                                setArticulo(donacion.necesidad().articulo());
                                List<Estado> estados =  new ArrayList<>();
                                for(final DonacionesByUsuarioQuery.Estado estado : donacion.estados() ){
                                    final List<Imagen> imagenes = new ArrayList<>();
                                    for(final DonacionesByUsuarioQuery.Imagene img : estado.imagenes()){
                                        imagenes.add(new Imagen(){{
                                            setUrl(img.url());
                                        }});
                                    }
                                    estados.add(new Estado(){{
                                        setImagenes(imagenes);
                                        setFecha(estado.fecha());
                                        setNombre(estado.nombre());
                                    }});
                                }
                                setEstados(estados);
                                if(estados.size()>0){
                                    setEstado(estados.get(0).getNombre());
                                }
                            }});
                        }
                        if(callback!=null){
                            callback.onMisDonacionesSuccess(lista);
                        }
                    }

                    @Override
                    public void onFailure(@Nonnull ApolloException e) {
                        if(callback!=null) {
                            callback.onMisDonacionesError();
                        }
                    }
                },handler));
    }
}
