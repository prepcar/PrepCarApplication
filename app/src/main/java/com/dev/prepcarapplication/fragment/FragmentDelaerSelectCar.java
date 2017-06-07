package com.dev.prepcarapplication.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.dev.prepcarapplication.CreateDealActivity;
import com.dev.prepcarapplication.DealerNewCarActivity;
import com.dev.prepcarapplication.NavigateActivityDealer;
import com.dev.prepcarapplication.R;
import com.dev.prepcarapplication.baseClasses.BaseFragment;
import com.dev.prepcarapplication.baseClasses.Constants;
import com.dev.prepcarapplication.networkTask.ApiManager;
import com.dev.prepcarapplication.utilities.ToastCustomClass;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by this on 2/24/2017.
 */

public class FragmentDelaerSelectCar extends BaseFragment {
    public static String TAG = "Selctcar", buyerID = "";
    Spinner selectcar_spiner;
    ArrayList<String> carlist, carid;
    Button select_next;
    TextView select_newcar;
    EditText select_description, select_price, select_disclimer, select_terms;
    EditText select_intrestrate, select_downpayment, select_tradeinvalue, edit_nickname, edit_monthly_payment;
    RadioButton radio_24, radio_36, radio_48, radio_60, radio_72;
    ImageView back;
    String month = "";
    RadioGroup radio_group;
    LinearLayout ll_months;
    RelativeLayout lin_yesfinance, lin_nofinance, lin_yesleaseoption, lin_noleaseoption,
            lin_yestradein, lin_notradein;
    TextView text_yesfinance, text_nofinance, text_yesleaseoption, text_noleaseoption, text_yestradein, text_notradein;

    public static FragmentDelaerSelectCar newInstanse(Bundle bundle) {
        FragmentDelaerSelectCar fragment = new FragmentDelaerSelectCar();
        if (bundle != null) {
            fragment.setArguments(bundle);
            buyerID = bundle.getString(Constants.buyerid);
        } else {
            buyerID = "";
        }
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        View view = inflater.inflate(R.layout.fragment_dealerelect_car, null);
        initUi(view);
        setListener();
        onYesFinanceClick();
        onYesTradeClick();
        onYesLeaseClick();
        return view;
    }

    private void callingServiceGetVichlelist() {
        ApiManager.getInstance().getVichileList(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        callingServiceGetVichlelist();
        select_intrestrate.setText("");
        select_downpayment.setText("");
        select_tradeinvalue.setText("");
        select_description.setText("");
        edit_monthly_payment.setText("");
        edit_nickname.setText("");
        select_disclimer.setText("");
        select_price.setText("");
        select_terms.setText("");
        radio_group.clearCheck();
    }

    @Override
    protected void initUi(View view) {
        radio_group = (RadioGroup) view.findViewById(R.id.radio_group);
        select_intrestrate = (EditText) view.findViewById(R.id.select_intrestrate);
        select_downpayment = (EditText) view.findViewById(R.id.select_downpayment);
        select_tradeinvalue = (EditText) view.findViewById(R.id.select_tradeinvalue);
        edit_monthly_payment = (EditText) view.findViewById(R.id.edit_monthly_payment);
        edit_nickname = (EditText) view.findViewById(R.id.edit_nickname);
        radio_24 = (RadioButton) view.findViewById(R.id.radio_24);
        radio_36 = (RadioButton) view.findViewById(R.id.radio_36);
        radio_48 = (RadioButton) view.findViewById(R.id.radio_48);
        radio_60 = (RadioButton) view.findViewById(R.id.radio_60);
        radio_72 = (RadioButton) view.findViewById(R.id.radio_72);
        back = (ImageView) view.findViewById(R.id.back);
        carid = new ArrayList<>();
        selectcar_spiner = (Spinner) view.findViewById(R.id.selectcar_spiner);
        carlist = new ArrayList<>();
        select_description = (EditText) view.findViewById(R.id.select_description);
        select_disclimer = (EditText) view.findViewById(R.id.select_disclimer);
        select_price = (EditText) view.findViewById(R.id.select_price);
        select_terms = (EditText) view.findViewById(R.id.select_terms);
        select_newcar = (TextView) view.findViewById(R.id.select_newcar);
        select_next = (Button) view.findViewById(R.id.select_next);

        lin_yesfinance = (RelativeLayout) view.findViewById(R.id.lin_yesfinance);
        lin_nofinance = (RelativeLayout) view.findViewById(R.id.lin_nofinance);
        lin_yesleaseoption = (RelativeLayout) view.findViewById(R.id.lin_yesleaseoption);
        lin_noleaseoption = (RelativeLayout) view.findViewById(R.id.lin_noleaseoption);
        lin_yestradein = (RelativeLayout) view.findViewById(R.id.lin_yestradein);
        lin_notradein = (RelativeLayout) view.findViewById(R.id.lin_notradein);

        text_yesfinance = (TextView) view.findViewById(R.id.text_yesfinance);
        text_nofinance = (TextView) view.findViewById(R.id.text_nofinance);
        text_yesleaseoption = (TextView) view.findViewById(R.id.text_yesleaseoption);
        text_noleaseoption = (TextView) view.findViewById(R.id.text_noleaseoption);
        text_yestradein = (TextView) view.findViewById(R.id.text_yestradein);
        text_notradein = (TextView) view.findViewById(R.id.text_notradein);

        ll_months = (LinearLayout) view.findViewById(R.id.ll_months);
        view.findViewById(R.id.icHome).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startMyActivityTop(NavigateActivityDealer.class, null);
                getActivity().finish();
            }
        });

        radio_group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.radio_24:
                        month = "24";
                        break;
                    case R.id.radio_36:
                        month = "36";
                        break;
                    case R.id.radio_48:
                        month = "48";
                        break;
                    case R.id.radio_60:
                        month = "60";
                        break;
                    case R.id.radio_72:
                        month = "72";
                        break;
                }
            }
        });
    }

    @Override
    protected void setValueOnUi() {
        ArrayAdapter<String> dataAdapter1 = new ArrayAdapter<String>(
                getActivity(), android.R.layout.simple_spinner_item,
                carlist);
        dataAdapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        selectcar_spiner.setAdapter(dataAdapter1);
    }

    @Override
    protected void setListener() {
        select_next.setOnClickListener(this);
        back.setOnClickListener(this);
        select_newcar.setOnClickListener(this);
        lin_yesfinance.setOnClickListener(this);
        lin_nofinance.setOnClickListener(this);
        lin_yesleaseoption.setOnClickListener(this);
        lin_noleaseoption.setOnClickListener(this);
        lin_yestradein.setOnClickListener(this);
        lin_notradein.setOnClickListener(this);
    }

    @Override
    public boolean onBackPressedListener() {
        return false;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.select_next:
                if (select_description.getText().toString().trim().equals("")) {
                    ToastCustomClass.showToast(getActivity(), "Please enter description");
                } else if (edit_nickname.getText().toString().trim().equals("")) {
                    ToastCustomClass.showToast(getActivity(), "Please enter Nick name");
                } else if (select_price.getText().toString().trim().equals("")) {
                    ToastCustomClass.showToast(getActivity(), "Please enter Price");
                } else if (edit_monthly_payment.isEnabled() && edit_monthly_payment.getText().toString().trim().equals("")) {
                    ToastCustomClass.showToast(getActivity(), "Please enter monthly payment");
                } else if (select_intrestrate.isEnabled() && select_intrestrate.getText().toString().trim().equals("")) {
                    ToastCustomClass.showToast(getActivity(), "Please enter Interest rate");
                } else if (select_downpayment.isEnabled() && select_downpayment.getText().toString().trim().equals("")) {
                    ToastCustomClass.showToast(getActivity(), "Please enter down payment");
                } else if (select_tradeinvalue.isEnabled() && select_tradeinvalue.getText().toString().trim().equals("")) {
                    ToastCustomClass.showToast(getActivity(), "Please enter trade in amount");
                } else if (select_terms.isEnabled() && select_terms.getText().toString().trim().equals("")) {
                    ToastCustomClass.showToast(getActivity(), "Please enter trade in amount");
                } else if (select_disclimer.isEnabled() && select_disclimer.getText().toString().trim().equals("")) {
                    ToastCustomClass.showToast(getActivity(), "Please enter trade in amount");
                } else {
                    callingServiceGetDealid();
                }
                break;
            case R.id.back:
                getActivity().finish();
                break;
            case R.id.select_newcar:
                Intent intent = new Intent(getActivity(), DealerNewCarActivity.class);
                intent.putExtra("classtype", "selectcar");
                startActivity(intent);
                break;
            case R.id.lin_yesfinance:
                onYesFinanceClick();
                break;
            case R.id.lin_nofinance:
                onNoFinanceClick();
                break;
            case R.id.lin_yesleaseoption:
                onYesLeaseClick();
                break;
            case R.id.lin_noleaseoption:
                onNoLeaseClick();
                break;
            case R.id.lin_yestradein:
                onYesTradeClick();
                break;
            case R.id.lin_notradein:
                onNoTradeClick();
                break;
        }

    }

    private void onNoTradeClick() {

        lin_notradein.setBackgroundResource(R.drawable.financebuttonshape_selectcar);
        text_notradein.setTextColor(Color.WHITE);
        lin_yestradein.setBackgroundResource(R.drawable.discoveryshape);
        text_yestradein.setTextColor(Color.parseColor("#293A92"));
        select_tradeinvalue.setEnabled(false);
    }

    private void onYesTradeClick() {
        lin_yestradein.setBackgroundResource(R.drawable.financebuttonshape_selectcar);
        text_yestradein.setTextColor(Color.WHITE);
        lin_notradein.setBackgroundResource(R.drawable.discoveryshape);
        text_notradein.setTextColor(Color.parseColor("#293A92"));
        select_tradeinvalue.setEnabled(true);
    }

    private void onNoLeaseClick() {
        lin_noleaseoption.setBackgroundResource(R.drawable.financebuttonshape_selectcar);
        text_noleaseoption.setTextColor(Color.WHITE);
        lin_yesleaseoption.setBackgroundResource(R.drawable.discoveryshape);
        text_yesleaseoption.setTextColor(Color.parseColor("#293A92"));
        select_terms.setEnabled(false);
        select_disclimer.setEnabled(false);
    }

    private void onYesLeaseClick() {
        lin_yesleaseoption.setBackgroundResource(R.drawable.financebuttonshape_selectcar);
        text_yesleaseoption.setTextColor(Color.WHITE);
        lin_noleaseoption.setBackgroundResource(R.drawable.discoveryshape);
        text_noleaseoption.setTextColor(Color.parseColor("#293A92"));
        select_terms.setEnabled(true);
        select_disclimer.setEnabled(true);

    }


    private void onNoFinanceClick() {
        lin_nofinance.setBackgroundResource(R.drawable.financebuttonshape_selectcar);
        text_nofinance.setTextColor(Color.WHITE);
        lin_yesfinance.setBackgroundResource(R.drawable.discoveryshape);
        text_yesfinance.setTextColor(Color.parseColor("#293A92"));
        edit_monthly_payment.setEnabled(false);
        select_intrestrate.setEnabled(false);
        select_downpayment.setEnabled(false);
        for (int i = 0; i < radio_group.getChildCount(); i++) {
            radio_group.getChildAt(i).setEnabled(false);
        }
    }

    private void onYesFinanceClick() {

        lin_yesfinance.setBackgroundResource(R.drawable.financebuttonshape_selectcar);
        text_yesfinance.setTextColor(Color.WHITE);
        lin_nofinance.setBackgroundResource(R.drawable.discoveryshape);
        text_nofinance.setTextColor(Color.parseColor("#293A92"));
        edit_monthly_payment.setEnabled(true);
        select_intrestrate.setEnabled(true);
        select_downpayment.setEnabled(true);
        for (int i = 0; i < radio_group.getChildCount(); i++) {
            radio_group.getChildAt(i).setEnabled(true);
        }
    }

    private void callingServiceGetDealid() {
        String caridw = carid.get(selectcar_spiner.getSelectedItemPosition());
        ApiManager.getInstance().createDeal(this, caridw, select_terms.getText().toString().trim() + "",
                select_disclimer.getText().toString().trim() + "", select_price.getText().toString().trim() + "", select_description.getText().toString().trim() + "",
                month, select_intrestrate.getText().toString().trim() + "", select_price.getText().toString().trim() + "",
                "", select_downpayment.getText().toString().trim(),
                select_tradeinvalue.getText().toString().trim() + "", "", edit_nickname.getText().toString() + "", edit_monthly_payment.getText().toString() + "");
    }


    public void setresponce(JSONObject jObject) {
        if (jObject.optInt("status") == 1 && jObject.optInt("dataFlow") == 1) {
            carlist.clear();
            carid.clear();
            JSONArray response = jObject.optJSONArray("response");
            for (int i = 0; i < response.length(); i++) {
                carlist.add(response.optJSONObject(i).optString("model_year") + " " + response.optJSONObject(i).optString("make") + " " + response.optJSONObject(i).optString("model_type"));
                carid.add(String.valueOf(response.optJSONObject(i).optInt("car_id")));
            }
            setValueOnUi();
        }
    }


    public void setresponceDeal(JSONObject jObject) {
        if (jObject.optInt("status") == 1 && jObject.optInt("dataflow") == 1) {
            JSONObject response = jObject.optJSONObject("response");
            Intent intent = new Intent(getActivity(), CreateDealActivity.class);
            intent.putExtra("dealid", response.optString("deal_id"));
            intent.putExtra(Constants.buyerid, buyerID);
            startActivity(intent);
        }
    }
}
