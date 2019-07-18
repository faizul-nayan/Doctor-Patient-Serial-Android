package com.ju.drmostafizur.domain.interactors.doctor.impl;

import com.ju.drmostafizur.domain.executor.Executor;
import com.ju.drmostafizur.domain.executor.MainThread;
import com.ju.drmostafizur.domain.interactors.base.AbstractInteractor;
import com.ju.drmostafizur.domain.interactors.doctor.DrScheduleByDayInteractor;
import com.ju.drmostafizur.network.RestClient;
import com.ju.drmostafizur.network.response.ResponseDrScheduleByDay;
import com.ju.drmostafizur.network.services.ServiceDrScheduleByDate;
import com.ju.drmostafizur.storage.converters.DrScheduleModelConverter;

import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by Faizul Haque Nayan on 19/07/17.
 */
public class DrScheduleByDayInteractorImpl extends AbstractInteractor implements DrScheduleByDayInteractor {

    private DrScheduleByDayInteractor.Callback mCallback;
    private ServiceDrScheduleByDate mService;
    private Call<ResponseDrScheduleByDay> mCall;


    public DrScheduleByDayInteractorImpl(Executor threadExecutor, MainThread mainThread,
                                       String apiKey, String day,Callback callback) {
        super(threadExecutor, mainThread);
        this.mCallback = callback;
        mService = RestClient.getService(ServiceDrScheduleByDate.class);
        this.mCall = mService.getDrScheduleInfo(day);

    }

    @Override
    public void run() {
        mCall.enqueue(new retrofit2.Callback<ResponseDrScheduleByDay>() {
            @Override
            public void onResponse(Call<ResponseDrScheduleByDay> call, Response<ResponseDrScheduleByDay> response) {
                if (response.body().getStatusCode() == 200){
                    mCallback.onDataFound(DrScheduleModelConverter.convertRestListToDoaminModel(response.body().getScheduleList()));
                }else{
                    mCallback.onDataNotFound(response.body().getMessage());
                }

            }

            @Override
            public void onFailure(Call<ResponseDrScheduleByDay> call, Throwable t) {
                mCallback.onDataNotFound(t.getMessage().toString());
            }
        });
    }

}
