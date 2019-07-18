package com.ju.drmostafizur.presentation.login;

import android.content.Context;

import com.ju.drmostafizur.domain.executor.Executor;
import com.ju.drmostafizur.domain.executor.MainThread;
import com.ju.drmostafizur.domain.interactors.base.AbstractInteractor;

public class LoginInteractorImpl extends AbstractInteractor implements LoginInteractor {

    private String mUserName;
    private String mPassword;
    private Context mContext;
    private Callback mCallback;

    public LoginInteractorImpl(Executor threadExecutor, MainThread mainThread, String userName,
                               String password, Context context, Callback callback) {
        super(threadExecutor, mainThread);
        this.mUserName = userName;
        this.mPassword = password;
        this.mCallback = callback;
        this.mContext = context;
    }

    @Override
    public void run() {
        mMainThread.post(new Runnable() {
            @Override
            public void run() {
                mCallback.onSuccess();
            }
        });
    }
}
