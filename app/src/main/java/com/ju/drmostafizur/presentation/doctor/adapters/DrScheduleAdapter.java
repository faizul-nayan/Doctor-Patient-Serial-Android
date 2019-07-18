package com.ju.drmostafizur.presentation.doctor.adapters;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ju.drmostafizur.R;
import com.ju.drmostafizur.presentation.doctor.presenters.DrSettingPresenter;
import com.ju.drmostafizur.presentation.model.DayModel;
import com.ju.drmostafizur.utills.Shape;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mehedi on 1/23/17.
 * Modify by Faizul Haque Nayan
 */

public class DrScheduleAdapter extends RecyclerView.Adapter<DrScheduleAdapter.CardViewHolder> {

    private Context context;
    private List<DayModel> processDatas = new ArrayList<>();
    private LayoutInflater inflater;
    private DrSettingPresenter.View view;

    public DrScheduleAdapter(Context context, List<DayModel> processDatas, DrSettingPresenter.View view) {
        this.context = context;
        this.processDatas = processDatas;
        this.view = view;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public CardViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = inflater.inflate(R.layout.dr_schedule_card, parent, false);
        CardViewHolder cardViewHolder = new CardViewHolder(view);
        return cardViewHolder;
    }

    @Override
    public void onBindViewHolder(CardViewHolder holder, int position) {
        holder.bind(processDatas.get(position));
    }

    @Override
    public int getItemCount() {

        return processDatas.size();
    }

    public class CardViewHolder extends RecyclerView.ViewHolder {

        private ImageView iv_process_image;
        private TextView tv_process_name;
        private RelativeLayout layout;

        public CardViewHolder(View itemView) {
            super(itemView);

            iv_process_image = (ImageView) itemView.findViewById(R.id.process_image);
            tv_process_name = (TextView) itemView.findViewById(R.id.process_name);
            layout = (RelativeLayout) itemView.findViewById(R.id.layout);
        }

        public void bind(final DayModel cardData) {

            iv_process_image.setImageResource(cardData.getIcon());
            tv_process_name.setText(cardData.getDayName());

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    view.onScheduleDayClick(cardData.getDayName());
                   // processPresenter.click(cardData);
                }
            });
           // layout.setBackgroundDrawable(Shape.drawCircle (context, 20, 20, Color.parseColor("#FBA436"), Color.parseColor("#FBA436")));

        }
    }
}
