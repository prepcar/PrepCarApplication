package com.dev.prepcarapplication.baseClasses;

import java.util.HashMap;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;


public abstract class BasePagerFragment extends BaseFragment{
	public abstract void setValueOnUi();
	public abstract void savePageState();
	
	protected Bundle myBundle = null;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);		
	}
	
	@Override
	public void onResume() {	
		super.onResume();
		//this.setTitleOnAction(title);
	}
	
	@SuppressLint("NewApi")
	public void setTitleOnAction(String text, boolean isUpperCase){
		//title  = text;
		((BaseFragmentActivity)getActivity()).setTitleOnAction(text);
	}
	
	protected void startMyActivity(Class targetActivity, Bundle myBundle){
		Intent intent = new Intent(getActivity(), targetActivity);
		if(myBundle != null)
			intent.putExtra(Constants.bundleArg, myBundle);
		startActivity(intent);
	}
	
	public void serviceCaller(final Fragment context, String urlsArr[], HashMap<String, String> params, Boolean isPost, Boolean isFileUploading, int requstCode, boolean isShowProgress) {
		((BaseFragmentActivity)getActivity()).serviceCaller(context, urlsArr, params, isPost, isFileUploading, requstCode, isShowProgress);
	}
	
	protected void addFragment(Fragment fragment, String tag, boolean addToBackStack) {
		((BaseFragmentActivity)getActivity()).addFragement(fragment, tag, addToBackStack);
	}
	
	protected void replaceFragment(Fragment fragment, String tag) {
		((BaseFragmentActivity)getActivity()).replaceFragement(fragment);
	}
}