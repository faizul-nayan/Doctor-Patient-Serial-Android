package com.ju.drmostafizur.presentation.doctor.ui.fragments;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.ju.drmostafizur.R;
import com.ju.drmostafizur.domain.executor.impl.ThreadExecutor;
import com.ju.drmostafizur.domain.model.DrDashboardPatientModel;
import com.ju.drmostafizur.presentation.doctor.adapters.DashboadPatientAdapter;
import com.ju.drmostafizur.presentation.doctor.adapters.DrHistoryAdapter;
import com.ju.drmostafizur.presentation.doctor.presenters.DrHistoryPresenter;
import com.ju.drmostafizur.presentation.doctor.presenters.impl.DrHistoryPresenterImpl;
import com.ju.drmostafizur.threading.MainThreadImpl;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


/**
 * Created by Ratan on 7/29/2015.
 * implements by Faizul Haque Nayan
 */
public class DrHistoryFragment extends Fragment  implements DrHistoryPresenter.View{

    private RecyclerView recyclerView;
    private DrHistoryAdapter mAdapter;
    private View view;
    private ProgressDialog mProgressDialog;
    private String dayOfTheWeek;
    private DrHistoryPresenter mPresenter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fo_report_fragment, null);
        init();
        mAdapter = new DrHistoryAdapter(getContext(), this);
        return view;
    }

    private void init(){
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String selectedDate = format.format(calendar.getTime());
        showProgress();
        mPresenter = new DrHistoryPresenterImpl(ThreadExecutor.getInstance(),
                MainThreadImpl.getInstance(), getContext(), selectedDate,this);
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
    public void setDrInfo(List<DrDashboardPatientModel> patientModelList) {

        mAdapter.addNewPatient(patientModelList);
        recyclerView.setAdapter(mAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        hideProgress();
    }
}
