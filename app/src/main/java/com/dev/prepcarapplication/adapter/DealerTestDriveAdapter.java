package com.dev.prepcarapplication.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dev.prepcarapplication.R;
import com.dev.prepcarapplication.baseClasses.BaseFragmentActivity;
import com.dev.prepcarapplication.bean.TestDriveModel;
import com.dev.prepcarapplication.utilities.RoundedImageView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class DealerTestDriveAdapter extends ArrayAdapter<String> {
    private ArrayList<TestDriveModel> arrayList;
    private BaseFragmentActivity frg;
    Context context;

    public DealerTestDriveAdapter(Context context, ArrayList<TestDriveModel> arrayList) {
        super(context, 0);
        this.context = context;
        this.arrayList = arrayList;
        this.frg = (BaseFragmentActivity) context;
    }

    @Override
    public int getCount() {

        return arrayList.size();
    }

    @Nullable
    @Override
    public String getItem(int position) {
        return String.valueOf(position);
    }

    @Override
    public long getItemId(int position) {

        return position;
    }

    @Override
    public View getView(final int position, View contentView, ViewGroup parent) {
        final ViewHolder holder;

        if (contentView == null) {
            contentView = View.inflate(getContext(), R.layout.item_dealer_test_drive, null);
            holder = new ViewHolder(contentView);
            contentView.setTag(holder);
        } else {
            holder = (ViewHolder) contentView.getTag();
        }
        if (position % 3 == 2) {
            holder.parentLayout.setBackgroundColor(Color.parseColor("#FFCCCB"));
        } else if (position % 3 == 1) {
            holder.parentLayout.setBackgroundColor(Color.parseColor("#F2F2F2"));
        } else {
            holder.parentLayout.setBackgroundColor(Color.parseColor("#D5D8E9"));
        }
        holder.tv_car_date.setText(arrayList.get(position).getDate());
        holder.tv_car_time.setText(arrayList.get(position).getTime());
        holder.tv_car_detail.setText(arrayList.get(position).getBuyer_name()
                + " \n\n"
                + arrayList.get(position).getModel_year() + "-" + arrayList.get(position).getMake() + "-" + arrayList.get(position).getModel_type());
        if (!arrayList.get(position).getSingle_car_pic().equals("")) {
            Picasso.with(context).load(arrayList.get(position).getSingle_car_pic()).
                    placeholder(R.mipmap.app_icon).into(holder.carImage);
        }
        if (arrayList.get(position).getConfirm_status() == 0) {
            holder.carplanstatus.setImageResource(R.drawable.new_image);
        }else if (arrayList.get(position).getConfirm_status() == 2){
            holder.carplanstatus.setImageResource(R.drawable.rescheduled);
        }else {
            if (arrayList.get(position).getAppointment_status() == 1)
                holder.carplanstatus.setImageResource(R.drawable.sold);
            else
                holder.carplanstatus.setImageResource(R.drawable.accepted);
        }


        return contentView;
    }

    private static class ViewHolder {
        private RoundedImageView carImage;
        TextView tv_car_detail, tv_car_date, tv_car_time;
        LinearLayout parentLayout;
        ImageView carplanstatus;

        private ViewHolder(View view) {
            this.carImage = (RoundedImageView) view.findViewById(R.id.image_car);
            this.tv_car_detail = (TextView) view.findViewById(R.id.tv_car_detail);
            this.tv_car_date = (TextView) view.findViewById(R.id.tv_car_date);
            this.tv_car_time = (TextView) view.findViewById(R.id.tv_car_time);
            this.parentLayout = (LinearLayout) view.findViewById(R.id.parent_layout);
            this.carplanstatus = (ImageView) view.findViewById(R.id.carplanstatus);
        }
    }

}

