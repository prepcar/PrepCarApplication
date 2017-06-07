package com.dev.prepcarapplication.adapter;

import android.content.Context;
import android.support.v4.view.ViewPager;
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

import java.util.ArrayList;

//This is simple adapter to show data in listview with multiple values.

public class MainScreenListAdapter extends BaseAdapter implements Filterable {
	Context _context;
	String[] list;
	private LayoutInflater inflator;
	ViewPager mPager;
	ArrayList<Integer> imagesArray;

	public MainScreenListAdapter(Context context, String[] from ) {
		_context = context;
		this.list = from;
		inflator = LayoutInflater.from(context);

	}

	public MainScreenListAdapter(Context context, String[] list, ArrayList<Integer> imagesArray) {
		// TODO Auto-generated constructor stub
		this.imagesArray=imagesArray;
		_context = context;
		this.list = list;
		inflator = LayoutInflater.from(context);
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return list.length;
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
	public View getView(int arg0, View convertView, ViewGroup arg2) {
		// TODO Auto-generated method stub
		convertView = (View) inflator.inflate(R.layout.item_mysubroutelist,
				null);

		TextView username = (TextView) convertView.findViewById(R.id.sub_text);
		 mPager = (ViewPager)convertView.findViewById(R.id.pager);
		 mPager.setFocusable(false);
		 name();

		username.setText(list[arg0]);

		return convertView;
	}

	static class Holder {
		public RelativeLayout root;
		ImageView userimage = null;
		TextView username = null;
		TextView location = null;
		TextView timedif = null;
		ViewPager mPager=null;
	}

	@Override
	public Filter getFilter() {
		// TODO Auto-generated method stub
		return null;
	}
	public void name() {
		mPager.setAdapter(new SlidingImage_Adapter(_context,
				imagesArray));
	}
}