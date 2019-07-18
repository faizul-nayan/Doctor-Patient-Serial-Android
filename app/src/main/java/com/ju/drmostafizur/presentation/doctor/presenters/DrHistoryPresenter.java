package com.ju.drmostafizur.presentation.doctor.presenters;

import com.ju.drmostafizur.domain.model.DrDashboardPatientModel;
import com.ju.drmostafizur.presentation.BasePresenter;
import com.ju.drmostafizur.presentation.BaseView;

import java.util.List;

/**
 * Created by Faizul Haque Nayan on 19/07/18.
 */
public interface DrHistoryPresenter extends BasePresenter {



    interface View extends BaseView {

        void setDrInfo(List<DrDashboardPatientModel> patientModelList);
    }

}
