package com.edurabroj.donape.components.donacion.mis_donaciones;

import android.os.Handler;

import com.apollographql.apollo.ApolloClient;

import dagger.Module;
import dagger.Provides;

@Module
public class ModuleMisDonaciones {
    @Provides
    public MisDonaciones.Presenter providesPresenter(MisDonaciones.Interactor interactor){
        return new MisDonacionesPresenter(interactor);
    }
    @Provides
    public MisDonaciones.Interactor providesInteractor(RepoMisDonaciones repository){
        return new MisDonacionesInteractor(repository);
    }
    @Provides
    RepoMisDonaciones provideRepository(ApolloClient client, Handler handler){
        return new RepoMisDonacionesGraphql(client);
    }
}
