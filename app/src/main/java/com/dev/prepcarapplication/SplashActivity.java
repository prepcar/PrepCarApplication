package com.dev.prepcarapplication;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.Signature;
import android.os.Bundle;
import android.os.Handler;
import android.util.Base64;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.dev.prepcarapplication.baseClasses.BaseFragmentActivity;
import com.dev.prepcarapplication.baseClasses.Constants;
import com.dev.prepcarapplication.preference.MySharedPreferences;
import com.google.firebase.iid.FirebaseInstanceId;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


public class SplashActivity extends BaseFragmentActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash);
		Log.d("Firebase", "token "+ FirebaseInstanceId.getInstance().getToken());
		keyHash();

		 new Handler().postDelayed(new Runnable() {
             
	            // Using handler with postDelayed called runnable run method
	  
	            @Override
	            public void run() {
	            	if(MySharedPreferences.getInstance().getBoolean(SplashActivity.this, Constants.ISSLIDER, false) && MySharedPreferences.getInstance().getBoolean(SplashActivity.this, Constants.ISREMEMBER, false)){
	            		if(MySharedPreferences.getInstance().getInteger(SplashActivity.this, Constants.role_id, 0)==3){
		            		Intent i = new Intent(SplashActivity.this, NavigateActivityDealer .class);
			                startActivity(i);
			                finish();	
		            	}
		            	else {
		            		Intent i1 = new Intent(SplashActivity.this, NavigateActivityBuyer .class);
		            		startActivity(i1);
		            		finish();	
		            	}	
	            	}
	            else if(MySharedPreferences.getInstance().getBoolean(SplashActivity.this, Constants.ISSLIDER, false)){
	            	Intent i1 = new Intent(SplashActivity.this, BeforeLoginActivity .class);
            		startActivity(i1);
            		finish();		
	            }
	            	else {
	            		Intent i = new Intent(SplashActivity.this, LogInSignUpActivity.class);
	            		startActivity(i);
	            		finish();}
	           }
	        }, 3*1000); // wait for 3 seconds
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.splash, menu);
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
	public String keyHash() {
		String keyhash="";
		try {
		    PackageInfo info = getPackageManager().getPackageInfo(
		    getPackageName(), PackageManager.GET_SIGNATURES);
		    for (Signature signature : info.signatures) {
		        MessageDigest md = MessageDigest.getInstance("SHA");
		        md.update(signature.toByteArray());
		        Log.d("KeyHash:", Base64.encodeToString(md.digest(), Base64.DEFAULT));
		        keyhash=Base64.encodeToString(md.digest(), Base64.DEFAULT);
		    }
		} catch (NameNotFoundException e) {
		} catch (NoSuchAlgorithmException e) {
		}
		return keyhash;
	}
}
