package com.ju.drmostafizur.utills;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Faizul Haque Nayan on 18/10/17.
 */

public class MySharePreferences {

    private SharedPreferences sharedpreferences;
    private SharedPreferences.Editor editor;
    public static final String SOFTWAREDATE = "SoftwareDate";
    public static final String BRANCHID = "BranchId";
    public static final String USERNAME = "UserName";
    public static final String PASSWORD = "Password";
    public static final String CHECKED = "Checked";
    public static final String BRANCHNAME = "BranchName";
    public static final String EMPNAME = "EmpName";
    public static final String EMPID = "EmpID";
    public static final String USERID = "UserId";
    public static final String EMPLOYEENAME = "EmployeeName";
    public static final String HOST = "Host";
    public static final String LABEL = "Label";
    public static final String FIELDOFFICER = "Field_Officer";
    public static final String HEADOFFICE = "Head_Office";
    public static final String WHO = "Who";
    public static final String WHICH = "Which";

    private static MySharePreferences mySharePreferences;

    public static final String MyPREFERENCES = "LocalPreferences";

    public MySharePreferences(Context context) {

        sharedpreferences = context.getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        editor = sharedpreferences.edit();
    }

    public static MySharePreferences getInstance(Context context){
        if(mySharePreferences == null){
           mySharePreferences =  new MySharePreferences(context);
        }

        return mySharePreferences;
    }

    public void setString(String key, String value){

        editor.putString(key,value);
        editor.apply();
        editor.commit();
    }


    public void setBoolean(String key, boolean temp){
        editor.putBoolean(key, temp);
        editor.apply();
        editor.commit();
    }

        public void setInt(String key, int value) {
            editor.putInt(key, value);
            editor.apply();
            editor.commit();
        }

    public boolean getboolean(String key){return sharedpreferences.getBoolean(key, false);}
    public int getInt(String key){return  sharedpreferences.getInt(key, 0);}
    public String getString(String key){return sharedpreferences.getString(key,"");}

}
