package com.ju.drmostafizur.network.response;

import com.google.gson.annotations.SerializedName;
import com.ju.drmostafizur.network.model.RESTDrInfoModel;

/**
 * Created by Faizul Haque Nayan on 19/07/13.
 */
public class ResponseGetDrInfoById extends BaseResponse {

    @SerializedName("info")
    private RESTDrInfoModel model;

    public RESTDrInfoModel getModel() {
        return model;
    }

    public void setModel(RESTDrInfoModel model) {
        this.model = model;
    }

}
