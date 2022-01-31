package com.aqib.secupay.network;

import com.aqib.secupay.models.UserLogin;

import okhttp3.ResponseBody;
import retrofit2.Response;

public interface ServerConnectListener {

    /**
     * Called if server call was successful
     *
     * @param response
     */

    void onSuccess(Response response,int requestCode);

    /**
     * Called if server call was failed.
     *
     * @param response
     */
    void onFailure(String  response,int responseCode);

}
