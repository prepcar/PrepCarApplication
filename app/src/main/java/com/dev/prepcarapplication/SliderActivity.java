package com.dev.prepcarapplication;

import com.dev.prepcarapplication.baseClasses.BaseFragmentActivity;
import com.dev.prepcarapplication.fragment.FragmentSlider;

import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class SliderActivity extends BaseFragmentActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_container);
		Intent i = getIntent();
		Bundle extras = i.getExtras();
		replaceFragement(FragmentSlider.newInstanse(extras),FragmentSlider.TAG);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.slider, menu);
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
