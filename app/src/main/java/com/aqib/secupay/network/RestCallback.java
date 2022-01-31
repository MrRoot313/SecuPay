package com.aqib.secupay.network;

import android.content.Context;



import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RestCallback <T> implements Callback {

    private Context mContext;

    private ServerConnectListener listener;

    private boolean isCanceled;
    private int requestCode;


    public RestCallback(ServerConnectListener listener, int requestCode,
                        Context context) {
        this.listener = listener;
        this.requestCode = requestCode;
        this.mContext = context;
    }

    public void cancel() {
        isCanceled = true;
    }

    public boolean isCancelled() {
        return isCanceled;
    }

    @Override
    public void onResponse(Call call, Response response) {
        if (call.isCanceled()) {
            return;
        }
        if (response.isSuccessful()) {
            if (response.body() != null) {
                listener.onSuccess(response,requestCode);
            } else {
                setErrorMessage(response.toString(), response.code());
            }
        } else {
            setErrorMessage(response.toString(), response.code());
        }
    }

    @Override
    public void onFailure(Call call, Throwable throwable) {
        listener.onFailure(throwable.getLocalizedMessage(),0);
    }

    public void setErrorMessage(String error, int code) {
        listener.onFailure(error,code);
    }
}
