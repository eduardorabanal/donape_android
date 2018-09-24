package com.edurabroj.donape.components.publicacion.lista;

import com.apollographql.apollo.ApolloCall;
import com.apollographql.apollo.ApolloClient;
import com.apollographql.apollo.api.Response;
import com.apollographql.apollo.rx2.Rx2Apollo;
import com.edurabroj.donape.PublicacionesQuery;
import com.edurabroj.donape.shared.entidades.Imagen;
import com.edurabroj.donape.shared.entidades.Publicacion;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import io.reactivex.Observable;
import io.reactivex.functions.Function;

public class RepoListaPublicacionGraphql  implements RepoListaPublicacion {
    private ApolloClient client;

    public RepoListaPublicacionGraphql(ApolloClient client) {
        this.client = client;
    }

    @Override
    public Observable<Publicacion> getPublicacionesData() {
        PublicacionesQuery query = PublicacionesQuery.builder().build();
        ApolloCall<PublicacionesQuery.Data> apolloCall = client.query(query);
        return Rx2Apollo.from(apolloCall)
                .toObservable()
                .concatMap((Response<PublicacionesQuery.Data> dataResponse) ->
                        Observable.fromIterable(dataResponse.data().publicaciones())
                )
                .concatMap((Function<PublicacionesQuery.Publicacione, Observable<Publicacion>>) publicacionApi ->
                        Observable.just(new Publicacion(){{
                            setId(publicacionApi.id());
                            setTitulo(publicacionApi.titulo());
                            setDescripcion(publicacionApi.descripcion());
                            List<Imagen> imagenes = new ArrayList<>();
                            for(PublicacionesQuery.Imagene imagen : Objects.requireNonNull( publicacionApi.imagenes() ) ){
                                imagenes.add(new Imagen(){{
                                    setUrl(imagen.url());
                                }});
                            }
                            setImagenes(imagenes);
                        }})
                );
    }
}
