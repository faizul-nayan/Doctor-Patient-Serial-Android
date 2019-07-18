package com.ju.drmostafizur.network.services;

import com.ju.drmostafizur.network.requests.RequestDrSettingSaved;
import com.ju.drmostafizur.network.response.ResponseDrSettingSaved;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

/**
 * Created by Faizul Haque Nayan on 19/07/13.
 */
public interface ServiceDrSettingSaved {

    @Headers("Connection: close")
    @POST("dr_settings/dr_setting_saved")
    Call<ResponseDrSettingSaved> setDrInfo(@Body RequestDrSettingSaved savingAccOpen);

}
