package com.dev.prepcarapplication;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Menu;
import android.view.MenuItem;

import com.dev.prepcarapplication.baseClasses.BaseFragmentActivity;
import com.dev.prepcarapplication.baseClasses.Constants;
import com.dev.prepcarapplication.fragment.FragmentDealerNewCar;

import org.json.JSONObject;

public class DealerNewCarActivity extends BaseFragmentActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_container);
		Intent i = getIntent();
		Bundle extras = i.getExtras();
	//	ToastCustomClass.showToast(this,"oncreate");
		replaceFragement(FragmentDealerNewCar.newInstanse(extras),FragmentDealerNewCar.TAG);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		// getMenuInflater().inflate(R.menu.new_car, menu);
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
		// if (super.callBackFromApi(object, fragment, requstCode)) {
		commonCallBack(object, requstCode, fragment);
		// }
		return true;
	}

	private void commonCallBack(Object object, int requestCode, Object fragment2) {
		JSONObject jObject;
		String message = "";
		jObject = (JSONObject) object;
		message = jObject.optString(Constants.message);
		switch (requestCode) {
		/*case Constants.year1:
			if (jObject.optInt("dataFlow", Constants.NOT_FLOW) == Constants.FLOW) {
				((FragmentDealerNewCar) fragment2).setresponce(jObject);
			} else {
				showDialog(message);
			}
			break;
		case Constants.make1:
			if (jObject.optInt("dataFlow", Constants.NOT_FLOW) == Constants.FLOW) {
				((FragmentDealerNewCar) fragment2).setresponceMake(jObject);
			} else {
				showDialog(message);
			}
			break;
		case Constants.model1:
			if (jObject.optInt("dataFlow", Constants.NOT_FLOW) == Constants.FLOW) {
				((FragmentDealerNewCar) fragment2).setresponceModel(jObject);
			} else {
				showDialog(message);
			}
			break;*/
		case Constants.carupload:
			if (jObject.optInt("dataflow", Constants.NOT_FLOW) == Constants.FLOW) {
				((FragmentDealerNewCar) fragment2).setresponceupload(jObject);
			} else {
				showDialog(message);
			}
			break;
			case Constants.carDetail:
				if (jObject.optInt("dataFlow") == Constants.FLOW) {
					((FragmentDealerNewCar) fragment2).setresponcecarDeatil(jObject);
				} else {
					showDialog(message);
				}
				break;
		
		}
	}

	public void showDialog(String msg) {
		final AlertDialog.Builder builder = new AlertDialog.Builder(
				DealerNewCarActivity.this);
		builder.setMessage(msg).setCancelable(false)
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