package com.edurabroj.donape.shared.graphql;

import com.apollographql.apollo.ApolloClient;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;

import static com.edurabroj.donape.shared.data.ApiData.HOST;

public class ClienteApolloProvider {
    public static ApolloClient getClient(){
        HttpLoggingInterceptor logginInterceptor = new HttpLoggingInterceptor();
        logginInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient okHttpClient = new OkHttpClient.Builder().addInterceptor(logginInterceptor).build();

        return ApolloClient.builder()
                .serverUrl(HOST+"graphql")
                .okHttpClient(okHttpClient)
                .build();
    }
}
