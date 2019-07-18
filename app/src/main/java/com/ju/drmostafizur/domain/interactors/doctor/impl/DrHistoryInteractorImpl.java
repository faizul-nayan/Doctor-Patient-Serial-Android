package com.ju.drmostafizur.domain.interactors.doctor.impl;

import com.ju.drmostafizur.domain.executor.Executor;
import com.ju.drmostafizur.domain.executor.MainThread;
import com.ju.drmostafizur.domain.interactors.base.AbstractInteractor;
import com.ju.drmostafizur.domain.interactors.doctor.DrHistoryInteractor;
import com.ju.drmostafizur.network.RestClient;
import com.ju.drmostafizur.network.response.ResponseDrHistory;
import com.ju.drmostafizur.network.services.ServiceDrHistory;
import com.ju.drmostafizur.storage.converters.DrDashboardPatientModelConverter;

import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by Faizul Haque Nayan on 19/07/18.
 */
public class DrHistoryInteractorImpl extends AbstractInteractor implements DrHistoryInteractor {

    private DrHistoryInteractor.Callback mCallback;
    private ServiceDrHistory mService;
    private Call<ResponseDrHistory> mCall;


    public DrHistoryInteractorImpl(Executor threadExecutor, MainThread mainThread,
                                   String apiKey, String day, Callback callback) {
        super(threadExecutor, mainThread);
        this.mCallback = callback;
        mService = RestClient.getService(ServiceDrHistory.class);
        this.mCall = mService.getDrHistoryByDay(day);

    }

    @Override
    public void run() {
        mCall.enqueue(new retrofit2.Callback<ResponseDrHistory>() {
            @Override
            public void onResponse(Call<ResponseDrHistory> call, Response<ResponseDrHistory> response) {
                if (response.body().getStatusCode() == 200){
                    mCallback.onDataFound(DrDashboardPatientModelConverter.convertRestListToDoaminModel(response.body().getPatientModelList()));
                }else{
                    mCallback.onDataNotFound(response.body().getMessage());
                }

            }

            @Override
            public void onFailure(Call<ResponseDrHistory> call, Throwable t) {
                mCallback.onDataNotFound(t.getMessage().toString());
            }
        });
    }

}
