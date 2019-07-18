package com.ju.drmostafizur.presentation.patient.ui.fragments;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.ju.drmostafizur.R;
import com.ju.drmostafizur.domain.executor.impl.ThreadExecutor;
import com.ju.drmostafizur.domain.model.PatientBookedModel;
import com.ju.drmostafizur.presentation.patient.presenters.PatientBookedPresenter;
import com.ju.drmostafizur.presentation.patient.presenters.impl.PatientBookedPresenterImpl;
import com.ju.drmostafizur.threading.MainThreadImpl;
import com.ju.drmostafizur.utills.MySharePreferences;
import com.ju.drmostafizur.utills.Values;
import com.stepstone.stepper.BlockingStep;
import com.stepstone.stepper.Step;
import com.stepstone.stepper.StepperLayout;
import com.stepstone.stepper.VerificationError;

/**
 * Created by Faizul Haque Nayan on 19/07/10.
 */
public class PaymentFragment extends Fragment implements Step, BlockingStep, PatientBookedPresenter.View {

    private static PaymentFragment mInstance;
    private View view;

    private LinearLayout payOnlineLayout;
    private LinearLayout paymentMethodLayout;
    private LinearLayout refLayout;
    private LinearLayout mainLayout;
    private LinearLayout finalLayout;
    private TextView paymentMethodTv;
    private TextView serialTv;
    private TextView tokenTv;
    private CheckBox payViaCashCheck;
    private CheckBox payViaOnlineCheck;
    private RadioGroup paymentRadioGroup;
    private Button paymentBtn;
    private Button finishBtn;
    private EditText refEt;

    private ProgressDialog mProgressDialog;

    private PatientBookedModel patientBookedModel;
    private Gson gson;

    private PatientBookedPresenter mPresenter;

    public static PaymentFragment getInstance() {
        mInstance = new PaymentFragment();
        return mInstance;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_patient_payment, null);
        init();
        return view;
    }

    void init(){
        mainLayout = (LinearLayout) view.findViewById(R.id.mainLayout);
        finalLayout = (LinearLayout) view.findViewById(R.id.finalLayout);
        payOnlineLayout = (LinearLayout) view.findViewById(R.id.payOnlineLayout);
        paymentMethodLayout = (LinearLayout) view.findViewById(R.id.paymentMethodLayout);
        refLayout = (LinearLayout) view.findViewById(R.id.refLayout);
        serialTv = (TextView) view.findViewById(R.id.serialTv);
        tokenTv = (TextView) view.findViewById(R.id.tokenTv);
        paymentMethodTv = (TextView) view.findViewById(R.id.paymentMethodTv);
        payViaCashCheck = (CheckBox) view.findViewById(R.id.payViaCashCheck);
        payViaOnlineCheck = (CheckBox) view.findViewById(R.id.payViaOnlineCheck);
        paymentRadioGroup = (RadioGroup) view.findViewById(R.id.paymentRadioGroup);
        paymentBtn = (Button) view.findViewById(R.id.paymentBtn);
        finishBtn = (Button) view.findViewById(R.id.finishBtn);
        refEt = (EditText) view.findViewById(R.id.refEt);
        mPresenter = new PatientBookedPresenterImpl(ThreadExecutor.getInstance(), MainThreadImpl.getInstance(), getContext(), this);
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
        payViaCashCheck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    payOnlineLayout.setVisibility(View.GONE);
                    paymentMethodLayout.setVisibility(View.GONE);
                    paymentMethodTv.setVisibility(View.GONE);
                    paymentBtn.setVisibility(View.GONE);
                    refLayout.setVisibility(View.GONE);
                }
                else{
                    payOnlineLayout.setVisibility(View.VISIBLE);
                    paymentMethodLayout.setVisibility(View.VISIBLE);
                    paymentMethodTv.setVisibility(View.VISIBLE);
                    paymentBtn.setVisibility(View.GONE);
                    refLayout.setVisibility(View.VISIBLE);
                }
            }
        });

        paymentRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                int item = group.getCheckedRadioButtonId();
                if(item == R.id.bKashRadio){
                    paymentBtn.setVisibility(View.GONE);
                    refLayout.setVisibility(View.VISIBLE);
                }
                else if(item == R.id.visaRadio || item == R.id.masterRadio){
                    paymentBtn.setVisibility(View.VISIBLE);
                    refLayout.setVisibility(View.GONE);
                }
            }
        });
    }

    @Override
    public void onError(@NonNull VerificationError error) {

    }

    @Override
    public void onNextClicked(StepperLayout.OnNextClickedCallback callback) {

    }

    @Override
    public void onCompleteClicked(StepperLayout.OnCompleteClickedCallback callback) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                if(checkValidation()){
                    showProgress();
                    if (payViaCashCheck.isChecked()){
                        patientBookedModel.setPayVaiCash(1);
                        patientBookedModel.setPaymentOnlien(0);
                    }else {
                        patientBookedModel.setPayVaiCash(0);
                        patientBookedModel.setPaymentOnlien(1);
                        if(paymentRadioGroup.getCheckedRadioButtonId() == R.id.bKashRadio){
                            patientBookedModel.setPayByBKash(1);
                            patientBookedModel.setbKashRecNo(refEt.getText().toString());
                            patientBookedModel.setPayOther(0);
                        }else {
                            patientBookedModel.setPayByBKash(0);
                            patientBookedModel.setPayOther(1);
                            patientBookedModel.setbKashRecNo("");
                            patientBookedModel.setOtherRef("");
                        }
                    }
                    mPresenter.setPatientBooked(patientBookedModel);
                    Log.e("sadasd",patientBookedModel.toString());
                }

            }
        }, 1000L);

    }

    @Override
    public void onBackClicked(StepperLayout.OnBackClickedCallback callback) {
        callback.goToPrevStep();
    }

    private boolean checkValidation(){
        if (payViaCashCheck.isChecked()){
            return true;
        }
        else {
            if(paymentRadioGroup.getCheckedRadioButtonId() == R.id.bKashRadio){
                if(refEt.getText().toString().equalsIgnoreCase("")){
                    refEt.setError("Require");
                    return false;
                }
                else {
                    return true;
                }
            }
            else {
                return true;
            }
        }

    }

    @Override
    public void onSaved(int serial, String message) {
        hideProgress();
        mainLayout.setVisibility(View.GONE);
        finalLayout.setVisibility(View.VISIBLE);
        serialTv.setText("Serial number: "+serial);
        tokenTv.setText("Your token: "+message);
        finishBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().finish();
            }
        });
    }

    @Override
    public void onNotSaved(String message) {
        hideProgress();
        showError(message);
    }

    @Override
    public void showProgress() {
        mProgressDialog = new ProgressDialog(getContext());
        mProgressDialog.setMessage("Please Wait...");
        mProgressDialog.show();
    }

    @Override
    public void hideProgress() {
        mProgressDialog.dismiss();
    }

    @Override
    public void showError(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }
}
