package com.dev.prepcarapplication.fragment;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dev.prepcarapplication.BeforeLoginActivity;
import com.dev.prepcarapplication.R;
import com.dev.prepcarapplication.adapter.SlidingImage_Adapter;
import com.dev.prepcarapplication.baseClasses.BaseFragment;
import com.dev.prepcarapplication.baseClasses.Constants;
import com.dev.prepcarapplication.preference.MySharedPreferences;

import me.relex.circleindicator.CircleIndicator;

public class FragmentSlider extends BaseFragment {
	public static String TAG = "SLIDER";
	TextView slider_text;
	private ArrayList<Integer> ImagesArray;
	ViewPager mPager;
	CircleIndicator indicator;
	private static int currentPage = 0;
	private static int NUM_PAGES = 0;
	static String roleId="";

	public static FragmentSlider newInstanse(Bundle bundle) {
		FragmentSlider fragment = new FragmentSlider();
		if (bundle != null) {
			fragment.setArguments(bundle);
			if(bundle.containsKey(Constants.type)){
				roleId=bundle.getString(Constants.type);
			}
			else {
				roleId="";
			}
		}else {
			roleId="";	
		}
		return fragment;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.fragment_slider, null);
		initUi(view);
		setListener();
		return view;
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.slider_text:
			MySharedPreferences.getInstance().putBooleanKeyValue(getActivity(), Constants.ISSLIDER, true);
			Intent intent2 = new Intent(getActivity(),
					BeforeLoginActivity.class)
					.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
			intent2.putExtra(Constants.type, roleId);
			getActivity().finishAffinity();
			startActivity(intent2);
			break;

		default:
			break;
		}
	}

	@Override
	protected void initUi(View view) {
		// TODO Auto-generated method stub
		ImagesArray = new ArrayList<Integer>();
		slider_text = (TextView) view.findViewById(R.id.slider_text);
		mPager = (ViewPager) view.findViewById(R.id.pager);
		indicator = (CircleIndicator) view.findViewById(R.id.indicator);
		
		
		init();
	}

	@Override
	protected void setValueOnUi() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void setListener() {
		// TODO Auto-generated method stub
		slider_text.setOnClickListener(this);
	}

	@Override
	public boolean onBackPressedListener() {
		// TODO Auto-generated method stub
		return false;
	}

	private void init() {
		if (roleId.equals("4")) {
			ImagesArray.add(R.drawable.buyer_box_1);
			ImagesArray.add(R.drawable.buyer_box_2);
			ImagesArray.add(R.drawable.buyer_box_3);
		} else {
			ImagesArray.add(R.drawable.dealer_box_1);
			ImagesArray.add(R.drawable.dealer_box_2);
			ImagesArray.add(R.drawable.dealer_box_3);
		}

		mPager.setAdapter(new SlidingImage_Adapter(getActivity(), ImagesArray));

		indicator.setViewPager(mPager);

		final float density = getResources().getDisplayMetrics().density;

		// Set circle indicator radius
		//indicator.setRadius(5 * density);
		NUM_PAGES = ImagesArray.size();

		// Auto start of viewpager
		final Handler handler = new Handler();
		final Runnable Update = new Runnable() {
			public void run() {
				if (currentPage == NUM_PAGES) {
					currentPage = 0;
				}
				mPager.setCurrentItem(currentPage++, true);
			}
		};
		Timer swipeTimer = new Timer();
		swipeTimer.schedule(new TimerTask() {
			@Override
			public void run() {
				// handler.post(Update);
			}
		}, 3000, 3000);

		// Pager listener over indicator
		indicator.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

			@Override
			public void onPageSelected(int position) {
				currentPage = position;
if(position==2){
	slider_text.setVisibility(View.VISIBLE);
}else {
	slider_text.setVisibility(View.GONE);
}
			}

			@Override
			public void onPageScrolled(int pos, float arg1, int arg2) {

			}

			@Override
			public void onPageScrollStateChanged(int pos) {

			}
		});

	}
}
