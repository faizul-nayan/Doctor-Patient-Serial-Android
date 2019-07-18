package com.ju.drmostafizur.presentation.patient.ui.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.ju.drmostafizur.R;
import com.ju.drmostafizur.presentation.patient.adapters.SerialStepperAdapter;
import com.stepstone.stepper.StepperLayout;

public class SerialBookedActivity extends AppCompatActivity {

    private StepperLayout mStepperLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_serial_booked);
        mStepperLayout = (StepperLayout) findViewById(R.id.stepperLayout);
        mStepperLayout.setAdapter(new SerialStepperAdapter(getSupportFragmentManager(), this));

    }
}
