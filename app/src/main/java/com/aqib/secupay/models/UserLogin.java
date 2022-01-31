package com.aqib.secupay.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserLogin{

    @Expose
    @SerializedName("authenticated")
    private  boolean authenticated;

    @Expose
    @SerializedName("user")
    private String  user;



    public boolean getAuthenticated() {
        return authenticated;
    }

    public void setAuthenticated(boolean authenticated) {
        this.authenticated = authenticated;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }
}
