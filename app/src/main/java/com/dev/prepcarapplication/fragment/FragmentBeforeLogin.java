package com.dev.prepcarapplication.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.dev.prepcarapplication.LoginActivity;
import com.dev.prepcarapplication.NavigateActivityBuyer;
import com.dev.prepcarapplication.NavigateActivityDealer;
import com.dev.prepcarapplication.R;
import com.dev.prepcarapplication.baseClasses.BaseFragment;
import com.dev.prepcarapplication.baseClasses.Constants;
import com.dev.prepcarapplication.networkTask.ApiManager;
import com.dev.prepcarapplication.preference.MySharedPreferences;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;

import org.json.JSONObject;

import java.util.Arrays;

public class FragmentBeforeLogin extends BaseFragment {
	public static String TAG = "LOGIN";
	Button login_dealer,login_buyer;
	ImageView login_fb;
	private CallbackManager callbackManager;
	static String roleId="";

	public static FragmentBeforeLogin newInstanse(Bundle bundle) {
		FragmentBeforeLogin fragment = new FragmentBeforeLogin();
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
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		FacebookSdk.sdkInitialize(getActivity());
		AppEventsLogger.activateApp(getActivity());

	}
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.fragment_before_login, null);
		initUi(view);
		setListener();
		return view;
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.login_dealer:
			Intent i=new  Intent(getActivity(),LoginActivity.class);
			i.putExtra(Constants.type, "3");
			startActivity(i);
			break;
		case R.id.login_buyer:
			Intent i1=new  Intent(getActivity(),LoginActivity.class);
			i1.putExtra(Constants.type, "4");
			startActivity(i1);
			break;
		case R.id.login_fb:
			fbLogin();
			break;
		default:
			break;
		}
	}

	@Override
	protected void initUi(View view) {
		// TODO Auto-generated method stub
		login_dealer = (Button) view.findViewById(R.id.login_dealer);
		login_buyer=(Button)view.findViewById(R.id.login_buyer);
		login_fb=(ImageView)view.findViewById(R.id.login_fb);
	}

	@Override
	protected void setValueOnUi() {
	}

	@Override
	protected void setListener() {
		// TODO Auto-generated method stub
		login_dealer.setOnClickListener(this);
		login_buyer.setOnClickListener(this);
		login_fb.setOnClickListener(this);
	}

	@Override
	public boolean onBackPressedListener() {
		// TODO Auto-generated method stub
		return false;
	}
	
	
	public void fbLogin() {

		callbackManager = CallbackManager.Factory.create();

		LoginManager.getInstance().logInWithReadPermissions(this,
				Arrays.asList("email", "public_profile"));

		LoginManager.getInstance().registerCallback(callbackManager,
				new FacebookCallback<LoginResult>() {
			@Override
			public void onSuccess(LoginResult loginResult) {

				GraphRequest request = GraphRequest.newMeRequest(
						loginResult.getAccessToken(),
						new GraphRequest.GraphJSONObjectCallback() {
							@Override
							public void onCompleted(JSONObject object,GraphResponse response) {
								if (response.getError() != null) {
									// handle error
									Log.d("ERROR", "ERROR");

								} else {
								//	isFbButtonClick = true;

									Log.i("JSONOBJECT",object.toString());
									Log.i("GRAPH RESPONSE",response.toString());

									if (object != null && object.optString(Constants.USER_Email) != null) {
										Log.e("facebook", object.toString());
									//fbJsonObject = object;
									ApiManager.getInstance().getFbLogin(FragmentBeforeLogin.this,object.optString("name"),"",object.optString("id"),"4",object.optString(Constants.USER_Email));
									}
								}
							}

						});
				Bundle parameters = new Bundle();

				parameters.putString("fields","id,name,first_name,last_name,email,birthday,location");
				request.setParameters(parameters);
				request.executeAsync();

			}

			@Override
			public void onCancel() {
				Log.d("oncancel", "On cancel");
				LoginManager.getInstance().logOut();
			}

			@Override
			public void onError(FacebookException error) {
				Log.d("onerror", error.toString());
				LoginManager.getInstance().logOut();
			}
		});
	}
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
	//	dismissLoader();
		if (callbackManager != null)
			callbackManager.onActivityResult(requestCode, resultCode, data);
		
	//	super.onActivityResult(requestCode, resultCode, data);
	}

	public void setresponce(JSONObject jObject) {
		// TODO Auto-generated method stub
		if(jObject.optInt("status")==1 && jObject.optInt("dataflow")==1){
			JSONObject responce=jObject.optJSONObject("response");
			MySharedPreferences.getInstance().putBooleanKeyValue(getActivity(), Constants.ISREMEMBER, true);
			MySharedPreferences.getInstance().putIntegerKeyValue(getActivity(), Constants.USER_ID, responce.optInt("userId"));
			MySharedPreferences.getInstance().putIntegerKeyValue(getActivity(), Constants.role_id, responce.optInt("role_id"));		
MySharedPreferences.getInstance().putBooleanKeyValue(getActivity(), Constants.fb, true);
		if(responce.optInt("role_id")==4){
			Intent intent2 = new Intent(getActivity(),
					NavigateActivityBuyer.class)
					.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
			getActivity().finishAffinity();
			startActivity(intent2);}
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
