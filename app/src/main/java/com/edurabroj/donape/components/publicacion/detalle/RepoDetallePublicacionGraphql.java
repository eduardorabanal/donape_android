package com.edurabroj.donape.components.publicacion.detalle;

import com.apollographql.apollo.ApolloCall;
import com.apollographql.apollo.ApolloClient;
import com.apollographql.apollo.api.Response;
import com.apollographql.apollo.rx2.Rx2Apollo;
import com.edurabroj.donape.PublicacionQuery;
import com.edurabroj.donape.shared.entidades.Imagen;
import com.edurabroj.donape.shared.entidades.Necesidad;
import com.edurabroj.donape.shared.entidades.Publicacion;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import io.reactivex.Observable;
import io.reactivex.functions.Function;

public class RepoDetallePublicacionGraphql implements RepoDetallePublicacion {
    private ApolloClient client;

    public RepoDetallePublicacionGraphql(ApolloClient client) {
        this.client = client;
    }

    @Override
    public Observable<Publicacion> getPublicacionDetalleData(int id) {
        PublicacionQuery query = PublicacionQuery.builder().id(id).build();
        ApolloCall<PublicacionQuery.Data> apolloCall = client.query(query);
        return Rx2Apollo.from(apolloCall)
                .toObservable()
                .concatMap((Response<PublicacionQuery.Data> dataResponse) ->
                        Observable.just(dataResponse.data().publicacion())
                )
                .concatMap((Function<PublicacionQuery.Publicacion, Observable<Publicacion>>) publicacionApi ->
                        Observable.just(new Publicacion(){{
                            setId(publicacionApi.id());
                            setTitulo(publicacionApi.titulo());
                            setDescripcion(publicacionApi.descripcion());
                            List<Imagen> imagenes = new ArrayList<>();
                            for(PublicacionQuery.Imagene imagen : Objects.requireNonNull( publicacionApi.imagenes() ) ){
                                imagenes.add(new Imagen(){{
                                    setUrl(imagen.url());
                                }});
                            }
                            List<Necesidad> necesidades = new ArrayList<>();
                            for(PublicacionQuery.Necesidade necesidad : publicacionApi.necesidades()){
                                necesidades.add(new Necesidad(){{
                                    setId(necesidad.id());
                                    setArticulo(necesidad.articulo());
                                    setCantidad(necesidad.cantidad());
                                }});
                            }
                            setImagenes(imagenes);
                            setNecesidades(necesidades);
                        }})
                );
    }
}
