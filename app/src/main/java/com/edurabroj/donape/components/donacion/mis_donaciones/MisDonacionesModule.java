package com.edurabroj.donape.components.donacion.mis_donaciones;

import android.os.Handler;

import com.apollographql.apollo.ApolloClient;

import dagger.Module;
import dagger.Provides;

@Module
public class MisDonacionesModule {
    @Provides
    public MisDonaciones.Presenter providesPresenter(MisDonaciones.Interactor interactor){
        return new MisDonacionesPresenter(interactor);
    }
    @Provides
    public MisDonaciones.Interactor providesInteractor(MisDonacionesRepository repository){
        return new MisDonacionesInteractor(repository);
    }
    @Provides MisDonacionesRepository provideRepository(ApolloClient client, Handler handler){
        return new MisDonacionesRepositoryGraphql(client);
    }
}
