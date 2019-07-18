package com.ju.drmostafizur.domain.interactors.doctor;

import com.ju.drmostafizur.domain.interactors.base.Interactor;
import com.ju.drmostafizur.domain.model.DrDashboardPatientModel;
import com.ju.drmostafizur.domain.model.DrInfoModel;
import com.ju.drmostafizur.domain.model.DrSchedule;

import java.util.List;

/**
 * Created by Faizul Haque Nayan on 19/07/14.
 */
public interface GetDrDashboardInfoInteractor extends Interactor {

    interface Callback{
        void onDrInfoFound(DrInfoModel infoModel, List<DrSchedule> scheduleList, List<DrDashboardPatientModel> patientModelList);
        void onDrInfoNotFound(String message);
    }
}
