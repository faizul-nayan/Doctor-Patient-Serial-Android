package com.ju.drmostafizur.presentation.patient.presenters.impl;

import android.content.Context;

import com.ju.drmostafizur.domain.executor.Executor;
import com.ju.drmostafizur.domain.executor.MainThread;
import com.ju.drmostafizur.domain.interactors.patient.GetDrSchedulePatientInteractor;
import com.ju.drmostafizur.domain.interactors.patient.impl.GetDrSchedulePatientInteractorImpl;
import com.ju.drmostafizur.domain.model.DrSchedule;
import com.ju.drmostafizur.presentation.AbstractPresenter;
import com.ju.drmostafizur.presentation.patient.presenters.PatientSchedulePresenter;

import java.util.List;

/**
 * Created by Faizul Haque Nayan on 19/07/16.
 */
public class PatientSchedulePresenterImpl  extends AbstractPresenter implements PatientSchedulePresenter,
        GetDrSchedulePatientInteractor.Callback{

    private PatientSchedulePresenter.View mView;
    private Executor mExecutor;
    private MainThread mMainThread;
    private Context mContext;

    public PatientSchedulePresenterImpl(Executor executor, MainThread mainThread, Context context, PatientSchedulePresenter.View view) {
        super(executor, mainThread);
        this.mView = view;
        this.mExecutor = executor;
        this.mMainThread = mainThread;
        this.mContext = context;

    }

    @Override
    public void getScheduleByDate(String day, String date) {
        new GetDrSchedulePatientInteractorImpl(mExecutor, mMainThread,"",day, date,this).execute();
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
    public void onDataFound(List<DrSchedule> scheduleList, int serial) {
        if(mView!= null){
            mView.onScheduleFound(scheduleList, serial);
        }
    }

    @Override
    public void onDataNotFound(String message) {
        if(mView!= null){
            mView.onScheduleNotFound(message);
        }
    }
}