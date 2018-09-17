package com.edurabroj.donape.components.donacion.mis_donaciones;

import android.content.Context;
import android.os.Handler;

import com.apollographql.apollo.ApolloClient;

import java.io.IOException;

import dagger.Module;
import dagger.Provides;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import static com.edurabroj.donape.shared.data.ApiData.GRAPHQL_ENDPOINT;
import static com.edurabroj.donape.shared.data.ApiData.HOST;
import static com.edurabroj.donape.shared.data.PreferencesData.TOKEN_KEY;
import static com.edurabroj.donape.shared.data.PreferencesData.TOKEN_PREV;
import static com.edurabroj.donape.shared.utils.PreferencesUtils.getStringPreference;

@Module
public class MisDonacionesModule {
    @Provides
    public MisDonaciones.Presenter providesPresenter(MisDonaciones.Interactor interactor){
        return new MisDonacionesPresenter(interactor);
    }
    @Provides
    public MisDonaciones.Interactor providesInteractor(ApolloClient client, Handler handler){
        return new MisDonacionesInteractor(client, handler);
    }
    @Provides
    ApolloClient providesApolloClient(final Context context){
        OkHttpClient okHttpClient = new OkHttpClient.Builder().addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request.Builder builder = chain.request()
                        .newBuilder();
                if(context!=null){
                    builder.addHeader("Authorization", TOKEN_PREV + getStringPreference(context,TOKEN_KEY));
                }
                Request request = builder.build();
                return chain.proceed(request);
            }
        }).build();

        return ApolloClient.builder()
                .serverUrl(HOST+GRAPHQL_ENDPOINT)
                .okHttpClient(okHttpClient)
                .build();
    }
}
