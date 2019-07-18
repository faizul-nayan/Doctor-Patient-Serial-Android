package com.ju.drmostafizur.domain.interactors.doctor.impl;

import android.util.Log;

import com.ju.drmostafizur.domain.executor.Executor;
import com.ju.drmostafizur.domain.executor.MainThread;
import com.ju.drmostafizur.domain.interactors.base.AbstractInteractor;
import com.ju.drmostafizur.domain.interactors.doctor.GetDrDashboardInfoInteractor;
import com.ju.drmostafizur.domain.model.DrDashboardPatientModel;
import com.ju.drmostafizur.domain.model.DrInfoModel;
import com.ju.drmostafizur.domain.model.DrSchedule;
import com.ju.drmostafizur.network.RestClient;
import com.ju.drmostafizur.network.model.RESTDrDashboardPatientModel;
import com.ju.drmostafizur.network.model.RESTDrSchedule;
import com.ju.drmostafizur.network.response.ResponseDrDashboard;
import com.ju.drmostafizur.network.response.ResponseGetDrInfoById;
import com.ju.drmostafizur.network.services.ServiceDrDashboard;
import com.ju.drmostafizur.storage.converters.DrDashboardPatientModelConverter;
import com.ju.drmostafizur.storage.converters.DrScheduleModelConverter;
import com.ju.drmostafizur.storage.converters.DrSettingModelConverter;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by Faizul Haque Nayan on 19/07/14.
 */
public class GetDrDashboardInfoInteractorImpl  extends AbstractInteractor implements GetDrDashboardInfoInteractor {

    private GetDrDashboardInfoInteractor.Callback mCallback;
    private ServiceDrDashboard mService;
    private Call<ResponseDrDashboard> mCall;


    public GetDrDashboardInfoInteractorImpl(Executor threadExecutor, MainThread mainThread, int drId,String day,
                                       String date,String apiKey, Callback callback) {
        super(threadExecutor, mainThread);
        this.mCallback = callback;
        mService = RestClient.getService(ServiceDrDashboard.class);
        this.mCall = mService.getDrDashboardInfo(drId, day, date);

    }

    @Override
    public void run() {
        mCall.enqueue(new retrofit2.Callback<ResponseDrDashboard>() {
            @Override
            public void onResponse(Call<ResponseDrDashboard> call, Response<ResponseDrDashboard> response) {
                if (response.body().getStatusCode() == 200){

                    DrInfoModel infoModel = DrSettingModelConverter.convertRestToDoaminModel(response.body().getInfoModel());
                    List<DrSchedule> scheduleList = new ArrayList<>();
                    List<RESTDrSchedule> list = response.body().getScheduleList();
                    if(list != null && list.size() > 0){
                        scheduleList = DrScheduleModelConverter.convertRestListToDoaminModel(response.body().getScheduleList());
                    }
                    List<DrDashboardPatientModel> patientModelList = new ArrayList<>();
                    List<RESTDrDashboardPatientModel> restDrDashboardPatientModelList = response.body().getPatientModelList();
                    if(restDrDashboardPatientModelList != null && restDrDashboardPatientModelList.size() > 0){
                        patientModelList = DrDashboardPatientModelConverter.convertRestListToDoaminModel(restDrDashboardPatientModelList);
                    }
                    mCallback.onDrInfoFound(infoModel, scheduleList,patientModelList);
                }else{
                  //  mCallback.OnDataNotFound(response.body().getMessage());
                }

            }

            @Override
            public void onFailure(Call<ResponseDrDashboard> call, Throwable t) {
               // mCallback.OnDataNotFound(t.getMessage().toString());
            }
        });
    }

}
