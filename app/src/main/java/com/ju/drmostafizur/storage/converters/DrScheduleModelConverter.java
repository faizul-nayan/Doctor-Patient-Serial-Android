package com.ju.drmostafizur.storage.converters;

import com.ju.drmostafizur.domain.model.DrSchedule;
import com.ju.drmostafizur.network.model.RESTDrSchedule;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Faizul Haque Nayan on 19/07/14.
 */
public class DrScheduleModelConverter {

    public static DrSchedule convertRestToDoaminModel(RESTDrSchedule restDrSchedule) {

        DrSchedule schedule = new DrSchedule();
        schedule.setId(restDrSchedule.getId());
        schedule.setHospitalName(restDrSchedule.getHospitalName());
        schedule.setEmployeeType(restDrSchedule.getEmployeeType());
        schedule.setMaxPatient(restDrSchedule.getMaxPatient());
        schedule.setTimeTo(restDrSchedule.getTimeTo());
        schedule.setTimeFrom(restDrSchedule.getTimeFrom());
        schedule.setIsDisable(restDrSchedule.getIsDisable());
        schedule.setDay(restDrSchedule.getDay());
        schedule.setAddress(restDrSchedule.getAddress());
        return schedule;
    }

    public static RESTDrSchedule convertDomainToRestModel(DrSchedule drInfoModel) {

        RESTDrSchedule restDrInfoModel = new RESTDrSchedule();
        restDrInfoModel.setId(drInfoModel.getId());
        restDrInfoModel.setHospitalName(drInfoModel.getHospitalName());
        restDrInfoModel.setEmployeeType(drInfoModel.getEmployeeType());
        restDrInfoModel.setMaxPatient(drInfoModel.getMaxPatient());
        restDrInfoModel.setTimeTo(drInfoModel.getTimeTo());
        restDrInfoModel.setTimeFrom(drInfoModel.getTimeFrom());
        restDrInfoModel.setIsDisable(drInfoModel.getIsDisable());
        restDrInfoModel.setDay(drInfoModel.getDay());
        restDrInfoModel.setAddress(drInfoModel.getAddress());
        return restDrInfoModel;
    }




    public static List<DrSchedule> convertRestListToDoaminModel(List<RESTDrSchedule> restLoans) {

        List<DrSchedule> loans = new ArrayList<>();

        for (RESTDrSchedule rest : restLoans) {
            loans.add(convertRestToDoaminModel(rest));
        }

        // cleanup
        restLoans.clear();

        return loans;
    }

    public static List<RESTDrSchedule> convertDomainListToRestModel(List<DrSchedule> loans) {

        List<RESTDrSchedule> restLoans = new ArrayList<>();

        for (DrSchedule loan : loans) {
            restLoans.add(convertDomainToRestModel(loan));
        }

        // cleanup
        loans.clear();

        return restLoans;
    }
}
