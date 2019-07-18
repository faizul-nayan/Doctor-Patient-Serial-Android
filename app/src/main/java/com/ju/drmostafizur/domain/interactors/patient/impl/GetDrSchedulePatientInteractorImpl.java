package com.ju.drmostafizur.domain.interactors.patient.impl;

import android.util.Log;

import com.ju.drmostafizur.domain.executor.Executor;
import com.ju.drmostafizur.domain.executor.MainThread;
import com.ju.drmostafizur.domain.interactors.base.AbstractInteractor;
import com.ju.drmostafizur.domain.interactors.patient.GetDrSchedulePatientInteractor;
import com.ju.drmostafizur.domain.model.DrSchedule;
import com.ju.drmostafizur.network.RestClient;
import com.ju.drmostafizur.network.model.RESTDrSchedule;
import com.ju.drmostafizur.network.response.ResponseDrSchedulePatient;
import com.ju.drmostafizur.network.services.ServiceDrSchedulePatient;
import com.ju.drmostafizur.storage.converters.DrScheduleModelConverter;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by Faizul Haque Nayan on 19/07/16.
 */
public class GetDrSchedulePatientInteractorImpl extends AbstractInteractor implements GetDrSchedulePatientInteractor {

    private GetDrSchedulePatientInteractor.Callback mCallback;
    private ServiceDrSchedulePatient mService;
    private Call<ResponseDrSchedulePatient> mCall;


    public GetDrSchedulePatientInteractorImpl(Executor threadExecutor, MainThread mainThread,
                                           String apiKey, String day, String date,Callback callback) {
        super(threadExecutor, mainThread);
        this.mCallback = callback;
        mService = RestClient.getService(ServiceDrSchedulePatient.class);
        this.mCall = mService.getDrScheduleInfo(day, date);

    }

    @Override
    public void run() {
        mCall.enqueue(new retrofit2.Callback<ResponseDrSchedulePatient>() {
            @Override
            public void onResponse(Call<ResponseDrSchedulePatient> call, Response<ResponseDrSchedulePatient> response) {
                if (response.body().getStatusCode() == 200){
                    List<DrSchedule> drScheduleList = new ArrayList<>();
                    List<RESTDrSchedule> scheduleList = response.body().getDrScheduleList();
                    if(scheduleList != null && scheduleList.size() > 0){
                        drScheduleList = DrScheduleModelConverter.convertRestListToDoaminModel(response.body().getDrScheduleList());
                    }
                    mCallback.onDataFound(drScheduleList, response.body().getSerial());
                }else{
                    mCallback.onDataNotFound(response.body().getMessage());
                }

            }

            @Override
            public void onFailure(Call<ResponseDrSchedulePatient> call, Throwable t) {
                mCallback.onDataNotFound(t.getMessage().toString());
            }
        });
    }

}
