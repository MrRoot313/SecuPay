package com.aqib.secupay;

import static java.util.concurrent.TimeUnit.SECONDS;

import android.arch.core.executor.testing.InstantTaskExecutorRule;

import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;

import com.aqib.secupay.models.UserLogin;
import com.aqib.secupay.network.ApiService;
import com.aqib.secupay.network.RestClient;
import com.aqib.secupay.viewmodel.LoginViewModel;
import junit.framework.TestCase;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;

import kotlin.jvm.JvmField;
import retrofit2.Call;
import retrofit2.Response;

@RunWith(AndroidJUnit4.class)
public class LoginViewModelTest extends TestCase {

    public ApiService apiService;
    public LoginViewModel loginViewModel;
    @Rule
    @JvmField
    public final RxImmediateSchedulerRule role=new RxImmediateSchedulerRule();
    @Rule
    public final InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    @Before
    public void start() {
        apiService = new RestClient("userName", "password").getApiService();
        loginViewModel = new LoginViewModel(ApplicationProvider.getApplicationContext());
    }

    @Test
   public void Login_success_test() {
        Call<UserLogin> call = apiService.login("userName", "password");
        try {
            Response<UserLogin> response = call.execute();
            assertTrue(response.body().getAuthenticated());

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @After
    public void end() {

    }
}
