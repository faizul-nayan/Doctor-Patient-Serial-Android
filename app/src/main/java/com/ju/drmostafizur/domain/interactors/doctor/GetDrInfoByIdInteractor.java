package com.ju.drmostafizur.domain.interactors.doctor;

import com.ju.drmostafizur.domain.interactors.base.Interactor;
import com.ju.drmostafizur.domain.model.DrInfoModel;
import com.ju.drmostafizur.presentation.doctor.presenters.DrSettingPresenter;

/**
 * Created by Faizul Haque Nayan on 19/07/13.
 */
public interface GetDrInfoByIdInteractor extends Interactor {

    interface Callback{
        void OnDataFound(DrInfoModel model);
        void OnDataNotFound(String model);
    }
}
