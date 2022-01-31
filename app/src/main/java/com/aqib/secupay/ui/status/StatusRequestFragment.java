package com.aqib.secupay.ui.status;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.aqib.secupay.databinding.FragmentStatusRequestBinding;

public class StatusRequestFragment extends Fragment {

    private FragmentStatusRequestBinding binding;
    private StatusCodeViewModel statusCodeViewModel;

    //private TextView textView;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        statusCodeViewModel =
                new ViewModelProvider(this).get(StatusCodeViewModel.class);
        statusCodeViewModel.setModel(getActivity().getApplication());
        binding = FragmentStatusRequestBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        observeApi();
        binding.btnRandom.setOnClickListener(v -> {
            statusCodeViewModel.showProgressDialog(getActivity(), "Please wait while processing", 0);
            statusCodeViewModel.getStatusCode();
        });

    }

    void observeApi() {

        statusCodeViewModel.mText.observe(getActivity(), response -> {
            statusCodeViewModel.hideProgressDialog();
            binding.tvRandomCode.setText(response);

        });

        statusCodeViewModel.serverError.observe(getActivity(), error -> {
            statusCodeViewModel.hideProgressDialog();
            binding.tvRandomCode.setText(String.valueOf(error.getErrorCode())+" "+error.getErrorMessage()); });

        statusCodeViewModel.networkError.observe(getActivity(), error -> {
            statusCodeViewModel.hideProgressDialog();
            binding.tvRandomCode.setText(String.valueOf(error.getErrorCode())+" "+error.getErrorMessage());
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}