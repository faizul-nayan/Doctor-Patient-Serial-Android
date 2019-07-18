package com.ju.drmostafizur.network;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.ju.drmostafizur.MyApplication;
import com.ju.drmostafizur.utills.MySharePreferences;
import com.ju.drmostafizur.utills.Values;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.Protocol;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Faizul Haque Nayan on 18/10/17.
 */

public class ApiClient {

    private static Retrofit retrofit = null;

    public static Retrofit getClient() {
        String hostName = MyApplication.getMySharePreferences().getString(MySharePreferences.HOST);
        // if (retrofit == null) {
        String END_URL = Values.BASE_URL+hostName+"/index.php/mobile_apps_api_fo/";
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .protocols(Arrays.asList(Protocol.HTTP_2, Protocol.HTTP_1_1, Protocol.SPDY_3))
                .connectTimeout(2000, TimeUnit.SECONDS)
                .readTimeout(20000, TimeUnit.SECONDS)
                .writeTimeout(10000, TimeUnit.SECONDS)
                .retryOnConnectionFailure(true)
                .build();

        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        retrofit = new Retrofit.Builder()
                .baseUrl(END_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();
        //  }


        return retrofit;
    }
}
