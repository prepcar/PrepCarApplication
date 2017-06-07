package com.dev.prepcarapplication.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.dev.prepcarapplication.BuyerNewCarActivity;
import com.dev.prepcarapplication.CarPlanActivity;
import com.dev.prepcarapplication.CurrentCarActivity;
import com.dev.prepcarapplication.DiscoveryActivity;
import com.dev.prepcarapplication.FinancesActivity;
import com.dev.prepcarapplication.NotificationActivityBuyer;
import com.dev.prepcarapplication.ProfileActivityBuyer;
import com.dev.prepcarapplication.R;
import com.dev.prepcarapplication.SearchActivityBuyer;
import com.dev.prepcarapplication.baseClasses.BaseFragment;
import com.dev.prepcarapplication.baseClasses.Constants;
import com.dev.prepcarapplication.preference.MySharedPreferences;

public class FragmentNavigateScreenBuyer extends BaseFragment {
	public static String TAG = "Navigatescreenbuyer";
	RelativeLayout lin_menu, lin_search, lin_carplan, lin_chat, lin_profile,navi_newcar,navi_curentcar,navi_finance,navi_discovery;
	ImageView image_menu, image_search, image_carplan, image_chat,image_dot,
			image_profile;
	static String rolrId="";

	public static FragmentNavigateScreenBuyer newInstanse(Bundle bundle) {
		FragmentNavigateScreenBuyer fragment = new FragmentNavigateScreenBuyer();
		if (bundle != null) {
			fragment.setArguments(bundle);
			if(bundle.containsKey(Constants.type)){
				rolrId=bundle.getString(Constants.type);
			}
			else{
				rolrId="";
			}
		}else{
		rolrId="";	
		}
		return fragment;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.fragment_navigate_screen_buyer,
				null);
		initUi(view);
		setListener();
		return view;
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.lin_menu:
			lin_menu.setBackgroundColor(Color.parseColor("#c4c4c4"));
			image_menu.setBackgroundResource(R.drawable.icon_white_manu);

			lin_search.setBackgroundColor(Color.parseColor("#FFFFFF"));
			image_search.setBackgroundResource(R.drawable.icon_search);

			lin_chat.setBackgroundColor(Color.parseColor("#FFFFFF"));
			image_chat.setBackgroundResource(R.drawable.icon_notification);

			lin_profile.setBackgroundColor(Color.parseColor("#FFFFFF"));
			image_profile.setBackgroundResource(R.drawable.icon_user);
			break;
		case R.id.lin_search:
			lin_search.setBackgroundColor(Color.parseColor("#c4c4c4"));
			image_search.setBackgroundResource(R.drawable.icon_white_search);

			lin_menu.setBackgroundColor(Color.parseColor("#FFFFFF"));
			image_menu.setBackgroundResource(R.drawable.icon_manu);

			lin_chat.setBackgroundColor(Color.parseColor("#FFFFFF"));
			image_chat.setBackgroundResource(R.drawable.icon_notification);

			lin_profile.setBackgroundColor(Color.parseColor("#FFFFFF"));
			image_profile.setBackgroundResource(R.drawable.icon_user);
			Intent intent22 = new Intent(getActivity(),
					SearchActivityBuyer.class)
					.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
			getActivity().finishAffinity();
			startActivity(intent22);
			break;
		case R.id.lin_carplan:
			startMyActivity(CarPlanActivity.class,null);
			getActivity().finish();
			break;
		case R.id.lin_chat:
			lin_chat.setBackgroundColor(Color.parseColor("#c4c4c4"));
			image_chat.setBackgroundResource(R.drawable.icon_white_notification);

			lin_search.setBackgroundColor(Color.parseColor("#FFFFFF"));
			image_search.setBackgroundResource(R.drawable.icon_search);

			lin_menu.setBackgroundColor(Color.parseColor("#FFFFFF"));
			image_menu.setBackgroundResource(R.drawable.icon_manu);

			lin_profile.setBackgroundColor(Color.parseColor("#FFFFFF"));
			image_profile.setBackgroundResource(R.drawable.icon_user);

			startMyActivity(NotificationActivityBuyer.class,null);
			getActivity().finish();
			break;
		case R.id.lin_profile:
			lin_profile.setBackgroundColor(Color.parseColor("#c4c4c4"));
			image_profile.setBackgroundResource(R.drawable.icon_white_usere);

			lin_search.setBackgroundColor(Color.parseColor("#FFFFFF"));
			image_search.setBackgroundResource(R.drawable.icon_search);

			lin_chat.setBackgroundColor(Color.parseColor("#FFFFFF"));
			image_chat.setBackgroundResource(R.drawable.icon_notification);

			lin_menu.setBackgroundColor(Color.parseColor("#FFFFFF"));
			image_menu.setBackgroundResource(R.drawable.icon_manu);
			Intent intent2 = new Intent(getActivity(),
					ProfileActivityBuyer.class)
					.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
			getActivity().finishAffinity();
			startActivity(intent2);
			break;
		case R.id.navi_discovery:
			startMyActivity(DiscoveryActivity.class, null);
			break;
		case R.id.navi_finance:
			startMyActivity(FinancesActivity.class, null);
			break;
		case R.id.navi_curentcar:
			startMyActivity(CurrentCarActivity.class, null);
			break;
		case R.id.navi_newcar:
			startMyActivity(BuyerNewCarActivity.class, null);
			break;

		default:
			break;
		}
	}

	@Override
	protected void initUi(View view) {
		navi_newcar=(RelativeLayout)view.findViewById(R.id.navi_newcar);
		navi_curentcar=(RelativeLayout)view.findViewById(R.id.navi_curentcar);
		navi_discovery=(RelativeLayout)view.findViewById(R.id.navi_discovery);
		navi_finance=(RelativeLayout)view.findViewById(R.id.navi_finance);
		
		lin_carplan = (RelativeLayout) view.findViewById(R.id.lin_carplan);
		lin_chat = (RelativeLayout) view.findViewById(R.id.lin_chat);
		lin_menu = (RelativeLayout) view.findViewById(R.id.lin_menu);
		lin_profile = (RelativeLayout) view.findViewById(R.id.lin_profile);
		lin_search = (RelativeLayout) view.findViewById(R.id.lin_search);
		image_carplan = (ImageView) view.findViewById(R.id.image_carplan);
		image_chat = (ImageView) view.findViewById(R.id.image_chat);
		image_dot = (ImageView) view.findViewById(R.id.image_dot);
		image_menu = (ImageView) view.findViewById(R.id.image_menu);
		image_profile = (ImageView) view.findViewById(R.id.image_profile);
		image_search = (ImageView) view.findViewById(R.id.image_search);
		lin_menu.setBackgroundColor(Color.parseColor("#c4c4c4"));
		image_menu.setBackgroundResource(R.drawable.icon_white_manu);
		if (!MySharedPreferences.getInstance().getBoolean(getActivity(),Constants.isUnreadNotification,false))
			image_dot.setVisibility(View.GONE);
	}

	@Override
	protected void setValueOnUi() {
		// TODO Auto-generated method stub
	}

	@Override
	protected void setListener() {
		// TODO Auto-generated method stub
		lin_carplan.setOnClickListener(this);
		lin_chat.setOnClickListener(this);
		lin_menu.setOnClickListener(this);
		lin_profile.setOnClickListener(this);
		lin_search.setOnClickListener(this);
		navi_curentcar.setOnClickListener(this);
		navi_discovery.setOnClickListener(this);
		navi_finance.setOnClickListener(this);
		navi_newcar.setOnClickListener(this);
	}

	@Override
	public boolean onBackPressedListener() {
		// TODO Auto-generated method stub
		return false;
	}

}
