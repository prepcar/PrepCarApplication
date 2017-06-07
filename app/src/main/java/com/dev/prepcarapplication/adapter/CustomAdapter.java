package com.dev.prepcarapplication.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.dev.prepcarapplication.R;
import com.dev.prepcarapplication.bean.BuyerList;

import java.util.ArrayList;

public class CustomAdapter extends BaseAdapter {

	ArrayList<BuyerList> list;
	private LayoutInflater inflator;
	Context context;

	public CustomAdapter(Context context, ArrayList<BuyerList> list) {
		this.context=context;
		inflator = LayoutInflater.from(context);
		this.list = list;
	}

	@Override
	public int getCount() {
		return list.size();
	}

	@Override
	public Object getItem(int i) {
		return list.get(i).getModel_year();
	}

	@Override
	public long getItemId(int i) {
		return i;
	}

	@Override
	public View getView(int i, View view, ViewGroup viewGroup) {
		View convertView = (View) inflator.inflate(R.layout.custom_spinner_items, null);
		TextView name=(TextView)convertView.findViewById(R.id.teamname);
		ImageView imaghe=(ImageView)convertView.findViewById(R.id.teamimage);
		try {
			name.setText(list.get(i).getModel_year());
		} catch (Exception e) {
			e.printStackTrace();
		}

		return convertView;
	}
}
