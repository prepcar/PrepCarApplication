package com.dev.prepcarapplication.fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.dev.prepcarapplication.R;
import com.dev.prepcarapplication.baseClasses.BaseFragment;
import com.dev.prepcarapplication.networkTask.ApiManager;
import com.dev.prepcarapplication.utilities.ToastCustomClass;

import org.json.JSONObject;

public class FragmentChangePassword extends BaseFragment {
	public static String TAG = "CHANGEPASSWORD";
	Button change_submit;
	EditText change_curent, change_newpass, change_conpass;

	public static FragmentChangePassword newInstanse(Bundle bundle) {
		FragmentChangePassword fragment = new FragmentChangePassword();
		if (bundle != null) {
			fragment.setArguments(bundle);
		}
		return fragment;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.fragment_change_pass, null);
		initUi(view);
		setListener();
		return view;
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.change_submit:
			if (change_curent.getText().toString().trim().equals("")) {
				change_curent.setError("Please enter old password");
			}else if (change_newpass.getText().toString().trim().equals("")) {
				change_newpass.setError("Please enter new password");
			} else if (change_conpass.getText().toString().trim().equals("")) {
				change_conpass.setError("Please enter confirm new password");
			}  else if (change_curent.getText().toString().length()<6 ||change_newpass.getText().toString().length()<6 ||
					change_conpass.getText().toString().length()<6) {
				ToastCustomClass.showToast(getActivity(), "Please can not be less than 6 characters");
			} else if(!change_newpass.getText().toString().trim().equals(change_conpass.getText().toString().trim())){
				ToastCustomClass.showToast(getActivity(), "Please enter same password");
			}else {
				callingServicechangePassword();
			}
			break;

		default:
			break;
		}
	}

	private void callingServicechangePassword() {
		// TODO Auto-generated method stub
		ApiManager.getInstance().getChangePassword(this, change_curent.getText().toString().trim(), change_newpass.getText().toString().trim());
	}

	@Override
	protected void initUi(View view) {
		// TODO Auto-generated method stub
		change_conpass = (EditText) view.findViewById(R.id.change_conpass);
		change_curent = (EditText) view.findViewById(R.id.change_curent);
		change_newpass = (EditText) view.findViewById(R.id.change_newpass);
		change_submit = (Button) view.findViewById(R.id.change_submit);
		//change_conpass.setTypeface(Typeface.createFromAsset(getActivity().getAssets(),  "Helvetica-Bold.otf"));
	}

	@Override
	protected void setValueOnUi() {
		// TODO Auto-generated method stub
	}

	@Override
	protected void setListener() {
		// TODO Auto-generated method stub
		change_submit.setOnClickListener(this);
	}

	@Override
	public boolean onBackPressedListener() {
		// TODO Auto-generated method stub
		return false;
	}

	public void setrespponce(JSONObject jObject) {
		// TODO Auto-generated method stub
		if(jObject.optInt("status")==1 ){
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
		           }
		       });
		AlertDialog alert = builder.create();
		alert.show();
	}
}
