package com.edurabroj.donape.http;

import android.content.Context;

import com.apollographql.apollo.ApolloClient;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;

import static com.edurabroj.donape.shared.data.ApiData.GRAPHQL_ENDPOINT;
import static com.edurabroj.donape.shared.data.ApiData.HOST;
import static com.edurabroj.donape.shared.data.PreferencesData.TOKEN_KEY;
import static com.edurabroj.donape.shared.data.PreferencesData.TOKEN_PREV;
import static com.edurabroj.donape.shared.utils.PreferencesUtils.getStringPreference;

@Module
public class HttpModule {
    @Provides
    @Singleton
    HttpLoggingInterceptor providesHttpBodyLoggingInterceptor(){
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        return interceptor;
    }

    @Provides
    ApolloClient providesApolloClient(HttpLoggingInterceptor interceptor, final Context context){
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .addInterceptor(chain -> {
                    Request.Builder builder = chain.request().newBuilder();
                    builder.addHeader("Authorization", TOKEN_PREV + getStringPreference(context,TOKEN_KEY));
                    Request request = builder.build();
                    return chain.proceed(request);
                }).build();

        return ApolloClient.builder()
                .serverUrl(HOST+GRAPHQL_ENDPOINT)
                .okHttpClient(okHttpClient)
                .build();
    }
}
