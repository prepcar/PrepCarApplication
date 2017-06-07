package com.dev.prepcarapplication.fragment;

import java.util.ArrayList;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;

import com.dev.prepcarapplication.R;
import com.dev.prepcarapplication.adapter.MainScreenListAdapter;
import com.dev.prepcarapplication.baseClasses.BaseFragment;

public class FragmentMainScreen extends BaseFragment {
	private static ViewPager mPager;
	private static int currentPage = 0;
	private static int NUM_PAGES = 0;
	public static String TAG = "MAINSCREEN";
	ListView listView;
	 String[] from = new String[] {"rowid", "col_1", "col_2", "col_3","rowid", "col_1", "col_2", "col_3"};
		private static final Integer[] IMAGES = { R.drawable.ic_launcher,
			R.drawable.ic_launcher, R.drawable.ic_launcher,
			R.drawable.ic_launcher ,R.drawable.ic_launcher,
			R.drawable.ic_launcher, R.drawable.ic_launcher,
			R.drawable.ic_launcher };
		
		private ArrayList<Integer> ImagesArray = new ArrayList<Integer>();
	public static FragmentMainScreen newInstanse(Bundle bundle) {
		FragmentMainScreen fragment = new FragmentMainScreen();
		if (bundle != null) {
			fragment.setArguments(bundle);
		}
		return fragment;
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.fragment_main_screen, null);
		initUi(view);
		init(view);
		setListener();
		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				System.out.println("poslist"+position);
				Toast.makeText(getActivity(), String.valueOf(position), Toast.LENGTH_SHORT).show();
			}
		});
		return view;
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub

	}

	@Override
	protected void initUi(View view) {
		// TODO Auto-generated method stub
		listView=(ListView)view.findViewById(R.id.listview);
		
	}

	@Override
	protected void setValueOnUi() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void setListener() {
		// TODO Auto-generated method stub
		 MainScreenListAdapter adapter=new MainScreenListAdapter(getActivity(), from,ImagesArray);
		 listView.setAdapter(adapter);
	}

	@Override
	public boolean onBackPressedListener() {
		// TODO Auto-generated method stub
		return false;
	}
	private void init(View view) {
		for (int i = 0; i < IMAGES.length; i++)
			ImagesArray.add(IMAGES[i]);

	}

}
