package com.ju.drmostafizur.domain.interactors.patient;

import com.ju.drmostafizur.domain.interactors.base.Interactor;
import com.ju.drmostafizur.domain.model.DrSchedule;

import java.util.List;

/**
 * Created by Faizul Haque Nayan on 19/07/16.
 */
public interface GetDrSchedulePatientInteractor extends Interactor {

    interface Callback{
        void onDataFound(List<DrSchedule> scheduleList, int serial);
        void onDataNotFound(String message);
    }
}
