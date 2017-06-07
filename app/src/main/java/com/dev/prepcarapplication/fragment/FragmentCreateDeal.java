package com.dev.prepcarapplication.fragment;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.dev.prepcarapplication.NavigateActivityDealer;
import com.dev.prepcarapplication.R;
import com.dev.prepcarapplication.baseClasses.BaseFragment;
import com.dev.prepcarapplication.baseClasses.Constants;
import com.dev.prepcarapplication.networkTask.ApiManager;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Created by this on 2/24/2017.
 */

public class FragmentCreateDeal extends BaseFragment {
    public static String TAG="DEAL";
    static  String dealid="",buyerId="";
    Button deal_sendbuton;
    TextView deal_deal,deal_vichicle,deal_paymentvalue,deal_carname,deal_cardeatil,
            deal_intrsrate,deal_downpayment,deal_tradeinvalue,deal_amountowed,deal_month;
    ImageView car_image,back;
    TextView deal_dis;
    Dialog dialog;
    String buyername="";

    public static FragmentCreateDeal newInstanse(Bundle bundle) {
        FragmentCreateDeal fragment = new FragmentCreateDeal();
        if (bundle != null) {
            fragment.setArguments(bundle);
            dealid=bundle.getString("dealid");
            buyerId=bundle.getString(Constants.buyerid);
        }else{
            buyerId="";
            dealid="";
        }
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        View view = inflater.inflate(R.layout.fragment_create_deal, null);
        initUi(view);
        setListener();
        callingServiceGetVichlelist();
        return view;
    }

    private void callingServiceGetVichlelist() {
        ApiManager.getInstance().getdealdetailt(this,dealid);
    }

    @Override
    protected void initUi(View view) {
        deal_month=(TextView)view.findViewById(R.id.deal_month);
        deal_intrsrate=(TextView)view.findViewById(R.id.deal_intrsrate);
        deal_downpayment=(TextView)view.findViewById(R.id.deal_downpayment);
        deal_tradeinvalue=(TextView)view.findViewById(R.id.deal_tradeinvalue);
        deal_amountowed=(TextView)view.findViewById(R.id.deal_amountowed);
        deal_dis=(TextView)view.findViewById(R.id.deal_dis);
        back=(ImageView)view.findViewById(R.id.back);
        car_image=(ImageView)view.findViewById(R.id.car_image);
        deal_cardeatil=(TextView)view.findViewById(R.id.deal_cardeatil);
        deal_carname=(TextView)view.findViewById(R.id.deal_carname);
        deal_deal=(TextView)view.findViewById(R.id.deal_deal);
        deal_paymentvalue=(TextView)view.findViewById(R.id.deal_paymentvalue);
        deal_vichicle=(TextView)view.findViewById(R.id.deal_vichicle);
        deal_sendbuton=(Button)view.findViewById(R.id.deal_sendbuton);

        view.findViewById(R.id.icHome).setOnClickListener(new View.OnClickListener() {
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
        deal_sendbuton.setOnClickListener(this);
        back.setOnClickListener(this);
    }

    @Override
    public boolean onBackPressedListener() {
        return false;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.deal_sendbuton:
                callingServicesendDeal();

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

    private void callingServicesendDeal() {
        ApiManager.getInstance().senddealdetailt(this,dealid,buyerId);
    }

    public void setresponce(JSONObject jObject) {
        if(jObject.optInt("status")==1 &&jObject.optInt("dataFlow")==1 ){
            JSONArray response=jObject.optJSONArray("response");
            if (!response.optJSONObject(0).optString("single_car_pic").equals("")) {
                Picasso.with(getActivity()).load(response.optJSONObject(0).optString("single_car_pic")).into(car_image);
            }
            deal_carname.setText(response.optJSONObject(0).optString("make").trim());
            deal_cardeatil.setText(response.optJSONObject(0).optString("description"));
            deal_vichicle.setText(response.optJSONObject(0).optString("model_year").trim()+" "+response.optJSONObject(0).optString("make").trim()+" "+response.optJSONObject(0).optString("model_type").trim());
            deal_paymentvalue.setText("$"+response.optJSONObject(0).optString("price"));
            deal_deal.setText(response.optJSONObject(0).optString("lease_deal_trems"));
            deal_dis.setText(response.optJSONObject(0).optString("lease_deal_disclaimer"));

            deal_intrsrate.setText(response.optJSONObject(0).optString("interest_rate")+" %");
            deal_downpayment.setText("$ "+response.optJSONObject(0).optString("down_payment"));
            deal_tradeinvalue.setText("$ "+response.optJSONObject(0).optString("trade_in_value"));
            deal_amountowed.setText("$ "+response.optJSONObject(0).optString("amount_on_trace"));
            deal_month.setText(response.optJSONObject(0).optString("months"));
            buyername = response.optJSONObject(0).optString("buyername");
        }
    }

    public void setresponceFinal(JSONObject jObject) {
        if(jObject.optInt("dataflow")==1 && jObject.optInt("status")==1) {
            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    showdialogTestdrive(buyername);
                }
            });

        }
    }
    public void showdialogTestdrive(String buyer) {

        dialog = new Dialog(getActivity());
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.setContentView(R.layout.dialog_create_deal);
        dialog.setCancelable(false);
        Button dialog_savebut = (Button) dialog.findViewById(R.id.cancle);
        TextView buyerName = (TextView)dialog.findViewById(R.id.descText1);
        buyerName.setText(buyer);
        dialog_savebut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                Intent intent2 = new Intent(getActivity(),
                        NavigateActivityDealer.class)
                        .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                getActivity().finishAffinity();
                startActivity(intent2);
            }
        });
        final Button dialog_cancel=(Button)dialog.findViewById(R.id.login);
        dialog_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                getActivity().finish();
            }
        });

        dialog.show();
    }

    @Override
    public void onResume() {
        super.onResume();
        if (dialog!=null){
            dialog.dismiss();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (dialog!=null){
            dialog.dismiss();
        }
    }
}
