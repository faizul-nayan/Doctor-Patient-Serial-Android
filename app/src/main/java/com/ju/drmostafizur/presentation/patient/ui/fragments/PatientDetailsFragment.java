package com.ju.drmostafizur.presentation.patient.ui.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ju.drmostafizur.R;
import com.stepstone.stepper.Step;
import com.stepstone.stepper.VerificationError;

/**
 * Created by Faizul Haque Nayan on 19/07/10.
 */
public class PatientDetailsFragment extends Fragment implements Step {

    private static PatientDetailsFragment mInstance;
    private View view;

    public static PatientDetailsFragment getInstance() {
        mInstance = new PatientDetailsFragment();
        return mInstance;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_patient_details, null);

        return view;
    }

    @Nullable
    @Override
    public VerificationError verifyStep() {
        return null;
    }

    @Override
    public void onSelected() {

    }

    @Override
    public void onError(@NonNull VerificationError error) {

    }
}
