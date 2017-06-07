package com.dev.prepcarapplication;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.dev.prepcarapplication.baseClasses.BaseFragmentActivity;
import com.dev.prepcarapplication.fragment.FragmentNavigateScreenBuyer;

public class NavigateScreenActivity extends BaseFragmentActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_container);
		getSupportActionBar().hide();
		replaceFragement(FragmentNavigateScreenBuyer.newInstanse(null),FragmentNavigateScreenBuyer.TAG);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
	//	getMenuInflater().inflate(R.menu.navigate_screen, menu);
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
}
