package com.ju.drmostafizur.presentation.doctor.ui.fragments;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.applandeo.materialcalendarview.CalendarView;
import com.applandeo.materialcalendarview.EventDay;
import com.applandeo.materialcalendarview.exceptions.OutOfDateRangeException;
import com.applandeo.materialcalendarview.listeners.OnDayClickListener;
import com.google.gson.Gson;
import com.ju.drmostafizur.R;
import com.ju.drmostafizur.domain.executor.impl.ThreadExecutor;
import com.ju.drmostafizur.domain.model.DrSchedule;
import com.ju.drmostafizur.domain.model.PatientBookedModel;
import com.ju.drmostafizur.presentation.patient.presenters.PatientSchedulePresenter;
import com.ju.drmostafizur.presentation.patient.presenters.impl.PatientSchedulePresenterImpl;
import com.ju.drmostafizur.presentation.patient.ui.fragments.PatientScheduleFragment;
import com.ju.drmostafizur.threading.MainThreadImpl;
import com.ju.drmostafizur.utills.MySharePreferences;
import com.ju.drmostafizur.utills.Values;
import com.stepstone.stepper.StepperLayout;
import com.stepstone.stepper.VerificationError;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

/**
 * Created by Ratan on 7/29/2015.
 * Modify by Faizul Haque Nayan
 */
public class DrScheduleFragment extends Fragment implements PatientSchedulePresenter.View{

    private View view;
    private static DrScheduleFragment mInstance;
    private LinearLayout scheduleLayout;
    private LinearLayout noDataLayout;
    private TextView hospitalNameTv;
    private TextView addressTv;
    private TextView visitingTimeTv;
    private TextView serialTv;
    private PatientSchedulePresenter mPresenter;
    private ProgressDialog mProgressDialog;

    public static DrScheduleFragment getInstance() {
        mInstance = new DrScheduleFragment();
        return mInstance;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.dr_schedule_fragment, null);
        mPresenter = new PatientSchedulePresenterImpl(ThreadExecutor.getInstance(), MainThreadImpl.getInstance(),
                getContext(), this);
        init();
        return view;
    }

    private void init(){

        scheduleLayout = (LinearLayout) view.findViewById(R.id.serialDetailsLayout);
        noDataLayout = (LinearLayout) view.findViewById(R.id.noDataFoundLayout);
        hospitalNameTv = (TextView) view.findViewById(R.id.hospitalNameTv);
        addressTv = (TextView) view.findViewById(R.id.addressTv);
        visitingTimeTv = (TextView) view.findViewById(R.id.visitingTimeTv);
        serialTv = (TextView) view.findViewById(R.id.serialTv);

        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_WEEK);
        calendar.set(year, month,day);

        CalendarView calendarView = (CalendarView) view.findViewById(R.id.calendarView);
        try {
            calendarView.setDate(calendar);
        } catch (OutOfDateRangeException e) {
            e.printStackTrace();
        }
      //  showProgress();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
       // mPresenter.getScheduleByDate(calendar.getDisplayName(Calendar.DAY_OF_WEEK,Calendar.LONG, Locale.getDefault()), dateFormat.format(calendar.getTime()));


        calendarView.setOnDayClickListener(new OnDayClickListener() {
            @SuppressLint("WrongConstant")
            @Override
            public void onDayClick(EventDay eventDay) {
                String selectedDay = eventDay.getCalendar().getDisplayName(Calendar.DAY_OF_WEEK,Calendar.LONG, Locale.getDefault());
                showProgress();
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                String selectedDate = dateFormat.format(eventDay.getCalendar().getTime());
                mPresenter.getScheduleByDate(selectedDay,selectedDate);
            }
        });

    }


    @Override
    public void onScheduleFound(List<DrSchedule> drSchedule, int serial) {
        hideProgress();
        if(drSchedule.size() > 0){
            scheduleLayout.setVisibility(View.VISIBLE);
            noDataLayout.setVisibility(View.GONE);
            hospitalNameTv.setText(drSchedule.get(0).getHospitalName());
            addressTv.setText(drSchedule.get(0).getAddress());
            visitingTimeTv.setText(drSchedule.get(0).getTimeFrom() +" to "+drSchedule.get(0).getTimeTo());
            serialTv.setText("Serial: "+(serial-1));
        }
        else {
            noDataLayout.setVisibility(View.VISIBLE);
            scheduleLayout.setVisibility(View.GONE);
        }
    }

    @Override
    public void onScheduleNotFound(String message) {
        hideProgress();
        scheduleLayout.setVisibility(View.GONE);
        noDataLayout.setVisibility(View.VISIBLE);
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
