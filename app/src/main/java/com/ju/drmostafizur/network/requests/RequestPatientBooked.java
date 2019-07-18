package com.ju.drmostafizur.network.requests;

import com.google.gson.annotations.SerializedName;
import com.ju.drmostafizur.network.model.RESTPatientBookedModel;

/**
 * Created by Faizul Haque Nayan on 19/07/16.
 */
public class RequestPatientBooked {

    @SerializedName("patient_info")
    private RESTPatientBookedModel restPatientBookedModel;

    public RESTPatientBookedModel getRestPatientBookedModel() {
        return restPatientBookedModel;
    }

    public void setRestPatientBookedModel(RESTPatientBookedModel restPatientBookedModel) {
        this.restPatientBookedModel = restPatientBookedModel;
    }
}
