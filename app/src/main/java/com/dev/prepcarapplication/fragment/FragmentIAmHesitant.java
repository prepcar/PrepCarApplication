package com.dev.prepcarapplication.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import com.dev.prepcarapplication.CarPlanActivity;
import com.dev.prepcarapplication.R;
import com.dev.prepcarapplication.SearchActivityBuyer;
import com.dev.prepcarapplication.adapter.MultiSelectionSpinner;
import com.dev.prepcarapplication.baseClasses.BaseFragment;
import com.dev.prepcarapplication.networkTask.ApiManager;
import com.dev.prepcarapplication.utilities.ToastCustomClass;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import io.apptik.widget.MultiSlider;

public class FragmentIAmHesitant extends BaseFragment {

    public static String TAG = "IAM";
    Spinner spiner_trsmision;
    MultiSelectionSpinner spiner_colour, spiner_prefrence;
    MultiSelectionSpinner spiner_europins, spiner_asians, spiner_americns;
    ImageView back;
    ImageView icHome;
    TextView text_seekbar, text_economy, text_suv, text_sedan,
            text_convertible, text_coupe, text_luxery, text_rdrowseat,
            text_hatchback, text_speed, text_van, text_hdtruck, text_electric,
            text_selfdriving, text_hybrid, maxseekvalue, minseekvalue;
    //SeekBar hesitate_seek;
    CheckBox chk_new, chk_certified, chk_preowned, chk_lease, chk_anything,
            chk_economy, chk_suv, chk_sedan, chk_convertible, chk_coupe,
            chk_luxery, chk_rdrowseat, chk_hachback, chk_speed, chk_van,
            chk_hdtruck, chk_electric, chk_selfdriving, chk_hybrid;
    RadioButton radio_new, radio_under10, radio_under30, radio_under50,
            radio_under70, radio_under100;
    List<String> categories1, categories11, categories12, categories13,
            categories4, categories5, categories6, listamerican, listashian, listeuropian, listcolour, listprefrences;
    Button buton_iamcar;
    int countstyle = 0;
    StringBuilder typebuilder, stylebuilder;
    RadioGroup radio_frst, radio_second;
    String type = "", style = "", milage = "", colour = "", trasmision = "",
            enginepower = "", prefrence = "", america = "", asians = "",
            europian = "";
    JSONArray typearray, stylearray, ashianjsonarray, americanjsonarray, europianjsonarray, jsonarraycolour, jsonarrayprefrences;
    MultiSlider doubleslider;

    public static FragmentIAmHesitant newInstanse(Bundle bundle) {
        FragmentIAmHesitant fragment = new FragmentIAmHesitant();
        if (bundle != null) {
            fragment.setArguments(bundle);
        }
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        View view = inflater.inflate(R.layout.fragment_iam_hesitant, null);
        initUi(view);
        setListener();
        cllingServicegetIAM();
        return view;
    }

    private void cllingServicegetIAM() {
        // TODO Auto-generated method stub
        ApiManager.getInstance().getIAMDetail(this);
    }

    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub
        switch (v.getId()) {
            case R.id.image_back:
                getActivity().finish();
                break;
            case R.id.buton_iamcar:
                callingServiceIAM();

                break;
            case R.id.icHome:
                startMyActivityTop(CarPlanActivity.class, null);
                getActivity().finish();
                break;
            default:
                break;
        }
    }

    private void callingServiceIAM() {
        // TODO Auto-generated method stub
        colour = spiner_colour.getSelectedItem().toString();
        trasmision = spiner_trsmision.getSelectedItem().toString();
       // enginepower = spiner_enginepower.getSelectedItem().toString();
        prefrence = spiner_prefrence.getSelectedItem().toString();
        americanjsonarray = new JSONArray();
        listamerican = spiner_americns.getSelectedStrings();
        for (int i = 0; i < listamerican.size(); i++) {
            americanjsonarray.put(listamerican.get(i));
        }
        ashianjsonarray = new JSONArray();
        listashian = spiner_asians.getSelectedStrings();
        for (int i = 0; i < listashian.size(); i++) {
            ashianjsonarray.put(listashian.get(i));
        }
        europianjsonarray = new JSONArray();
        listeuropian = spiner_europins.getSelectedStrings();
        for (int i = 0; i < listeuropian.size(); i++) {
            europianjsonarray.put(listeuropian.get(i));
        }
        jsonarraycolour = new JSONArray();
        listcolour = spiner_colour.getSelectedStrings();
        for (int i = 0; i < listcolour.size(); i++) {
            jsonarraycolour.put(listcolour.get(i));
        }
        jsonarrayprefrences = new JSONArray();
        listprefrences = spiner_prefrence.getSelectedStrings();
        for (int i = 0; i < listprefrences.size(); i++) {
            jsonarrayprefrences.put(listprefrences.get(i));
        }
        setStyle();
        setType();
        setmilage();
        ApiManager.getInstance().getIAM(this,
                minseekvalue.getText().toString().trim(), typearray.toString(),
                stylearray.toString(), milage, jsonarraycolour.toString(), trasmision, enginepower,
                jsonarrayprefrences.toString(), americanjsonarray.toString(), ashianjsonarray.toString(), europianjsonarray.toString(),
                maxseekvalue.getText().toString().trim());
    }

    private void setStyle() {
        stylearray = new JSONArray();
        if (chk_economy.isChecked()) {
            stylearray.put(text_economy.getText().toString());
        }
        if (chk_suv.isChecked()) {
            stylearray.put(text_suv.getText().toString());
        }
        if (chk_sedan.isChecked()) {
            stylearray.put(text_sedan.getText().toString());
        }
        if (chk_convertible.isChecked()) {
            stylearray.put(text_convertible.getText().toString());
        }
        if (chk_coupe.isChecked()) {
            stylearray.put(text_coupe.getText().toString());
        }
        if (chk_luxery.isChecked()) {
            stylearray.put(text_luxery.getText().toString());
        }
        if (chk_rdrowseat.isChecked()) {
            stylearray.put(text_rdrowseat.getText().toString());
        }
        if (chk_hachback.isChecked()) {
            stylearray.put(text_hatchback.getText().toString());
        }
        if (chk_speed.isChecked()) {
            stylearray.put(text_speed.getText().toString());
        }
        if (chk_van.isChecked()) {
            stylearray.put(text_van.getText().toString());
        }
        if (chk_hdtruck.isChecked()) {
            stylearray.put(text_hdtruck.getText().toString());
        }
        if (chk_electric.isChecked()) {
            stylearray.put(text_electric.getText().toString());
        }
        if (chk_selfdriving.isChecked()) {
            stylearray.put(text_selfdriving.getText().toString());
        }
        if (chk_hybrid.isChecked()){
            stylearray.put(text_hybrid.getText().toString());
        }
    }

    private void setmilage() {
        // TODO Auto-generated method stub
        if (radio_new.isChecked()) {
            milage = radio_new.getText().toString();
        } else if (radio_under10.isChecked()) {
            milage = radio_under10.getText().toString();
        } else if (radio_under30.isChecked()) {
            milage = radio_under30.getText().toString();
        } else if (radio_under50.isChecked()) {
            milage = radio_under50.getText().toString();
        } else if (radio_under70.isChecked()) {
            milage = radio_under70.getText().toString();
        } else if (radio_under100.isChecked()) {
            milage = radio_under100.getText().toString();
        }

    }

    private void setType() {
        // TODO Auto-generated method stub
        typearray = new JSONArray();
        if (chk_new.isChecked()) {
            typearray.put(chk_new.getText().toString());
        }
        if (chk_certified.isChecked()) {
            typearray.put(chk_certified.getText().toString());
        }
        if (chk_preowned.isChecked()) {
            typearray.put(chk_preowned.getText().toString());
        }
        if (chk_lease.isChecked()) {
            typearray.put(chk_lease.getText().toString());
        }
        if (chk_anything.isChecked()) {
            typearray.put(chk_anything.getText().toString());
        }

    }

    @Override
    protected void initUi(View view) {
        // TODO Auto-generated method stub
        maxseekvalue = (TextView) view.findViewById(R.id.maxseekvalue);
        minseekvalue = (TextView) view.findViewById(R.id.minseekvalue);
        doubleslider = (MultiSlider) view.findViewById(R.id.range_slider5);
        ashianjsonarray = new JSONArray();
        americanjsonarray = new JSONArray();
        europianjsonarray = new JSONArray();
        listamerican = new ArrayList<>();
        listashian = new ArrayList<>();
        listcolour = new ArrayList<>();
        listprefrences = new ArrayList<>();
        listeuropian = new ArrayList<>();
        typearray = new JSONArray();
        text_convertible = (TextView) view.findViewById(R.id.text_convertible);
        icHome = (ImageView) view.findViewById(R.id.icHome);
        text_coupe = (TextView) view.findViewById(R.id.text_coupe);
        text_economy = (TextView) view.findViewById(R.id.text_economy);
        text_electric = (TextView) view.findViewById(R.id.text_electric);
        text_hatchback = (TextView) view.findViewById(R.id.text_hatchback);
        text_hdtruck = (TextView) view.findViewById(R.id.text_hdtruck);
        text_luxery = (TextView) view.findViewById(R.id.text_luxery);
        text_rdrowseat = (TextView) view.findViewById(R.id.text_rdrowseat);
        text_sedan = (TextView) view.findViewById(R.id.text_sedan);
        text_selfdriving = (TextView) view.findViewById(R.id.text_selfdriving);
        text_hybrid = (TextView) view.findViewById(R.id.text_hybrid);
        text_speed = (TextView) view.findViewById(R.id.text_speed);
        text_suv = (TextView) view.findViewById(R.id.text_suv);
        text_van = (TextView) view.findViewById(R.id.text_van);
        stylebuilder = new StringBuilder();
        radio_frst = (RadioGroup) view.findViewById(R.id.radio_frst);
        radio_second = (RadioGroup) view.findViewById(R.id.radio_second);
        typebuilder = new StringBuilder();
        buton_iamcar = (Button) view.findViewById(R.id.buton_iamcar);
        chk_anything = (CheckBox) view.findViewById(R.id.chk_anything);
        chk_certified = (CheckBox) view.findViewById(R.id.chk_certified);
        chk_convertible = (CheckBox) view.findViewById(R.id.chk_convertible);
        chk_coupe = (CheckBox) view.findViewById(R.id.chk_coupe);
        chk_economy = (CheckBox) view.findViewById(R.id.chk_economy);
        chk_electric = (CheckBox) view.findViewById(R.id.chk_electric);
        chk_hachback = (CheckBox) view.findViewById(R.id.chk_hachback);
        chk_hdtruck = (CheckBox) view.findViewById(R.id.chk_hdtruck);
        chk_lease = (CheckBox) view.findViewById(R.id.chk_lease);
        chk_luxery = (CheckBox) view.findViewById(R.id.chk_luxery);
        chk_new = (CheckBox) view.findViewById(R.id.chk_new);
        chk_preowned = (CheckBox) view.findViewById(R.id.chk_preowned);
        chk_rdrowseat = (CheckBox) view.findViewById(R.id.chk_rdrowseat);
        chk_sedan = (CheckBox) view.findViewById(R.id.chk_sedan);
        chk_selfdriving = (CheckBox) view.findViewById(R.id.chk_selfdriving);
        chk_hybrid = (CheckBox) view.findViewById(R.id.chk_hybrid);
        chk_speed = (CheckBox) view.findViewById(R.id.chk_speed);
        chk_suv = (CheckBox) view.findViewById(R.id.chk_suv);
        chk_van = (CheckBox) view.findViewById(R.id.chk_van);
        radio_new = (RadioButton) view.findViewById(R.id.radio_new);
        radio_under10 = (RadioButton) view.findViewById(R.id.radio_under10);
        radio_under100 = (RadioButton) view.findViewById(R.id.radio_under100);
        radio_under30 = (RadioButton) view.findViewById(R.id.radio_under30);
        radio_under50 = (RadioButton) view.findViewById(R.id.radio_under50);
        radio_under70 = (RadioButton) view.findViewById(R.id.radio_under70);
        //hesitate_seek = (SeekBar) view.findViewById(R.id.hesitate_seek);
        text_seekbar = (TextView) view.findViewById(R.id.text_seekbar);
        back = (ImageView) view.findViewById(R.id.image_back);
        spiner_americns = (MultiSelectionSpinner) view.findViewById(R.id.spiner_americns);
        spiner_asians = (MultiSelectionSpinner) view.findViewById(R.id.spiner_asians);
        spiner_colour = (MultiSelectionSpinner) view.findViewById(R.id.spiner_colour);
        spiner_europins = (MultiSelectionSpinner) view.findViewById(R.id.spiner_europins);
        spiner_prefrence = (MultiSelectionSpinner) view.findViewById(R.id.spiner_prefrence);
        spiner_trsmision = (Spinner) view.findViewById(R.id.spiner_trsmision);
        categories1 = new ArrayList<String>();
        categories1.add("White");
        categories1.add("Black");
        categories1.add("Blue");
        categories1.add("Green");
        categories1.add("Pink");
        categories1.add("Yellow");
        categories1.add("Gray");
        categories1.add("Silver");
        categories1.add("Brown");
        categories1.add("Red");
        categories1.add("Purple");
        categories1.add("No preferences");
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(
                getActivity(), android.R.layout.simple_spinner_item,
                categories1);
        dataAdapter
                .setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spiner_colour.setItems(categories1);

        categories11 = new ArrayList<String>();
        categories11.add("Automatic");
        categories11.add("Manual");
        ArrayAdapter<String> dataAdapter1 = new ArrayAdapter<String>(
                getActivity(), android.R.layout.simple_spinner_item,
                categories11);
        dataAdapter1
                .setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spiner_trsmision.setAdapter(dataAdapter1);

        /*categories12 = new ArrayList<String>();
        categories12.add("Low");
        categories12.add("Average");
        categories12.add("High");
        categories12.add("Very high");
        ArrayAdapter<String> dataAdapter2 = new ArrayAdapter<String>(
                getActivity(), android.R.layout.simple_spinner_item,
                categories12);
        dataAdapter2
                .setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spiner_enginepower.setAdapter(dataAdapter2);*/

        categories13 = new ArrayList<String>();
        categories13.add("Sunroof/Moonroof");
        categories13.add("Leather seats");
        categories13.add("Back-up camera");
        categories13.add("Navigation");
        categories13.add("Keyless entry");
        categories13.add("Apple CarPlay/Android Auto");
        categories13.add("Bluetooth");
        categories13.add("Heated seats");
        categories13.add("Remote start");
        categories13.add("Parking assist");
        categories13.add("Premium audio");
        categories13.add("3rd row seat");
        ArrayAdapter<String> dataAdapter3 = new ArrayAdapter<String>(
                getActivity(), android.R.layout.simple_spinner_item,
                categories13);
        dataAdapter3
                .setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spiner_prefrence.setItems(categories13);

        categories4 = new ArrayList<String>();
        categories4.add("Ford");
        categories4.add("Chevrolet");
        categories4.add("Lincoln");
        categories4.add("Tesla");
        categories4.add("Cadillac");
        categories4.add("Buick");
        categories4.add("Chrysler");
        categories4.add("Dodge");
        categories4.add("Jeep");
        categories4.add("GMC");
        categories4.add("None");
        ArrayAdapter<String> dataAdapter4 = new ArrayAdapter<String>(
                getActivity(), android.R.layout.simple_spinner_item,
                categories4);
        dataAdapter4
                .setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spiner_americns.setItems(categories4);

        categories5 = new ArrayList<String>();
        categories5.add("Toyota");
        categories5.add("Acura");
        categories5.add("Nissan");
        categories5.add("Suzuki");
        categories5.add("Honda");
        categories5.add("Hyundai");
        categories5.add("Lexus");
        categories5.add("INFINITI");
        categories5.add("Mitsubishi");
        categories5.add("Subaru");
        categories5.add("Mazda");
        categories5.add("Kia");
        categories5.add("None");
        ArrayAdapter<String> dataAdapter5 = new ArrayAdapter<String>(
                getActivity(), android.R.layout.simple_spinner_item,
                categories5);
        dataAdapter5
                .setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spiner_asians.setItems(categories5);

        categories6 = new ArrayList<String>();
        categories6.add("BMW");
        categories6.add("Audi");
        categories6.add("Fiat");
        categories6.add("Volvo");
        categories6.add("Volkswagen");
        categories6.add("Mercedes");
        categories6.add("Jaguar");
        categories6.add("MINI");
        categories6.add("None");
        ArrayAdapter<String> dataAdapter6 = new ArrayAdapter<String>(
                getActivity(), android.R.layout.simple_spinner_item,
                categories6);
        dataAdapter6
                .setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spiner_europins.setItems(categories6);

        doubleslider.setMax(27);
        doubleslider.setMin(0);
        doubleslider.setOnThumbValueChangeListener(new MultiSlider.OnThumbValueChangeListener() {
            @Override
            public void onValueChanged(MultiSlider multiSlider, MultiSlider.Thumb thumb, int thumbIndex, int value) {
                if (thumbIndex == 0) {
                    minseekvalue.setText(String.valueOf(value + 1990));
                } else {
                    maxseekvalue.setText(String.valueOf(value + 1990));
                }
            }
        });

        chk_convertible
                .setOnCheckedChangeListener(new OnCheckedChangeListener() {

                    @Override
                    public void onCheckedChanged(CompoundButton buttonView,
                                                 boolean isChecked) {
                        // TODO Auto-generated method stub

                        if (isChecked) {
                            if (countstyle < 5) {
                                countstyle++;
                            } else {
                                ToastCustomClass.showToast(getActivity(),
                                        "you can select max 5 styles");
                                chk_convertible.setChecked(false);
                            }
                        } else {
                            countstyle--;
                        }
                    }
                });
        chk_coupe.setOnCheckedChangeListener(new OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView,
                                         boolean isChecked) {
                // TODO Auto-generated method stub
                if (isChecked) {
                    if (countstyle < 5) {
                        countstyle++;
                    } else {
                        ToastCustomClass.showToast(getActivity(),
                                "you can select max 5 styles");
                        chk_coupe.setChecked(false);
                    }
                } else {
                    countstyle--;
                }
            }
        });
        chk_economy.setOnCheckedChangeListener(new OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView,
                                         boolean isChecked) {
                // TODO Auto-generated method stub
                if (isChecked) {
                    if (countstyle < 5) {
                        countstyle++;
                    } else {
                        ToastCustomClass.showToast(getActivity(),
                                "you can select max 5 styles");
                        chk_economy.setChecked(false);
                    }
                } else {
                    countstyle--;
                }
            }
        });
        chk_electric.setOnCheckedChangeListener(new OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView,
                                         boolean isChecked) {
                // TODO Auto-generated method stub
                if (isChecked) {
                    if (countstyle < 5) {
                        countstyle++;
                    } else {
                        ToastCustomClass.showToast(getActivity(),
                                "you can select max 5 styles");
                        chk_electric.setChecked(false);
                    }
                } else {
                    countstyle--;
                }
            }
        });
        chk_hachback.setOnCheckedChangeListener(new OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView,
                                         boolean isChecked) {
                // TODO Auto-generated method stub
                if (isChecked) {
                    if (countstyle < 5) {
                        countstyle++;
                    } else {
                        ToastCustomClass.showToast(getActivity(),
                                "you can select max 5 styles");
                        chk_hachback.setChecked(false);
                    }
                } else {
                    countstyle--;
                }
            }
        });
        chk_hdtruck.setOnCheckedChangeListener(new OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView,
                                         boolean isChecked) {
                // TODO Auto-generated method stub
                if (isChecked) {
                    if (countstyle < 5) {
                        countstyle++;
                    } else {
                        ToastCustomClass.showToast(getActivity(),
                                "you can select max 5 styles");
                        chk_hdtruck.setChecked(false);
                    }
                } else {
                    countstyle--;
                }
            }
        });

        chk_luxery.setOnCheckedChangeListener(new OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView,
                                         boolean isChecked) {
                // TODO Auto-generated method stub
                if (isChecked) {
                    if (countstyle < 5) {
                        countstyle++;
                    } else {
                        ToastCustomClass.showToast(getActivity(),
                                "you can select max 5 styles");
                        chk_luxery.setChecked(false);
                    }
                } else {
                    countstyle--;
                }
            }
        });

        chk_rdrowseat.setOnCheckedChangeListener(new OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView,
                                         boolean isChecked) {
                // TODO Auto-generated method stub
                if (isChecked) {
                    if (countstyle < 5) {
                        countstyle++;
                    } else {
                        ToastCustomClass.showToast(getActivity(),
                                "you can select max 5 styles");
                        chk_rdrowseat.setChecked(false);
                    }
                } else {
                    countstyle--;
                }
            }
        });
        chk_sedan.setOnCheckedChangeListener(new OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView,
                                         boolean isChecked) {
                // TODO Auto-generated method stub
                if (isChecked) {
                    if (countstyle < 5) {
                        countstyle++;
                    } else {
                        ToastCustomClass.showToast(getActivity(),
                                "you can select max 5 styles");
                        chk_sedan.setChecked(false);
                    }
                } else {
                    countstyle--;
                }
            }
        });
        chk_selfdriving
                .setOnCheckedChangeListener(new OnCheckedChangeListener() {

                    @Override
                    public void onCheckedChanged(CompoundButton buttonView,
                                                 boolean isChecked) {
                        // TODO Auto-generated method stub
                        if (isChecked) {
                            if (countstyle < 5) {
                                countstyle++;
                            } else {
                                ToastCustomClass.showToast(getActivity(),
                                        "you can select max 5 styles");
                                chk_selfdriving.setChecked(false);
                            }
                        } else {
                            countstyle--;
                        }
                    }
                });
        chk_speed.setOnCheckedChangeListener(new OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView,
                                         boolean isChecked) {
                // TODO Auto-generated method stub
                if (isChecked) {
                    if (countstyle < 5) {
                        countstyle++;
                    } else {
                        ToastCustomClass.showToast(getActivity(),
                                "you can select max 5 styles");
                        chk_speed.setChecked(false);
                    }
                } else {
                    countstyle--;
                }
            }
        });
        chk_suv.setOnCheckedChangeListener(new OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView,
                                         boolean isChecked) {
                // TODO Auto-generated method stub
                if (isChecked) {
                    if (countstyle < 5) {
                        countstyle++;
                    } else {
                        ToastCustomClass.showToast(getActivity(),
                                "you can select max 5 styles");
                        chk_suv.setChecked(false);
                    }
                } else {
                    countstyle--;
                }
            }
        });
        chk_van.setOnCheckedChangeListener(new OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView,
                                         boolean isChecked) {
                // TODO Auto-generated method stub
                if (isChecked) {
                    if (countstyle < 5) {
                        countstyle++;
                    } else {
                        ToastCustomClass.showToast(getActivity(),
                                "you can select max 5 styles");
                        chk_van.setChecked(false);
                    }
                } else {
                    countstyle--;
                }
            }
        });
        chk_hybrid.setOnCheckedChangeListener(new OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView,
                                         boolean isChecked) {
                // TODO Auto-generated method stub
                if (isChecked) {
                    if (countstyle < 5) {
                        countstyle++;
                    } else {
                        ToastCustomClass.showToast(getActivity(),
                                "you can select max 5 styles");
                        chk_hybrid.setChecked(false);
                    }
                } else {
                    countstyle--;
                }
            }
        });
        radio_new.setOnCheckedChangeListener(new OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView,
                                         boolean isChecked) {
                // TODO Auto-generated method stub
                if (isChecked) {
                    radio_under70.setChecked(false);
                    radio_under100.setChecked(false);
                }
            }

        });
        radio_under10.setOnCheckedChangeListener(new OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView,
                                         boolean isChecked) {
                // TODO Auto-generated method stub
                if (isChecked) {
                    radio_under70.setChecked(false);
                    radio_under100.setChecked(false);
                }
            }
        });
        radio_under30.setOnCheckedChangeListener(new OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView,
                                         boolean isChecked) {
                // TODO Auto-generated method stub
                if (isChecked) {
                    radio_under70.setChecked(false);
                    radio_under100.setChecked(false);
                }
            }
        });
        radio_under50.setOnCheckedChangeListener(new OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView,
                                         boolean isChecked) {
                // TODO Auto-generated method stub
                if (isChecked) {
                    radio_under70.setChecked(false);
                    radio_under100.setChecked(false);
                }
            }
        });
        radio_under70.setOnCheckedChangeListener(new OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView,
                                         boolean isChecked) {
                // TODO Auto-generated method stub
                if (isChecked) {
                    radio_new.setChecked(false);
                    radio_under10.setChecked(false);
                    radio_under30.setChecked(false);
                    radio_under50.setChecked(false);
                }
            }
        });
        radio_under100
                .setOnCheckedChangeListener(new OnCheckedChangeListener() {

                    @Override
                    public void onCheckedChanged(CompoundButton buttonView,
                                                 boolean isChecked) {
                        // TODO Auto-generated method stub
                        if (isChecked) {
                            radio_new.setChecked(false);
                            radio_under10.setChecked(false);
                            radio_under30.setChecked(false);
                            radio_under50.setChecked(false);
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
        // TODO Auto-generated method stub
        back.setOnClickListener(this);
        buton_iamcar.setOnClickListener(this);
        icHome.setOnClickListener(this);
    }

    @Override
    public boolean onBackPressedListener() {
        // TODO Auto-generated method stub
        return false;
    }

    public void setresponce(JSONObject jObject) {
        // TODO Auto-generated method stub
        int colur = 0, trasmisionr1 = 0, enginepower1 = 0, prefrencer = 0, sendamericanr = 0, sendasianr = 0, sendeuropianr = 0;
        if (jObject.optInt("status") == 1 && jObject.optInt("dataflow") == 1) {
            try {    //
                if (!jObject.optString("year_range").equals("")) {
                    minseekvalue.setText(jObject.optString("year_range"));
                    maxseekvalue.setText(jObject.optString("year_range_max"));
                }
                try {
                    doubleslider.getThumb(0).setValue(Integer.parseInt(jObject.optString("year_range")) - 1990);
                    doubleslider.getThumb(1).setValue(Integer.parseInt(jObject.optString("year_range_max")) - 1990);
                    //	doubleslider.setMin(Integer.parseInt(jObject.optString("year_range")) - 1990);
                    //doubleslider.setMax(Integer.parseInt(jObject.optString("year_range_max")) - 1990);
                    jsonarraycolour = jObject.optJSONArray("color");
                    if (jsonarraycolour.length() > 0) {
                        for (int i = 0; i < jsonarraycolour.length(); i++) {
                            listcolour.add(jsonarraycolour.getString(i).toString());
                        }
                        spiner_colour.setSelection(listcolour);
                    }

                    trasmision = jObject.optString("transmission");
                    for (int i = 0; i < categories11.size(); i++) {
                        if (categories11.get(i).equals(trasmision)) {
                            trasmisionr1 = i;
                        }
                    }
                    spiner_trsmision.setSelection(trasmisionr1);

                    /*enginepower = jObject.optString("engine_power");
                    for (int i = 0; i < categories12.size(); i++) {
                        if (categories12.get(i).equals(enginepower)) {
                            enginepower1 = i;
                        }
                    }
                    spiner_enginepower.setSelection(enginepower1);*/

                    jsonarrayprefrences = jObject.optJSONArray("preferences");
                    if (jsonarrayprefrences.length() > 0) {
                        for (int i = 0; i < jsonarrayprefrences.length(); i++) {
                            listprefrences.add(jsonarrayprefrences.getString(i).toString());
                        }
                        spiner_prefrence.setSelection(listprefrences);
                    }

                    americanjsonarray = jObject.optJSONArray("brand_americans");
                    if (americanjsonarray.length() > 0) {
                        for (int i = 0; i < americanjsonarray.length(); i++) {
                            listamerican.add(americanjsonarray.getString(i).toString());
                        }
                        spiner_americns.setSelection(listamerican);
                    }


                    ashianjsonarray = jObject.optJSONArray("brand_asians");
                    if (ashianjsonarray.length() > 0) {
                        for (int i = 0; i < ashianjsonarray.length(); i++) {
                            listashian.add(ashianjsonarray.getString(i).toString());
                        }
                        spiner_asians.setSelection(listashian);
                    }

                    europianjsonarray = jObject.optJSONArray("brand_europeans");
                    if (europianjsonarray.length() > 0) {
                        for (int i = 0; i < europianjsonarray.length(); i++) {
                            listeuropian.add(europianjsonarray.getString(i).toString());
                        }
                        spiner_europins.setSelection(listeuropian);
                    }

                    //text_seekbar
                } catch (NumberFormatException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                milage = jObject.optString("mileage");
                if (milage.equals("new")) {
                    radio_new.setChecked(true);
                } else if (milage.equals("Under 10k")) {
                    radio_under10.setChecked(true);
                } else if (milage.equals("Under 30k")) {
                    radio_under30.setChecked(true);
                } else if (milage.equals("Under 50k")) {
                    radio_under50.setChecked(true);
                } else if (milage.equals("Under 70k")) {
                    radio_under70.setChecked(true);
                } else if (milage.equals("Under 100k")) {
                    radio_under100.setChecked(true);
                }

                JSONArray array = jObject.optJSONArray("type");
                //System.out.println("arraysize=" + array.length());
                if (array != null && array.length() > 0) {
                    for (int i = 0; i < array.length(); i++) {
                        if (array.get(i).equals("New")) {
                            chk_new.setChecked(true);
                        }
                        if (array.get(i).equals("Certified")) {
                            chk_certified.setChecked(true);
                        }
                        if (array.get(i).equals("Pre-owned")) {
                            chk_preowned.setChecked(true);
                        }
                        if (array.get(i).equals("Lease")) {
                            chk_lease.setChecked(true);
                        }
                        if (array.get(i).equals("Anything reliable")) {
                            chk_anything.setChecked(true);
                        }
                    }
                }

                JSONArray array1 = jObject.optJSONArray("styles");
                if (array1 != null && array1.length() > 0) {
                    for (int i1 = 0; i1 < array1.length(); i1++) {
                        if (array1.get(i1).equals("Economy")) {
                            chk_economy.setChecked(true);
                        }
                        if (array1.get(i1).equals("SUV")) {
                            chk_suv.setChecked(true);
                        }
                        if (array1.get(i1).equals("Sedan")) {
                            chk_sedan.setChecked(true);
                        }
                        if (array1.get(i1).equals("Convertible")) {
                            chk_convertible.setChecked(true);
                        }
                        if (array1.get(i1).equals("Coupe")) {
                            chk_coupe.setChecked(true);
                        }
                        if (array1.get(i1).equals("Luxery")) {
                            chk_luxery.setChecked(true);
                        }
                        if (array1.get(i1).equals("3rd row seat")) {
                            chk_rdrowseat.setChecked(true);
                        }
                        if (array1.get(i1).equals("Hatchback")) {
                            chk_hachback.setChecked(true);
                        }
                        if (array1.get(i1).equals("Speed")) {
                            chk_speed.setChecked(true);
                        }
                        if (array1.get(i1).equals("Van")) {
                            chk_van.setChecked(true);
                        }
                        if (array1.get(i1).equals("HD truck")) {
                            chk_hdtruck.setChecked(true);
                        }
                        if (array1.get(i1).equals("Electric")) {
                            chk_electric.setChecked(true);
                        }
                        if (array1.get(i1).equals("Self driving")) {
                            chk_selfdriving.setChecked(true);
                        }
                        if (array1.get(i1).equals("Hybrid")) {
                            chk_hybrid.setChecked(true);
                        }
                    }
                }
            } catch (JSONException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    public void setresponcefinal(JSONObject jObject) {
        // TODO Auto-generated method stub
        if (jObject.optInt("status") == 1 && jObject.optInt("dataflow") == 1) {
            Intent intent2 = new Intent(getActivity(),
                    SearchActivityBuyer.class)
                    .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            getActivity().finishAffinity();
            startActivity(intent2);
        }
    }

}
