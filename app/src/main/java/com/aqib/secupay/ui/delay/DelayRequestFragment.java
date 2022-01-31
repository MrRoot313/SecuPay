package com.aqib.secupay.ui.delay;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.aqib.secupay.R;
import com.aqib.secupay.databinding.FragmentDelayRequestBinding;
import com.aqib.secupay.utils.SharedPref;

import java.util.Objects;

public class DelayRequestFragment extends Fragment {

    private FragmentDelayRequestBinding binding;
    private DelayRequestViewModel delayRequestViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        delayRequestViewModel =
                new ViewModelProvider(this).get(DelayRequestViewModel.class);
        delayRequestViewModel.setModel(requireActivity().getApplication());
        binding = FragmentDelayRequestBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        observeApi();
        SharedPref.initializeResources(getActivity());
        binding.tvCounter.setText(String.format("%s%s", getString(R.string.counter_text), SharedPref.getObj().getCounter()));

        binding.btnDelayRequest.setOnClickListener(v -> {
            delayRequestViewModel.showProgressDialog(getActivity(), getString(R.string.loading_message), 0);
            delayRequestViewModel.getDelayRequest(SharedPref.getObj().getCounter());
        });

    }

    void observeApi() {

        delayRequestViewModel.delayedRequestModel.observe(requireActivity(), response -> {
            delayRequestViewModel.hideProgressDialog();
            binding.tvDelay.setText(response);
            SharedPref.getObj().setCounter(SharedPref.getObj().getCounter() + 1);

        });

        delayRequestViewModel.serverError.observe(requireActivity(), error -> {
            delayRequestViewModel.hideProgressDialog();
            Toast.makeText(getActivity(), error.getErrorMessage(), Toast.LENGTH_SHORT).show();
        });
        delayRequestViewModel.networkError.observe(requireActivity(), error -> {
            delayRequestViewModel.hideProgressDialog();
            Toast.makeText(getActivity(), error.getErrorMessage(), Toast.LENGTH_SHORT).show();
        });
        delayRequestViewModel.counter.observe(requireActivity(), counter -> {
            binding.tvCounter.setText(String.format("%s%s", getString(R.string.counter_text), counter));
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}