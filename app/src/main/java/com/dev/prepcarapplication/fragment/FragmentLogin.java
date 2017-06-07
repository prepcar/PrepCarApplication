package com.dev.prepcarapplication.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.dev.prepcarapplication.ForgotPasswordActivity;
import com.dev.prepcarapplication.NavigateActivityBuyer;
import com.dev.prepcarapplication.NavigateActivityDealer;
import com.dev.prepcarapplication.R;
import com.dev.prepcarapplication.SignUpActivity;
import com.dev.prepcarapplication.baseClasses.BaseFragment;
import com.dev.prepcarapplication.baseClasses.Constants;
import com.dev.prepcarapplication.networkTask.ApiManager;
import com.dev.prepcarapplication.preference.MySharedPreferences;
import com.dev.prepcarapplication.utilities.ToastCustomClass;
import com.dev.prepcarapplication.utilities.UtilsClass;
import com.google.firebase.iid.FirebaseInstanceId;

import org.json.JSONObject;

public class FragmentLogin extends BaseFragment {
	public static String TAG = "LOGIN";
	Button login_loginbuton;
	EditText login_password, login_email;
	TextView login_forgot, login_signup;
	static String roleId="";
	
	public static FragmentLogin newInstanse(Bundle bundle) {
		FragmentLogin fragment = new FragmentLogin();
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
		View view = inflater.inflate(R.layout.fragment_login, null);
		initUi(view);
		setListener();
		return view;
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.login_signup:
			Intent i=new  Intent(getActivity(),SignUpActivity.class);
			i.putExtra(Constants.type, roleId);
			startActivity(i);
			break;
		case R.id.login_loginbuton:
			if(login_email.getText().toString().trim().equals("")){
				login_email.setError("Please enter email address");
			}else if (!UtilsClass.isEmailValid(login_email.getText().toString()
					.trim())) {
				login_email.setError("Please enter valid email.");
			}else if(login_password.getText().toString().trim().equals("")){
				login_password.setError("Please enter password");
			}else if(login_password.getText().toString().length()<6){
				ToastCustomClass.showToast(getActivity(), "Password should be minimum 6 characters");
			}else{
				callingServiceLogin();
			}
			break;
		case R.id.login_forgot:
			Intent i2=new  Intent(getActivity(),ForgotPasswordActivity.class);
			i2.putExtra(Constants.type, roleId);
			startActivity(i2);
			break;
		default:
			break;
}
	}

	private void callingServiceLogin() {
		View view = getActivity().getCurrentFocus();
		if (view != null) {
			InputMethodManager imm = (InputMethodManager)getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
			imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
		}
		String token= FirebaseInstanceId.getInstance().getToken();
		Log.e(TAG, "callingServiceLogin: "+token );
		MySharedPreferences.getInstance().putStringKeyValue(getActivity(), Constants.USER_NAME, login_email.getText().toString().trim());;
		MySharedPreferences.getInstance().putStringKeyValue(getActivity(), Constants.PASSWORD, login_password.getText().toString().trim());;
		ApiManager.getInstance().getLogin(this, login_email.getText().toString().trim(), login_password.getText().toString().trim(),roleId,token);
	}

	@Override
	protected void initUi(View view) {
		login_email = (EditText) view.findViewById(R.id.login_email);
		login_forgot = (TextView) view.findViewById(R.id.login_forgot);
		login_loginbuton = (Button) view.findViewById(R.id.login_loginbuton);
		login_password = (EditText) view.findViewById(R.id.login_password);
		login_signup = (TextView) view.findViewById(R.id.login_signup);
		if (roleId.equals("3")){
			login_signup.setText(R.string.signupnowdealer);
		}

	}

	@Override
	protected void setValueOnUi() {
	}

	@Override
	protected void setListener() {
		login_loginbuton.setOnClickListener(this);
		login_forgot.setOnClickListener(this);
		login_signup.setOnClickListener(this);
	}

	@Override
	public boolean onBackPressedListener() {
		// TODO Auto-generated method stub
		return false;
	}

	public void setresponce(JSONObject jObject) {
		// TODO Auto-generated method stub
		if(jObject.optInt("status")==1 && jObject.optInt("dataflow")==1){
			MySharedPreferences.getInstance().putBooleanKeyValue(getActivity(), Constants.ISREMEMBER, true);
			MySharedPreferences.getInstance().putIntegerKeyValue(getActivity(), Constants.USER_ID, jObject.optInt("userId"));
			MySharedPreferences.getInstance().putIntegerKeyValue(getActivity(), Constants.role_id, jObject.optInt("role_id"));		
			MySharedPreferences.getInstance().putStringKeyValue(getActivity(), Constants.EMAIL, jObject.optString("email"));
			MySharedPreferences.getInstance().putStringKeyValue(getActivity(), Constants.ZIPCODE, jObject.optString("zip"));
			Log.e(TAG, "setresponce: "+MySharedPreferences.getInstance().getInteger(getActivity(),Constants.role_id,0) );
			if(jObject.optInt("role_id")==4){
			Intent intent2 = new Intent(getActivity(),
					NavigateActivityBuyer.class)
					.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
			getActivity().finishAffinity();
			startActivity(intent2);
			}
		else{
			Intent intent2 = new Intent(getActivity(),
					NavigateActivityDealer.class)
					.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
			getActivity().finishAffinity();
			startActivity(intent2);
		}
		}
	}

}
