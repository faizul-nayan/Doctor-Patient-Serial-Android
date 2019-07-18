package com.ju.drmostafizur;

import android.app.Application;

import com.ju.drmostafizur.utills.MySharePreferences;

/**
 * Created by Faizul Haque Nayan on 18/10/17.
 */

public class MyApplication extends Application {

    private static MyApplication myApplication;
    private static MySharePreferences mySharePreferences;


    @Override
    public void onCreate() {
        super.onCreate();

      //  Fabric.with(this, new Crashlytics.Builder().core(new CrashlyticsCore.Builder().disabled(BuildConfig.DEBUG).build()).build());
        //Fabric.with(this, new Crashlytics());
        //FlowManager.init(new FlowConfig.Builder(this).build());
        myApplication = this;
        mySharePreferences = MySharePreferences.getInstance(this);
    }

    public static MySharePreferences getMySharePreferences() {
        return mySharePreferences;
    }

    public static void setMySharePreferences() {
        mySharePreferences = MySharePreferences.getInstance(myApplication);
    }

}
