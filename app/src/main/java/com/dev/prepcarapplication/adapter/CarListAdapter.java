package com.dev.prepcarapplication.adapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import com.dev.prepcarapplication.DealerNewCarActivity;
import com.dev.prepcarapplication.R;
import com.dev.prepcarapplication.baseClasses.BaseFragmentActivity;
import com.dev.prepcarapplication.bean.BuyerList;
import com.dev.prepcarapplication.networkTask.ApiManager;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

//This is simple adapter to show data in listview with multiple values.

public class CarListAdapter extends BaseAdapter implements Filterable {
	Context _context;
	ArrayList<BuyerList> list;
	private LayoutInflater inflator;
	boolean subshow;
	String type;
	BaseFragmentActivity fragment;
	Holder holder;

	public CarListAdapter(Context context, ArrayList<BuyerList> list, String type) {
		_context = context;
		this.list = list;
		inflator = LayoutInflater.from(context);
		this.type=type;

	}

	public CarListAdapter(Context context, ArrayList<BuyerList> list, boolean b, Activity activity) {
		// TODO Auto-generated constructor stub
		this.fragment=(BaseFragmentActivity) activity;
		subshow=b;
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return arg0;
	}

	@Override
	public View getView(final int arg0, View convertView, ViewGroup arg2) {
		// TODO Auto-generated method stub
		if (convertView == null) {
			convertView = (View) inflator.inflate(R.layout.car_item, null);
			holder = new CarListAdapter.Holder(convertView);
			convertView.setTag(holder);
		} else {
			holder = (CarListAdapter.Holder) convertView.getTag();
		}

		holder.car_name.setText(list.get(arg0).getModel_type());
		holder.model.setText(list.get(arg0).getMake()+" : "+list.get(arg0).getModel_year());
		if (!list.get(arg0).getSinglepic().equals("")) {
			Picasso.with(_context).load(list.get(arg0).getSinglepic()).into(holder.car_image);
		}
		holder.image_delete.setTag(arg0);
		holder.image_edit.setTag(arg0);
		holder.image_delete.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				int pos=(Integer)v.getTag();
				showDialog(pos);

			}
		});
		holder.image_edit.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent=new Intent(fragment,DealerNewCarActivity.class);
				intent.putExtra("carid", list.get(arg0).getCarId());
				intent.putExtra("classtype","carlist");
				fragment.startActivity(intent);
			}
		});
		return convertView;
	}

	private void callingServicedletecarbyId(String carid) {
		ApiManager.getInstance().getDeletCar(fragment,carid);
	}
	public void showDialog(final int pos){
		final AlertDialog.Builder builder = new AlertDialog.Builder(_context);
		builder.setMessage("Are you sure want to delete?")
				.setCancelable(true)
				.setPositiveButton("OK", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int id) {
						AlertDialog alert = builder.create();
						alert.dismiss();
						callingServicedletecarbyId(list.get(pos).getCarId());
						list.remove(pos);
						notifyDataSetChanged();
					}
				});
		AlertDialog alert = builder.create();
		alert.show();
	}
	static class Holder {
		ImageView car_image = null;
		TextView car_name = null;
		TextView model = null;
		ImageView image_edit,image_delete;
		Holder(View convertView){
			 car_image=(ImageView)convertView.findViewById(R.id.car_image);
			 car_name=(TextView)convertView.findViewById(R.id.car_name);
			 model=(TextView)convertView.findViewById(R.id.model);
			image_edit=(ImageView)convertView.findViewById(R.id.image_edit);
			image_delete=(ImageView)convertView.findViewById(R.id.image_delete);
		}
	}

	@Override
	public Filter getFilter() {
		// TODO Auto-generated method stub
		return null;
	}
	
}