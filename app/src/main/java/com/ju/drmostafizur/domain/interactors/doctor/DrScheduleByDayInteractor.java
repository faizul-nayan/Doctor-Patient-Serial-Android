package com.ju.drmostafizur.domain.interactors.doctor;

import com.google.gson.annotations.SerializedName;
import com.ju.drmostafizur.domain.interactors.base.Interactor;
import com.ju.drmostafizur.domain.model.DrSchedule;

import java.util.List;

/**
 * Created by Faizul Haque Nayan on 19/07/17.
 */
public interface DrScheduleByDayInteractor extends Interactor {

    interface Callback{
        void onDataFound(List<DrSchedule> scheduleList);
        void onDataNotFound(String message);
    }
}
