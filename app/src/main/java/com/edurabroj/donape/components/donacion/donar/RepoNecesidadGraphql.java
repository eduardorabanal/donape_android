package com.edurabroj.donape.components.donacion.donar;

import com.apollographql.apollo.ApolloCall;
import com.apollographql.apollo.ApolloClient;
import com.apollographql.apollo.api.Response;
import com.apollographql.apollo.rx2.Rx2Apollo;
import com.edurabroj.donape.NecesidadQuery;
import com.edurabroj.donape.shared.entidades.Necesidad;

import io.reactivex.Observable;
import io.reactivex.functions.Function;

public class RepoNecesidadGraphql implements RepoNecesidad {
    ApolloClient apolloClient;

    public RepoNecesidadGraphql(ApolloClient apolloClient) {
        this.apolloClient = apolloClient;
    }

    @Override
    public Observable<Necesidad> getNecesidadData(int necesidadId) {
        NecesidadQuery query = NecesidadQuery.builder()
                .id(necesidadId)
                .build();
        ApolloCall<NecesidadQuery.Data> apolloCall = apolloClient.query(query);
        return Rx2Apollo.from(apolloCall)
                .toObservable()
                .concatMap((Response<NecesidadQuery.Data> dataResponse) ->
                        Observable.just(dataResponse.data().necesidad())
                )
                .concatMap((Function<NecesidadQuery.Necesidad, Observable<Necesidad>>) necesidadApi ->
                        Observable.just(new Necesidad(){{
                            setId(necesidadApi.id());
                            setCantidad(necesidadApi.cantidad_requerida());
                            setArticulo(necesidadApi.articulo());
                        }})
                );
    }
}
