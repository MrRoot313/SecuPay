package com.aqib.secupay.network;

import com.aqib.secupay.utils.AppConstants;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;



public class RestClient {

    private static Retrofit retrofit = null;
    private ApiService apiService;
    public RestClient() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
       // if (SharedResourcesClass.LOG)
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(15, TimeUnit.SECONDS)
                .readTimeout(15, TimeUnit.SECONDS)
                .writeTimeout(15, TimeUnit.SECONDS)
                // .cache(new Cache(context.getCacheDir(), 10 * 1024 * 1024))
                .addInterceptor(interceptor)
               // .addInterceptor(BasicAuthenticator("user","pass"))
                .build();
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(AppConstants.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .client(client)
                    .build();
        }
        apiService = retrofit.create(ApiService.class);
    }

    public RestClient(String  userName,String password) {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        // if (SharedResourcesClass.LOG)
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(15, TimeUnit.SECONDS)
                .readTimeout(15, TimeUnit.SECONDS)
                .writeTimeout(15, TimeUnit.SECONDS)
                // .cache(new Cache(context.getCacheDir(), 10 * 1024 * 1024))
                .addInterceptor(interceptor)
                .addInterceptor(new Authenticator(userName,password))
                .build();
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(AppConstants.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .client(client)
                    .build();
        }
        apiService = retrofit.create(ApiService.class);
    }
    public ApiService getApiService() {
        return apiService;
    }
}
