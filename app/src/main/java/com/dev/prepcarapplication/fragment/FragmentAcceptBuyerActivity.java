package com.dev.prepcarapplication.fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.dev.prepcarapplication.R;
import com.dev.prepcarapplication.adapter.DealerActivityAdapter;
import com.dev.prepcarapplication.baseClasses.BaseFragment;
import com.dev.prepcarapplication.baseClasses.Constants;
import com.dev.prepcarapplication.bean.DealerActivityModel;
import com.dev.prepcarapplication.networkTask.ApiManager;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class FragmentAcceptBuyerActivity extends BaseFragment {

    public  static  String TAG="ACCEPT_BUYER_FAVOURITE";
    private ListView cardStackView;
    ArrayList<DealerActivityModel> machesarray;
    DealerActivityAdapter adapter;
    TextView tvNoCars;
    static  String miles="",location="";

    public static FragmentAcceptBuyerActivity newInstanse(Bundle bundle) {
        FragmentAcceptBuyerActivity fragment = new FragmentAcceptBuyerActivity();
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
        ApiManager.getInstance().getDealerActivity(this);
    }

    @Override
    protected void initUi(View view) {
        tvNoCars=(TextView)view.findViewById(R.id.tv_no_cars);
        machesarray=new ArrayList<>();
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

    }



    public void setresponce(JSONObject jObject) {

        machesarray.clear();
        if(jObject.optInt("status")==1 && jObject.optInt("dataflow")==1){
            JSONArray data=jObject.optJSONArray("data");
            for (int j = 0; j < data.length(); j++) {
                DealerActivityModel model = new DealerActivityModel();
                model.setActivityTitle(data.optJSONObject(j).optString("msg"));
                model.setActivity_message(data.optJSONObject(j).optString("msg1"));


                machesarray.add(model);
            }
            Log.i("arrsize===",String.valueOf(machesarray.size()));
            if(machesarray.size()>0){
                tvNoCars.setVisibility(View.GONE);
                setData();
            }else{
                tvNoCars.setVisibility(View.VISIBLE);
               // showDialog("This is where you send car matches for buyer's carplan. Make sure you’ve sent your Matches to buyer.");
            }
        }

    }

    private void setData() {
        adapter = new DealerActivityAdapter(getActivity(),machesarray);
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
