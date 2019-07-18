package com.ju.drmostafizur.domain.interactors.doctor;

import com.ju.drmostafizur.domain.interactors.base.Interactor;

/**
 * Created by Faizul Haque Nayan on 19/07/17.
 */
public interface DrScheduleRemoveInteractor extends Interactor {

    interface Callback{
        void onDeleteSuccess(String message);
        void onDeleteFailed(String message);
    }
}
