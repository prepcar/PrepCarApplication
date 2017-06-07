package com.dev.prepcarapplication;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Menu;
import android.view.MenuItem;

import com.dev.prepcarapplication.baseClasses.BaseFragmentActivity;
import com.dev.prepcarapplication.baseClasses.Constants;
import com.dev.prepcarapplication.fragment.FragmentIKnowWhatIWant;

import org.json.JSONObject;

public class IKnowWhatIWantActivity extends BaseFragmentActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_container);
		replaceFragement(FragmentIKnowWhatIWant.newInstanse(null),FragmentIKnowWhatIWant.TAG);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.iknow_what_iwant, menu);
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
		/*case Constants.year:
			if (jObject.optInt("dataFlow", Constants.NOT_FLOW) == Constants.FLOW) {
				((FragmentIKnowWhatIWant)fragment2).setresponceyear(jObject);
			} else {
				 showDialog(message);
			}
			break;
		case Constants.make:
			if (jObject.optInt("dataFlow", Constants.NOT_FLOW) == Constants.FLOW) {
				((FragmentIKnowWhatIWant)fragment2).setresponceMake(jObject);
			} else {
				// showDialog(message);
			}
			break;
		case Constants.model:
			if (jObject.optInt("dataFlow", Constants.NOT_FLOW) == Constants.FLOW) {
				((FragmentIKnowWhatIWant)fragment2).setresponceModel(jObject);
			} else {
				// showDialog(message);
			}*/
		case Constants.getIKNOW:
			if (jObject.optInt("dataflow", Constants.NOT_FLOW) == Constants.FLOW) {
				((FragmentIKnowWhatIWant)fragment2).setresponceFianl(jObject);
			} else {
				// showDialog(message);
			}
			break;
			case Constants.getIKNOWALL:
				if (jObject.optInt("dataflow", Constants.NOT_FLOW) == Constants.FLOW) {
					((FragmentIKnowWhatIWant)fragment2).setresponceFianlAllData(jObject);
				} else {
					// showDialog(message);
				}
				break;
		/*case Constants.make1:
			if (jObject.optInt("dataFlow", Constants.NOT_FLOW) == Constants.FLOW) {
				((FragmentIKnowWhatIWant)fragment2).setresponceMake1(jObject);
			} else {
				 showDialog(message);
			}
			break;
		case Constants.model1:
			if (jObject.optInt("dataFlow", Constants.NOT_FLOW) == Constants.FLOW) {
				((FragmentIKnowWhatIWant)fragment2).setresponceModel1(jObject);
			} else {
				 showDialog(message);
			}
			break;*/
		/*case Constants.currentcar:
			if (jObject.optInt("dataFlow", Constants.NOT_FLOW) == Constants.FLOW) {
				((FragmentCurrentCar)fragment2).setresponceFinalResponce(jObject);
			} else {
				 showDialog(message);
			}
			break;*/
		}
	}
	public void showDialog(String msg){
		final AlertDialog.Builder builder = new AlertDialog.Builder(IKnowWhatIWantActivity.this);
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
