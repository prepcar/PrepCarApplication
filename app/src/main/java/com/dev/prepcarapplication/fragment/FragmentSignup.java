package com.dev.prepcarapplication.fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.dev.prepcarapplication.LoginActivity;
import com.dev.prepcarapplication.R;
import com.dev.prepcarapplication.baseClasses.BaseFragment;
import com.dev.prepcarapplication.baseClasses.Constants;
import com.dev.prepcarapplication.networkTask.ApiManager;
import com.dev.prepcarapplication.preference.MySharedPreferences;
import com.dev.prepcarapplication.utilities.ToastCustomClass;
import com.google.firebase.iid.FirebaseInstanceId;

import org.json.JSONObject;

public class FragmentSignup extends BaseFragment{
	public static String TAG="SIGNUP";
	Button signup_signupbuton;
	EditText signup_zipcode,signup_conpass,signup_password,signup_email,signup_code;
	static String roleId="";
	
	public static FragmentSignup newInstanse(Bundle bundle) {
		FragmentSignup fragment = new FragmentSignup();
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
		View view = inflater.inflate(R.layout.fragment_sign_up, null);
		initUi(view);
		setListener();
		return view;
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.signup_signupbuton:
			if(signup_email.getText().toString().trim().equals("")){
				signup_email.setError("Please enter email addres");
			}else if (roleId.equals("3") && signup_code.getText().toString().trim().equals("")){
				signup_code.setError("Please enter dealership code");
			}
			else if(signup_password.getText().toString().trim().equals("")){
				signup_password.setError("Please enter password");
			}else if(signup_password.getText().toString().length()<6){
				ToastCustomClass.showToast(getActivity(), "Password should be minimum 6 characters");
			}
			else if(signup_conpass.getText().toString().trim().equals("")){
				signup_conpass.setError("Please confirm password");
			}
			else if(signup_conpass.getText().toString().length()<6){
				ToastCustomClass.showToast(getActivity(), "Password should be minimum 6 characters");
			}
			else if(!signup_password.getText().toString().trim().equals(signup_conpass.getText().toString().trim())){
				ToastCustomClass.showToast(getActivity(), "Please enter same password");
			}else if(roleId.equals("4") && signup_zipcode.getText().toString().trim().equals("")){
				signup_zipcode.setError("Please enter email addres");
			}else {
				callingServiceSignup();
			}
			break;

		default:
			break;
		}
	}

	private void callingServiceSignup() {
		String token=FirebaseInstanceId.getInstance().getToken();
		Log.e(TAG, "callingServiceSignup: "+token );
		if(roleId.equals("3")){
			ApiManager.getInstance().getSignUp(this, token,signup_email.getText().toString().trim(), signup_password.getText().toString().trim() ,
					"",roleId,signup_code.getText().toString().trim());
		}
		else{
			ApiManager.getInstance().getSignUp(this, token,signup_email.getText().toString().trim(), signup_password.getText().toString().trim() ,
					signup_zipcode.getText().toString().trim(),roleId,"");
		}
		
	}

	@Override
	protected void initUi(View view) {
		signup_signupbuton=(Button)view.findViewById(R.id.signup_signupbuton);
		signup_conpass=(EditText)view.findViewById(R.id.signup_conpass);
		signup_email=(EditText)view.findViewById(R.id.signup_email);
		signup_password=(EditText)view.findViewById(R.id.signup_password);
		signup_zipcode=(EditText)view.findViewById(R.id.signup_zipcode);
		signup_code=(EditText)view.findViewById(R.id.signup_code);
		if(roleId.equals("4")){
			signup_code.setVisibility(View.GONE);
			signup_zipcode.setVisibility(View.VISIBLE);
		}
	}

	@Override
	protected void setValueOnUi() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void setListener() {
		// TODO Auto-generated method stub
		signup_signupbuton.setOnClickListener(this);
	}

	@Override
	public boolean onBackPressedListener() {
		// TODO Auto-generated method stub
		return false;
	}

	public void setresponce(JSONObject jObject) {
		// TODO Auto-generated method stub
		if(jObject.optInt("status")==1 && jObject.optInt("dataflow")==1){
			MySharedPreferences.getInstance().putStringKeyValue(getActivity(), Constants.ZIPCODE, signup_zipcode.getText().toString());
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
		        	   Intent intent = new Intent(getActivity(),LoginActivity.class)
						.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
		        	   intent.putExtra(Constants.type, roleId);
						getActivity().finishAffinity();
						startActivity(intent);
		           }
		       });
		AlertDialog alert = builder.create();
		alert.show();
	}
}
