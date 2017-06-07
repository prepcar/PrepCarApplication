package com.dev.prepcarapplication;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.dev.prepcarapplication.baseClasses.BaseFragmentActivity;
import com.dev.prepcarapplication.baseClasses.Constants;
import com.dev.prepcarapplication.fragment.FragmentDelaerSelectCar;

import org.json.JSONObject;

public class DealerSelectCarActivity extends BaseFragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_container);
        Intent i = getIntent();
        Bundle extras = i.getExtras();
        replaceFragement(FragmentDelaerSelectCar.newInstanse(extras), FragmentDelaerSelectCar.TAG);
    }

    @Override
    protected void initControls(Bundle savedInstanceState) {

    }

    @Override
    protected void setValueOnUI() {

    }
    @Override
    protected Boolean callBackFromApi(Object object, Activity act,
                                      int requstCode) {
        if (super.callBackFromApi(object, act, requstCode)) {
            commonCallBack(object, requstCode, act);
        }
        return true;
    }

    @Override
    protected Boolean callBackFromApi(Object object, Fragment fragment,
                                      int requstCode) {
        //	if (super.callBackFromApi(object, fragment, requstCode)) {
        commonCallBack(object, requstCode, fragment);
        //	}
        return true;
    }

    private void commonCallBack(Object object, int requestCode, Object fragment2) {
        JSONObject jObject;
        String message = "";
        jObject = (JSONObject) object;
        message = jObject.optString(Constants.message);
        switch (requestCode) {
            case Constants.carlist:
                if (jObject.optInt("dataFlow") == Constants.FLOW) {
                    ((FragmentDelaerSelectCar)fragment2).setresponce(jObject);
                } else {
                    showDialog(message);
                }
                break;
            case Constants.createdeal:
                if (jObject.optInt("dataflow") == Constants.FLOW) {
                    ((FragmentDelaerSelectCar)fragment2).setresponceDeal(jObject);
                } else {
                    showDialog(message);
                }
                break;
        }
    }
    public void showDialog(String msg){
        final AlertDialog.Builder builder = new AlertDialog.Builder(DealerSelectCarActivity.this);
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
