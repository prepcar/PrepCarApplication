package com.dev.prepcarapplication.adapter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.dev.prepcarapplication.R;
import com.dev.prepcarapplication.baseClasses.BaseFragmentActivity;
import com.dev.prepcarapplication.bean.NewMatchesBean;
import com.dev.prepcarapplication.utilities.RoundedImageView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class DealerFavouriteAdapter extends ArrayAdapter<String> {
    ArrayList<NewMatchesBean> arrayList;
    BaseFragmentActivity frg;
    Context context;

    public DealerFavouriteAdapter(Context  context, ArrayList<NewMatchesBean> arrayList) {
        super(context, 0);
        this.context=context;
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
            contentView = View.inflate(getContext(), R.layout.item_dealer_favourite, null);
            holder = new ViewHolder(contentView);
            contentView.setTag(holder);
        } else {
            holder = (ViewHolder) contentView.getTag();
        }
        if (!arrayList.get(position).getSingle_car_pic().equals("")) {
            Picasso.with(context)
                    .load(arrayList.get(position).getSingle_car_pic())
                    .into(holder.carImage);
        }
        holder.tv_car_make.setText(arrayList.get(position).getMake());
        holder.tv_car_location.setText(arrayList.get(position).getCity()+", "+arrayList.get(position).getState());
        holder.tv_car_model.setText(arrayList.get(position).getModel_type());
        holder.tv_car_date.setText(arrayList.get(position).getDate());
        holder.tv_car_price.setText("$ "+arrayList.get(position).getBestPrice());


        return contentView;
    }

    private static class ViewHolder {
        private RoundedImageView carImage;
        TextView tv_car_make, tv_car_location, tv_car_model, tv_car_date, tv_car_price;

        private ViewHolder(View view) {
            this.carImage = (RoundedImageView) view.findViewById(R.id.image_car);
            this.tv_car_make = (TextView) view.findViewById(R.id.tv_car_make);
            this.tv_car_location = (TextView) view.findViewById(R.id.tv_car_location);
            this.tv_car_model = (TextView) view.findViewById(R.id.tv_car_model);
            this.tv_car_date = (TextView) view.findViewById(R.id.tv_car_date);
            this.tv_car_price = (TextView) view.findViewById(R.id.tv_car_price);

        }
    }

}

