package com.ju.drmostafizur.domain.interactors.patient;

import com.ju.drmostafizur.domain.interactors.base.Interactor;

/**
 * Created by Faizul Haque Nayan on 19/07/16.
 */
public interface PatientBookedInteractor extends Interactor {

    interface Callback{
        void onDataSaved(int serial, String tocken);
        void onDataNotSaved(String message);
    }
}
