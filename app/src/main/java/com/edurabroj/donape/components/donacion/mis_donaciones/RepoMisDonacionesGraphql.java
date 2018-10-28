package com.edurabroj.donape.components.donacion.mis_donaciones;

import com.apollographql.apollo.ApolloCall;
import com.apollographql.apollo.ApolloClient;
import com.apollographql.apollo.api.Response;
import com.apollographql.apollo.rx2.Rx2Apollo;
import com.edurabroj.donape.DonacionesByUsuarioQuery;
import com.edurabroj.donape.shared.entidades.Donacion;
import com.edurabroj.donape.shared.entidades.Estado;
import com.edurabroj.donape.shared.entidades.Imagen;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.functions.Function;

public class RepoMisDonacionesGraphql implements RepoMisDonaciones {
    private ApolloClient client;

    RepoMisDonacionesGraphql(ApolloClient client) {
        this.client = client;
    }

    @Override
    public Observable<Donacion> getMyDonationsData() {
        DonacionesByUsuarioQuery query = DonacionesByUsuarioQuery.builder().build();
        ApolloCall<DonacionesByUsuarioQuery.Data> apolloCall = client.query(query);
        return Rx2Apollo.from(apolloCall)
                .toObservable()
                .concatMap((Response<DonacionesByUsuarioQuery.Data> dataResponse) ->
                        Observable.fromIterable(dataResponse.data().donacionesByUsuario())
                )
                .concatMap((Function<DonacionesByUsuarioQuery.DonacionesByUsuario, Observable<Donacion>>) donacionApi ->
                        Observable.just(new Donacion(){{
                            setId(donacionApi.id());
                            setCantidad(donacionApi.cantidad());
                            setTitulo(donacionApi.necesidad().publicacion().titulo());
                            setArticulo(donacionApi.necesidad().articulo());
                            List<Estado> estados = new ArrayList<>();
                            if(donacionApi.estados()!=null){
                                for(DonacionesByUsuarioQuery.Estado estado : donacionApi.estados() ){
                                    estados.add(new Estado(){{
                                        setNombre(estado.nombre());
                                        List<Imagen> imagenes = new ArrayList<>();
                                        if(estado.imagenes()!=null){
                                            for(DonacionesByUsuarioQuery.Imagene imagenApi : estado.imagenes()){
                                                imagenes.add(new Imagen(){{
                                                    setUrl(imagenApi.url());
                                                }});
                                            }
                                        }
                                        setImagenes(imagenes);
                                        setFecha(estado.fecha());
                                    }});
                                }
                            }
                            setEstados(estados);
                            setEstado(getEstados().size()>0 ? getEstados().get(0) : new Estado());
                            setFecha(getEstado().getFecha());
                        }})
                );
    }
}
