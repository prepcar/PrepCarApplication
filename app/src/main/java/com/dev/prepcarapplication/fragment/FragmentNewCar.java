package com.dev.prepcarapplication.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.dev.prepcarapplication.CarPlanActivity;
import com.dev.prepcarapplication.IAmHesitantActivity;
import com.dev.prepcarapplication.IKnowWhatIWantActivity;
import com.dev.prepcarapplication.R;
import com.dev.prepcarapplication.baseClasses.BaseFragment;

public class FragmentNewCar extends BaseFragment{
	public static String TAG="NEWCAR";
	Button buton_iknow,buton_iam;
	ImageView back;
	ImageView icHome;
	public static FragmentNewCar newInstanse(Bundle bundle) {
		FragmentNewCar fragment = new FragmentNewCar();
		if (bundle != null) {
			fragment.setArguments(bundle);
		}
		return fragment;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.fragment_new_car,null);
		initUi(view);
		setListener();
		return view;
	}


	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.buton_iknow:
			startMyActivity(IKnowWhatIWantActivity.class, null);
			break;
		case R.id.buton_iam:
			startMyActivity(IAmHesitantActivity.class, null);
			break;
		case R.id.image_back:
			getActivity().finish();
			break;
			case R.id.icHome:
				startMyActivityTop(CarPlanActivity.class,null);
				getActivity().finish();
				break;
		default:
			break;
		}
	}

	@Override
	protected void initUi(View view) {
		// TODO Auto-generated method stub
		buton_iknow=(Button)view.findViewById(R.id.buton_iknow);
		buton_iam=(Button)view.findViewById(R.id.buton_iam);
		back=(ImageView)view.findViewById(R.id.image_back);
		icHome = (ImageView) view.findViewById(R.id.icHome);
	}

	@Override
	protected void setValueOnUi() {
	}

	@Override
	protected void setListener() {
		// TODO Auto-generated method stub
		buton_iam.setOnClickListener(this);
		buton_iknow.setOnClickListener(this);
		back.setOnClickListener(this);
		icHome.setOnClickListener(this);
	}

	@Override
	public boolean onBackPressedListener() {
		// TODO Auto-generated method stub
		return false;
	}

}
