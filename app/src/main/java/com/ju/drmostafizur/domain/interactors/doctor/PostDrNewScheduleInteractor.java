package com.ju.drmostafizur.domain.interactors.doctor;

import com.ju.drmostafizur.domain.interactors.base.Interactor;

/**
 * Created by Faizul Haque Nayan on 19/07/14.
 */
public interface PostDrNewScheduleInteractor extends Interactor {

    interface Callback{
        void onScheduleSaved(String message);
        void onScheduleNotSaved(String message);
    }
}
