package com.dev.prepcarapplication.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.dev.prepcarapplication.AcceptBuyerRequestActivity;
import com.dev.prepcarapplication.NavigateActivityDealer;
import com.dev.prepcarapplication.R;
import com.dev.prepcarapplication.baseClasses.BaseFragment;
import com.dev.prepcarapplication.baseClasses.Constants;
import com.dev.prepcarapplication.networkTask.ApiManager;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Created by this on 2/22/2017.
 */

public class FragmentDealerBuyerDetail extends BaseFragment {
    static String buyerid = "",carId="";
    public static String TAG = "Buyer Detail";
    ImageView buyerdetail_image,back;
    TextView buyerdetail_loc, buyerdetail_name, buyerdetail_deatil, buyerdetail_deatil1;
    Button buton_accept, buton_declined;
    ImageView icHome;

    public static FragmentDealerBuyerDetail newInstanse(Bundle bundle) {
        FragmentDealerBuyerDetail fragment = new FragmentDealerBuyerDetail();
        if (bundle != null) {
            fragment.setArguments(bundle);
            if (bundle.containsKey(Constants.buyerid)) {
                buyerid = bundle.getString(Constants.buyerid);
                carId = bundle.getString(Constants.catId);
            } else {
                buyerid = "";
                carId="";
            }
        } else {
            buyerid = "";
            carId="";
        }
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        View view = inflater.inflate(R.layout.fragment_dealer_buyer_detail, null);
        initUi(view);
        setListener();
        callingerviceBuyerDetail();
        return view;
    }

    private void callingerviceBuyerDetail() {
        ApiManager.getInstance().getbuyerdeatil(this, buyerid);
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
        buton_declined = (Button) view.findViewById(R.id.buton_declined);
        icHome = (ImageView) view.findViewById(R.id.icHome);

        icHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startMyActivityTop(NavigateActivityDealer.class, null);
                getActivity().finish();
            }
        });
    }

    @Override
    protected void setValueOnUi() {

    }

    @Override
    protected void setListener() {
        buton_declined.setOnClickListener(this);
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
                callingServiceDeclined("1");
                buton_accept.setVisibility(View.GONE);
                break;
            case R.id.buton_declined:
                callingServiceDeclined("2");
                break;
            case R.id.back:
                Intent intent2 = new Intent(getActivity(),
                        NavigateActivityDealer.class)
                        .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                getActivity().finishAffinity();
                startActivity(intent2);
                break;
        }
    }

    private void callingServiceDeclined(String status) {
        ApiManager.getInstance().DeclinedBuyer(this,buyerid,status,carId);
    }

    public void setresponce(JSONObject jObject) {
        if (jObject.optInt("status") == 1 && jObject.optInt("dataFlow") == 1) {
            JSONArray responce = jObject.optJSONArray("response");
            if (!responce.optJSONObject(0).optString("profile_picture").equals("")) {
                Picasso.with(getActivity()).load(responce.optJSONObject(0).optString("profile_picture")).into(buyerdetail_image);
            }
            buyerdetail_name.setText(responce.optJSONObject(0).optString("first_name") + " " + responce.optJSONObject(0).optString("last_name"));
            buyerdetail_loc.setText(responce.optJSONObject(0).optString("city") + " " + responce.optJSONObject(0).optString("state"));
            buyerdetail_deatil1.setText(responce.optJSONObject(0).optString("shopping_notes"));
        }
    }

    public void setresponceDeclined(JSONObject jObject) {
        if (jObject.optInt("status") == 1 && jObject.optInt("dataFlow") == 1) {
            JSONArray response=jObject.optJSONArray("response");
            int status=response.optJSONObject(0).optInt("dealer_status");
            if(status==2){
                Intent intent2 = new Intent(getActivity(),
                        NavigateActivityDealer.class)
                        .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                getActivity().finishAffinity();
                startActivity(intent2);}
            else if(status==1){
                Intent intent=new Intent(getActivity(), AcceptBuyerRequestActivity.class);
                intent.putExtra(Constants.buyerid,buyerid);
                startActivity(intent);
            }
        }
    }
}
