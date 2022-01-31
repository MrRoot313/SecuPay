package com.aqib.secupay;

import android.arch.core.executor.testing.InstantTaskExecutorRule;

import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.aqib.secupay.network.ApiService;
import com.aqib.secupay.network.RestClient;
import com.aqib.secupay.ui.status.StatusCodeViewModel;

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
public class StatusViewModelTest extends TestCase {

    public ApiService apiService;
    public StatusCodeViewModel statusCodeViewModel;
    @Rule
    @JvmField
    public final RxImmediateSchedulerRule role=new RxImmediateSchedulerRule();
    @Rule
    public final InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    @Before
    public void start() {
        apiService = new RestClient().getApiService();
        statusCodeViewModel = new StatusCodeViewModel();
    }

    @Test
   public void status_code_200() {
        Call<ResponseBody> call = apiService.getRandomStatusCod("200");
        try {
            Response<ResponseBody> response = call.execute();
            assertEquals(200,response.code());

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    @Test
    public void status_code_300() {
        Call<ResponseBody> call = apiService.getRandomStatusCod("300");
        try {
            Response<ResponseBody> response = call.execute();
            assertEquals(300,response.code());

        } catch (IOException e) {
            e.printStackTrace();
        }

    }  @Test
    public void status_code_400() {
        Call<ResponseBody> call = apiService.getRandomStatusCod("400");
        try {
            Response<ResponseBody> response = call.execute();
            assertEquals(400,response.code());

        } catch (IOException e) {
            e.printStackTrace();
        }

    }  @Test
    public void status_code_500() {
        Call<ResponseBody> call = apiService.getRandomStatusCod("500");
        try {
            Response<ResponseBody> response = call.execute();
            assertEquals(500,response.code());

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @After
    public void end() {

    }
}
