package com.ju.drmostafizur.domain.interactors.doctor;

import com.ju.drmostafizur.domain.interactors.base.Interactor;

/**
 * Created by Faizul Haque Nayan on 19/07/13.
 */
public interface SavedDrSettingInteractor extends Interactor {

    interface Callback{

        void onSuccess(String message);
        void onFailed(String message);
    }
}
