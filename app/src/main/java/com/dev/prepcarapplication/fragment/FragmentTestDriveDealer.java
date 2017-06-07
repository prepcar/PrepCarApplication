package com.dev.prepcarapplication.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.dev.prepcarapplication.DealerTestDriveDetailActivity;
import com.dev.prepcarapplication.R;
import com.dev.prepcarapplication.adapter.DealerTestDriveAdapter;
import com.dev.prepcarapplication.baseClasses.BaseFragment;
import com.dev.prepcarapplication.baseClasses.Constants;
import com.dev.prepcarapplication.bean.TestDriveModel;
import com.dev.prepcarapplication.networkTask.ApiManager;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by this on 2/16/2017.
 */

public class FragmentTestDriveDealer extends BaseFragment {
    public static String TAG = "TEST DRIVE";
    ArrayList<TestDriveModel> arrayListItems;
    ListView mListNotifications;
    TextView mTvNoNotifications;


    public static FragmentTestDriveDealer newInstanse(Bundle bundle) {
        FragmentTestDriveDealer fragment = new FragmentTestDriveDealer();
        if (bundle != null) {
            fragment.setArguments(bundle);
        }

        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        View view = inflater.inflate(R.layout.fragment_test_drive_dealer, null);
        initUi(view);
        setListener();

        refresh();
        callServiceForNotifications();

        mListNotifications.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(), DealerTestDriveDetailActivity.class);
                intent.putExtra(Constants.testdrivedata,arrayListItems.get(position));
                startActivity(intent);
                getActivity().finish();

            }
        });
        return view;
    }

    private void callServiceForNotifications() {
        ApiManager.getInstance().getAllScheduledTestDrive(this);
    }

    @Override
    protected void initUi(View view) {
        arrayListItems = new ArrayList<>();
        mListNotifications = (ListView) view.findViewById(R.id.list_notifications);
        mTvNoNotifications = (TextView) view.findViewById(R.id.tv_no_notification);


        refresh();

    }

    @Override
    protected void setValueOnUi() {

    }

    @Override
    protected void setListener() {


    }


    @Override
    public boolean onBackPressedListener() {
        return false;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

        }
    }

    private void refresh() {
        mListNotifications.setAdapter(new DealerTestDriveAdapter(getActivity(), arrayListItems));

        if (arrayListItems.size() == 0) {
            mTvNoNotifications.setVisibility(View.VISIBLE);
        } else {
            mTvNoNotifications.setVisibility(View.GONE);
        }
    }


    public void setRespponce(JSONObject jObject) {
        arrayListItems.clear();
        Log.e(TAG, "setRespponce: coming");
        if (jObject.optInt("status") == 1 && jObject.optInt("dataflow") == 1) {
            JSONArray data = jObject.optJSONArray("data");
            for (int j = 0; j < data.length(); j++) {
                TestDriveModel model = new TestDriveModel();
                JSONObject object = data.optJSONObject(j);
                model.setTestdrive_id(object.optInt("testdrive_id"));
                model.setConfirm_status(object.optInt("confirm_status"));
                model.setDealer_id(object.optString("dealer_id"));
                model.setBuyer_id(object.optString("buyer_id"));
                model.setDeal_id(object.optString("deal_id"));
                model.setDate(object.optString("date"));
                model.setTime(object.optString("time"));
                model.setCar_id(object.optString("car_id"));
                model.setModel_year(object.optString("model_year"));
                model.setModel_type(object.optString("model_type"));
                model.setMake(object.optString("make"));
                model.setBuyer_name(object.optString("buyer_name"));
                model.setSingle_car_pic(object.optString("single_car_pic"));
                model.setAppointment_status(object.optInt("appointment_status"));

                arrayListItems.add(model);
            }
            refresh();
        }
    }

}
