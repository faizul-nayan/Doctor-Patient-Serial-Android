package com.ju.drmostafizur.domain.interactors.doctor;

import com.ju.drmostafizur.domain.model.DrSchedule;
import com.ju.drmostafizur.presentation.BasePresenter;
import com.ju.drmostafizur.presentation.BaseView;

import java.util.List;

/**
 * Created by Faizul Haque Nayan on 19/07/17.
 */
public interface DrScheduleDialogPresenter extends BasePresenter {


    void getScheduleByDay(String day);
    void removeSchedule(String day);

    interface View extends BaseView{
        void onScheduleFound(List<DrSchedule> scheduleList);
        void onScheduleNotFound(String message);
        void onScheduleRemoved(String message);
        void onScheduleNotRemoved(String message);
    }
}
