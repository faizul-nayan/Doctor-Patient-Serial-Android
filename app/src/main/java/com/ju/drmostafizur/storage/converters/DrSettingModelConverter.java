package com.ju.drmostafizur.storage.converters;

import com.ju.drmostafizur.domain.model.DrInfoModel;
import com.ju.drmostafizur.network.model.RESTDrInfoModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Faizul Haque Nayan on 19/07/13.
 */
public class DrSettingModelConverter {

    public static DrInfoModel convertRestToDoaminModel(RESTDrInfoModel restDrInfoModel) {

        DrInfoModel drInfoModel = new DrInfoModel();
        drInfoModel.setId(restDrInfoModel.getId());
        drInfoModel.setFullName(restDrInfoModel.getFullName());
        drInfoModel.setDesignation(restDrInfoModel.getDesignation());
        drInfoModel.setPhoneNumber(restDrInfoModel.getPhoneNumber());
        drInfoModel.setEmail(restDrInfoModel.getEmail());
        drInfoModel.setAddress(restDrInfoModel.getAddress());
        drInfoModel.setQualifications(restDrInfoModel.getQualifications());
        return drInfoModel;
    }

    public static RESTDrInfoModel convertDomainToRestModel(DrInfoModel drInfoModel) {

        RESTDrInfoModel restDrInfoModel = new RESTDrInfoModel();
        restDrInfoModel.setId(drInfoModel.getId());
        restDrInfoModel.setFullName(drInfoModel.getFullName());
        restDrInfoModel.setDesignation(drInfoModel.getDesignation());
        restDrInfoModel.setPhoneNumber(drInfoModel.getPhoneNumber());
        restDrInfoModel.setEmail(drInfoModel.getEmail());
        restDrInfoModel.setAddress(drInfoModel.getAddress());
        restDrInfoModel.setQualifications(drInfoModel.getQualifications());
        return restDrInfoModel;
    }




    public static List<DrInfoModel> convertRestListToDoaminModel(List<RESTDrInfoModel> restLoans) {

        List<DrInfoModel> loans = new ArrayList<>();

        for (RESTDrInfoModel rest : restLoans) {
            loans.add(convertRestToDoaminModel(rest));
        }

        // cleanup
        restLoans.clear();

        return loans;
    }

    public static List<RESTDrInfoModel> convertDomainListToRestModel(List<DrInfoModel> loans) {

        List<RESTDrInfoModel> restLoans = new ArrayList<>();

        for (DrInfoModel loan : loans) {
            restLoans.add(convertDomainToRestModel(loan));
        }

        // cleanup
        loans.clear();

        return restLoans;
    }

}
