package com.ju.drmostafizur.network.services;

import com.ju.drmostafizur.domain.model.DrSchedule;
import com.ju.drmostafizur.network.requests.RequestDrNewSchedule;
import com.ju.drmostafizur.network.response.ResponseDrNewSchedule;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.Headers;
import retrofit2.http.POST;

/**
 * Created by Faizul Haque Nayan on 19/07/14.
 */
public interface ServiceDrNewSchedule {

    @Headers("Connection: close")
    @POST("dr_schedules/dr_schedule_saved")
    Call<ResponseDrNewSchedule> setDrScheduleInfo(@Body RequestDrNewSchedule requestDrNewSchedule);
}
