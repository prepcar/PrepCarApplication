package com.dev.prepcarapplication.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.dev.prepcarapplication.LoginActivity;
import com.dev.prepcarapplication.NavigateScreenActivity;
import com.dev.prepcarapplication.R;
import com.dev.prepcarapplication.SliderActivity;
import com.dev.prepcarapplication.baseClasses.BaseFragment;
import com.dev.prepcarapplication.baseClasses.Constants;

public class FragmentLogInSignUp extends BaseFragment {
	public static String TAG = "LOGIN";
	Button loginsignup_buyer,loginsignup_dealer;

	public static FragmentLogInSignUp newInstanse(Bundle bundle) {
		FragmentLogInSignUp fragment = new FragmentLogInSignUp();
		if (bundle != null) {
			fragment.setArguments(bundle);
		}
		return fragment;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.fragment_login_signup, null);
		initUi(view);
		setListener();
		return view;
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.loginsignup_buyer:
			Intent i=new  Intent(getActivity(),SliderActivity.class);
			i.putExtra(Constants.type, "4");
			startActivity(i);
			break;
		case R.id.loginsignup_dealer:
			Intent i1=new  Intent(getActivity(),SliderActivity.class);
			i1.putExtra(Constants.type, "3");
			startActivity(i1);
			break;

		default:
			break;
		}
	}

	@Override
	protected void initUi(View view) {
		// TODO Auto-generated method stub
		loginsignup_dealer = (Button) view.findViewById(R.id.loginsignup_dealer);
		loginsignup_buyer=(Button)view.findViewById(R.id.loginsignup_buyer);
	}

	@Override
	protected void setValueOnUi() {
		// TODO Auto-generated method stub
	}

	@Override
	protected void setListener() {
		// TODO Auto-generated method stub
		loginsignup_dealer.setOnClickListener(this);
		loginsignup_buyer.setOnClickListener(this);
	}

	@Override
	public boolean onBackPressedListener() {
		// TODO Auto-generated method stub
		return false;
	}

}
