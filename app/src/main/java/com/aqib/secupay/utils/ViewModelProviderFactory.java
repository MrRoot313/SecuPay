package com.aqib.secupay.utils;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.aqib.secupay.ui.login.LoginViewModel;

public class ViewModelProviderFactory implements ViewModelProvider.Factory {
    private Application application;

    public ViewModelProviderFactory(Application application){
        this.application = application;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if(modelClass.isAssignableFrom(LoginViewModel.class)){
            return (T) new LoginViewModel(application);
        }else {
            return null;
        }
    }
}
