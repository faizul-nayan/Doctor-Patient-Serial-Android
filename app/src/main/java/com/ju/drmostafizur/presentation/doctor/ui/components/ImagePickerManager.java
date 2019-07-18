package com.ju.drmostafizur.presentation.doctor.ui.components;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;

/**
 * Created by jahid on 2/16/17.
 */

public class ImagePickerManager extends PickerManager {

    public ImagePickerManager(Activity activity) {
        super(activity);
    }

    protected void sendToExternalApp( ){
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        intent.setType("image/*");

        activity.startActivityForResult(Intent.createChooser(intent, "Select avatar..."),
                REQUEST_CODE_SELECT_IMAGE);
    }

    @Override
    public void setUri(Uri uri)
    {
        mProcessingPhotoUri = uri;
    }
}
