package com.aqib.secupay;

import android.arch.core.executor.testing.InstantTaskExecutorRule;

import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.aqib.secupay.models.DelayedRequestModel;
import com.aqib.secupay.models.UserLogin;
import com.aqib.secupay.network.ApiService;
import com.aqib.secupay.network.RestClient;
import com.aqib.secupay.ui.delay.DelayRequestViewModel;
import com.aqib.secupay.viewmodel.LoginViewModel;
import com.google.gson.Gson;

import junit.framework.TestCase;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;

import kotlin.jvm.JvmField;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;

@RunWith(AndroidJUnit4.class)
public class DelayStatusTest extends TestCase {

    public ApiService apiService;
    public DelayRequestViewModel delayRequestViewModel;
    @Rule
    @JvmField
    public final RxImmediateSchedulerRule role=new RxImmediateSchedulerRule();
    @Rule
    public final InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    @Before
    public void start() {
        apiService = new RestClient().getApiService();
        delayRequestViewModel = new DelayRequestViewModel();
    }

    @Test
   public void delay_counter_test() {
        Call<ResponseBody> call = apiService.getDelayRequest(1);
        try {
            Response<ResponseBody> response = call.execute();
            DelayedRequestModel responseResult=new Gson().fromJson(response.body().string(), DelayedRequestModel.class);

            assertEquals("https://httpbin.org/delay/1",responseResult.getUrl());

        } catch (IOException e) {
            e.printStackTrace();//
        }

    }

    @After
    public void end() {

    }
}
