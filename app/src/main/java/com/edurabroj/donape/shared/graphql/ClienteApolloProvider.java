package com.edurabroj.donape.shared.graphql;

import android.app.Activity;
import android.content.Context;

import com.apollographql.apollo.ApolloClient;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;

import static com.edurabroj.donape.shared.data.ApiData.GRAPHQL_ENDPOINT;
import static com.edurabroj.donape.shared.data.ApiData.HOST;
import static com.edurabroj.donape.shared.data.PreferencesData.TOKEN_KEY;
import static com.edurabroj.donape.shared.data.PreferencesData.TOKEN_PREV;
import static com.edurabroj.donape.shared.utils.PreferencesUtils.getStringPreference;

public class ClienteApolloProvider {
    public static ApolloClient getClient(final Context context){
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
