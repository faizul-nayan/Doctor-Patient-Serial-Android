package com.ju.drmostafizur.utills;

import android.content.Context;
import android.content.pm.PackageManager;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.ju.drmostafizur.R;

import java.util.ArrayList;

/**
 * Created by Faizul Haque Nayan on 8/28/17.
 */

public class Utils {

    public static void prepareSpinner(Context context, Spinner spinner,
                                      ArrayList<String> data) {

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(context,
                R.layout.item_spinner, data);
        spinner.setAdapter(adapter);

    }

    public static void prepareSpinner(Context context, Spinner spinner,
                                      ArrayList<String> data, String selectedItem) {

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(context,
                R.layout.item_spinner, data);
        spinner.setAdapter(adapter);

        int selectedPosition = adapter.getPosition(selectedItem);

        spinner.setSelection(selectedPosition);

    }

    public static boolean isDeviceSupportCamera(Context context) {
        if (context.getPackageManager().hasSystemFeature(
                PackageManager.FEATURE_CAMERA)) {
            // this device has a camera
            return true;
        } else {
            // no camera on this device
            return false;
        }
    }

    public static String formatDoubleToShort(double value) {
        double number = Math.abs(value);
        String[] suffix = new String[]{"k", "m", "b", "t"};
        int size = (number != 0) ? (int) Math.log10(number) : 0;
        if (size >= 3) {
            while (size % 3 != 0) {
                size = size - 1;
            }
        }
        double notation = Math.pow(10, size);
        String result = (size >= 3) ? +(Math.round((number / notation) * 100) / 100.0d) + suffix[(size / 3) - 1] : +number + "";
        return value >= 0 ? result : "-" + result;
    }
}
