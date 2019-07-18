package com.ju.drmostafizur.domain.interactors.doctor.impl;

import com.ju.drmostafizur.domain.executor.Executor;
import com.ju.drmostafizur.domain.executor.MainThread;
import com.ju.drmostafizur.domain.interactors.doctor.DrScheduleByDayInteractor;
import com.ju.drmostafizur.domain.interactors.doctor.DrScheduleDialogPresenter;
import com.ju.drmostafizur.domain.interactors.doctor.DrScheduleRemoveInteractor;
import com.ju.drmostafizur.domain.model.DrSchedule;
import com.ju.drmostafizur.presentation.AbstractPresenter;
import com.ju.drmostafizur.presentation.doctor.presenters.DrSettingPresenter;

import java.util.List;

/**
 * Created by Faizul Haque Nayan on 19/07/17.
 */
public class DrScheduleDialogPresenterImpl extends AbstractPresenter implements DrScheduleDialogPresenter,
        DrScheduleByDayInteractor.Callback, DrScheduleRemoveInteractor.Callback{

private DrScheduleDialogPresenter.View mView;
private Executor mExecutor;
private MainThread mMainThread;

public DrScheduleDialogPresenterImpl(Executor executor,MainThread mainThread,DrScheduleDialogPresenter.View view){
        super(executor,mainThread);
        this.mView=view;
        this.mExecutor=executor;
        this.mMainThread=mainThread;

}

    @Override
    public void onDataFound(List<DrSchedule> scheduleList) {
        mView.onScheduleFound(scheduleList);
    }

    @Override
    public void onDataNotFound(String message) {
        mView.showError(message);
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
    public void getScheduleByDay(String day) {
        new DrScheduleByDayInteractorImpl(mExecutor, mMainThread, "", day, this).execute();
    }

    @Override
    public void removeSchedule(String day) {
        new DrScheduleRemoveInteractorImpl(mExecutor, mMainThread, day, "", this).execute();
    }

    @Override
    public void onDeleteSuccess(String message) {
        mView.onScheduleRemoved(message);
    }

    @Override
    public void onDeleteFailed(String message) {
        mView.onScheduleNotRemoved(message);
    }
}