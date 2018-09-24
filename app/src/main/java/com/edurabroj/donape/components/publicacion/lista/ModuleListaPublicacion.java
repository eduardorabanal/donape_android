package com.edurabroj.donape.components.publicacion.lista;

import com.apollographql.apollo.ApolloClient;
import com.edurabroj.donape.components.session.RepoSession;

import dagger.Module;
import dagger.Provides;

@Module
public class ModuleListaPublicacion {
    @Provides
    public ListaPublicacion.Presenter providesPresenter(ListaPublicacion.Interactor interactor){
        return new ListaPublicacionPresenter(interactor);
    }
    @Provides
    public ListaPublicacion.Interactor providesInteractor(RepoListaPublicacion repoListaPublicacion, RepoSession repoSession){
        return new ListaPublicacionInteractor(repoListaPublicacion, repoSession);
    }
    @Provides RepoListaPublicacion provideRepository(ApolloClient client){
        return new RepoListaPublicacionGraphql(client);
    }
}
