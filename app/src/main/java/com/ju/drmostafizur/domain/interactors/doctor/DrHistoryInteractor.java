package com.ju.drmostafizur.domain.interactors.doctor;

import com.ju.drmostafizur.domain.interactors.base.Interactor;
import com.ju.drmostafizur.domain.model.DrDashboardPatientModel;

import java.util.List;

/**
 * Created by Faizul Haque Nayan on 19/07/18.
 */
public interface DrHistoryInteractor extends Interactor {

    interface Callback{
        void onDataFound(List<DrDashboardPatientModel> patientModelList);
        void onDataNotFound(String message);
    }
}
