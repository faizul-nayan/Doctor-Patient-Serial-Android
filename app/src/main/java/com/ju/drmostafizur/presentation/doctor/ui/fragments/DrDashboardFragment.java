package com.ju.drmostafizur.presentation.doctor.ui.fragments;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.ju.drmostafizur.R;
import com.ju.drmostafizur.domain.executor.impl.ThreadExecutor;
import com.ju.drmostafizur.domain.model.DrDashboardPatientModel;
import com.ju.drmostafizur.domain.model.DrInfoModel;
import com.ju.drmostafizur.domain.model.DrSchedule;
import com.ju.drmostafizur.domain.model.Patient;
import com.ju.drmostafizur.presentation.doctor.adapters.DashboadPatientAdapter;
import com.ju.drmostafizur.presentation.doctor.presenters.DrDashboardPresenter;
import com.ju.drmostafizur.presentation.doctor.presenters.impl.DrDashboardPresenterImpl;
import com.ju.drmostafizur.threading.MainThreadImpl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by Ratan on 7/29/2015.
 * Modify by Faizul Hauqe Nayan
 */

public class DrDashboardFragment extends Fragment implements DrDashboardPresenter.View {


    private View view;

    private RecyclerView mRecyclerView;

    private DashboadPatientAdapter mAdapter;
    private DrDashboardPresenter mPresenter;
    private TextView hospitalNameTv;
    private TextView hospitalAddressTv;
    private TextView drNameTv;
    private TextView drQualificationTv;
    private TextView drAddressTv;
    private TextView todayTimeTv;
    private TextView maxPatientTv;
    private TextView bookedPatientTv;

    private ProgressDialog mProgressDialog;
    private String dayOfTheWeek;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.dr_dashboard_fragment, null);
        init();
        mAdapter = new DashboadPatientAdapter(getContext(), this);

        return view;
    }

    private void init(){
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        hospitalNameTv = (TextView) view.findViewById(R.id.hospitalNameTv);
        hospitalAddressTv = (TextView) view.findViewById(R.id.addressTv);
        drNameTv = (TextView) view.findViewById(R.id.drNameTv);
        drQualificationTv = (TextView) view.findViewById(R.id.drQualificationTv);
        drAddressTv = (TextView) view.findViewById(R.id.drAddressTv);
        todayTimeTv = (TextView) view.findViewById(R.id.todayTimeTv);
        maxPatientTv = (TextView) view.findViewById(R.id.maxPatientTv);
        bookedPatientTv = (TextView) view.findViewById(R.id.bookedTv);
        mPresenter = new DrDashboardPresenterImpl(ThreadExecutor.getInstance(),
                MainThreadImpl.getInstance(), getContext(), this);
        SimpleDateFormat sdf = new SimpleDateFormat("EEEE");
        Date d = new Date();
        dayOfTheWeek = sdf.format(d);
    }

    @Override
    public void onResume() {
        super.onResume();
        showProgress();
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String selectedDate = format.format(calendar.getTime());
        mPresenter.getDrInfo(12, dayOfTheWeek,selectedDate);
    }



    @Override
    public void showProgress() {
        mProgressDialog = new ProgressDialog(getActivity());
        mProgressDialog.setMessage("Please Wait...");
        mProgressDialog.show();
    }


    @Override
    public void hideProgress() {
        mProgressDialog.dismiss();
    }

    @Override
    public void showError(String message) {
        hideProgress();
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setDrInfo(DrInfoModel drInfo, List<DrSchedule> scheduleList, List<DrDashboardPatientModel> patientModelList) {
        drNameTv.setText(drInfo.getFullName());
        drQualificationTv.setText(drInfo.getQualifications());
        drAddressTv.setText(drInfo.getAddress());

        if(scheduleList.size() > 0){
            DrSchedule schedule = scheduleList.get(0);
            hospitalNameTv.setText(schedule.getHospitalName());
            hospitalAddressTv.setText(schedule.getAddress());
            todayTimeTv.setText(schedule.getTimeFrom()+" to "+schedule.getTimeTo());
            maxPatientTv.setText(""+schedule.getMaxPatient());
        }
        mAdapter.addNewPatient(patientModelList);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        bookedPatientTv.setText(""+patientModelList.size());
        hideProgress();
    }
}
