package com.ju.drmostafizur.presentation.doctor.presenters.impl;

import android.content.Context;

import com.ju.drmostafizur.domain.executor.Executor;
import com.ju.drmostafizur.domain.executor.MainThread;
import com.ju.drmostafizur.domain.interactors.doctor.DrHistoryInteractor;
import com.ju.drmostafizur.domain.interactors.doctor.GetDrDashboardInfoInteractor;
import com.ju.drmostafizur.domain.interactors.doctor.impl.DrHistoryInteractorImpl;
import com.ju.drmostafizur.domain.model.DrDashboardPatientModel;
import com.ju.drmostafizur.presentation.AbstractPresenter;
import com.ju.drmostafizur.presentation.doctor.presenters.DrHistoryPresenter;

import java.util.List;

/**
 * Created by Faizul Haque Nayan on 19/07/18.
 */
public class DrHistoryPresenterImpl extends AbstractPresenter implements DrHistoryPresenter, DrHistoryInteractor.Callback{

    private DrHistoryPresenter.View mView;
    private Executor mExecutor;
    private MainThread mMainThread;
    private Context mContext;

    public DrHistoryPresenterImpl(Executor executor, MainThread mainThread, Context context, String day,DrHistoryPresenter.View view) {
        super(executor, mainThread);
        this.mView = view;
        this.mExecutor = executor;
        this.mMainThread = mainThread;
        this.mContext = context;
        new DrHistoryInteractorImpl(executor, mMainThread, "", day, this).execute();

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
    public void onDataFound(List<DrDashboardPatientModel> patientModelList) {
        mView.hideProgress();
        mView.setDrInfo(patientModelList);
    }

    @Override
    public void onDataNotFound(String message) {
        mView.hideProgress();
        mView.showError(message);
    }
}
