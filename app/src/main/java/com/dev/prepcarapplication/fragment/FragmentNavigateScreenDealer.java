package com.dev.prepcarapplication.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.dev.prepcarapplication.AcceptBuyerRequestActivity;
import com.dev.prepcarapplication.DealerBuyerDetailActivity;
import com.dev.prepcarapplication.NotificationActivityBuyer;
import com.dev.prepcarapplication.ProfileActivityDealer;
import com.dev.prepcarapplication.R;
import com.dev.prepcarapplication.TestDriveActivityDealer;
import com.dev.prepcarapplication.adapter.BuyerListAdapter;
import com.dev.prepcarapplication.baseClasses.BaseFragment;
import com.dev.prepcarapplication.baseClasses.Constants;
import com.dev.prepcarapplication.bean.BuyeListBean;
import com.dev.prepcarapplication.networkTask.ApiManager;
import com.dev.prepcarapplication.preference.MySharedPreferences;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class FragmentNavigateScreenDealer extends BaseFragment {
    public static String TAG = "NavigatescreenDealer";
    ListView dealerList;
    RelativeLayout lin_menu, lin_search, lin_carplan, lin_chat, lin_profile;
    ImageView image_menu, image_search, image_carplan, image_chat,image_dot, image_profile;
    ArrayList<BuyeListBean> buyerLists;

    public static FragmentNavigateScreenDealer newInstanse(Bundle bundle) {
        FragmentNavigateScreenDealer fragment = new FragmentNavigateScreenDealer();
        if (bundle != null) {
            fragment.setArguments(bundle);
        }
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        View view = inflater.inflate(R.layout.fragment_navigate_sacreen_dealer, null);
        initUi(view);
        setListener();
        callingServiceGetBuyerList();
        return view;
    }

    private void callingServiceGetBuyerList() {
        ApiManager.getInstance().getBuyerList(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.lin_menu:
                lin_menu.setBackgroundColor(Color.parseColor("#c4c4c4"));
                image_menu.setBackgroundResource(R.drawable.icon_white_home);

                lin_search.setBackgroundColor(Color.parseColor("#FFFFFF"));
                image_search.setBackgroundResource(R.drawable.icon_calender);

                lin_chat.setBackgroundColor(Color.parseColor("#FFFFFF"));
                image_chat.setBackgroundResource(R.drawable.icon_notification);

                lin_profile.setBackgroundColor(Color.parseColor("#FFFFFF"));
                image_profile.setBackgroundResource(R.drawable.icon_user);
                break;
            case R.id.lin_search:
                lin_search.setBackgroundColor(Color.parseColor("#c4c4c4"));
                image_search.setBackgroundResource(R.drawable.icon_white_calender);

                lin_menu.setBackgroundColor(Color.parseColor("#FFFFFF"));
                image_menu.setBackgroundResource(R.drawable.icon_home);

                lin_chat.setBackgroundColor(Color.parseColor("#FFFFFF"));
                image_chat.setBackgroundResource(R.drawable.icon_notification);

                lin_profile.setBackgroundColor(Color.parseColor("#FFFFFF"));
                image_profile.setBackgroundResource(R.drawable.icon_user);

                startMyActivity(TestDriveActivityDealer.class,null);
                getActivity().finish();
                break;
            case R.id.lin_carplan:
                break;
            case R.id.lin_chat:
                lin_chat.setBackgroundColor(Color.parseColor("#c4c4c4"));
                image_chat.setBackgroundResource(R.drawable.icon_white_notification);

                lin_search.setBackgroundColor(Color.parseColor("#FFFFFF"));
                image_search.setBackgroundResource(R.drawable.icon_calender);

                lin_menu.setBackgroundColor(Color.parseColor("#FFFFFF"));
                image_menu.setBackgroundResource(R.drawable.icon_home);

                lin_profile.setBackgroundColor(Color.parseColor("#FFFFFF"));
                image_profile.setBackgroundResource(R.drawable.icon_user);
                startMyActivity(NotificationActivityBuyer.class,null);
                getActivity().finish();
                break;
            case R.id.lin_profile:
                lin_profile.setBackgroundColor(Color.parseColor("#c4c4c4"));
                image_profile.setBackgroundResource(R.drawable.icon_white_usere);

                lin_search.setBackgroundColor(Color.parseColor("#FFFFFF"));
                image_search.setBackgroundResource(R.drawable.icon_calender);

                lin_chat.setBackgroundColor(Color.parseColor("#FFFFFF"));
                image_chat.setBackgroundResource(R.drawable.icon_notification);

                lin_menu.setBackgroundColor(Color.parseColor("#FFFFFF"));
                image_menu.setBackgroundResource(R.drawable.icon_home);
                Intent intent2 = new Intent(getActivity(),
                        ProfileActivityDealer.class)
                        .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                getActivity().finishAffinity();
                startActivity(intent2);
                break;
            default:
                break;
        }
    }

    @Override
    protected void initUi(View view) {
        // TODO Auto-generated method stubl
        dealerList = (ListView) view.findViewById(R.id.dealer_list);
        dealerList.setEmptyView(view.findViewById(android.R.id.empty));
        buyerLists = new ArrayList<>();
        lin_carplan = (RelativeLayout) view.findViewById(R.id.lin_carplan);
        lin_chat = (RelativeLayout) view.findViewById(R.id.lin_chat);
        lin_menu = (RelativeLayout) view.findViewById(R.id.lin_menu);
        lin_profile = (RelativeLayout) view.findViewById(R.id.lin_profile);
        lin_search = (RelativeLayout) view.findViewById(R.id.lin_search);
        image_carplan = (ImageView) view.findViewById(R.id.image_carplan);
        image_chat = (ImageView) view.findViewById(R.id.image_chat);
        image_dot = (ImageView) view.findViewById(R.id.image_dot);
        image_menu = (ImageView) view.findViewById(R.id.image_menu);
        image_profile = (ImageView) view.findViewById(R.id.image_profile);
        image_search = (ImageView) view.findViewById(R.id.image_search);
        lin_menu.setBackgroundColor(Color.parseColor("#c4c4c4"));
        image_menu.setBackgroundResource(R.drawable.icon_white_home);

        if (!MySharedPreferences.getInstance().getBoolean(getActivity(),Constants.isUnreadNotification,false))
            image_dot.setVisibility(View.GONE);

        dealerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(buyerLists.get(position).getCarplan_status().equals("1")){
                    Intent intent=new Intent(getActivity(), AcceptBuyerRequestActivity.class);
                    intent.putExtra(Constants.buyerid,buyerLists.get(position).getBuyer_id());
                    MySharedPreferences.getInstance().putStringKeyValue(getActivity(),Constants.carplanId,buyerLists.get(position).getCarplan_id());
                    MySharedPreferences.getInstance().putStringKeyValue(getActivity(),Constants.buyerid,buyerLists.get(position).getBuyer_id());
                    startActivity(intent);
                }
                else{
                    Intent intent=new Intent(getActivity(),DealerBuyerDetailActivity.class);
                    intent.putExtra(Constants.buyerid,buyerLists.get(position).getBuyer_id());
                    intent.putExtra(Constants.catId,buyerLists.get(position).getCar_id());
                    MySharedPreferences.getInstance().putStringKeyValue(getActivity(),Constants.carplanId,buyerLists.get(position).getCarplan_id());
                    MySharedPreferences.getInstance().putStringKeyValue(getActivity(),Constants.buyerid,buyerLists.get(position).getBuyer_id());
                    startActivity(intent);
                }
            }
        });
    }

    @Override
    protected void setValueOnUi() {
        // TODO Auto-generated method stub
    }

    @Override
    protected void setListener() {
        lin_carplan.setOnClickListener(this);
        lin_chat.setOnClickListener(this);
        lin_menu.setOnClickListener(this);
        lin_profile.setOnClickListener(this);
        lin_search.setOnClickListener(this);
    }

    @Override
    public boolean onBackPressedListener() {
        // TODO Auto-generated method stub
        return false;
    }

    public void setresponce(JSONObject jsonObject) {
        if (jsonObject.optInt("status") == 1 && jsonObject.optInt("dataFlow") == 1) {
            JSONArray responce = jsonObject.optJSONArray("response");
            for (int i = 0; i < responce.length(); i++) {
                BuyeListBean listBean = new BuyeListBean();
                listBean.setFirst_name(responce.optJSONObject(i).optString("first_name"));
                listBean.setLast_name(responce.optJSONObject(i).optString("last_name"));
                listBean.setProfile_picture(responce.optJSONObject(i).optString("profile_picture"));
                listBean.setCity(responce.optJSONObject(i).optString("city"));
                listBean.setState(responce.optJSONObject(i).optString("state"));
                listBean.setBuyer_id(responce.optJSONObject(i).optString("buyer_id"));
                listBean.setCar_id(responce.optJSONObject(i).optString("car_id"));
                listBean.setCarplan_status(String.valueOf(responce.optJSONObject(i).optInt("carplan_status")));
                listBean.setCarplan_id(String.valueOf(responce.optJSONObject(i).optInt("carplan_id")));
                buyerLists.add(listBean);
            }
            setDATA();
        }
    }

    private void setDATA() {
        BuyerListAdapter adapter = new BuyerListAdapter(getActivity(), buyerLists, true);
        dealerList.setAdapter(adapter);
    }

}
