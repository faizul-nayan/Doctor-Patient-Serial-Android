package com.ju.drmostafizur.presentation.login;

import android.content.Context;

import com.ju.drmostafizur.domain.executor.Executor;
import com.ju.drmostafizur.domain.executor.MainThread;
import com.ju.drmostafizur.presentation.AbstractPresenter;

import java.util.ConcurrentModificationException;

public class LoginPresenterImpl extends AbstractPresenter implements LoginPresenter,
        LoginInteractor.Callback{

    private View mView;
    private Context mContext;

    public LoginPresenterImpl(Executor executor, MainThread mainThread, Context context,View view) {
        super(executor, mainThread);
        this.mView = view;
        this.mContext = context;
    }

    @Override
    public void resume() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void stop() {

    }

    @Override
    public void destroy() {

    }

    @Override
    public void onError(String message) {

    }

    @Override
    public void validateCredentials(String userName, String password) {

        new LoginInteractorImpl(mExecutor, mMainThread, userName, password, mContext, this).execute();
    }

    @Override
    public void onSuccess() {
        mView.hideProgress();
        mView.loginSuccess();
    }

    @Override
    public void onFailed() {
        mView.hideProgress();
        mView.loginFailed();
    }
}
