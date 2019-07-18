package com.ju.drmostafizur.domain.interactors.doctor.impl;

import com.ju.drmostafizur.domain.executor.Executor;
import com.ju.drmostafizur.domain.executor.MainThread;
import com.ju.drmostafizur.domain.interactors.base.AbstractInteractor;
import com.ju.drmostafizur.domain.interactors.doctor.GetDrInfoByIdInteractor;
import com.ju.drmostafizur.domain.model.DrInfoModel;
import com.ju.drmostafizur.network.RestClient;
import com.ju.drmostafizur.network.response.ResponseGetDrInfoById;
import com.ju.drmostafizur.network.services.ServiceGetDrInfobyId;
import com.ju.drmostafizur.storage.converters.DrSettingModelConverter;

import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by Faizul Haque Nayan on 19/07/13.
 */
public class GetDrInfoByIdInteractorImpl extends AbstractInteractor implements GetDrInfoByIdInteractor {

    private GetDrInfoByIdInteractor.Callback mCallback;
    private ServiceGetDrInfobyId mService;
    private Call<ResponseGetDrInfoById> mCall;
    private DrInfoModel model;


    public GetDrInfoByIdInteractorImpl(Executor threadExecutor, MainThread mainThread, int drId,
                                        String apiKey, Callback callback) {
        super(threadExecutor, mainThread);
        this.mCallback = callback;
        mService = RestClient.getService(ServiceGetDrInfobyId.class);
        this.mCall = mService.getDrInfo(drId);

    }

    @Override
    public void run() {
        mCall.enqueue(new retrofit2.Callback<ResponseGetDrInfoById>() {
            @Override
            public void onResponse(Call<ResponseGetDrInfoById> call, Response<ResponseGetDrInfoById> response) {
                if (response.body().getStatusCode() == 200){
                    mCallback.OnDataFound(DrSettingModelConverter.convertRestToDoaminModel(response.body().getModel()));
                }else{
                    mCallback.OnDataNotFound(response.body().getMessage());
                }

            }

            @Override
            public void onFailure(Call<ResponseGetDrInfoById> call, Throwable t) {
                mCallback.OnDataNotFound(t.getMessage().toString());
            }
        });
    }

}
