package com.aqib.secupay.ui.delay;

import static com.aqib.secupay.utils.AppConstants.NETWORK_ERROR_MSG;

import android.app.Application;
import android.app.ProgressDialog;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.aqib.secupay.models.ErrorsModel;
import com.aqib.secupay.network.ApiService;
import com.aqib.secupay.network.RestCallback;
import com.aqib.secupay.network.RestClient;
import com.aqib.secupay.network.ServerConnectListener;
import com.aqib.secupay.utils.NetworkUtility;
import com.aqib.secupay.utils.SharedPref;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Response;

public class DelayRequestViewModel extends ViewModel implements ServerConnectListener {


    public MutableLiveData<String > delayedRequestModel = new MutableLiveData<>();
    public MutableLiveData<ErrorsModel> networkError = new MutableLiveData<>();
    public MutableLiveData<ErrorsModel> serverError = new MutableLiveData<>();
    public MutableLiveData<String > counter = new MutableLiveData<>();
    private ProgressDialog mProgressDialog;
    private Application mApplication;
    private int mCounter;

    public DelayRequestViewModel() {

    }
    public void getDelayRequest(int counter) {
        mCounter=counter;
        ApiService apiService = new RestClient().getApiService();

        if (NetworkUtility.isNetworkAvailable(mApplication)) {

            try {
                apiService.getDelayRequest(counter).enqueue(new RestCallback<ResponseBody>(this,
                        3000, mApplication));

            } catch (Exception e) {
                networkError.setValue(new ErrorsModel(e.getMessage(), 0));
            }
        } else {
            networkError.setValue(new ErrorsModel(NETWORK_ERROR_MSG, 0));
        }
    }


    @Override
    public void onSuccess(Response response, int requestCode) {
        if (requestCode == 3000) {
            try {
                assert response.body() != null;
                delayedRequestModel.setValue(((ResponseBody) response.body()).string());
                SharedPref.getObj().setCounter(mCounter+1);
                counter.setValue(String.valueOf(mCounter+1));
            } catch (IOException e) {
                networkError.setValue(new ErrorsModel(e.getMessage(),0));
            }


        }
    }

    @Override
    public void onFailure(String response, int responseCode) {
        serverError.setValue(new ErrorsModel(response,responseCode));
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
        SharedPref.initializeResources(mApplication);
    }
}