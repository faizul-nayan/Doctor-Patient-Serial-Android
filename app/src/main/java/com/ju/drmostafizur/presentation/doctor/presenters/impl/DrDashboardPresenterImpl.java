package com.ju.drmostafizur.presentation.doctor.presenters.impl;

import android.content.Context;

import com.ju.drmostafizur.domain.executor.Executor;
import com.ju.drmostafizur.domain.executor.MainThread;
import com.ju.drmostafizur.domain.interactors.doctor.GetDrDashboardInfoInteractor;
import com.ju.drmostafizur.domain.interactors.doctor.impl.GetDrDashboardInfoInteractorImpl;
import com.ju.drmostafizur.domain.model.DrDashboardPatientModel;
import com.ju.drmostafizur.domain.model.DrInfoModel;
import com.ju.drmostafizur.domain.model.DrSchedule;
import com.ju.drmostafizur.presentation.AbstractPresenter;
import com.ju.drmostafizur.presentation.doctor.presenters.DrDashboardPresenter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Faizul Haque Nayan on 19/07/03.
 */

public class DrDashboardPresenterImpl extends AbstractPresenter implements DrDashboardPresenter,
        GetDrDashboardInfoInteractor.Callback{

    private DrDashboardPresenter.View mView;
    private Executor mExecutor;
    private MainThread mMainThread;
    private Context mContext;

    public DrDashboardPresenterImpl(Executor executor, MainThread mainThread, Context context, DrDashboardPresenter.View view) {
        super(executor, mainThread);
        this.mView = view;
        this.mExecutor = executor;
        this.mMainThread = mainThread;
        this.mContext = context;

    }

    @Override
    public void resume() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void stop() {

    }

    @Override
    public void destroy() {

    }

    @Override
    public void onError(String message) {

    }

    @Override
    public void getDrInfo(int drId, String day, String date) {
        new GetDrDashboardInfoInteractorImpl(mExecutor, mMainThread, 12, day,date,"", this).execute();
    }

    @Override
    public void onDrInfoFound(DrInfoModel infoModel, List<DrSchedule> drScheduleList, List<DrDashboardPatientModel> patientModelList) {
        if (mView != null){
            mView.setDrInfo(infoModel, drScheduleList, patientModelList);
        }
    }

    @Override
    public void onDrInfoNotFound(String message) {
        if (mView != null){
            mView.showError(message);
        }
    }
}
