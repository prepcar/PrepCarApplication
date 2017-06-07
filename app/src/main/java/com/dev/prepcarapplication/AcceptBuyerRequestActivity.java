package com.dev.prepcarapplication;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dev.prepcarapplication.baseClasses.BaseFragmentActivity;
import com.dev.prepcarapplication.baseClasses.Constants;
import com.dev.prepcarapplication.fragment.FragmentAcceptBuyerActivity;
import com.dev.prepcarapplication.fragment.FragmentAcceptBuyerCarPlan;
import com.dev.prepcarapplication.fragment.FragmentAcceptBuyerFavorites;
import com.dev.prepcarapplication.fragment.FragmentAcceptBuyerSent;

import org.json.JSONObject;

public class AcceptBuyerRequestActivity extends BaseFragmentActivity {
	TextView text_logout;
	RelativeLayout rel_carplan, rel_sent, rel_favorites,rel_activity;
	TextView text_carplan, text_sent, text_favorites,text_activity;
	Bundle bundle;
	ImageView image_back;
	static String roleId;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_container_accept_request);
		initControls(savedInstanceState);
		Intent i = getIntent();
		bundle= i.getExtras();
		replaceFragement(FragmentAcceptBuyerCarPlan.newInstanse(bundle),
				FragmentAcceptBuyerCarPlan.TAG);
		findViewById(R.id.icHome).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				startMyActivity(NavigateActivityDealer.class, null);
				finish();
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		// getMenuInflater().inflate(R.menu.profile, menu);
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

		image_back=(ImageView)findViewById(R.id.image_back);
		text_carplan = (TextView) findViewById(R.id.text_carplan);
		text_sent = (TextView) findViewById(R.id.text_sent);
		text_favorites = (TextView) findViewById(R.id.text_favorites);
		text_activity = (TextView) findViewById(R.id.text_activity);

		rel_carplan = (RelativeLayout) findViewById(R.id.rel_carplan);
		rel_sent = (RelativeLayout) findViewById(R.id.rel_sent);
		rel_favorites = (RelativeLayout) findViewById(R.id.rel_favorites);
		rel_activity = (RelativeLayout) findViewById(R.id.rel_activity);

		text_carplan.setTextColor(Color.parseColor("#293A92"));
		text_sent.setTextColor(Color.parseColor("#c4c4c4"));
		text_favorites.setTextColor(Color.parseColor("#c4c4c4"));
		text_activity.setTextColor(Color.parseColor("#c4c4c4"));
		rel_activity.setVisibility(View.GONE);
		rel_carplan.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				text_carplan.setTextColor(Color.parseColor("#293A92"));
				text_sent.setTextColor(Color.parseColor("#c4c4c4"));
				text_favorites.setTextColor(Color.parseColor("#c4c4c4"));
				text_activity.setTextColor(Color.parseColor("#c4c4c4"));
				replaceFragement(FragmentAcceptBuyerCarPlan.newInstanse(bundle),
						FragmentAcceptBuyerCarPlan.TAG);
			}
		});
		rel_sent.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				text_carplan.setTextColor(Color.parseColor("#c4c4c4"));
				text_sent.setTextColor(Color.parseColor("#293A92"));
				text_favorites.setTextColor(Color.parseColor("#c4c4c4"));
				text_activity.setTextColor(Color.parseColor("#c4c4c4"));
				rel_activity.setVisibility(View.GONE);
				rel_carplan.setVisibility(View.VISIBLE);

				replaceFragement(FragmentAcceptBuyerSent.newInstanse(bundle),
						FragmentAcceptBuyerSent.TAG);
			}
		});
		rel_favorites.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				text_carplan.setTextColor(Color.parseColor("#c4c4c4"));
				text_sent.setTextColor(Color.parseColor("#c4c4c4"));
				text_favorites.setTextColor(Color.parseColor("#293A92"));
				text_activity.setTextColor(Color.parseColor("#c4c4c4"));
				rel_activity.setVisibility(View.VISIBLE);
				rel_carplan.setVisibility(View.GONE);
				replaceFragement(FragmentAcceptBuyerFavorites.newInstanse(null),
						FragmentAcceptBuyerFavorites.TAG);
			}
		});
		rel_activity.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				text_carplan.setTextColor(Color.parseColor("#c4c4c4"));
				text_sent.setTextColor(Color.parseColor("#c4c4c4"));
				text_activity.setTextColor(Color.parseColor("#293A92"));
				text_favorites.setTextColor(Color.parseColor("#c4c4c4"));
				replaceFragement(FragmentAcceptBuyerActivity.newInstanse(null),
						FragmentAcceptBuyerActivity.TAG);

			}
		});
		image_back.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
			}
		});
	}

	@Override
	protected void setValueOnUI() {
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

		case Constants.acceptcarplan:
			if (jObject.optInt(Constants.status, Constants.NOT_FLOW) == Constants.FLOW) {
			//	ToastCustomClass.showToast(this, message);
				((FragmentAcceptBuyerCarPlan)fragment2).setrespponce(jObject);
			} else {
				 showDialog(message);
			}
			break;
			case Constants.dealerFavourite:
				if (jObject.optInt("dataflow", Constants.NOT_FLOW) == Constants.FLOW) {
					((FragmentAcceptBuyerFavorites)fragment2).setresponceFav(jObject);
				}
					//  showDialog(message);
					break;
			case Constants.dealerSent:
				if (jObject.optInt("dataflow", Constants.NOT_FLOW) == Constants.FLOW) {
					((FragmentAcceptBuyerSent)fragment2).setresponce(jObject);
				}
				break;
			case Constants.dealerActivity:
				if (jObject.optInt("dataflow", Constants.NOT_FLOW) == Constants.FLOW) {
					((FragmentAcceptBuyerActivity)fragment2).setresponce(jObject);
				}
				break;
			default:
				break;

		}
	}
	public void showDialog(String msg){
		final AlertDialog.Builder builder = new AlertDialog.Builder(AcceptBuyerRequestActivity.this);
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
