package com.dev.prepcarapplication.fragment;

import org.json.JSONObject;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.dev.prepcarapplication.ForgotPasswordActivity;
import com.dev.prepcarapplication.LoginActivity;
import com.dev.prepcarapplication.NavigateActivityBuyer;
import com.dev.prepcarapplication.R;
import com.dev.prepcarapplication.SignUpActivity;
import com.dev.prepcarapplication.baseClasses.BaseFragment;
import com.dev.prepcarapplication.baseClasses.Constants;
import com.dev.prepcarapplication.networkTask.ApiManager;
import com.dev.prepcarapplication.preference.MySharedPreferences;
import com.dev.prepcarapplication.utilities.UtilsClass;

public class FragmentForgotPassword extends BaseFragment {
	public static String TAG = "LOGIN";
	Button forgot_loginbuton;
	EditText forgot_email;
	static String roleId="";
	
	public static FragmentForgotPassword newInstanse(Bundle bundle) {
		FragmentForgotPassword fragment = new FragmentForgotPassword();
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
		View view = inflater.inflate(R.layout.fragment_forgot_pass, null);
		initUi(view);
		setListener();
		return view;
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		
		case R.id.forgot_loginbuton:
			if(forgot_email.getText().toString().trim().equals("")){
				forgot_email.setError("Please enter email address");
			}else if (!UtilsClass.isEmailValid(forgot_email.getText().toString()
					.trim())) {
				forgot_email.setError("Please enter valid email.");
			}else{
				callingServiceForgotpass();
			}
			break;
		default:
			break;
}
	}

	private void callingServiceForgotpass() {
		ApiManager.getInstance().getForgotPass(this, forgot_email.getText().toString().trim(),roleId);
	}

	@Override
	protected void initUi(View view) {
		forgot_email = (EditText) view.findViewById(R.id.forgot_email);
		forgot_loginbuton = (Button) view.findViewById(R.id.forgot_loginbuton);
	}

	@Override
	protected void setValueOnUi() {
	}

	@Override
	protected void setListener() {
		forgot_loginbuton.setOnClickListener(this);
	}

	@Override
	public boolean onBackPressedListener() {
		// TODO Auto-generated method stub
		return false;
	}

	public void setresponce(JSONObject jObject) {
		// TODO Auto-generated method stub
		if(jObject.optInt("status")==1){
			showDialog(jObject.optString("message"));
		}
	}
	public void showDialog(String msg){
		final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
		builder.setMessage(msg)
		       .setCancelable(false)
		       .setPositiveButton("OK", new DialogInterface.OnClickListener() {
		           public void onClick(DialogInterface dialog, int id) {
		        	   AlertDialog alert = builder.create();
		        	   alert.dismiss();
		        	   Intent intent2 = new Intent(getActivity(),
		   					LoginActivity.class)
		   					.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
		        	   intent2.putExtra(Constants.type, roleId);
		   			getActivity().finishAffinity();
		   			startActivity(intent2);
		           }
		       });
		AlertDialog alert = builder.create();
		alert.show();
	}
}
