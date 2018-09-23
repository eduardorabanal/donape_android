package com.edurabroj.donape.components.donacion.mis_donaciones;

import com.apollographql.apollo.ApolloCall;
import com.apollographql.apollo.ApolloClient;
import com.apollographql.apollo.api.Response;
import com.apollographql.apollo.rx2.Rx2Apollo;
import com.edurabroj.donape.DonacionesByUsuarioQuery;
import com.edurabroj.donape.shared.entidades.Donacion;

import io.reactivex.Observable;
import io.reactivex.functions.Function;

public class MisDonacionesRepositoryGraphql implements MisDonacionesRepository {
    private ApolloClient client;

    MisDonacionesRepositoryGraphql(ApolloClient client) {
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
                            setFecha(donacionApi.fecha());
                            setCantidad(donacionApi.cantidad());
                            setTitulo(donacionApi.necesidad().publicacion().titulo());
                            setArticulo(donacionApi.necesidad().articulo());
                            setEstado("Pendiente");
                        }})
                );
    }
}
