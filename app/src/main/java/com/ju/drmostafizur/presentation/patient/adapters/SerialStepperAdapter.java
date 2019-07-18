package com.ju.drmostafizur.presentation.patient.adapters;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.IntRange;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import com.ju.drmostafizur.presentation.patient.ui.fragments.PatientDetailsFragment;
import com.ju.drmostafizur.presentation.patient.ui.fragments.PatientInfoFragment;
import com.ju.drmostafizur.presentation.patient.ui.fragments.PatientScheduleFragment;
import com.ju.drmostafizur.presentation.patient.ui.fragments.PaymentFragment;
import com.stepstone.stepper.Step;
import com.stepstone.stepper.adapter.AbstractFragmentStepAdapter;
import com.stepstone.stepper.viewmodel.StepViewModel;

/**
 * Created by Faizul Haque Nayan on 19/07/10.
 */
public class SerialStepperAdapter extends AbstractFragmentStepAdapter {

    private Context mContext;
    private PatientInfoFragment patientInfoFragment;
    private PatientDetailsFragment patientDetailsFragment;
    private PaymentFragment paymentFragment;
    private PatientScheduleFragment patientScheduleFragment;
    Bundle stepperBundle;
    private int stepperPosition = 0;


    public SerialStepperAdapter(FragmentManager fm,Context context) {
        super(fm, context);
        this.mContext = context;
        patientScheduleFragment = PatientScheduleFragment.getInstance();
        patientInfoFragment = PatientInfoFragment.getInstance();
        patientDetailsFragment = PatientDetailsFragment.getInstance();
        paymentFragment = new PaymentFragment();
        stepperBundle = new Bundle();

    }

    @Override
    public Step createStep(int position) {
        stepperPosition = position;
        return (Step) getStepFragment(position);

    }

    private Fragment getStepFragment(int position) {
        Fragment fragment = new Fragment();
       // stepperBundle.putInt("key", position);
        //stepperBundle.putInt(AppValues.EXTRA_SAMITY_ID, mSamityId);
       // stepperBundle.putString(AppValues.TAG, tag);
        if(position == 0){
            fragment = patientScheduleFragment;
        }
        else if (position == 1){
            fragment = patientInfoFragment;
        }
        else if(position == 2){
            fragment = paymentFragment;
        }
        fragment.setArguments(stepperBundle);
        return fragment;
    }

    @Override
    public int getCount() {
        return 3;
    }

    @NonNull
    @Override
    public StepViewModel getViewModel(@IntRange(from = 0) int position) {
        //Override this method to set Step title for the Tabs, not necessary for other stepper types

        String title = "";
        if(position == 0){
            title = "Doctor Schedule";
        }
        else if (position == 1){
            title = "Patient Info";
        }
        else if(position == 2){
            title = "Payment";
        }

        return new StepViewModel.Builder(context)
                .setTitle(title) //can be a CharSequence instead
                .create();
    }

}