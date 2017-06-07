package com.dev.prepcarapplication.fragment;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.dev.prepcarapplication.R;
import com.dev.prepcarapplication.adapter.DealerFavouriteAdapter;
import com.dev.prepcarapplication.baseClasses.BaseFragment;
import com.dev.prepcarapplication.baseClasses.Constants;
import com.dev.prepcarapplication.bean.NewMatchesBean;
import com.dev.prepcarapplication.networkTask.ApiManager;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by this on 2/22/2017.
 */

public class FragmentAcceptBuyerFavorites extends BaseFragment {

    public  static  String TAG="ACCEPT_BUYER_FAVOURITE";
    private ListView cardStackView;
    ArrayList<NewMatchesBean> machesarray;
    DealerFavouriteAdapter adapter;
    TextView tvNoCars;
    static  String miles="",location="";

    public static FragmentAcceptBuyerFavorites newInstanse(Bundle bundle) {
        FragmentAcceptBuyerFavorites fragment = new FragmentAcceptBuyerFavorites();
        if (bundle != null) {
            fragment.setArguments(bundle);
            miles=bundle.getString(Constants.miles);
            location=bundle.getString(Constants.location);
        }else{
            miles="";
            location="";
        }
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        View view = inflater.inflate(R.layout.fragment_sent, null);
        initUi(view);
        setListener();
        callingServicebyTag();
        return view;
    }
    public  void callingServicebyTag(){
        //if(miles.equals("") && location.equals("")){
        callingServiceNewmatches();
//}
        //else{
        //    callingFindDealer();
        //}
    }
    private void callingServiceNewmatches() {
        ApiManager.getInstance().getDealerFavourite(this);
    }

    @Override
    protected void initUi(View view) {
        tvNoCars=(TextView)view.findViewById(R.id.tv_no_cars);
        machesarray=new ArrayList<NewMatchesBean>();
        cardStackView = (ListView) view.findViewById(R.id.cardstack_view);
        //cardStackView.setCardStackEventListener(this);

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
        switch (v.getId()){

        }
    }
    static void makeToast(Context ctx, String s){
        Toast.makeText(ctx, s, Toast.LENGTH_SHORT).show();
    }


    public void setresponceFav(JSONObject jObject) {
//        ToastCustomClass.showToast(getActivity(),"responce");
        machesarray.clear();
        if(jObject.optInt("status")==1 && jObject.optInt("dataflow")==1){
            JSONArray data=jObject.optJSONArray("data");
            for (int j = 0; j < data.length(); j++) {
                NewMatchesBean bean=new NewMatchesBean();
                bean.setCar_id(data.optJSONObject(j).optInt("car_id"));
                bean.setCar_pic(data.optJSONObject(j).optString("car_pic"));
                bean.setProfile_picture(data.optJSONObject(j).optString("profile_picture"));
                bean.setDealership_name(data.optJSONObject(j).optString("dealership_name"));
                bean.setCity(data.optJSONObject(j).optString("city"));
                bean.setState(data.optJSONObject(j).optString("state"));
                bean.setCountry(data.optJSONObject(j).optString("country"));
                bean.setModel_year(data.optJSONObject(j).optString("model_year"));
                bean.setMake(data.optJSONObject(j).optString("make"));
                bean.setModel_type(data.optJSONObject(j).optString("model_type"));
                bean.setExterior_color(data.optJSONObject(j).optString("exterior_color"));
                bean.setStock_number(data.optJSONObject(j).optString("stock_number"));
                bean.setDealer_id(data.optJSONObject(j).optInt("dealer_id"));
                bean.setDeal_id(data.optJSONObject(j).optInt("deal_id"));
                bean.setRating(data.optJSONObject(j).optInt("rating"));
                bean.setFirst_name(data.optJSONObject(j).optString("first_name"));
                bean.setLast_name(data.optJSONObject(j).optString("last_name"));
                bean.setSingle_car_pic(data.optJSONObject(j).optString("single_car_pic"));
                bean.setDescription(data.optJSONObject(j).optString("description"));
                bean.setTransmission(data.optJSONObject(j).optString("transmission"));
                bean.setBestPrice(data.optJSONObject(j).optString("price"));
                bean.setCoolFeatures(data.optJSONObject(j).optJSONArray("features"));
                bean.setCarplanId(String.valueOf(data.optJSONObject(j).optString("carplan_id")));
                bean.setNickname(data.optJSONObject(j).optString("nickname"));
                bean.setMonthlyPayment(data.optJSONObject(j).optString("monthly_payment"));
                bean.setDate(data.optJSONObject(j).optString("date"));

                if(data.optJSONObject(j).optInt("rating")>2){
                    bean.setSetRating(true);
                }else{
                    bean.setSetRating(false);
                }
                machesarray.add(bean);
            }
            Log.i("arrsize===",String.valueOf(machesarray.size()));
            if(machesarray.size()>0){
                tvNoCars.setVisibility(View.GONE);
                setData();
            }else{
                tvNoCars.setVisibility(View.VISIBLE);
                //showDialog("This is where you send car matches for buyer's carplan. Make sure you’ve sent your Matches to buyer.");
            }
        }

    }

    private void setData() {
        adapter = new DealerFavouriteAdapter(getActivity(),machesarray);
        cardStackView.setAdapter(adapter);
    }
    public void showDialog(String msg){
        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage(msg)
                .setCancelable(false)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        AlertDialog alert = builder.create();
                        alert.dismiss();
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();
    }

}
