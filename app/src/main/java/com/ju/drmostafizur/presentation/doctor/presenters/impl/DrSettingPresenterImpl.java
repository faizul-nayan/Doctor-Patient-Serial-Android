package com.ju.drmostafizur.presentation.doctor.presenters.impl;

import com.ju.drmostafizur.domain.executor.Executor;
import com.ju.drmostafizur.domain.executor.MainThread;
import com.ju.drmostafizur.domain.interactors.doctor.DrScheduleByDayInteractor;
import com.ju.drmostafizur.domain.interactors.doctor.GetDrInfoByIdInteractor;
import com.ju.drmostafizur.domain.interactors.doctor.PostDrNewScheduleInteractor;
import com.ju.drmostafizur.domain.interactors.doctor.SavedDrSettingInteractor;
import com.ju.drmostafizur.domain.interactors.doctor.impl.DrScheduleByDayInteractorImpl;
import com.ju.drmostafizur.domain.interactors.doctor.impl.GetDrInfoByIdInteractorImpl;
import com.ju.drmostafizur.domain.interactors.doctor.impl.PostDrNewScheduleInteractorImpl;
import com.ju.drmostafizur.domain.interactors.doctor.impl.SavedDrSettingInteractorImpl;
import com.ju.drmostafizur.domain.model.DrInfoModel;
import com.ju.drmostafizur.domain.model.DrSchedule;
import com.ju.drmostafizur.presentation.AbstractPresenter;
import com.ju.drmostafizur.presentation.doctor.presenters.DrSettingPresenter;

import java.util.List;

/**
 * Created by Faizul Haque Nayan on 19/07/13.
 */
public class DrSettingPresenterImpl extends AbstractPresenter implements DrSettingPresenter,
        SavedDrSettingInteractor.Callback, GetDrInfoByIdInteractor.Callback,
        PostDrNewScheduleInteractor.Callback{

    private DrSettingPresenter.View mView;
    private Executor mExecutor;
    private MainThread mMainThread;

    public DrSettingPresenterImpl(Executor executor, MainThread mainThread, DrSettingPresenter.View view) {
        super(executor, mainThread);
        this.mView = view;
        this.mExecutor = executor;
        this.mMainThread = mainThread;
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
    public void savedDoctorInfo(String fullName, String designation, String phoneNumber, String email, String address) {
        DrInfoModel model = new DrInfoModel();
        model.setFullName(fullName);
        model.setDesignation(designation);
        model.setEmail(email);
        model.setPhoneNumber(phoneNumber);
        model.setAddress(address);

        new SavedDrSettingInteractorImpl(mExecutor, mMainThread,model,"",this).execute();
    }

    @Override
    public void getDrInfo(int id) {
        new GetDrInfoByIdInteractorImpl(mExecutor, mMainThread, id,"",this).execute();
    }

    @Override
    public void onNewSchedule(DrSchedule drSchedule) {
        new PostDrNewScheduleInteractorImpl(mExecutor, mMainThread, "", drSchedule, this).execute();
    }


    @Override
    public void onSuccess(String message) {
        if(mView != null){
            mView.showError(message);
        }
    }

    @Override
    public void onFailed(String message) {
        if(mView != null){
            mView.showError(message);
        }
    }

    @Override
    public void OnDataFound(DrInfoModel model) {
        if(mView != null){
            mView.setDrInfo(model);
        }
    }

    @Override
    public void OnDataNotFound(String message) {
        if(mView != null){
            mView.showError(message);
        }
    }

    @Override
    public void onScheduleSaved(String message) {
        mView.onScheduleSaved();
    }

    @Override
    public void onScheduleNotSaved(String message) {
        if(mView != null){
            mView.showError(message);
        }
    }
}
