package com.ju.drmostafizur.network.services;

import com.ju.drmostafizur.network.requests.RequestDrSettingSaved;
import com.ju.drmostafizur.network.response.ResponseDrSettingSaved;
import com.ju.drmostafizur.network.response.ResponseGetDrInfoById;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by Faizul Haque Nayan on 19/07/13.
 */
public interface ServiceGetDrInfobyId {

    @Headers("Connection: close")
    @GET("dr_settings/find_dr_info")
    Call<ResponseGetDrInfoById> getDrInfo(@Query("id") int drId);
}
