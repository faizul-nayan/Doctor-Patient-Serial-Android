package com.ju.drmostafizur.presentation.login;

import android.widget.EditText;

import com.ju.drmostafizur.presentation.BasePresenter;
import com.ju.drmostafizur.presentation.BaseView;

public interface LoginPresenter extends BasePresenter {

    void validateCredentials(String userName, String password);

    interface View extends BaseView{

        boolean checkValidation(EditText etUserName, EditText etPassword);
        void loginSuccess();
        void loginFailed();

    }
}
