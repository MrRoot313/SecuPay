package com.aqib.secupay.network;

import com.aqib.secupay.models.UserLogin;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;


public interface ApiService {

    //Login
    @Headers("Content-Type: application/json")
    @GET("basic-auth/{user}/{passwd}")
    Call<UserLogin> login(@Path("user") String userName,@Path("passwd") String password);

    //Getting Random Code
    @Headers("Content-Type: application/json")
    @GET("/status/{codes}")
    Call<ResponseBody> getRandomStatusCod(@Path("codes") String codes);

    //Getting Delay Request
    @Headers("Content-Type: application/json")
    @GET("/delay/{counter}")
    Call<ResponseBody> getDelayRequest(@Path("counter") int counter);

}
