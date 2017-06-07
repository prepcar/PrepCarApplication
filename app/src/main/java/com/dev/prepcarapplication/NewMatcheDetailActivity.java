package com.dev.prepcarapplication;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.dev.prepcarapplication.baseClasses.BaseFragmentActivity;
import com.dev.prepcarapplication.baseClasses.Constants;
import com.dev.prepcarapplication.fragment.FragmentNewMatcheDetail;

import org.json.JSONObject;

public class NewMatcheDetailActivity extends BaseFragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_container);
        Intent i = getIntent();
        Bundle extras = i.getExtras();
        replaceFragement(FragmentNewMatcheDetail.newInstanse(extras),FragmentNewMatcheDetail.TAG);
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
            case Constants.detailnewmatches:
                if (jObject.optInt("dataFlow", Constants.NOT_FLOW) == Constants.FLOW) {
                    ((FragmentNewMatcheDetail)fragment2).setresponce(jObject);
                } else {
                    showDialog(message);
                }
                break;



        }
    }
    public void showDialog(String msg){
        final AlertDialog.Builder builder = new AlertDialog.Builder(NewMatcheDetailActivity.this);
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
