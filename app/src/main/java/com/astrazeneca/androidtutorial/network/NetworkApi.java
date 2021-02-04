package com.astrazeneca.androidtutorial.network;

import com.astrazeneca.androidtutorial.Util;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetworkApi {

    private static Retrofit retrofit = null;

    public static Retrofit getClient() {

        if (retrofit==null) {

            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);

            //   OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

            OkHttpClient okHttpClient = TrustUnsafe.getUnsafeOkHttpClient();
            OkHttpClient.Builder builder = okHttpClient.newBuilder();
            builder.connectTimeout(15, TimeUnit.SECONDS);
            builder.readTimeout(30, TimeUnit.SECONDS);
            // builder.writeTimeout(15, TimeUnit.SECONDS);

            //    OkHttpClient okHttpClient = TrustUnsafe.getUnsafeOkHttpClient();

            builder.addInterceptor(logging);

            retrofit = new Retrofit.Builder()
                    .baseUrl(Util.FIREBASE_ROOTURL)
                    .client(builder.build())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
