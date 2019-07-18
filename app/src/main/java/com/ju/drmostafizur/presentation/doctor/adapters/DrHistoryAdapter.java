package com.ju.drmostafizur.presentation.doctor.adapters;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ju.drmostafizur.R;
import com.ju.drmostafizur.domain.model.DrDashboardPatientModel;
import com.ju.drmostafizur.presentation.BaseViewHolder;
import com.ju.drmostafizur.presentation.doctor.presenters.DrHistoryPresenter;
import com.ju.drmostafizur.utills.Shape;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Faizul Haque Nayan on 19/07/18.
 */
public class DrHistoryAdapter extends RecyclerView.Adapter<BaseViewHolder> {

    private Context mContext;
    private LayoutInflater mInflater;
    private DrHistoryPresenter.View  mView;
    private List<DrDashboardPatientModel> mPatientList;

    private static final int VIEW_TYPE_EMPTY = 0;
    private static final int VIEW_TYPE_NORMAL = 1;

    public DrHistoryAdapter(Context context, DrHistoryPresenter.View view) {
        this.mContext = context;
        this.mView = view;
        this.mInflater = LayoutInflater.from(context);
        mPatientList = new ArrayList<>();
    }

    public void addNewPatient(List<DrDashboardPatientModel> patientList){
        if(mPatientList != null && mPatientList.size() > 0){
            mPatientList.clear();
        }
        this.mPatientList = patientList;
        notifyDataSetChanged();
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        switch (viewType) {
            case VIEW_TYPE_NORMAL:
                return new CardViewHolder(LayoutInflater.from(parent.getContext()).inflate(
                        R.layout.raw_patien_list, parent, false));
            case VIEW_TYPE_EMPTY:
                return new EmptyViewHolder(LayoutInflater.from(parent.getContext()).inflate(
                        R.layout.no_data_found, parent, false));
            default:
                return new CardViewHolder(LayoutInflater.from(parent.getContext()).inflate(
                        R.layout.raw_patien_list, parent, false));

        }
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {

        holder.onBind(position);
    }

    @Override
    public int getItemViewType(int position) {
        if (mPatientList != null && mPatientList.size() > 0) {
            return VIEW_TYPE_NORMAL;
        } else {
            return VIEW_TYPE_EMPTY;
        }
    }

    @Override
    public int getItemCount() {
        if (mPatientList != null && mPatientList.size() > 0) {
            return mPatientList.size();
        } else {
            return 1;
        }
    }

    public class CardViewHolder extends BaseViewHolder {

        @BindView(R.id.slNoEt)
        TextView serialNoEt;
        @BindView(R.id.nameEt)
        TextView nameEt;
        @BindView(R.id.phoneEt)
        TextView phoneEt;
        @BindView(R.id.ageEt)
        TextView ageEt;
        @BindView(R.id.statusIv)
        ImageView statusIv;

        public CardViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        @Override
        protected void clear() {

        }

        public void onBind(int number) {

            final DrDashboardPatientModel savingDetails = mPatientList.get(number);
            serialNoEt.setText(""+savingDetails.getSerial());
            nameEt.setText(savingDetails.getFullName());
            phoneEt.setText(savingDetails.getPhoneNumber());
            ageEt.setText("Age: "+savingDetails.getAge());

            serialNoEt.setBackgroundDrawable(Shape.drawCircle (mContext, 100, 100, Color.parseColor("#FBA436"), Color.parseColor("#FBA436")));

            if(savingDetails.getPayment().equalsIgnoreCase("Cash")){
                statusIv.setImageResource(R.drawable.active_icon);
            }
            else {
                statusIv.setImageResource(R.drawable.inactive_icon);
            }
        }
    }

    public class EmptyViewHolder extends BaseViewHolder{

        @BindView(R.id.noDataTextView)
        TextView mTextView;

        public EmptyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            mTextView.setText("Sorry, We couldn't find anything matches.\n" +
                    "Please try again or make new transactions .");
        }

        @Override
        protected void clear() {

        }
    }
}