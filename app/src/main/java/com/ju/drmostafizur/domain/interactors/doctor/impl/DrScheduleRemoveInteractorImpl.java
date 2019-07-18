package com.ju.drmostafizur.domain.interactors.doctor.impl;

import com.ju.drmostafizur.domain.executor.Executor;
import com.ju.drmostafizur.domain.executor.MainThread;
import com.ju.drmostafizur.domain.interactors.base.AbstractInteractor;
import com.ju.drmostafizur.domain.interactors.doctor.DrScheduleRemoveInteractor;
import com.ju.drmostafizur.domain.interactors.doctor.GetDrInfoByIdInteractor;
import com.ju.drmostafizur.domain.model.DrInfoModel;
import com.ju.drmostafizur.network.RestClient;
import com.ju.drmostafizur.network.response.ResponseDrScheduleRemove;
import com.ju.drmostafizur.network.response.ResponseGetDrInfoById;
import com.ju.drmostafizur.network.services.ServiceDrScheduleRemove;
import com.ju.drmostafizur.network.services.ServiceGetDrInfobyId;
import com.ju.drmostafizur.storage.converters.DrSettingModelConverter;

import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by Faizul Haque Nayan on 19/07/17.
 */
public class DrScheduleRemoveInteractorImpl extends AbstractInteractor implements DrScheduleRemoveInteractor {

    private DrScheduleRemoveInteractor.Callback mCallback;
    private ServiceDrScheduleRemove mService;
    private Call<ResponseDrScheduleRemove> mCall;
    private DrInfoModel model;


    public DrScheduleRemoveInteractorImpl(Executor threadExecutor, MainThread mainThread, String day,
                                       String apiKey, Callback callback) {
        super(threadExecutor, mainThread);
        this.mCallback = callback;
        mService = RestClient.getService(ServiceDrScheduleRemove.class);
        this.mCall = mService.getDrDashboardInfo(day);

    }

    @Override
    public void run() {
        mCall.enqueue(new retrofit2.Callback<ResponseDrScheduleRemove>() {
            @Override
            public void onResponse(Call<ResponseDrScheduleRemove> call, Response<ResponseDrScheduleRemove> response) {
                if (response.body().getStatusCode() == 200){
                    mCallback.onDeleteSuccess(response.body().getMessage());
                }else{
                    mCallback.onDeleteFailed(response.body().getMessage());
                }

            }

            @Override
            public void onFailure(Call<ResponseDrScheduleRemove> call, Throwable t) {
                mCallback.onDeleteFailed(t.getMessage().toString());
            }
        });
    }

}
