package com.aqib.secupay.ui.status;

import static com.aqib.secupay.utils.AppConstants.DEFAULT_STATUS_CODES;
import static com.aqib.secupay.utils.AppConstants.NETWORK_ERROR_MSG;

import android.app.Application;
import android.app.ProgressDialog;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.aqib.secupay.models.ErrorsModel;
import com.aqib.secupay.network.ApiService;
import com.aqib.secupay.network.RestCallback;
import com.aqib.secupay.network.RestClient;
import com.aqib.secupay.network.ServerConnectListener;
import com.aqib.secupay.utils.NetworkUtility;

import okhttp3.ResponseBody;
import retrofit2.Response;

public class StatusCodeViewModel extends ViewModel implements ServerConnectListener {

    public MutableLiveData<String> mText = new MutableLiveData<>();
    public MutableLiveData<ErrorsModel> networkError = new MutableLiveData<>();
    public MutableLiveData<ErrorsModel> serverError = new MutableLiveData<>();
    private ProgressDialog mProgressDialog;
    private Application mApplication;


    public StatusCodeViewModel() {
    }

    public void getStatusCode() {

        ApiService apiService = new RestClient().getApiService();

        if (NetworkUtility.isNetworkAvailable(mApplication)) {

            try {
                apiService.getRandomStatusCod(DEFAULT_STATUS_CODES).enqueue(new RestCallback<ResponseBody>(this,
                        2000, mApplication));

            } catch (Exception e) {
                networkError.setValue(new ErrorsModel(e.getMessage(), 0));
            }
        } else {
            networkError.setValue(new ErrorsModel(NETWORK_ERROR_MSG, 0));
        }


    }

    @Override
    public void onSuccess(Response response, int requestCode) {
        if (requestCode == 2000) {
            mText.setValue( getCode(response));
        }
    }

    @Override
    public void onFailure(String response, int responseCode) {
        serverError.setValue(new ErrorsModel(response, responseCode));
    }

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

    public void setModel(@NonNull Application application) {
        mApplication = application;
    }
    private String getCode(Response response)
    {

        switch (response.code()) {
            case 100:
                return "Code : " + response.code() + "Informational responses: " +
                        response.toString();
            case 200:
                return  "Code : " + response.code() + "Success: " +
                        response.toString();
            case 300:
                return "Code : " + response.code() + "Redirection: " +
                        response.toString();
            case 400:
                return "Code : " + response.code() + "Client Errors: " +
                        response.toString();
            case 500:
               return "Code : " + response.code() + "Server Errors: " +
                       response.toString();
        }
        return "Something else";
    }
}