package com.ju.drmostafizur.presentation.doctor.presenters;

import com.ju.drmostafizur.domain.model.DrInfoModel;
import com.ju.drmostafizur.domain.model.DrSchedule;
import com.ju.drmostafizur.presentation.BasePresenter;
import com.ju.drmostafizur.presentation.BaseView;

import java.util.List;

/**
 * Created by Faizul Haque Nayan on 19/07/13.
 */
public interface DrSettingPresenter extends BasePresenter {

    void savedDoctorInfo(String fullName, String designation, String phoneNumber,
                         String email, String address);
    void getDrInfo(int id);
    void onNewSchedule(DrSchedule drSchedule);


    interface View extends BaseView{
        void setDrInfo(DrInfoModel drInfo);
        void onScheduleDayClick(String day);
        void onScheduleSaved();
    }

}
