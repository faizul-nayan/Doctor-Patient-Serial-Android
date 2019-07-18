package com.ju.drmostafizur.domain.interactors.doctor.impl;

import com.ju.drmostafizur.domain.executor.Executor;
import com.ju.drmostafizur.domain.executor.MainThread;
import com.ju.drmostafizur.domain.interactors.base.AbstractInteractor;
import com.ju.drmostafizur.domain.interactors.doctor.SavedDrSettingInteractor;
import com.ju.drmostafizur.domain.model.DrInfoModel;
import com.ju.drmostafizur.network.RestClient;
import com.ju.drmostafizur.network.model.RESTDrInfoModel;
import com.ju.drmostafizur.network.requests.RequestDrSettingSaved;
import com.ju.drmostafizur.network.response.ResponseDrSettingSaved;
import com.ju.drmostafizur.network.services.ServiceDrSettingSaved;
import com.ju.drmostafizur.storage.converters.DrSettingModelConverter;

import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by Faizul Haque Nayan on 19/07/13.
 */
public class SavedDrSettingInteractorImpl extends AbstractInteractor implements SavedDrSettingInteractor {

    private Callback mCallback;
    private ServiceDrSettingSaved mService;
    private Call<ResponseDrSettingSaved> mCall;
    private DrInfoModel model;


    public SavedDrSettingInteractorImpl(Executor threadExecutor, MainThread mainThread, DrInfoModel savingAccOpen,
                                         String apiKey, Callback callback) {
        super(threadExecutor, mainThread);
        this.mCallback = callback;
        this.model = savingAccOpen;
        mService = RestClient.getService(ServiceDrSettingSaved.class);
        RESTDrInfoModel restSavingAccOpen = DrSettingModelConverter.convertDomainToRestModel(model);
        RequestDrSettingSaved saved = new RequestDrSettingSaved();
        saved.setModel(restSavingAccOpen);

        this.mCall = mService.setDrInfo(saved);

    }

    @Override
    public void run() {
        mCall.enqueue(new retrofit2.Callback<ResponseDrSettingSaved>() {
            @Override
            public void onResponse(Call<ResponseDrSettingSaved> call, Response<ResponseDrSettingSaved> response) {
                if (response.body().getStatusCode() == 200){
                    mCallback.onSuccess(response.body().getMessage());
                }else{
                    mCallback.onFailed(response.body().getMessage());
                }

            }

            @Override
            public void onFailure(Call<ResponseDrSettingSaved> call, Throwable t) {
                mCallback.onFailed(t.getMessage().toString());
            }
        });
    }

}
