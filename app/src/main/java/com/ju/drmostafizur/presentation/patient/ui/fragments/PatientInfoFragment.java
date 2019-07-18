package com.ju.drmostafizur.presentation.patient.ui.fragments;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

import com.google.gson.Gson;
import com.ju.drmostafizur.R;
import com.ju.drmostafizur.domain.model.PatientBookedModel;
import com.ju.drmostafizur.utills.InputUtils;
import com.ju.drmostafizur.utills.MySharePreferences;
import com.ju.drmostafizur.utills.Utils;
import com.ju.drmostafizur.utills.Values;
import com.stepstone.stepper.BlockingStep;
import com.stepstone.stepper.Step;
import com.stepstone.stepper.StepperLayout;
import com.stepstone.stepper.VerificationError;

/**
 * Created by Faizul Haque Nayan on 19/07/10.
 */
public class PatientInfoFragment extends Fragment implements Step, BlockingStep {

    private static PatientInfoFragment mInstance;
    private View view;

    private EditText fullNameEt;
    private EditText parentsSpousEt;
    private EditText addressEt;
    private EditText phoneNumberEt;
    private EditText emailEt;
    private EditText ageEt;
    private Spinner bloodGroupSpinner;
    private RadioGroup genderGroup;
    private RadioButton maleRadio;
    private RadioButton femaleRadio;

    private PatientBookedModel patientBookedModel;
    private Gson gson;

    public static PatientInfoFragment getInstance() {
        mInstance = new PatientInfoFragment();
        return mInstance;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_patient_info, null);
        init();
        return view;
    }

    private void init(){
        fullNameEt = (EditText) view.findViewById(R.id.fullNameEt);
        parentsSpousEt = (EditText) view.findViewById(R.id.fatherSpousNameEt);
        addressEt = (EditText) view.findViewById(R.id.patientAddressEt);
        phoneNumberEt = (EditText) view.findViewById(R.id.patientPhoneNumberEt);
        emailEt = (EditText) view.findViewById(R.id.patientEmailEt);
        ageEt = (EditText) view.findViewById(R.id.patientAgeEt);
        genderGroup = (RadioGroup) view.findViewById(R.id.genderRadioGroup);
        bloodGroupSpinner = (Spinner) view.findViewById(R.id.patientBloodGroupSpinner);
        //maleRadio = (RadioButton) view.findViewById(R.id.maleRadio);
       // femaleRadio = (RadioButton) view.findViewById(R.id.femaleRadio);
        //genderGroup.addView(maleRadio);
        //genderGroup.addView(femaleRadio);
        Utils.prepareSpinner(getActivity(),bloodGroupSpinner, Values.bloodGroupList());
        gson = new Gson();

    }

    @Nullable
    @Override
    public VerificationError verifyStep() {
        return null;
    }

    @Override
    public void onSelected() {
        patientBookedModel = gson.fromJson(MySharePreferences.getInstance(getContext()).getString(Values.PATIENT_BOOKED_MODEL), PatientBookedModel.class);
        Log.e("info", patientBookedModel.toString());
    }

    @Override
    public void onError(@NonNull VerificationError error) {

    }

    private boolean checkValidation(){

        if (fullNameEt.getText().toString().equalsIgnoreCase("")){
            fullNameEt.setError("Should not be empty");
            return false;
        }
        else if (parentsSpousEt.getText().toString().equalsIgnoreCase("")){
            parentsSpousEt.setError("Should not be empty");
            return false;
        }
        else if (phoneNumberEt.getText().toString().equalsIgnoreCase("")){
            phoneNumberEt.setError("Should not be empty");
            return false;
        }
        else if (ageEt.getText().toString().equalsIgnoreCase("")){
            ageEt.setError("Should not be empty");
            return false;
        }
        else {
            return true;
        }
    }

    @Override
    public void onNextClicked(StepperLayout.OnNextClickedCallback callback) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if(checkValidation()){
                    patientBookedModel.setFullName(fullNameEt.getText().toString());
                    patientBookedModel.setFatherSpouseName(parentsSpousEt.getText().toString());
                    patientBookedModel.setAddress(addressEt.getText().toString());
                    patientBookedModel.setPhoneNumber(phoneNumberEt.getText().toString());
                    patientBookedModel.setEmail(emailEt.getText().toString());
                    patientBookedModel.setAge(Integer.parseInt(ageEt.getText().toString()));

                    int genId = genderGroup.getCheckedRadioButtonId();
                    if(genId == R.id.maleRadio){
                        patientBookedModel.setGender("Male");
                    }
                    else {
                        patientBookedModel.setGender("Male");
                    }
                    patientBookedModel.setBloodGroup(bloodGroupSpinner.getSelectedItem().toString());
                    String info = gson.toJson(patientBookedModel, PatientBookedModel.class);
                    MySharePreferences.getInstance(getContext()).setString(Values.PATIENT_BOOKED_MODEL, info);
                    callback.goToNextStep();
                }


            }
        }, 1000L);
    }

    @Override
    public void onCompleteClicked(StepperLayout.OnCompleteClickedCallback callback) {

    }

    @Override
    public void onBackClicked(StepperLayout.OnBackClickedCallback callback) {
        callback.goToPrevStep();
    }
}
