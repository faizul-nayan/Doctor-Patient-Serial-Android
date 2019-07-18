package com.ju.drmostafizur.presentation.patient.ui.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.ju.drmostafizur.R;
import com.ju.drmostafizur.domain.executor.impl.ThreadExecutor;
import com.ju.drmostafizur.domain.model.DrDashboardPatientModel;
import com.ju.drmostafizur.domain.model.DrInfoModel;
import com.ju.drmostafizur.domain.model.DrSchedule;
import com.ju.drmostafizur.presentation.login.LoginActivity;
import com.ju.drmostafizur.presentation.model.ImageModel;
import com.ju.drmostafizur.presentation.patient.adapters.SliderAdapterExample;
import com.ju.drmostafizur.presentation.patient.presenters.PatientDashboardPresenter;
import com.ju.drmostafizur.presentation.patient.presenters.impl.PatientDashboardPresenterImpl;
import com.ju.drmostafizur.presentation.patient.ui.activities.SerialBookedActivity;
import com.ju.drmostafizur.threading.MainThreadImpl;
import com.viewpagerindicator.CirclePageIndicator;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Faizul Haque Nayan on 19/07/15.
 */
public class PatientDashboardFragment extends Fragment implements PatientDashboardPresenter.View{

    private static PatientDetailsFragment mInstance;
    private View view;

    private TextView nameTv;
    private TextView designationTv;
    private TextView qualificationTv;
    private TextView addressTv;
    private TextView emailTv;
    private TextView phoneTv;
    private TextView dayTv;
    private TextView dateTv;
    private TextView reaminignTv;
    private TextView loginTv;
    private Button bookedBtn;

    private PatientDashboardPresenter mPresenter;

    private int[] myImageList = new int[]{R.drawable.harley2, R.drawable.benz2,
            R.drawable.vecto,R.drawable.webshots
            ,R.drawable.bikess,R.drawable.img1};

    private String[] imageList = new String[]{
      "https://images.pexels.com/photos/263210/pexels-photo-263210.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=750&w=1260",
            "https://images.pexels.com/photos/905874/pexels-photo-905874.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=750&w=1260",
            "https://images.pexels.com/photos/1250655/pexels-photo-1250655.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=750&w=1260",
            "https://images.pexels.com/photos/139398/thermometer-headache-pain-pills-139398.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=750&w=1260"
    };

    private static ViewPager mPager;
    private static int currentPage = 0;
    private static int NUM_PAGES = 0;
    private ArrayList<ImageModel> imageModelArrayList;

    private String dayOfTheWeek;
    private String formattedDate;

    public static PatientDetailsFragment getInstance() {
        mInstance = new PatientDetailsFragment();
        return mInstance;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_patient_dashboard, null);
        imageModelArrayList = new ArrayList<>();
        imageModelArrayList = populateList();

        startSlider();
        init();
        mPresenter = new PatientDashboardPresenterImpl(ThreadExecutor.getInstance(), MainThreadImpl.getInstance(), getContext(), this);

        return view;
    }

    private void init(){
        nameTv = (TextView) view.findViewById(R.id.drNameTv);
        designationTv = (TextView) view.findViewById(R.id.drDesignationTv);
        qualificationTv = (TextView) view.findViewById(R.id.drQualificationTv);
        addressTv = (TextView) view.findViewById(R.id.drAddressTv);
        emailTv = (TextView) view.findViewById(R.id.drEmailTv);
        phoneTv = (TextView) view.findViewById(R.id.drPhoneTv);
        dayTv = (TextView) view.findViewById(R.id.dayTv);
        dateTv = (TextView) view.findViewById(R.id.dateTv);
        reaminignTv = (TextView) view.findViewById(R.id.remainingTv);
        loginTv = (TextView) view.findViewById(R.id.loginTv);
        bookedBtn = (Button) view.findViewById(R.id.bookedBtn);

        SimpleDateFormat sdf = new SimpleDateFormat("EEEE");
        Date d = new Date();
        dayOfTheWeek = sdf.format(d);
        dayTv.setText(dayOfTheWeek);

        Date c = Calendar.getInstance().getTime();
        System.out.println("Current time => " + c);

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        formattedDate = df.format(c);
        dateTv.setText(formattedDate);

        bookedBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), SerialBookedActivity.class));
            }
        });

        loginTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), LoginActivity.class));
                getActivity().finish();
            }
        });
    }

    private ArrayList<ImageModel> populateList(){

        ArrayList<ImageModel> list = new ArrayList<>();

        for(int i = 0; i < imageList.length; i++){
            ImageModel imageModel = new ImageModel();
            imageModel.setImage_drawable(imageList[i]);
            list.add(imageModel);
        }

        return list;
    }

    private void startSlider() {

        mPager = (ViewPager) view.findViewById(R.id.pager);
        mPager.setAdapter(new SliderAdapterExample(getContext(),imageModelArrayList));

        CirclePageIndicator indicator = (CirclePageIndicator)
                view.findViewById(R.id.indicator);

        indicator.setViewPager(mPager);

        final float density = getResources().getDisplayMetrics().density;

//Set circle indicator radius
        indicator.setRadius(5 * density);

        NUM_PAGES =imageModelArrayList.size();

        // Auto start of viewpager
        final Handler handler = new Handler();
        final Runnable Update = new Runnable() {
            public void run() {
                if (currentPage == NUM_PAGES) {
                    currentPage = 0;
                }
                mPager.setCurrentItem(currentPage++, true);
            }
        };
        Timer swipeTimer = new Timer();
        swipeTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(Update);
            }
        }, 2500, 2500);

        indicator.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageSelected(int position) {
                currentPage = position;

            }

            @Override
            public void onPageScrolled(int pos, float arg1, int arg2) {

            }

            @Override
            public void onPageScrollStateChanged(int pos) {

            }
        });

    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.getDrInfo(12, dayOfTheWeek,formattedDate);
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void showError(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setDrInfo(DrInfoModel drInfo, List<DrSchedule> scheduleList, List<DrDashboardPatientModel> patientModelList) {
        nameTv.setText(drInfo.getFullName());
        designationTv.setText(drInfo.getDesignation());
        qualificationTv.setText(drInfo.getQualifications());
        addressTv.setText(drInfo.getAddress());
        phoneTv.setText(drInfo.getPhoneNumber());
        emailTv.setText(drInfo.getEmail());
        if (scheduleList.size() > 0){
            reaminignTv.setText("Today's Serial Remaining: "+(scheduleList.get(0).getMaxPatient() - patientModelList.size()));
        }
        else {
            reaminignTv.setText("No Schedule Today");
        }

    }
}
