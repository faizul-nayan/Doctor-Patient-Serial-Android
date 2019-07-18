package com.ju.drmostafizur.domain.interactors.patient.impl;

import com.ju.drmostafizur.domain.executor.Executor;
import com.ju.drmostafizur.domain.executor.MainThread;
import com.ju.drmostafizur.domain.interactors.base.AbstractInteractor;
import com.ju.drmostafizur.domain.interactors.patient.PatientBookedInteractor;
import com.ju.drmostafizur.domain.model.DrSchedule;
import com.ju.drmostafizur.domain.model.PatientBookedModel;
import com.ju.drmostafizur.network.RestClient;
import com.ju.drmostafizur.network.requests.RequestPatientBooked;
import com.ju.drmostafizur.network.response.ResponsePatientBooked;
import com.ju.drmostafizur.network.services.ServicePatientBook;
import com.ju.drmostafizur.storage.converters.PatientBookedConverter;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by Faizul Haque Nayan on 19/07/16.
 */
public class PatientBookedInteractorImpl extends AbstractInteractor implements PatientBookedInteractor {

    private PatientBookedInteractor.Callback mCallback;
    private ServicePatientBook mService;
    private Call<ResponsePatientBooked> mCall;


    public PatientBookedInteractorImpl(Executor threadExecutor, MainThread mainThread,
                                       String apiKey, PatientBookedModel patientBookedModel, Callback callback) {
        super(threadExecutor, mainThread);
        this.mCallback = callback;
        mService = RestClient.getService(ServicePatientBook.class);
        RequestPatientBooked requestPatientBooked = new RequestPatientBooked();
        requestPatientBooked.setRestPatientBookedModel(PatientBookedConverter.convertDomainToRestModel(patientBookedModel));

        this.mCall = mService.setPatientBookedInfo(requestPatientBooked);

    }

    @Override
    public void run() {
        mCall.enqueue(new retrofit2.Callback<ResponsePatientBooked>() {
            @Override
            public void onResponse(Call<ResponsePatientBooked> call, Response<ResponsePatientBooked> response) {
                if (response.body().getStatusCode() == 200){

                    mCallback.onDataSaved(response.body().getSerial(), response.body().getTocken());
                }else{
                    mCallback.onDataNotSaved(response.body().getMessage());
                }

            }

            @Override
            public void onFailure(Call<ResponsePatientBooked> call, Throwable t) {
                mCallback.onDataNotSaved(t.getMessage().toString());
            }
        });
    }

}
