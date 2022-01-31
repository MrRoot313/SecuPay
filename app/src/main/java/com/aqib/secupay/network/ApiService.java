package com.aqib.secupay.network;

import com.aqib.secupay.models.DelayedRequestModel;
import com.aqib.secupay.models.UserLogin;
import com.aqib.secupay.utils.AppWebServices;

import java.util.HashMap;
import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.HeaderMap;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
import retrofit2.http.Path;


public interface ApiService {

    //Login
    @Headers("Content-Type: application/json")
    @GET("basic-auth/{user}/{passwd}")
    Call<UserLogin> login(@Path("user") String userName,@Path("passwd") String password);

    @Headers("Content-Type: application/json")
    @GET("/status/{codes}")
    Call<ResponseBody> getRandomStatusCod(@Path("codes") String codes);

    @Headers("Content-Type: application/json")
    @GET("/delay/{counter}")
    Call<ResponseBody> getDelayRequest(@Path("counter") int counter);

}
