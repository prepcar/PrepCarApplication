package com.dev.prepcarapplication.fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.dev.prepcarapplication.R;
import com.dev.prepcarapplication.adapter.CardAdapter;
import com.dev.prepcarapplication.baseClasses.BaseFragment;
import com.dev.prepcarapplication.baseClasses.Constants;
import com.dev.prepcarapplication.bean.NewMatchesBean;
import com.dev.prepcarapplication.networkTask.ApiManager;
import com.yuyakaido.android.cardstackview.CardStackView;
import com.yuyakaido.android.cardstackview.Direction;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;


/**
 * Created by Shubham on 2/6/2017.
 */

public class FragmentNewMatches extends BaseFragment implements CardStackView.CardStackEventListener{
    public  static  String TAG="NEWMACHES";
    ArrayList<String> arraylist;
    private CardStackView cardStackView;
    ArrayList<NewMatchesBean>  machesarray;
    CardAdapter adapter;
    ImageView refrechcard;
    static  String miles="",location="";
    boolean isClicked;

    public static FragmentNewMatches newInstanse(Bundle bundle) {
        FragmentNewMatches fragment = new FragmentNewMatches();
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

        View view = inflater.inflate(R.layout.fragment_new_maches, null);
        initUi(view);
        setListener();
       callingServicebyTag();
        return view;
    }
public  void callingServicebyTag(){

        callingServiceNewmatches();
}
    private void callingServiceNewmatches() {
        ApiManager.getInstance().getNewMatches(this);
    }

    @Override
    protected void initUi(View view) {
        refrechcard=(ImageView)view.findViewById(R.id.refrechcard);
        machesarray=new ArrayList<>();
        arraylist=new ArrayList<>();
        arraylist.add("Abkjshfj");arraylist.add("Abkjshfj");arraylist.add("Abkjshfj");arraylist.add("Abkjshfj");
        cardStackView = (CardStackView)view.findViewById(R.id.cardstack_view);
        cardStackView.setCardStackEventListener(this);
        setData();
    }

    @Override
    protected void setValueOnUi() {

    }

    @Override
    protected void setListener() {
        refrechcard.setOnClickListener(this);
    }

    @Override
    public boolean onBackPressedListener() {
        return false;
    }

    @Override
    public void onClick(View v) {
    switch (v.getId()){
    case R.id.refrechcard:
     callingServicebyTag();
        isClicked=true;
        break;
}
    }


    @Override
    public void onBeginSwipe(int index, Direction direction) {

    }

    @Override
    public void onEndSwipe(Direction direction) {
        cardStackView.getTopView().findViewById(R.id.item_card_stack_right_text).setAlpha(0);
        cardStackView.getTopView().findViewById(R.id.item_card_stack_left_text).setAlpha(0);
    }

    @Override
    public void onSwiping(float positionX) {
        TextView right = (TextView) cardStackView.getTopView().findViewById(R.id.item_card_stack_right_text);
        TextView left = (TextView) cardStackView.getTopView().findViewById(R.id.item_card_stack_left_text);
        if (positionX > 0) {
            right.setAlpha(positionX);
        } else if (positionX < 0) {
            left.setAlpha(-positionX);
        }
    }

    @Override
    public void onDiscarded(int index, Direction direction) {
      //  ToastCustomClass.showToast(getActivity(),"discarded");
    }

    @Override
    public void onTapUp(int index) {
        System.out.println("index==="+index);
      /*  Intent i=new Intent(getActivity(),NewMatcheDetailActivity.class);
        System.out.println("carid==="+machesarray.get(index).getCar_id());
        i.putExtra(Constants.catId,String.valueOf(machesarray.get(index).getCar_id()));
        startActivity(i);*/
    }

    public void setresponce(JSONObject jObject) {
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

            if(data.optJSONObject(j).optInt("rating")>2){
                bean.setSetRating(true);
            }else{
                bean.setSetRating(false);
            }
            machesarray.add(bean);
        }
        Log.i("arrsize===",String.valueOf(machesarray.size()));
        if(machesarray.size()>0){
            adapter.notifyDataSetChanged();
            refrechcard.setVisibility(View.VISIBLE);
            if (isClicked)
                setData();
        }else{
            refrechcard.setVisibility(View.GONE);
            showDialog("This is where dealers will send your car matches. Make sure you’ve sent your Carplan to dealers near you.");
        }
    }

    }

    private void setData() {
        adapter = new CardAdapter(getActivity(),machesarray,this);
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
