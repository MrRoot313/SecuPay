package com.aqib.secupay.ui.login;

import static com.aqib.secupay.utils.AppConstants.DEFAULT_STATUS_CODES;
import static com.aqib.secupay.utils.AppConstants.NETWORK_ERROR_MSG;

import android.app.Application;
import android.app.ProgressDialog;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.aqib.secupay.models.ErrorsModel;
import com.aqib.secupay.models.UserLogin;
import com.aqib.secupay.network.ApiService;
import com.aqib.secupay.network.RestCallback;
import com.aqib.secupay.network.RestClient;
import com.aqib.secupay.network.ServerConnectListener;
import com.aqib.secupay.utils.NetworkUtility;

import retrofit2.Response;

public class LoginViewModel extends AndroidViewModel implements ServerConnectListener {


    public MutableLiveData<ErrorsModel> networkError = new MutableLiveData<>();
    public MutableLiveData<ErrorsModel> serverError = new MutableLiveData<>();
    private ProgressDialog mProgressDialog;
    public MutableLiveData<UserLogin> loginResponseMutableLiveData = new MutableLiveData<>();

    public LoginViewModel(@NonNull Application application) {
        super(application);
    }

    public void loginApi(String userName, String password) {

        ApiService apiService = new RestClient(userName, password).getApiService();

        if (NetworkUtility.isNetworkAvailable(getApplication())) {

            try {
                apiService.login(userName, password).enqueue(new RestCallback<UserLogin>(this,
                        1000, getApplication()));

            } catch (Exception e) {
                networkError.setValue(new ErrorsModel(e.getMessage(),0));
            }
        } else {
            networkError.setValue(new ErrorsModel(NETWORK_ERROR_MSG,0));
        }


    }

    @Override
    public void onSuccess(Response response, int requestCode) {

        if (requestCode == 1000) {
            UserLogin userLogin = (UserLogin) response.body();

            assert userLogin != null;
            if (userLogin.getAuthenticated()) {
                loginResponseMutableLiveData.setValue(userLogin);
            } else {
                assert response.errorBody() != null;
                serverError.setValue(new ErrorsModel(response.errorBody().toString(),requestCode));
            }
        }
    }

    @Override
    public void onFailure(String response,int responseCode) {
        serverError.setValue(new ErrorsModel(response,responseCode));
    }

    //Show Loader
    public void showProgressDialog(Context context, String text, int progress) {
        if (mProgressDialog == null) {
            mProgressDialog = new ProgressDialog(context);
        }
        mProgressDialog.setMessage(text);
        mProgressDialog.setIndeterminate(false);
        mProgressDialog.setMax(progress);
        mProgressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        mProgressDialog.setCancelable(false);
        mProgressDialog.show();
    }

    public void hideProgressDialog() {
        if (mProgressDialog != null) {
            mProgressDialog.dismiss();
        }
    }

}
