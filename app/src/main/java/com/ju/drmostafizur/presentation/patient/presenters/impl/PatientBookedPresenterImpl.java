package com.ju.drmostafizur.presentation.patient.presenters.impl;

import android.content.Context;

import com.ju.drmostafizur.domain.executor.Executor;
import com.ju.drmostafizur.domain.executor.MainThread;
import com.ju.drmostafizur.domain.interactors.patient.PatientBookedInteractor;
import com.ju.drmostafizur.domain.interactors.patient.impl.PatientBookedInteractorImpl;
import com.ju.drmostafizur.domain.model.PatientBookedModel;
import com.ju.drmostafizur.presentation.AbstractPresenter;
import com.ju.drmostafizur.presentation.patient.presenters.PatientBookedPresenter;

/**
 * Created by Faizul Haque Nayan on 19/07/16.
 */
public class PatientBookedPresenterImpl extends AbstractPresenter implements PatientBookedPresenter,
        PatientBookedInteractor.Callback{

    private PatientBookedPresenter.View mView;
    private Executor mExecutor;
    private MainThread mMainThread;
    private Context mContext;

    public PatientBookedPresenterImpl(Executor executor, MainThread mainThread, Context context, PatientBookedPresenter.View view) {
        super(executor, mainThread);
        this.mView = view;
        this.mExecutor = executor;
        this.mMainThread = mainThread;
        this.mContext = context;

    }


    @Override
    public void setPatientBooked(PatientBookedModel patientBooked) {
        new PatientBookedInteractorImpl(mExecutor, mMainThread, "", patientBooked,this).execute();
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
    public void onDataSaved(int serial, String tocken) {
        mView.onSaved(serial, tocken);
    }

    @Override
    public void onDataNotSaved(String message) {
        mView.onNotSaved(message);
    }
}