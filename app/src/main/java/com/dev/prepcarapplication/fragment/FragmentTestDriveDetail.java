package com.dev.prepcarapplication.fragment;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.dev.prepcarapplication.NavigateActivityDealer;
import com.dev.prepcarapplication.R;
import com.dev.prepcarapplication.TestDriveActivityDealer;
import com.dev.prepcarapplication.baseClasses.BaseFragment;
import com.dev.prepcarapplication.baseClasses.Constants;
import com.dev.prepcarapplication.bean.TestDriveModel;
import com.dev.prepcarapplication.networkTask.ApiManager;
import com.dev.prepcarapplication.utilities.ToastCustomClass;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Created by this on 2/22/2017.
 */

public class FragmentTestDriveDetail extends BaseFragment {
    public static String TAG = "Buyer Detail";
    ImageView buyerdetail_image,back;
    TextView buyerdetail_loc, buyerdetail_name, buyerdetail_deatil, buyerdetail_deatil1;
    Button buton_accept, buton_reschedule;
    ImageView icHome;
    public static TestDriveModel model;
    RadioGroup radio_group;
    LinearLayout layout_accept_reschedule;

    public static FragmentTestDriveDetail newInstanse(Bundle bundle) {
        FragmentTestDriveDetail fragment = new FragmentTestDriveDetail();
        model =(TestDriveModel)bundle.getSerializable(Constants.testdrivedata);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        View view = inflater.inflate(R.layout.fragment_test_drive_detail, null);
        initUi(view);
        setListener();

        radio_group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                if (checkedId==R.id.radio_sold){
                    callingServiceAppointMentStatus("1");
                }if (checkedId==R.id.radio_lost){
                    callingServiceAppointMentStatus("3");
                }if (checkedId==R.id.radio_pending){
                    callingServiceAppointMentStatus("2");
                }
            }
        });
        return view;
    }

    @Override
    protected void initUi(View view) {
        back=(ImageView)view.findViewById(R.id.back);
        buyerdetail_loc = (TextView) view.findViewById(R.id.buyerdetail_loc);
        buyerdetail_deatil1 = (TextView) view.findViewById(R.id.buyerdetail_detail1);
        buyerdetail_deatil = (TextView) view.findViewById(R.id.buyerdetail_detail);
        buyerdetail_name = (TextView) view.findViewById(R.id.buyerdetail_name);
        buyerdetail_image = (ImageView) view.findViewById(R.id.buyerdetail_image);
        buton_accept = (Button) view.findViewById(R.id.buton_accept);
        buton_reschedule = (Button) view.findViewById(R.id.buton_declined);
        icHome = (ImageView) view.findViewById(R.id.icHome);
        radio_group = (RadioGroup) view.findViewById(R.id.radio_group);
        layout_accept_reschedule = (LinearLayout) view.findViewById(R.id.layout_accept_reschedule);

        icHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startMyActivityTop(NavigateActivityDealer.class, null);
                getActivity().finish();
            }
        });

        buyerdetail_name.setText(model.getBuyer_name());
        buyerdetail_deatil1.setText("Car : "+model.getModel_year() + "-" + model.getMake() + "-" + model.getModel_type()
        +"\n\nDate : "+model.getDate()
        +"\n\nTime : "+model.getTime());
        if (!model.getSingle_car_pic().equals(""))
        Picasso.with(getActivity()).load(model.getSingle_car_pic()).placeholder(R.drawable.loading)
                .error(R.drawable.download).into(buyerdetail_image);

        if (model.getConfirm_status()==1){
            layout_accept_reschedule.setVisibility(View.GONE);
            radio_group.setVisibility(View.VISIBLE);
        }else{
            radio_group.setVisibility(View.GONE);
            layout_accept_reschedule.setVisibility(View.VISIBLE);
        }
        if (model.getAppointment_status()==1){
            radio_group.check(R.id.radio_sold);
        }
        if (model.getAppointment_status()==3){
            radio_group.check(R.id.radio_lost);
        }
        if (model.getAppointment_status()==2){
            radio_group.check(R.id.radio_pending);
        }

    }

    @Override
    protected void setValueOnUi() {

    }

    @Override
    protected void setListener() {
        buton_reschedule.setOnClickListener(this);
        buton_accept.setOnClickListener(this);
        back.setOnClickListener(this);

    }

    @Override
    public boolean onBackPressedListener() {
        return false;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.buton_accept:
                callingServiceTestDriveRequest("1");
                buton_accept.setVisibility(View.GONE);
                break;
            case R.id.buton_declined:
                callingServiceTestDriveRequest("2");
                break;
            case R.id.back:
                startMyActivity(TestDriveActivityDealer.class,null);
                getActivity().finish();
                break;
        }
    }

    private void callingServiceTestDriveRequest(String status) {
        ApiManager.getInstance().testDriveRequest(this,String.valueOf(model.getTestdrive_id()),status);
    }

    private void callingServiceAppointMentStatus(String status) {
        ApiManager.getInstance().testDriveAppointmentStatus(this,model.getDeal_id(),status);
    }


    public void setresponceDeclined(JSONObject jObject) {
        if (jObject.optInt("status") == 1 && jObject.optInt("dataFlow") == 1) {
            JSONArray response=jObject.optJSONArray("response");
            int status=response.optJSONObject(0).optInt("dealer_status");

        }
    }

    public void setResponseAppointment(JSONObject jObject) {
        if (jObject.optInt("status") == 1 && jObject.optInt("dataFlow") == 1) {
            ToastCustomClass.showToast(getActivity(),jObject.optString("message"));
        }
    }
}
