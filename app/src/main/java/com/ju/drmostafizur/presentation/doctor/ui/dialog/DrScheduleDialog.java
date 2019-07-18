package com.ju.drmostafizur.presentation.doctor.ui.dialog;

import android.app.DialogFragment;
import android.app.TimePickerDialog;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.ju.drmostafizur.R;
import com.ju.drmostafizur.domain.executor.impl.ThreadExecutor;
import com.ju.drmostafizur.domain.interactors.doctor.DrScheduleDialogPresenter;
import com.ju.drmostafizur.domain.interactors.doctor.impl.DrScheduleDialogPresenterImpl;
import com.ju.drmostafizur.domain.model.DrSchedule;
import com.ju.drmostafizur.threading.MainThreadImpl;
import com.ju.drmostafizur.utills.Utils;
import com.ju.drmostafizur.utills.Values;

import java.util.Calendar;
import java.util.List;

import butterknife.ButterKnife;

/**
 * Created by Faizul Haque Nayan on 19/07/09.
 */
public class DrScheduleDialog extends DialogFragment implements DrScheduleDialogPresenter.View{

    private DialogCallback mDialogCallback;
    private DrScheduleDialog mDialog;

    private NumberPicker mVisitorPicker;
    private EditText hospitalNameEt;
    private EditText addressEt;
    private Spinner employeeTypeSpinner;
    private TextView dateFromTp;
    private TextView dateToTp;
    private CheckBox isDisableCheck;
    private Button savedBtn;
    private Button removeBtn;
    private Toolbar toolbar;
    private String mDay;

    private View view;
    private DrScheduleDialogPresenter mPresenter;

    @Override
    public void onScheduleFound(List<DrSchedule> scheduleList) {
        DrSchedule drSchedule = scheduleList.get(0);
        hospitalNameEt.setText(drSchedule.getHospitalName());
        addressEt.setText(drSchedule.getAddress());
        Utils.prepareSpinner(getActivity(),employeeTypeSpinner, Values.employeerTypeList(), drSchedule.getEmployeeType());
        dateFromTp.setText(drSchedule.getTimeFrom());
        dateToTp.setText(drSchedule.getTimeTo());
        if(drSchedule.getIsDisable() == 1){
            isDisableCheck.setChecked(true);
        }
        mVisitorPicker.setValue(drSchedule.getMaxPatient());
    }

    @Override
    public void onScheduleNotFound(String message) {

    }

    @Override
    public void onScheduleRemoved(String message) {
        showError(message);
        mDialog.dismiss();
    }

    @Override
    public void onScheduleNotRemoved(String message) {
        showError(message);
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void showError(String message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
    }

    public interface DialogCallback{
       // void onAddNominee();
        void getTime(TextView editText);
        void setNewSchedule(DrSchedule drSchedule);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.dialog_dr_schedule,
                container, false);

        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        getDialog().getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        getDialog().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        getDialog().setCanceledOnTouchOutside(false);
        ButterKnife.bind(this, view);
        init();
        return view;
    }

    public void setCallback(DialogCallback dialogCallback, DrScheduleDialog dialog, String day){
        this.mDialogCallback = dialogCallback;
        this.mDialog = dialog;
        this.mDay = day;
        mPresenter = new DrScheduleDialogPresenterImpl(ThreadExecutor.getInstance(), MainThreadImpl.getInstance(), this);
    }

    private void init(){
        hospitalNameEt = (EditText) view.findViewById(R.id.hospitalNameEt);
        addressEt = (EditText) view.findViewById(R.id.addressEt);
        employeeTypeSpinner = (Spinner) view.findViewById(R.id.employeeTypeSpinner);
        dateFromTp = (TextView) view.findViewById(R.id.dateFromTp);
        dateToTp = (TextView) view.findViewById(R.id.dateToTp);
        isDisableCheck = (CheckBox) view.findViewById(R.id.disableCb);
        mVisitorPicker = (NumberPicker) view.findViewById(R.id.visitorPicker);
        savedBtn = (Button) view.findViewById(R.id.saveBtn);
        removeBtn = (Button) view.findViewById(R.id.removeBtn);
        Utils.prepareSpinner(getActivity(),employeeTypeSpinner, Values.employeerTypeList());

        toolbar = (Toolbar) view.findViewById(R.id.toolbar);
        toolbar.setTitle("Schedule");
        toolbar.setTitleTextColor(getResources().getColor(R.color.colorIcons));

        mVisitorPicker.setMinValue(0);
        mVisitorPicker.setMaxValue(40);
        mVisitorPicker.setWrapSelectorWheel(true);

        removeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.removeSchedule(mDay);
            }
        });

        savedBtn.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {
                if (checkValidation()){
                    DrSchedule schedule = new DrSchedule();
                    schedule.setDay(mDay);
                    schedule.setHospitalName(hospitalNameEt.getText().toString());
                    schedule.setEmployeeType(employeeTypeSpinner.getSelectedItem().toString());
                    schedule.setMaxPatient(mVisitorPicker.getValue());
                    schedule.setTimeFrom(dateFromTp.getText().toString());
                    schedule.setTimeTo(dateToTp.getText().toString());
                    if (isDisableCheck.isSelected()){
                        schedule.setIsDisable(1);
                    }
                    else {
                        schedule.setIsDisable(0);
                    }
                    mDialogCallback.setNewSchedule(schedule);
                }
            }
        });
        dateFromTp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDialogCallback.getTime(dateFromTp);
            }
        });
        dateToTp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDialogCallback.getTime(dateToTp);
            }
        });

        mPresenter.getScheduleByDay(mDay);

    }

    private boolean checkValidation(){

        if(hospitalNameEt.getText().toString().equalsIgnoreCase("")){
            hospitalNameEt.setError("Should not be empty");
            return false;
        }
        else if(employeeTypeSpinner.getSelectedItemPosition() == -1){
            TextView errorText = (TextView)employeeTypeSpinner.getSelectedView();
            errorText.setError("");
            errorText.setTextColor(Color.RED);//just to highlight that this is an error
            errorText.setText("Mandatory field");
            return false;
        }
        else if(mVisitorPicker.getValue() == 0){
            Toast.makeText(getActivity(), "Max visitor Should not be empty", Toast.LENGTH_SHORT).show();
            return false;
        }
        else if(dateFromTp.getText().toString().equalsIgnoreCase("")|| dateFromTp.getText().toString().equalsIgnoreCase("Set time")){
            Toast.makeText(getActivity(), "Date to Should not be empty", Toast.LENGTH_SHORT).show();
            return false;
        }else if(dateToTp.getText().toString().equalsIgnoreCase("")|| dateToTp.getText().toString().equalsIgnoreCase("Set time")){
            Toast.makeText(getActivity(), "Date from Should not be empty", Toast.LENGTH_SHORT).show();
            return false;
        }else if(addressEt.getText().toString().equalsIgnoreCase("")){
            addressEt.setError("Should not be empty");
            return false;
        }
        else {
            return true;
        }

    }
    public void setTime(TextView editText, int hour, int minutes){

                    String timeSet = "";
                    if (hour > 12) {
                        hour -= 12;
                        timeSet = "PM";
                    } else if (hour == 0) {
                        hour += 12;
                        timeSet = "AM";
                    } else if (hour == 12){
                        timeSet = "PM";
                    }else{
                        timeSet = "AM";
                    }

                    String min = "";
                    if (minutes < 10)
                        min = "0" + minutes ;
                    else
                        min = String.valueOf(minutes);

                    // Append in a StringBuilder
                    String aTime = new StringBuilder().append(hour).append(':')
                            .append(min ).append(" ").append(timeSet).toString();
        editText.setText(aTime);
                    Log.e("final time: ",aTime);
    }

}
