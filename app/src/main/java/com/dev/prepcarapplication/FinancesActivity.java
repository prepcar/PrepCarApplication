package com.dev.prepcarapplication;

import org.json.JSONObject;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Menu;
import android.view.MenuItem;

import com.dev.prepcarapplication.baseClasses.BaseFragmentActivity;
import com.dev.prepcarapplication.baseClasses.Constants;
import com.dev.prepcarapplication.fragment.FragmentBeforeLogin;
import com.dev.prepcarapplication.fragment.FragmentFinances;

public class FinancesActivity extends BaseFragmentActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_container);
		replaceFragement(FragmentFinances.newInstanse(null),FragmentFinances.TAG);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.finances, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	protected void initControls(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void setValueOnUI() {
		// TODO Auto-generated method stub
		
	}
	@Override
	protected Boolean callBackFromApi(Object object, Activity act,
			int requstCode) {
		if (super.callBackFromApi(object, act, requstCode)) {
			commonCallBack(object, requstCode, act);
		}
		return true;
	}

	@Override
	protected Boolean callBackFromApi(Object object, Fragment fragment,
			int requstCode) {
	//	if (super.callBackFromApi(object, fragment, requstCode)) {
			commonCallBack(object, requstCode, fragment);
	//	}
		return true;
	}

	private void commonCallBack(Object object, int requestCode, Object fragment2) {
		JSONObject jObject;
		String message = "";
		jObject = (JSONObject) object;
		message = jObject.optString(Constants.message);
		switch (requestCode) {
		case Constants.finaces:
			if (jObject.optInt(Constants.status, Constants.NOT_FLOW) == Constants.FLOW) {
				((FragmentFinances)fragment2).setresponce(jObject);
			} else {
				 showDialog(message);
			}
			break;
		case Constants.getFinance:
			if (jObject.optInt(Constants.status, Constants.NOT_FLOW) == Constants.FLOW) {
				((FragmentFinances)fragment2).setresponcegetFinance(jObject);
			} else {
				 showDialog(message);
			}
			break;
		}
	}
	public void showDialog(String msg){
		final AlertDialog.Builder builder = new AlertDialog.Builder(FinancesActivity.this);
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