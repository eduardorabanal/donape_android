package com.edurabroj.donape.components.donacion.donar;

import com.apollographql.apollo.ApolloClient;

import dagger.Module;
import dagger.Provides;

@Module
public class ModuleDonar {
    @Provides
    DonarMVP.Presenter providePresenter(DonarMVP.Interactor interactor){
        return new DonarPresenter(interactor);
    }

    @Provides
    DonarMVP.Interactor provideInteractor(RepoDonar repoDonar, RepoNecesidad repoNecesidad){
        return new DonarInteractor(repoDonar, repoNecesidad);
    }

    @Provides
    RepoDonar provideRepoDonar(ApolloClient apolloClient){
        return new RepoDonarGraphql(apolloClient);
    }

    @Provides
    RepoNecesidad provideRepoNecesidad(ApolloClient apolloClient){
        return new RepoNecesidadGraphql(apolloClient);
    }
}
