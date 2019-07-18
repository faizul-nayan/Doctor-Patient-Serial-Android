package com.ju.drmostafizur.presentation.patient.ui.activities;

import android.app.ProgressDialog;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.ju.drmostafizur.R;
import com.ju.drmostafizur.domain.executor.impl.ThreadExecutor;
import com.ju.drmostafizur.domain.model.DrInfoModel;
import com.ju.drmostafizur.presentation.doctor.presenters.DrDashboardPresenter;
import com.ju.drmostafizur.presentation.doctor.presenters.impl.DrDashboardPresenterImpl;
import com.ju.drmostafizur.presentation.doctor.ui.fragments.FoTabFragment;
import com.ju.drmostafizur.presentation.patient.ui.fragments.PatientDashboardFragment;
import com.ju.drmostafizur.threading.MainThreadImpl;

public class PatientDashboardActivity extends AppCompatActivity {

    private DrDashboardPresenter mPresenter;
    private ProgressDialog mProgressDialog;

    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dr_dashboard);
        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.containerPatient, new PatientDashboardFragment()).commit();

    }

}
