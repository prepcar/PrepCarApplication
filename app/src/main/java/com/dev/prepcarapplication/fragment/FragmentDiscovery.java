package com.dev.prepcarapplication.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.dev.prepcarapplication.CarPlanActivity;
import com.dev.prepcarapplication.FinancesActivity;
import com.dev.prepcarapplication.R;
import com.dev.prepcarapplication.adapter.MultiSelectionSpinner;
import com.dev.prepcarapplication.baseClasses.BaseFragment;
import com.dev.prepcarapplication.networkTask.ApiManager;
import com.dev.prepcarapplication.utilities.ToastCustomClass;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class FragmentDiscovery extends BaseFragment {

    public static String TAG = "DISCOVERY";
    Button buton_discovety;
    EditText edit_note;//, edit_zipcode;
    Spinner dis_dis;
    MultiSelectionSpinner dis_uses;
    RelativeLayout lin_male, lin_female, lin_nochildren, lin_yeschildren, lin_nocar, lin_yescar;
    ImageView image_male, image_female, back;
    TextView  text_nochildren, text_yeschildren, text_nocar, text_yescar;
    CheckBox chk_large, chk_medium, chk_small;
    String gender = "", firstcar = "", height = "", children = "", uses = "", discount = "";
    List<String> categories, categories1;
    int setposuses = 0, setposdiscount = 0;
    JSONArray usearray;
    List<String> selList;
    ImageView icHome;

    public static FragmentDiscovery newInstanse(Bundle bundle) {
        FragmentDiscovery fragment = new FragmentDiscovery();
        if (bundle != null) {
            fragment.setArguments(
                    bundle);
        }
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        View view = inflater.inflate(R.layout.fragment_discovery, null);
        initUi(view);
        setListener();
        callingServicegetDiscovery();
        return view;
    }

    private void callingServicegetDiscovery() {
        // TODO Auto-generated method stub
        ApiManager.getInstance().getDiscoveryDetail(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.lin_male:
                gender = "male";
                lin_male.setBackgroundResource(R.drawable.buttonshape);
                image_male.setBackgroundResource(R.drawable.white_male_icon);
                lin_female.setBackgroundResource(R.drawable.discoveryshape);
                image_female.setBackgroundResource(R.drawable.female);
                break;
            case R.id.lin_female:
                gender = "female";
                lin_female.setBackgroundResource(R.drawable.buttonshape);
                image_female.setBackgroundResource(R.drawable.white_female_icon);
                lin_male.setBackgroundResource(R.drawable.discoveryshape);
                image_male.setBackgroundResource(R.drawable.male);
                break;
            case R.id.lin_nocar:
                firstcar = "no";
                lin_nocar.setBackgroundResource(R.drawable.buttonshape);
                text_nocar.setTextColor(Color.WHITE);
                lin_yescar.setBackgroundResource(R.drawable.discoveryshape);
                text_yescar.setTextColor(Color.parseColor("#c4c4c4"));
                break;
            case R.id.lin_yescar:
                firstcar = "yes";
                lin_nocar.setBackgroundResource(R.drawable.discoveryshape);
                text_nocar.setTextColor(Color.parseColor("#c4c4c4"));
                lin_yescar.setBackgroundResource(R.drawable.buttonshape);
                text_yescar.setTextColor(Color.WHITE);
                break;
            case R.id.lin_nochildren:
                children = "no";
                lin_nochildren.setBackgroundResource(R.drawable.buttonshape);
                text_nochildren.setTextColor(Color.WHITE);
                lin_yeschildren.setBackgroundResource(R.drawable.discoveryshape);
                text_yeschildren.setTextColor(Color.parseColor("#c4c4c4"));
                break;
            case R.id.lin_yeschildren:
                children = "yes";
                lin_nochildren.setBackgroundResource(R.drawable.discoveryshape);
                text_nochildren.setTextColor(Color.parseColor("#c4c4c4"));
                lin_yeschildren.setBackgroundResource(R.drawable.buttonshape);
                text_yeschildren.setTextColor(Color.WHITE);
                break;
            case R.id.buton_discovety:
           if(gender.equals("")){
				ToastCustomClass.showToast(getActivity(), "please salect your gender");
			}

			else{
                calingserviceDiscovery();
                }

                break;
            case R.id.image_back:
                getActivity().finish();
                break;
            case R.id.icHome:
                startMyActivityTop(CarPlanActivity.class,null);
                getActivity().finish();
                break;
            default:
                break;
        }
    }

    private void calingserviceDiscovery() {
        checkheight();
        uses = dis_uses.getSelectedItemsAsString();
        selList=dis_uses.getSelectedStrings();
        usearray=new JSONArray();
        for (int i = 0; i <selList.size() ; i++) {
            usearray.put(selList.get(i));
        }
        //
        //System.out.println("mainuseslist=="+sel.toString()+"string===="+selee);
        //	uses=(String) dis_uses.getSelectedItem().toString();
        discount = (String) dis_dis.getSelectedItem().toString();
        ApiManager.getInstance().getDiscovery(this, gender, firstcar, usearray.toString(), height, children, discount,
                "", edit_note.getText().toString().trim());
    }

    private void checkheight() {
        // TODO Auto-generated method stub
        if (chk_small.isChecked()) {
            height = getString(R.string.small);
        } else if (chk_medium.isChecked()) {
            height = getString(R.string.medium);
        } else if (chk_large.isChecked()) {
            height = getString(R.string.large);
        }
    }

    @Override
    protected void initUi(View view) {
        // TODO Auto-generated method stub
        selList=new ArrayList<>();
        back = (ImageView) view.findViewById(R.id.image_back);
        buton_discovety = (Button) view.findViewById(R.id.buton_discovety);
        edit_note = (EditText) view.findViewById(R.id.edit_note);
       // edit_zipcode = (EditText) view.findViewById(R.id.edit_zipcode);
        dis_uses = (MultiSelectionSpinner) view.findViewById(R.id.dis_uses);
        dis_dis = (Spinner) view.findViewById(R.id.dis_dis);
        icHome = (ImageView) view.findViewById(R.id.icHome);
        categories = new ArrayList<String>();
        categories.add("Commute");
        categories.add("Family");
        categories.add("City");
        categories.add("Road trips");
        categories.add("Extreme weather");
        categories.add("Lyft/Uber/Rideshare");

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(
                getActivity(), android.R.layout.simple_spinner_item, categories);
        dataAdapter
                .setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //dis_uses.setAdapter(dataAdapter);
        dis_uses.setItems(categories);


        categories1 = new ArrayList<String>();
        categories1.add("Educator");
        categories1.add("Student");
        categories1.add("Military");
        categories1.add("First responder");
        categories1.add("N/A");
        ArrayAdapter<String> dataAdapter1 = new ArrayAdapter<String>(
                getActivity(), android.R.layout.simple_spinner_item,
                categories1);
        dataAdapter1
                .setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dis_dis.setAdapter(dataAdapter1);
        image_male = (ImageView) view.findViewById(R.id.image_male);
        image_female = (ImageView) view.findViewById(R.id.image_female);
        lin_female = (RelativeLayout) view.findViewById(R.id.lin_female);
        lin_male = (RelativeLayout) view.findViewById(R.id.lin_male);
        text_nochildren = (TextView) view.findViewById(R.id.text_nochildren);
        text_yeschildren = (TextView) view.findViewById(R.id.text_yeschildren);
        text_nocar = (TextView) view.findViewById(R.id.text_nocar);
        text_yescar = (TextView) view.findViewById(R.id.text_yescar);
        lin_nochildren = (RelativeLayout) view
                .findViewById(R.id.lin_nochildren);
        lin_yeschildren = (RelativeLayout) view
                .findViewById(R.id.lin_yeschildren);
        lin_nocar = (RelativeLayout) view.findViewById(R.id.lin_nocar);
        lin_yescar = (RelativeLayout) view.findViewById(R.id.lin_yescar);
        chk_small = (CheckBox) view.findViewById(R.id.chk_small);
        chk_medium = (CheckBox) view.findViewById(R.id.chk_medium);
        chk_large = (CheckBox) view.findViewById(R.id.chk_large);
        chk_small.setOnCheckedChangeListener(new OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView,
                                         boolean isChecked) {
                if (isChecked) {
                    height = "tall";
                    chk_medium.setChecked(false);
                    chk_large.setChecked(false);
                } else {
                    height = "";
                }
            }
        });
        chk_medium.setOnCheckedChangeListener(new OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView,
                                         boolean isChecked) {
                if (isChecked) {
                    height = "grande";
                    chk_small.setChecked(false);
                    chk_large.setChecked(false);
                } else {
                    height = "";
                }
            }
        });
        chk_large.setOnCheckedChangeListener(new OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView,
                                         boolean isChecked) {
                if (isChecked) {
                    height = "venti";
                    chk_medium.setChecked(false);
                    chk_small.setChecked(false);
                } else {
                    height = "";
                }
            }
        });

    }

    @Override
    protected void setValueOnUi() {
    }

    @Override
    protected void setListener() {
        // TODO Auto-generated method stub
        lin_female.setOnClickListener(this);
        lin_male.setOnClickListener(this);
        lin_nochildren.setOnClickListener(this);
        lin_yeschildren.setOnClickListener(this);
        lin_nocar.setOnClickListener(this);
        lin_yescar.setOnClickListener(this);
        buton_discovety.setOnClickListener(this);
        icHome.setOnClickListener(this);
        back.setOnClickListener(this);
    }

    @Override
    public boolean onBackPressedListener() {
        // TODO Auto-generated method stub
        return false;
    }

    public void setresponce(JSONObject jObject) {
        // TODO Auto-generated method stub
        if (jObject.optInt("status") == 1 && jObject.optInt("dataflow") == 1) {
            startMyActivity(FinancesActivity.class, null);
            getActivity().finish();
        }
    }

    public void setresponceDetail(JSONObject jObject) {
        // TODO Auto-generated method stub
        if (jObject.optInt("status") == 1 && jObject.optInt("dataflow") == 1) {
            try {
                edit_note.setText(jObject.optString("shoppingnotes"));
               // edit_zipcode.setText(jObject.optString("zipcode"));
                gender = jObject.optString("identify");
                if (gender.equals("female")) {
                    lin_female.setBackgroundResource(R.drawable.buttonshape);
                    image_female.setBackgroundResource(R.drawable.white_female_icon);
                    lin_male.setBackgroundResource(R.drawable.discoveryshape);
                    image_male.setBackgroundResource(R.drawable.male);
                } else if (gender.equals("male")) {
                    lin_male.setBackgroundResource(R.drawable.buttonshape);
                    image_male.setBackgroundResource(R.drawable.white_male_icon);
                    lin_female.setBackgroundResource(R.drawable.discoveryshape);
                    image_female.setBackgroundResource(R.drawable.female);
                }
                firstcar = jObject.optString("firstcar");
                if (firstcar.equals("no")) {
                    lin_nocar.setBackgroundResource(R.drawable.buttonshape);
                    text_nocar.setTextColor(Color.WHITE);
                    lin_yescar.setBackgroundResource(R.drawable.discoveryshape);
                    text_yescar.setTextColor(Color.parseColor("#c4c4c4"));
                } else if (firstcar.equals("yes")) {
                    lin_nocar.setBackgroundResource(R.drawable.discoveryshape);
                    text_nocar.setTextColor(Color.parseColor("#c4c4c4"));
                    lin_yescar.setBackgroundResource(R.drawable.buttonshape);
                    text_yescar.setTextColor(Color.WHITE);
                }
                height = jObject.optString("height");
                if (height.equals(getString(R.string.small))) {
                    chk_small.setChecked(true);
                } else if (height.equals(getString(R.string.medium))) {
                    chk_medium.setChecked(true);
                } else if (height.equals(getString(R.string.large))) {
                    chk_large.setChecked(true);
                }
                children = jObject.optString("smallchildren");
                if (children.equals("no")) {
                    lin_nochildren.setBackgroundResource(R.drawable.buttonshape);
                    text_nochildren.setTextColor(Color.WHITE);
                    lin_yeschildren.setBackgroundResource(R.drawable.discoveryshape);
                    text_yeschildren.setTextColor(Color.parseColor("#c4c4c4"));
                } else if (children.equals("yes")) {
                    lin_nochildren.setBackgroundResource(R.drawable.discoveryshape);
                    text_nochildren.setTextColor(Color.parseColor("#c4c4c4"));
                    lin_yeschildren.setBackgroundResource(R.drawable.buttonshape);
                    text_yeschildren.setTextColor(Color.WHITE);
                }
                usearray = jObject.optJSONArray("mainuse");
                for (int i = 0; i < usearray.length(); i++) {
                    selList.add(usearray.getString(i).toString());
                }
                dis_uses.setSelection(selList);
                discount = jObject.optString("discount");
                for (int i = 0; i < categories1.size(); i++) {
                    if (categories1.get(i).equals(discount)) {
                        setposdiscount = i;
                    }
                }
                dis_dis.setSelection(setposdiscount);
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

}
