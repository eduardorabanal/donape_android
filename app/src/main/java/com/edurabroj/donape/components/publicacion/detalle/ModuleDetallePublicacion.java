package com.edurabroj.donape.components.publicacion.detalle;

import com.apollographql.apollo.ApolloClient;

import dagger.Module;
import dagger.Provides;

@Module
public class ModuleDetallePublicacion {
    @Provides
    DetallePublicacion.Presenter providePresenter(DetallePublicacion.Interactor interactor){
        return new DetallePublicacionPresenter(interactor);
    }

    @Provides
    DetallePublicacion.Interactor provideInteractor(RepoDetallePublicacion repoDetallePublicacion){
        return new DetallePublicacionInteractor(repoDetallePublicacion);
    }

    @Provides
    RepoDetallePublicacion provideRepo(ApolloClient apolloClient){
        return new RepoDetallePublicacionGraphql(apolloClient);
    }


}
