package com.edurabroj.donape.components.donacion.donar;

import com.apollographql.apollo.ApolloCall;
import com.apollographql.apollo.ApolloClient;
import com.apollographql.apollo.api.Response;
import com.apollographql.apollo.rx2.Rx2Apollo;
import com.edurabroj.donape.DonacionCreateMutation;
import com.edurabroj.donape.DonacionesByUsuarioQuery;
import com.edurabroj.donape.shared.entidades.Donacion;

import io.reactivex.Observable;
import io.reactivex.functions.Function;

public class RepoDonarGraphql implements RepoDonar {
    private ApolloClient apolloClient;

    public RepoDonarGraphql(ApolloClient apolloClient) {
        this.apolloClient = apolloClient;
    }

    @Override
    public Observable<Donacion> saveDonacionData(int necesidadId, float cantidad) {
        DonacionCreateMutation mutation = DonacionCreateMutation.builder()
                .cantidad(cantidad)
                .necesidad(necesidadId)
                .build();
        ApolloCall<DonacionCreateMutation.Data> apolloCall = apolloClient.mutate(mutation);
        return Rx2Apollo.from(apolloCall)
                .toObservable()
                .concatMap((Response<DonacionCreateMutation.Data> dataResponse) ->
                        Observable.just(dataResponse.data().crearDonacion())
                )
                .concatMap((Function<DonacionCreateMutation.CrearDonacion, Observable<Donacion>>) donacionApi ->
                        Observable.just(new Donacion(){{
                            setId(donacionApi.id());
                            setFecha(donacionApi.fecha());
                            setCantidad(donacionApi.cantidad());
                            //todo: poner estado real
                            //todo: poner fecha  real
                        }})
                );
    }
}
