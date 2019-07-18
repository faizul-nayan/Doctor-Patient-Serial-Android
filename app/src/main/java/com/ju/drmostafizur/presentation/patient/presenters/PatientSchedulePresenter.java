package com.ju.drmostafizur.presentation.patient.presenters;

import com.ju.drmostafizur.domain.model.DrSchedule;
import com.ju.drmostafizur.presentation.BasePresenter;
import com.ju.drmostafizur.presentation.BaseView;

import java.util.List;

/**
 * Created by Faizul Haque Nayan on 19/07/16.
 */
public interface PatientSchedulePresenter extends BasePresenter {

    void getScheduleByDate(String day, String date);

    interface View extends BaseView{
        void onScheduleFound(List<DrSchedule> drSchedule, int serial);
        void onScheduleNotFound(String message);
    }
}
