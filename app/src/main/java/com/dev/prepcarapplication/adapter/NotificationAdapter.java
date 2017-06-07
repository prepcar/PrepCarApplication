package com.dev.prepcarapplication.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dev.prepcarapplication.CarPlanActivity;
import com.dev.prepcarapplication.NavigateActivityDealer;
import com.dev.prepcarapplication.R;
import com.dev.prepcarapplication.TestDriveActivityDealer;
import com.dev.prepcarapplication.baseClasses.BaseFragmentActivity;
import com.dev.prepcarapplication.bean.NotificationModel;
import com.dev.prepcarapplication.utilities.RoundedImageView;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuAdapter;

import java.util.ArrayList;

//This is simple adapter to show data in listview with multiple values.

public class NotificationAdapter extends SwipeMenuAdapter<NotificationAdapter.ViewHolder> {
	Context _context;
	ArrayList<NotificationModel> list;
	private LayoutInflater inflator;
	boolean subshow;
	BaseFragmentActivity fragment;
	//Holder holder;

	public NotificationAdapter(Context context, ArrayList<NotificationModel> list) {
		_context = context;
		this.list = list;
		inflator = LayoutInflater.from(context);

	}

	public NotificationAdapter(Context context, ArrayList<NotificationModel> list, boolean b, Activity activity) {
		// TODO Auto-generated constructor stub
		this.fragment=(BaseFragmentActivity) activity;
		subshow=b;
		_context = context;
		this.list = list;
		inflator = LayoutInflater.from(context);
	}
	@Override
	public View onCreateContentView(ViewGroup parent, int viewType) {
		return LayoutInflater.from(parent.getContext()).inflate(R.layout.notification_item, parent, false);
	}

	@Override
	public ViewHolder onCompatCreateViewHolder(View realContentView, int viewType) {
		return new ViewHolder(realContentView);
	}

	@Override
	public void onBindViewHolder(ViewHolder holder, final int position) {
		if (position%3==2){
			holder.parentLayout.setBackgroundColor(Color.parseColor("#FFCCCB"));
			Log.e("asdf", "getView: 1" );
		}else if (position%3==1){
			holder.parentLayout.setBackgroundColor(Color.parseColor("#F2F2F2"));
			Log.e("asdf", "getView: 2" );
		}else{
			holder.parentLayout.setBackgroundColor(Color.parseColor("#D5D8E9"));
			Log.e("asdf", "getView: 3" );
		}
		holder.notification_title.setText(list.get(position).getMessage());
		holder.notification_detail.setText(list.get(position).getTime());

		holder.parentLayout.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if (list.get(position).getNotificationType().equals("3") ||
						list.get(position).getNotificationType().equals("2")){
					_context.startActivity(new Intent(_context, CarPlanActivity.class));
					((Activity)_context).finish();
				}if (list.get(position).getNotificationType().equals("1")){
					_context.startActivity(new Intent(_context, NavigateActivityDealer.class));
					((Activity)_context).finish();
				}if (list.get(position).getNotificationType().equals("4")){
					_context.startActivity(new Intent(_context, TestDriveActivityDealer.class));
					((Activity)_context).finish();
				}
			}
		});
	}

	@Override
	public int getItemCount() {
		return list.size();
	}

	static class ViewHolder extends RecyclerView.ViewHolder {
		RoundedImageView notification_image = null;
		TextView notification_title = null;
		TextView notification_detail = null;
		LinearLayout parentLayout;
		ViewHolder(View convertView){
			super(convertView);
			notification_image=(RoundedImageView)convertView.findViewById(R.id.notification_image);
			notification_title=(TextView)convertView.findViewById(R.id.notification_title);
			notification_detail=(TextView)convertView.findViewById(R.id.notification_detail);
			parentLayout=(LinearLayout) convertView.findViewById(R.id.parent_layout);
		}

	}

}