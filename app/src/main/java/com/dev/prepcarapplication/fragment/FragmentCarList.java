package com.dev.prepcarapplication.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import com.dev.prepcarapplication.DealerNewCarActivity;
import com.dev.prepcarapplication.R;
import com.dev.prepcarapplication.adapter.CarListAdapter;
import com.dev.prepcarapplication.baseClasses.BaseFragment;
import com.dev.prepcarapplication.baseClasses.Constants;
import com.dev.prepcarapplication.bean.BuyerList;
import com.dev.prepcarapplication.networkTask.ApiManager;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class FragmentCarList extends BaseFragment  {
	public static String TAG = "PROFILE";
	Button buton_addnewcar;
	 static String roleId="";
	 ListView carlist_listview;
	 CarListAdapter adapter;
	 ArrayList<BuyerList> arrayList;

	public static FragmentCarList newInstanse(Bundle bundle) {
		FragmentCarList fragment = new FragmentCarList();
		if (bundle != null) {
			fragment.setArguments(bundle);
			if(bundle.containsKey(Constants.type)){
				roleId=bundle.getString(Constants.type);
			}
			else{
				roleId="";
			}
		}else{
			roleId="";
		}
		return fragment;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.fragment_car_list, null);
		initUi(view);
		setListener();
		callingServiceCralisting();
		return view;
	}

	private void callingServiceCralisting() {
		// TODO Auto-generated method stub
		ApiManager.getInstance().getCarList(this);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.buton_addnewcar:
			Intent intent=new Intent(getActivity(),DealerNewCarActivity.class);
			intent.putExtra(Constants.type, roleId);
			intent.putExtra("classtype","carlist");
			startActivity(intent);
			break;

		default:
			break;
		}
	}

	@Override
	protected void initUi(View view) {
		// TODO Auto-generated method stub
		buton_addnewcar = (Button) view.findViewById(R.id.buton_addnewcar);
		carlist_listview=(ListView)view.findViewById(R.id.carlist_listview);
		arrayList=new ArrayList<BuyerList>();
	}

	@Override
	protected void setValueOnUi() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void setListener() {
		// TODO Auto-generated method stub
		buton_addnewcar.setOnClickListener(this);
	}

	@Override
	public boolean onBackPressedListener() {
		// TODO Auto-generated method stub
		return false;
	}

	public void setrespponce(JSONObject jObject) {
		// TODO Auto-generated method stub
		if(jObject.optInt("status")==1 && jObject.optInt("dataFlow")==1){
			JSONArray responce=jObject.optJSONArray("response");
			for (int i = 0; i < responce.length(); i++) {
				BuyerList bean=new BuyerList();
				bean.setCar_pic(responce.optJSONObject(i).optString("car_pic"));
				bean.setMake(responce.optJSONObject(i).optString("make"));
				bean.setModel_type(responce.optJSONObject(i).optString("model_type"));
				bean.setModel_year(responce.optJSONObject(i).optString("model_year"));
				bean.setSinglepic(responce.optJSONObject(i).optString("single_car_pic"));
				bean.setCarId(String.valueOf(responce.optJSONObject(i).optInt("car_id")));
				arrayList.add(bean);
			}
			setdataOnlist();
		}
	}

	private void setdataOnlist() {
		// TODO Auto-generated method stub
		adapter=new CarListAdapter(getActivity(), arrayList, true,getActivity());
		carlist_listview.setAdapter(adapter);
	}

}
