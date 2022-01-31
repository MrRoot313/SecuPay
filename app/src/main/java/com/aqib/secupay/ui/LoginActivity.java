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
                Toast.makeText(this,getString(R.string.enter_name_error), Toast.LENGTH_SHORT).show();
                return;
            }
            if (binding.etPassword.getText().toString().isEmpty()) {
                Toast.makeText(this, getString(R.string.enter_password_error), Toast.LENGTH_SHORT).show();
                return;
            }
            loginViewModel.showProgressDialog(this, getString(R.string.loading_message), 100);
            loginViewModel.loginApi(binding.etUserName.getText().toString(), binding.etPassword.getText().toString());
        });

    }

    void observeApi() {

        loginViewModel.loginResponseMutableLiveData.observe(this, loginResponse -> {
            loginViewModel.hideProgressDialog();
            Toast.makeText(this, getString(R.string.logged_user) + loginResponse.getUser(), Toast.LENGTH_LONG).show();
            NetworkUtility.callIntentWithFlags(this, MainActivity.class, true);
        });

        loginViewModel.serverError.observe(this, error -> {
            loginViewModel.hideProgressDialog();
            Toast.makeText(this,getString(R.string.error_code)+error.getErrorCode()+getString(R.string.error) +error.getErrorMessage(), Toast.LENGTH_SHORT).show();
        });

        loginViewModel.networkError.observe(this, error -> {
            loginViewModel.hideProgressDialog();
            Toast.makeText(this,getString(R.string.error) +error.getErrorMessage(), Toast.LENGTH_SHORT).show();
        });
    }
}