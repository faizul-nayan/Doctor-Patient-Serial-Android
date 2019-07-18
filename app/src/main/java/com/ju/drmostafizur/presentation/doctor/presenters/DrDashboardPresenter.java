package com.ju.drmostafizur.presentation.doctor.presenters;

import com.ju.drmostafizur.domain.model.DrDashboardPatientModel;
import com.ju.drmostafizur.domain.model.DrInfoModel;
import com.ju.drmostafizur.domain.model.DrSchedule;
import com.ju.drmostafizur.presentation.AbstractPresenter;
import com.ju.drmostafizur.presentation.BasePresenter;
import com.ju.drmostafizur.presentation.BaseView;

import java.util.List;

/**
 * Created by Faizul Haque Nayan on 19/07/03.
 */

public interface DrDashboardPresenter extends BasePresenter {

    void getDrInfo(int drId, String day, String date);

    interface View extends BaseView{
        void setDrInfo(DrInfoModel drInfo, List<DrSchedule> drScheduleList, List<DrDashboardPatientModel> patientModelList);
    }
}
