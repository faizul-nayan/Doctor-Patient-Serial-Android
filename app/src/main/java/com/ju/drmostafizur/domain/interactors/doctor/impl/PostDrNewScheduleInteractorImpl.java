package com.ju.drmostafizur.domain.interactors.doctor.impl;

import com.ju.drmostafizur.domain.executor.Executor;
import com.ju.drmostafizur.domain.executor.MainThread;
import com.ju.drmostafizur.domain.interactors.base.AbstractInteractor;
import com.ju.drmostafizur.domain.interactors.doctor.PostDrNewScheduleInteractor;
import com.ju.drmostafizur.domain.model.DrSchedule;
import com.ju.drmostafizur.network.RestClient;
import com.ju.drmostafizur.network.requests.RequestDrNewSchedule;
import com.ju.drmostafizur.network.response.ResponseDrNewSchedule;
import com.ju.drmostafizur.network.services.ServiceDrNewSchedule;
import com.ju.drmostafizur.storage.converters.DrScheduleModelConverter;

import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by Faizul Haque Nayan on 19/07/14.
 */
public class PostDrNewScheduleInteractorImpl extends AbstractInteractor implements PostDrNewScheduleInteractor {

    private PostDrNewScheduleInteractor.Callback mCallback;
    private ServiceDrNewSchedule mService;
    private Call<ResponseDrNewSchedule> mCall;


    public PostDrNewScheduleInteractorImpl(Executor threadExecutor, MainThread mainThread,
                                       String apiKey, DrSchedule drSchedule,Callback callback) {
        super(threadExecutor, mainThread);
        this.mCallback = callback;
        mService = RestClient.getService(ServiceDrNewSchedule.class);
        RequestDrNewSchedule schedule = new RequestDrNewSchedule();
        schedule.setSchedule(DrScheduleModelConverter.convertDomainToRestModel(drSchedule));
        this.mCall = mService.setDrScheduleInfo(schedule);

    }

    @Override
    public void run() {
        mCall.enqueue(new retrofit2.Callback<ResponseDrNewSchedule>() {
            @Override
            public void onResponse(Call<ResponseDrNewSchedule> call, Response<ResponseDrNewSchedule> response) {
                if (response.body().getStatusCode() == 200){
                    mCallback.onScheduleSaved(response.body().getMessage());
                }else{
                    mCallback.onScheduleNotSaved(response.body().getMessage());
                }

            }

            @Override
            public void onFailure(Call<ResponseDrNewSchedule> call, Throwable t) {
                mCallback.onScheduleNotSaved(t.getMessage().toString());
            }
        });
    }

}
