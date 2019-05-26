package com.example.zomato.client.api;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetroClient {

    private static final String ZOMATO_USER_KEY = "628a1abcfef726a6dc3a625c15c76089";


    public static Retrofit getClient(String baseUrl) {

        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        HttpLoggingInterceptor bodyInterceptor = new HttpLoggingInterceptor();
        bodyInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        HttpLoggingInterceptor headInterceptor = new HttpLoggingInterceptor();
        headInterceptor.setLevel(HttpLoggingInterceptor.Level.HEADERS);
        httpClient.addInterceptor(bodyInterceptor);
        httpClient.addInterceptor(headInterceptor);
        httpClient.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Interceptor.Chain chain) throws IOException {
                Request original = chain.request();

                Request request = original.newBuilder()
                        .header("user-key", ZOMATO_USER_KEY)
                        .method(original.method(), original.body())
                        .build();

                return chain.proceed(request);
            }
        });
        OkHttpClient client = httpClient.build();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();
        return retrofit;
    }
}
