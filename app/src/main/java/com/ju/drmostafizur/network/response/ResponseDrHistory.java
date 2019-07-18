package com.ju.drmostafizur.network.response;

import com.google.gson.annotations.SerializedName;
import com.ju.drmostafizur.network.model.RESTDrDashboardPatientModel;

import java.util.List;

/**
 * Created by Faizul Haque Nayan on 19/07/18.
 */
public class ResponseDrHistory extends BaseResponse {

    @SerializedName("patient")
    private List<RESTDrDashboardPatientModel> patientModelList;

    public List<RESTDrDashboardPatientModel> getPatientModelList() {
        return patientModelList;
    }

    public void setPatientModelList(List<RESTDrDashboardPatientModel> patientModelList) {
        this.patientModelList = patientModelList;
    }
}
