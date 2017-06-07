package com.dev.prepcarapplication.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dev.prepcarapplication.R;
import com.dev.prepcarapplication.baseClasses.BaseFragmentActivity;
import com.dev.prepcarapplication.bean.BuyeListBean;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

//This is simple adapter to show data in listView with multiple values.

public class BuyerListAdapter extends BaseAdapter implements Filterable {
    Context _context;
    ArrayList<BuyeListBean> list;
    private LayoutInflater inflator;
    boolean subshow;
    String type;
    BaseFragmentActivity fragment;
    Holder holder = null;


    public BuyerListAdapter(Context context, ArrayList<BuyeListBean> list, String type) {
        _context = context;
        this.list = list;
        inflator = LayoutInflater.from(context);
        this.type = type;
        this.fragment = (BaseFragmentActivity) context;
    }

    public BuyerListAdapter(Context context, ArrayList<BuyeListBean> list, boolean b) {
        // TODO Auto-generated constructor stub
        subshow = b;
        _context = context;
        this.list = list;
        inflator = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return list.size();
    }

    @Override
    public Object getItem(int arg0) {

        return null;
    }

    @Override
    public long getItemId(int arg0) {

        return arg0;
    }

    @Override
    public View getView(int arg0, View convertView, ViewGroup arg2) {

        if (convertView == null) {
            convertView = (View) inflator.inflate(R.layout.buyer_item, null);
            holder = new Holder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (Holder) convertView.getTag();
        }
        holder.buyer_name.setText(list.get(arg0).getFirst_name() + " " + list.get(arg0).getLast_name());
        holder.buyer_loc.setText(list.get(arg0).getCity() + " " + list.get(arg0).getState());
        if (!list.get(arg0).getProfile_picture().equals("")) {
            Picasso.with(_context).load(list.get(arg0).getProfile_picture()).into(holder.buyer_image);
        }
        if(list.get(arg0).getCarplan_status().equals("0")){
            Picasso.with(_context).load(R.drawable.new_image).into(holder.carplanstatus);
        }
     else  if(list.get(arg0).getCarplan_status().equals("1")){
           Picasso.with(_context).load(R.drawable.accepted).into(holder.carplanstatus);
       }
        return convertView;
    }

    static class Holder {
        public RelativeLayout root;
        ImageView buyer_image = null;
        TextView buyer_name = null;
        TextView buyer_loc = null;
        TextView buyer_loc1 = null;
        ImageView carplanstatus;

        Holder(View view) {
            buyer_image = (ImageView) view.findViewById(R.id.buyer_image);
            buyer_name = (TextView) view.findViewById(R.id.buyer_name);
            buyer_loc = (TextView) view.findViewById(R.id.buyer_loc);
            buyer_loc1 = (TextView) view.findViewById(R.id.buyer_loc1);
            carplanstatus=(ImageView)view.findViewById(R.id.carplanstatus);
        }
    }

    @Override
    public Filter getFilter() {

        return null;
    }

}