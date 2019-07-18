package com.ju.drmostafizur.storage.converters;

import com.ju.drmostafizur.domain.model.DrDashboardPatientModel;
import com.ju.drmostafizur.network.model.RESTDrDashboardPatientModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Faizul Haque Nayan on 19/07/17.
 */
public class DrDashboardPatientModelConverter {

    public static DrDashboardPatientModel convertRestToDoaminModel(RESTDrDashboardPatientModel restDrSchedule) {

        DrDashboardPatientModel schedule = new DrDashboardPatientModel();
        schedule.setId(restDrSchedule.getId());
        schedule.setFullName(restDrSchedule.getFullName());
        schedule.setAge(restDrSchedule.getAge());
        schedule.setPayment(restDrSchedule.getPayment());
        schedule.setPhoneNumber(restDrSchedule.getPhoneNumber());
        schedule.setSerial(restDrSchedule.getSerial());
        return schedule;
    }

    public static RESTDrDashboardPatientModel convertDomainToRestModel(DrDashboardPatientModel drDashboardPatientModel) {

        RESTDrDashboardPatientModel restDrDashboardPatientModel = new RESTDrDashboardPatientModel();
        restDrDashboardPatientModel.setId(drDashboardPatientModel.getId());
        restDrDashboardPatientModel.setFullName(drDashboardPatientModel.getFullName());
        restDrDashboardPatientModel.setAge(drDashboardPatientModel.getAge());
        restDrDashboardPatientModel.setPayment(drDashboardPatientModel.getPayment());
        restDrDashboardPatientModel.setPhoneNumber(drDashboardPatientModel.getPhoneNumber());
        restDrDashboardPatientModel.setSerial(drDashboardPatientModel.getSerial());
        return restDrDashboardPatientModel;
    }




    public static List<DrDashboardPatientModel> convertRestListToDoaminModel(List<RESTDrDashboardPatientModel> restLoans) {

        List<DrDashboardPatientModel> loans = new ArrayList<>();

        for (RESTDrDashboardPatientModel rest : restLoans) {
            loans.add(convertRestToDoaminModel(rest));
        }

        // cleanup
        restLoans.clear();

        return loans;
    }

    public static List<RESTDrDashboardPatientModel> convertDomainListToRestModel(List<DrDashboardPatientModel> loans) {

        List<RESTDrDashboardPatientModel> restLoans = new ArrayList<>();

        for (DrDashboardPatientModel loan : loans) {
            restLoans.add(convertDomainToRestModel(loan));
        }

        // cleanup
        loans.clear();

        return restLoans;
    }
}
