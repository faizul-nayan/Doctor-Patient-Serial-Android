package com.ju.drmostafizur.presentation.doctor.ui.components;

import android.app.Activity;
import android.content.Intent;
import android.provider.MediaStore;

/**
 * Created by jahid on 2/16/17.
 */

public class CameraPickerManager  extends PickerManager {

    public CameraPickerManager(Activity activity) {
        super(activity);
    }

    protected void sendToExternalApp()
    {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        mProcessingPhotoUri =  getImageFile();
        intent.putExtra(MediaStore.EXTRA_OUTPUT, mProcessingPhotoUri);
        activity.startActivityForResult(intent, REQUEST_CODE_SELECT_IMAGE);

        /*Intent install = new Intent(Intent.ACTION_VIEW);
        install.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        Uri apkURI = FileProvider.getUriForFile(
                context,
                context.getApplicationContext()
                        .getPackageName() + ".provider", file);
        install.setDataAndType(apkURI, mimeType);
        install.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);*/
    }
}