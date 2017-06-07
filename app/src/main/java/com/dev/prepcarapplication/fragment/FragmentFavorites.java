package com.dev.prepcarapplication.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dev.prepcarapplication.R;

import com.dev.prepcarapplication.adapter.FavoritesAdapter;
import com.dev.prepcarapplication.baseClasses.BaseFragment;
import com.dev.prepcarapplication.bean.NewMatchesBean;
import com.dev.prepcarapplication.networkTask.ApiManager;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by this on 2/6/2017.
 */

public class FragmentFavorites extends BaseFragment {
    public static String TAG = "FAVORITES";
    RecyclerView fav_list;
    FavoritesAdapter adapter;
    ArrayList<NewMatchesBean> arrayListItems;

    public static FragmentFavorites newInstanse(Bundle bundle) {
        FragmentFavorites fragment = new FragmentFavorites();
        if (bundle != null) {
            fragment.setArguments(bundle);
        }
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        View view = inflater.inflate(R.layout.fragment_favorites, null);
        initUi(view);
        setListener();
        callingServiceFavorites();
        return view;
    }

    private void callingServiceFavorites() {
        ApiManager.getInstance().getFavorites(this);
    }

    @Override
    protected void initUi(View view) {
        arrayListItems = new ArrayList<>();
        fav_list = (RecyclerView) view.findViewById(R.id.fav_list);
        fav_list.setHasFixedSize(true);
        fav_list.setLayoutManager(new LinearLayoutManager(getActivity()));
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

    }

    public void setresponceFav(JSONObject jObject) {
        if (jObject.optInt("status") == 1 && jObject.optInt("dataflow") == 1) {
            JSONArray data = jObject.optJSONArray("data");
            for (int j = 0; j < data.length(); j++) {
                NewMatchesBean bean = new NewMatchesBean();
                ArrayList<String> arrayListImage = new ArrayList<>();
                bean.setCar_id(data.optJSONObject(j).optInt("car_id"));
                bean.setDeal_id(data.optJSONObject(j).optInt("deal_id"));
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
                bean.setSingle_car_pic(data.optJSONObject(j).optString("single_car_pic"));
                bean.setFirst_name(data.optJSONObject(j).optString("first_name"));
                bean.setLast_name(data.optJSONObject(j).optString("last_name"));
                bean.setDealerPhone(data.optJSONObject(j).optString("phone"));
                bean.setDescription(data.optJSONObject(j).optString("description"));
                bean.setTransmission(data.optJSONObject(j).optString("transmission"));
                bean.setMonths(data.optJSONObject(j).optString("months"));
                bean.setInterestRate(data.optJSONObject(j).optString("interest_rate"));
                bean.setSalesTaxRate(data.optJSONObject(j).optString("sales_taxrate"));
                bean.setDownPayment(data.optJSONObject(j).optString("down_payment"));
                bean.setTradeInValue(data.optJSONObject(j).optString("trade_in_value"));
                bean.setAmountOnTrade(data.optJSONObject(j).optString("amount_on_trace"));
                bean.setTerms(data.optJSONObject(j).optString("lease_deal_trems"));
                bean.setDisclaimer(data.optJSONObject(j).optString("lease_deal_disclaimer"));
                bean.setBestPrice(data.optJSONObject(j).optString("price"));
                bean.setCoolFeatures(data.optJSONObject(j).optJSONArray("features"));
                bean.setNickname(data.optJSONObject(j).optString("nickname"));
                bean.setMonthlyPayment(data.optJSONObject(j).optString("monthly_payment"));
                bean.setCarplanId(data.optJSONObject(j).optString("carplan_id"));
                bean.setTest_drive_status(data.optJSONObject(j).optString("test_drive_status"));
                bean.setDeal_expire(data.optJSONObject(j).optInt("deal_expire"));
                bean.setRating_id(data.optJSONObject(j).optInt("rating_id"));
                bean.setClicked(false);

                JSONArray jsonArray = data.optJSONObject(j).optJSONArray("car_pic");
                for (int i = 0; i < jsonArray.length(); i++) {

                    arrayListImage.add(jsonArray.optString(i));
                }
                if (arrayListImage.size() == 0) {
                    arrayListImage.add(data.optJSONObject(j).optString("single_car_pic"));
                }
                bean.setCarImages(arrayListImage);
                arrayListItems.add(bean);
            }
            Log.i("arrsize===", String.valueOf(arrayListItems.size()));
        }
        setData();
    }

    private void setData() {
        adapter = new FavoritesAdapter(getActivity(), arrayListItems, getActivity());
        fav_list.setAdapter(adapter);
    }
}