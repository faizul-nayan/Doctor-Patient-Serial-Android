package com.ju.drmostafizur.presentation.doctor.ui.components;

/**
 * Created by jahid on 2/16/17.
 */

public class GlobalHolder {
    private PickerManager pickerManager;

    private static GlobalHolder ourInstance = new GlobalHolder();

    public static GlobalHolder getInstance() {
        return ourInstance;
    }

    private GlobalHolder() {
    }


    public PickerManager getPickerManager() {
        return pickerManager;
    }

    public void setPickerManager(PickerManager pickerManager) {
        this.pickerManager = pickerManager;
    }
}
