package com.dev.prepcarapplication.fragment;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.dev.prepcarapplication.CarPlanActivity;
import com.dev.prepcarapplication.R;
import com.dev.prepcarapplication.baseClasses.BaseFragment;
import com.dev.prepcarapplication.bean.NewMatchesBean;
import com.dev.prepcarapplication.networkTask.ApiManager;
import com.dev.prepcarapplication.utilities.ToastCustomClass;
import com.dev.prepcarapplication.utilities.TrakerNEW;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Created by this on 2/16/2017.
 */

public class FragmentFindRangeDealer extends BaseFragment {
    public static String TAG = "FINDDEALER";
    RelativeLayout range_50, range_5, range_15, range_25, range_35;
    String locationNmae = "", miles = "";
    EditText edit_location;
    final int REQUEST_STORAGE = 1;
    TrakerNEW gpsTraker;
    TextView text_dealercount;
    Button range_finddealer;
    ArrayList<NewMatchesBean> arrayListItems;
    CountDownTimer timw;


    public static FragmentFindRangeDealer newInstanse(Bundle bundle) {
        FragmentFindRangeDealer fragment = new FragmentFindRangeDealer();
        if (bundle != null) {
            fragment.setArguments(bundle);
        }
        return fragment;
    }

    private void requestStoragePermission() {
        if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_STORAGE);
        } else {
            //   callingLocation();
            Log.i("Main", "Storage permissions have already been granted. Download the file");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        View view = inflater.inflate(R.layout.fragment_find_range_dealer, null);
        initUi(view);
        setListener();
        requestStoragePermission();

        //Timer();
        return view;
    }

    @Override
    protected void initUi(View view) {
        arrayListItems = new ArrayList<>();
        range_finddealer = (Button) view.findViewById(R.id.range_finddealer);
        text_dealercount = (TextView) view.findViewById(R.id.text_dealercount);
        edit_location = (EditText) view.findViewById(R.id.edit_location);
        range_5 = (RelativeLayout) view.findViewById(R.id.range_5);
        range_15 = (RelativeLayout) view.findViewById(R.id.range_15);
        range_25 = (RelativeLayout) view.findViewById(R.id.range_25);
        range_35 = (RelativeLayout) view.findViewById(R.id.range_35);
        range_50 = (RelativeLayout) view.findViewById(R.id.range_50);
    }

    @Override
    protected void setValueOnUi() {

    }

    @Override
    protected void setListener() {
        range_5.setOnClickListener(this);
        range_15.setOnClickListener(this);
        range_25.setOnClickListener(this);
        range_35.setOnClickListener(this);
        range_50.setOnClickListener(this);
        range_finddealer.setOnClickListener(this);

    }

    @Override
    public void onResume() {
        super.onResume();
        gpsTraker = new TrakerNEW(getActivity());
        if (!gpsTraker.canGetLocation()){
            gpsTraker.showSettingsAlert();
        }else{
            Timer();
        }
    }

    @Override
    public boolean onBackPressedListener() {
        return false;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.range_5:
                miles = "5";
                range_5.setBackgroundResource(R.drawable.rangedealerselect);
                range_15.setBackgroundResource(R.drawable.rangedealer);
                range_25.setBackgroundResource(R.drawable.rangedealer);
                range_35.setBackgroundResource(R.drawable.rangedealer);
                range_50.setBackgroundResource(R.drawable.rangedealer);
                callingServiceDealerCoutnt("5");
                break;
            case R.id.range_15:
                miles = "15";
                range_5.setBackgroundResource(R.drawable.rangedealer);
                range_15.setBackgroundResource(R.drawable.rangedealerselect);
                range_25.setBackgroundResource(R.drawable.rangedealer);
                range_35.setBackgroundResource(R.drawable.rangedealer);
                range_50.setBackgroundResource(R.drawable.rangedealer);
                callingServiceDealerCoutnt("15");
                break;
            case R.id.range_25:
                callServiceByDefault();
                break;
            case R.id.range_35:
                miles = "35";
                range_5.setBackgroundResource(R.drawable.rangedealer);
                range_15.setBackgroundResource(R.drawable.rangedealer);
                range_25.setBackgroundResource(R.drawable.rangedealer);
                range_35.setBackgroundResource(R.drawable.rangedealerselect);
                range_50.setBackgroundResource(R.drawable.rangedealer);
                callingServiceDealerCoutnt("35");
                break;

            case R.id.range_50:
                miles = "50";
                range_5.setBackgroundResource(R.drawable.rangedealer);
                range_15.setBackgroundResource(R.drawable.rangedealer);
                range_25.setBackgroundResource(R.drawable.rangedealer);
                range_35.setBackgroundResource(R.drawable.rangedealer);
                range_50.setBackgroundResource(R.drawable.rangedealerselect);
                callingServiceDealerCoutnt("50");
                break;
            case R.id.range_finddealer:
                if (miles.equals("")) {
                    ToastCustomClass.showToast(getActivity(), "Please select miles");
                } else {
                    /*Bundle bundle = new Bundle();
                    bundle.putString(Constants.miles, miles);
                    bundle.putString(Constants.location, edit_location.getText().toString().trim());
                    Intent intent22 = new Intent(getActivity(),
                            CarPlanActivity.class)
                            .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    intent22.putExtras(bundle);
                    getActivity().finishAffinity();
                    startActivity(intent22);*/
                    callingFindDealer();
                }
                break;
        }
    }

    private void callServiceByDefault() {
        miles = "25";
        range_5.setBackgroundResource(R.drawable.rangedealer);
        range_15.setBackgroundResource(R.drawable.rangedealer);
        range_25.setBackgroundResource(R.drawable.rangedealerselect);
        range_35.setBackgroundResource(R.drawable.rangedealer);
        range_50.setBackgroundResource(R.drawable.rangedealer);
        callingServiceDealerCoutnt("25");
    }

    private void callingFindDealer() {
        ApiManager.getInstance().getFindDelaer(this, miles, edit_location.getText().toString().trim());
    }

    public void callingServiceDealerCoutnt(String mil) {
        ApiManager.getInstance().getFindDelaerCount(this, mil, edit_location.getText().toString().trim());
    }

    public String getLocationName(double lattitude, double longitude) {

        String cityName = "Not Found";
        Geocoder gcd = new Geocoder(getActivity(), Locale.getDefault());
        try {

            List<Address> addresses;
            addresses = gcd.getFromLocation(lattitude, longitude,
                    1);

            for (Address adrs : addresses) {
                if (adrs != null) {

                    String city = adrs.getLocality();
                    String state = adrs.getSubLocality();
                    String country = adrs.getCountryName();
                    if (city != null && !city.equals("")) {
                        cityName = " "+city + ", " + country;
                        System.out.println("city ::  " + cityName);
                    } else {

                    }
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return cityName;

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_STORAGE) {
            // If request is cancelled, the result arrays are empty.
            //  callingLocation();
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Timer();
            } else {

            }
        }
    }

    public void callingLocation() {
        new Thread(new Runnable() {
            public void run() {
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        System.out.println("loc===" + getLocationName(gpsTraker.getLatitude(), gpsTraker.getLongitude()));
                        edit_location.setText(getLocationName(gpsTraker.getLatitude(), gpsTraker.getLongitude()));
                    }
                });
            }
        }).start();
    }


    public void setrespponce(JSONObject jObject) {
        if (jObject.optInt("status") == 1 && jObject.optInt("dataflow") == 1) {
            text_dealercount.setText(String.valueOf(jObject.optInt("count")));
        }
    }

    public void Timer() {
        timw = new CountDownTimer(1000, 1000) {
            public void onTick(long millisUntilFinished) {
            }

            public void onFinish() {

                edit_location.setText(getLocationName(gpsTraker.getLatitude(), gpsTraker.getLongitude()));
                if (!edit_location.getText().toString().equals("") || !edit_location.getText().toString().equalsIgnoreCase("not found")){
                    callServiceByDefault();
                }

            }
        }.start();
    }

    public void setresponceFind(JSONObject jObject) {
        if (jObject.optInt("status") == 1 && jObject.optInt("dataflow") == 1) {
            JSONArray data = jObject.optJSONArray("data");

            Toast.makeText(getActivity(), "Your car plan successfully sent to dealer", Toast.LENGTH_SHORT).show();
            startMyActivity(CarPlanActivity.class,null);
            getActivity().finish();
        }

    }

}
