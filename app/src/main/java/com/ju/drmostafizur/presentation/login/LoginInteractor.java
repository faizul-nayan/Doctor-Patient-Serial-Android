package com.ju.drmostafizur.presentation.login;

import com.ju.drmostafizur.domain.interactors.base.Interactor;

public interface LoginInteractor extends Interactor {

    interface Callback{
        void onSuccess();
        void onFailed();
    }
}
