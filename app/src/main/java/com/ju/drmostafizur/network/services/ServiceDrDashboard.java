package com.ju.drmostafizur.network.services;

import com.ju.drmostafizur.network.response.ResponseDrDashboard;
import com.ju.drmostafizur.network.response.ResponseGetDrInfoById;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

/**
 * Created by Faizul Haque Nayan on 19/07/14.
 */
public interface ServiceDrDashboard {

    @Headers("Connection: close")
    @GET("dr_dashboards/index")
    Call<ResponseDrDashboard> getDrDashboardInfo(@Query("id") int drId, @Query("day") String day, @Query("date") String date);

}
