package com.ju.drmostafizur.network;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import okhttp3.ConnectionSpec;
import okhttp3.OkHttpClient;
import okhttp3.Protocol;
import okhttp3.TlsVersion;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * <p/>
 * This is the main entry point for network communication. Use this class for instancing REST services which do the
 * actual communication.
 */
public class RestClient {

    /**
     * This is our main backend/server URL.
     */
   // public static final String BASEURL = "https://microfin360.com/";
    public static String BASEURL = "http://192.168.4.106/coop_360/";
   // public static  String BASEURL = "http://192.168.56.101/Microfin360-Core/";


   // public  static  String BASEURL = "http://192.168.1.148/";

//    public static final String REST_API_URL = "http://192.168.0.12:3000";

    //  private static Retrofit retrofit = null;

    public static Retrofit getClient() {
      //  String REST_API_URL = BASEURL+ MainApplication.getPreference().getString(PrefManager.sMfiName)+"/index.php/mobile_apps_api_fo/";
        String REST_API_URL = BASEURL+"index.php/dr/";
        // if (retrofit==null) {


        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);


        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        ConnectionSpec spec = new ConnectionSpec.Builder(ConnectionSpec.MODERN_TLS)
                .tlsVersions(TlsVersion.TLS_1_2, TlsVersion.TLS_1_1, TlsVersion.SSL_3_0)
                .allEnabledCipherSuites()
                .build();

        OkHttpClient client = new OkHttpClient();
        try {
            client = new OkHttpClient.Builder()
                    .addInterceptor(interceptor)
                    //.addNetworkInterceptor(new StethoInterceptor())
                    .protocols(Arrays.asList(Protocol.HTTP_2, Protocol.HTTP_1_1, Protocol.SPDY_3))
                    .sslSocketFactory(new TLSSocketFactory())
                    //.connectionSpecs(Collections.singletonList(spec))
                    .connectTimeout(300, TimeUnit.SECONDS)
                    .readTimeout(300, TimeUnit.SECONDS)
                    .writeTimeout(300, TimeUnit.SECONDS)
                    .retryOnConnectionFailure(true)
                    .build();
        } catch (KeyManagementException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(REST_API_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(client)
                .build();
        // }
        return retrofit;
    }

    public static <T> T getService(Class<T> serviceClass) {
        return getClient().create(serviceClass);
    }
}
