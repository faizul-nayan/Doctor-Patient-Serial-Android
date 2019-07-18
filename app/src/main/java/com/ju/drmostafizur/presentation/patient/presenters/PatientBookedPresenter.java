package com.ju.drmostafizur.presentation.patient.presenters;

import com.ju.drmostafizur.domain.model.PatientBookedModel;
import com.ju.drmostafizur.presentation.BasePresenter;
import com.ju.drmostafizur.presentation.BaseView;

/**
 * Created by Faizul Haque Nayan on 19/07/16.
 */
public interface PatientBookedPresenter extends BasePresenter {

    void setPatientBooked(PatientBookedModel patientBooked);

    interface View extends BaseView{
        void onSaved(int serial, String tocken);
        void onNotSaved(String message);
    }
}
