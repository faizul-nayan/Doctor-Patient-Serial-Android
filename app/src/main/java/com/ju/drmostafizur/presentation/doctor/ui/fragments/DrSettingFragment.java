package com.ju.drmostafizur.presentation.doctor.ui.fragments;

import android.app.Activity;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.ju.drmostafizur.R;
import com.ju.drmostafizur.domain.executor.impl.ThreadExecutor;
import com.ju.drmostafizur.domain.model.DrInfoModel;
import com.ju.drmostafizur.domain.model.DrSchedule;
import com.ju.drmostafizur.presentation.doctor.adapters.DrScheduleAdapter;
import com.ju.drmostafizur.presentation.doctor.presenters.DrSettingPresenter;
import com.ju.drmostafizur.presentation.doctor.presenters.impl.DrSettingPresenterImpl;
import com.ju.drmostafizur.presentation.doctor.ui.components.PickerBuilder;
import com.ju.drmostafizur.presentation.doctor.ui.dialog.DrScheduleDialog;
import com.ju.drmostafizur.presentation.model.DayModel;
import com.ju.drmostafizur.threading.MainThreadImpl;
import com.ju.drmostafizur.utills.CameraPermission;
import com.ju.drmostafizur.utills.InputUtils;
import com.ju.drmostafizur.utills.Utils;
import com.ju.drmostafizur.utills.Values;
import com.makeramen.roundedimageview.RoundedImageView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by Faizul Haque Nayan on 19/07/09.
 */
public class DrSettingFragment extends Fragment implements DrScheduleDialog.DialogCallback, DrSettingPresenter.View{

    private View view;
    private Button infoSavedBtn;
    private EditText fullNameEt;
    private EditText designationbEt;
    private EditText phoneEt;
    private EditText emailEt;
    private EditText addressEt;
    private EditText qualificationEt;
    private RoundedImageView mImageView;
    private DrScheduleDialog.DialogCallback mDialogCallback;
    private GridLayoutManager gridLayoutManager;
    private RecyclerView recyclerView;
    private DrScheduleAdapter adapter;
    private Button mButtonAddPhoto;
    private Activity mActivity;
    public Uri profileUri = null;
    public String fMemberFilePath = "";
    private boolean imageSelect;
    private DrSettingPresenter mPresenter;
    private boolean flag;

    private ProgressDialog mProgressDialog;
    private DrScheduleDialog dialog;
    private String mDay;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.dr_setting_fragment, null);
        mDialogCallback = this;
        init();

        return view;
    }

    private void init() {
        mActivity = getActivity();
        dialog = new DrScheduleDialog();
        mPresenter = new DrSettingPresenterImpl(ThreadExecutor.getInstance(), MainThreadImpl.getInstance(), this);
        infoSavedBtn = (Button) view.findViewById(R.id.infoSavedBtn);
        fullNameEt = (EditText) view.findViewById(R.id.fullNameEt);
        designationbEt = (EditText) view.findViewById(R.id.designationEt);
        emailEt = (EditText) view.findViewById(R.id.emailEt);
        addressEt = (EditText) view.findViewById(R.id.addressEt);
        phoneEt = (EditText) view.findViewById(R.id.phoneEt);
        qualificationEt = (EditText) view.findViewById(R.id.qualificationEt);
        mImageView = (RoundedImageView) view.findViewById(R.id.image_view);
        recyclerView = (RecyclerView) view.findViewById(R.id.dayRecyclerView);
        mButtonAddPhoto = (Button) view.findViewById(R.id.button_add);
        infoSavedBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkInfoValidation()){
                    showProgress();
                    mPresenter.savedDoctorInfo(fullNameEt.getText().toString(),
                            designationbEt.getText().toString(), phoneEt.getText().toString(),
                            emailEt.getText().toString(), addressEt.getText().toString());
                }
            }
        });

        mButtonAddPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setPhoto();
            }
        });

        adapter = new DrScheduleAdapter(getContext(), getDayList(), this);
        gridLayoutManager = new GridLayoutManager(getContext(), calculateNoOfColumns(getContext()));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setAdapter(adapter);
    }

    public static int calculateNoOfColumns(Context context) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        float dpWidth = displayMetrics.widthPixels / displayMetrics.density;
        int noOfColumns = (int) (dpWidth / 120);
        return noOfColumns;
    }

    private List<DayModel> getDayList(){
        List<DayModel> list = new ArrayList<>();

        DayModel model = new DayModel("Sunday", R.drawable.ic_arrow_right);
        list.add(model);
        model = new DayModel("Monday", R.drawable.ic_arrow_right);
        list.add(model);
        model = new DayModel("Tuesday", R.drawable.ic_arrow_right);
        list.add(model);
        model = new DayModel("Wednesday", R.drawable.ic_arrow_right);
        list.add(model);
        model = new DayModel("Thursday", R.drawable.ic_arrow_right);
        list.add(model);
        model = new DayModel("Friday", R.drawable.ic_arrow_right);
        list.add(model);
        model = new DayModel("Saturday", R.drawable.ic_arrow_right);
        list.add(model);


        return list;
    }

    void setPhoto(){
        final CharSequence[] items;
        if (CameraPermission.checkPermission(getContext()) && Utils.isDeviceSupportCamera(getContext())) {
            items = new CharSequence[2];
            items[0] = "Camera";
            items[1] = "Gallery";
        } else {
            items = new CharSequence[1];
            items[0] = "Gallery";
        }

        android.app.AlertDialog.Builder alertdialog = new android.app.AlertDialog.Builder(mActivity);
        alertdialog.setTitle("Add Image");
        alertdialog.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                if (items[item].equals("Camera")) {
                    new PickerBuilder(mActivity, PickerBuilder.SELECT_FROM_CAMERA)
                            .setOnImageReceivedListener(new PickerBuilder.onImageReceivedListener() {
                                @Override
                                public void onImageReceived(Uri imageUri) {
                                    profileUri = imageUri;
                                    fMemberFilePath = imageUri.toString();
                                    mImageView.setImageBitmap(InputUtils.compressImage(getContext(), imageUri.toString(), 816, 612));
                                    imageSelect = true;
                                }
                            })
                            .setImageName("member")
                            .setImageFolderName(Values.IMAGE_FOLDER_NAME)
                            .withTimeStamp(true)
                            .setCropScreenColor(mActivity.getResources().getColor(R.color.colorAccent))
                            .start();
                } else if (items[item].equals("Gallery")) {
                    new PickerBuilder(mActivity, PickerBuilder.SELECT_FROM_GALLERY)
                            .setOnImageReceivedListener(new PickerBuilder.onImageReceivedListener() {
                                @Override
                                public void onImageReceived(Uri imageUri) {
                                    profileUri = imageUri;
                                    fMemberFilePath = imageUri.toString();
                                    mImageView.setImageBitmap(InputUtils.compressImage(mActivity, imageUri.toString(), 816, 612));
                                    imageSelect = true;
                                }
                            })
                            .setImageName("member")
                            .setImageFolderName(Values.IMAGE_FOLDER_NAME)
                            .withTimeStamp(true)
                            .setCropScreenColor(mActivity.getResources().getColor(R.color.colorAccent))
                            .start();
                }
            }
        });
        Log.e("AbstractMemberActivity", fMemberFilePath.toString());
        alertdialog.show();
    }



    @Override
    public void showProgress() {
        mProgressDialog = new ProgressDialog(getActivity());
        mProgressDialog.setMessage("Please Wait...");
        mProgressDialog.show();
    }


    @Override
    public void hideProgress() {
        mProgressDialog.dismiss();
    }

    @Override
    public void showError(String message) {
        hideProgress();
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onResume() {
        super.onResume();
        showProgress();
        mPresenter.getDrInfo(12);
    }

    private boolean checkInfoValidation(){

        flag = true;

        if(fullNameEt.getText().toString().equalsIgnoreCase("")){
            fullNameEt.setError("Full name must have");
            flag = false;
        }
        else if(qualificationEt.getText().toString().equalsIgnoreCase("")){
            flag = false;
            qualificationEt.setError("Qualification should not be empty");
        }
        else if(phoneEt.getText().toString().equalsIgnoreCase("")){
            flag = false;
            phoneEt.setError("Phone number should not be empty");
        }
        else if(emailEt.getText().toString().equalsIgnoreCase("")){
            flag = false;
            emailEt.setError("Email address should not be empty");
        }
        else {
            flag = true;
        }


        return flag;
    }

    @Override
    public void setDrInfo(DrInfoModel drInfo) {
        fullNameEt.setText(drInfo.getFullName());
        designationbEt.setText(drInfo.getDesignation());
        emailEt.setText(drInfo.getEmail());
        phoneEt.setText(drInfo.getPhoneNumber());
        addressEt.setText(drInfo.getAddress());
        qualificationEt.setText(drInfo.getQualifications());
        hideProgress();
    }

    @Override
    public void onScheduleDayClick(String day) {
        this.mDay = day;
        dialog.setCallback(mDialogCallback, dialog,mDay);
        dialog.show(getActivity().getFragmentManager(), "show");
    }

    @Override
    public void onScheduleSaved() {
        dialog.dismiss();
        showError("New Schedule Saved");
    }


    @Override
    public void getTime(TextView editText) {
        Calendar mcurrentTime = Calendar.getInstance();
        int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
        int minute = mcurrentTime.get(Calendar.MINUTE);
        TimePickerDialog mTimePicker;
        mTimePicker = new TimePickerDialog(getActivity(), new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                dialog.setTime(editText, selectedHour, selectedMinute);
            }
        }, hour, minute, true);
        mTimePicker.setTitle("Select Time");
        mTimePicker.show();
    }

    @Override
    public void setNewSchedule(DrSchedule drSchedule) {
        mPresenter.onNewSchedule(drSchedule);
    }
}
