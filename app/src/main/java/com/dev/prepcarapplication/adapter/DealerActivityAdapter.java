package com.dev.prepcarapplication.adapter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.dev.prepcarapplication.R;
import com.dev.prepcarapplication.baseClasses.BaseFragmentActivity;
import com.dev.prepcarapplication.bean.DealerActivityModel;

import java.util.ArrayList;

public class DealerActivityAdapter extends ArrayAdapter<String> {
    ArrayList<DealerActivityModel> arrayList;
    BaseFragmentActivity frg;
    Context context;

    public DealerActivityAdapter(Context  context, ArrayList<DealerActivityModel> arrayList) {
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
            contentView = View.inflate(getContext(), R.layout.item_dealer_activity, null);
            holder = new ViewHolder(contentView);
            contentView.setTag(holder);
        } else {
            holder = (ViewHolder) contentView.getTag();
        }

        holder.activity_title.setText(arrayList.get(position).getActivityTitle());
        holder.activity_detail.setText(arrayList.get(position).getActivity_message());


        return contentView;
    }

    private static class ViewHolder {

        TextView activity_title, activity_detail;

        private ViewHolder(View view) {
            this.activity_title = (TextView) view.findViewById(R.id.activity_title);
            this.activity_detail = (TextView) view.findViewById(R.id.activity_detail);


        }
    }

}

