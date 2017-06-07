package com.dev.prepcarapplication.fragment;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.dev.prepcarapplication.R;
import com.dev.prepcarapplication.adapter.NewMatchesDetail_Adapter;
import com.dev.prepcarapplication.baseClasses.BaseFragment;
import com.dev.prepcarapplication.baseClasses.Constants;
import com.dev.prepcarapplication.bean.NewMatchesBean;
import com.dev.prepcarapplication.networkTask.ApiManager;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import me.relex.circleindicator.CircleIndicator;

/**
 * Created by this on 2/9/2017.
 */

public class FragmentNewMatcheDetail extends BaseFragment{
    public static String TAG="NEWMATCHESDETAIL";
    ViewPager newmatchespager;
    NewMatchesDetail_Adapter adapter;
    private ArrayList<Integer> ImagesArray;
    CircleIndicator indicator;
    ImageView image_back;
    static  String carId="0";
    ArrayList<NewMatchesBean> arrayList;
    public ImageView maches_carpic, cirImage,deletefav;
    TextView text_card3, text_adres, text_card, maches_miles, text_card1, text_card4, text_card5;
    RatingBar ratingbar;

    public static FragmentNewMatcheDetail newInstanse(Bundle bundle) {
        FragmentNewMatcheDetail fragment = new FragmentNewMatcheDetail();
        if (bundle != null) {
            fragment.setArguments(bundle);
            if (bundle.containsKey(Constants.catId)) {
                carId = bundle.getString(Constants.catId);
            } else {
                carId = "0";
            }
        } else {
            carId = "0";
        }
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        View view = inflater.inflate(R.layout.fragment_new_matche_detail, null);
        initUi(view);
        setListener();
        callingServiceDetail();
        return view;
    }

    private void callingServiceDetail() {
        System.out.println("cariddetail=="+carId);
        ApiManager.getInstance().getDetailNewmaches(this,carId);
    }

    @Override
    protected void initUi(View view) {
        ratingbar=(RatingBar)view.findViewById(R.id.ratingbar);
        arrayList=new ArrayList<>();
        image_back=(ImageView)view.findViewById(R.id.image_back);
        indicator=(CircleIndicator)view.findViewById(R.id.indicator);
        ImagesArray = new ArrayList<Integer>();
        newmatchespager=(ViewPager)view.findViewById(R.id.newmatchespager);
        ImagesArray.add(R.drawable.download);
      //  ImagesArray.add(R.drawable.buyer_box_2);
      //  ImagesArray.add(R.drawable.buyer_box_3);
        newmatchespager.setAdapter(new NewMatchesDetail_Adapter(getActivity(), ImagesArray));
        indicator.setViewPager(newmatchespager);
        this.maches_carpic = (ImageView) view.findViewById(R.id.maches_carpic);
        this.cirImage = (ImageView) view.findViewById(R.id.cirImage);
        this.text_card3 = (TextView) view.findViewById(R.id.text_card3);
        this.text_adres = (TextView) view.findViewById(R.id.text_adres);
        this.text_card = (TextView) view.findViewById(R.id.text_card);
        this.maches_miles = (TextView) view.findViewById(R.id.maches_miles);
        this.text_card4 = (TextView) view.findViewById(R.id.text_card4);
        this.text_card1 = (TextView) view.findViewById(R.id.text_card1);
        this.deletefav=(ImageView)view.findViewById(R.id.deletefav);
    }

    @Override
    protected void setValueOnUi() {

    }

    @Override
    protected void setListener() {
        image_back.setOnClickListener(this);
    }

    @Override
    public boolean onBackPressedListener() {
        return false;
    }

    @Override
    public void onClick(View v) {
    switch (v.getId()){
    case R.id.image_back:
        getActivity().finish();
        break;
}
    }

    public void setresponce(JSONObject jObject) {
        if(jObject.optInt("status")==1 && jObject.optInt("dataFlow")==1){
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
                bean.setRating(data.optJSONObject(j).optInt("rating"));
                arrayList.add(bean);
            }
            Log.i("arrsize===",String.valueOf(arrayList.size()));
        }
        setData();
    }

    private void setData() {
        text_card3.setText(arrayList.get(0).getDealership_name());
        text_adres.setText(arrayList.get(0).getState() + "," + arrayList.get(0).getCountry());
        text_card.setText(arrayList.get(0).getExterior_color());
        maches_miles.setText(arrayList.get(0).getStock_number());
        text_card1.setText(arrayList.get(0).getModel_year() + " " + arrayList.get(0).getMake() + " " + arrayList.get(0).getMake());
        ratingbar.setRating(arrayList.get(0).getRating());
    }
}
