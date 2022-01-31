package com.aqib.secupay.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.widget.Toast;

import com.aqib.secupay.MainActivity;
import com.aqib.secupay.R;
import com.aqib.secupay.databinding.ActivityLoginBinding;
import com.aqib.secupay.utils.NetworkUtility;
import com.aqib.secupay.utils.ViewModelProviderFactory;
import com.aqib.secupay.viewmodel.LoginViewModel;

public class LoginActivity extends AppCompatActivity {

    private LoginViewModel loginViewModel;
    private ViewModelProviderFactory factory;

    //Layout
    private ActivityLoginBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initActivity();
        observeApi();
    }

    private void initActivity() {

        binding = DataBindingUtil.setContentView(this, R.layout.activity_login);
        factory = new ViewModelProviderFactory(getApplication());
        loginViewModel = new ViewModelProvider(this, factory).get(LoginViewModel.class);
        binding.setModel(loginViewModel);

        binding.btnLogin.setOnClickListener(v -> {

            if (binding.etUserName.getText().toString().isEmpty()) {
                Toast.makeText(this, "Please enter username", Toast.LENGTH_SHORT).show();
                return;
            }
            if (binding.etPassword.getText().toString().isEmpty()) {
                Toast.makeText(this, "Please enter password", Toast.LENGTH_SHORT).show();
                return;
            }
            loginViewModel.showProgressDialog(this, "Please wait while logging", 100);
            loginViewModel.loginApi(binding.etUserName.getText().toString(), binding.etPassword.getText().toString());
        });

    }

    void observeApi() {

        loginViewModel.loginResponseMutableLiveData.observe(this, loginResponse -> {
            loginViewModel.hideProgressDialog();
            Toast.makeText(this, "Logged User: " + loginResponse.getUser(), Toast.LENGTH_LONG).show();
            NetworkUtility.callIntentWithFlags(this, MainActivity.class, true);
        });

        loginViewModel.serverError.observe(this, error -> {
            loginViewModel.hideProgressDialog();
            Toast.makeText(this,"Error Code: "+error.getErrorCode()+" Error: " +error.getErrorMessage(), Toast.LENGTH_SHORT).show();
        });

        loginViewModel.networkError.observe(this, error -> {
            loginViewModel.hideProgressDialog();
            Toast.makeText(this,"Error: " +error.getErrorMessage(), Toast.LENGTH_SHORT).show();
        });
    }
}