package com.dev.prepcarapplication;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.dev.prepcarapplication.baseClasses.BaseFragmentActivity;
import com.dev.prepcarapplication.baseClasses.Constants;
import com.dev.prepcarapplication.bean.TestDriveModel;
import com.dev.prepcarapplication.fragment.FragmentTestDriveDetail;

import org.json.JSONObject;

public class DealerTestDriveDetailActivity extends BaseFragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent i = getIntent();
        TestDriveModel extras = (TestDriveModel) i.getSerializableExtra(Constants.testdrivedata);
        Bundle bundle = new Bundle();
        bundle.putSerializable(Constants.testdrivedata,extras);
        setContentView(R.layout.activity_container);
        replaceFragement(FragmentTestDriveDetail.newInstanse(bundle), FragmentTestDriveDetail.TAG);
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

            case Constants.testDriveRequest:
                if (jObject.optInt("dataFlow") == Constants.FLOW) {
                    ((FragmentTestDriveDetail)fragment2).setresponceDeclined(jObject);

                } else {
                    showDialog(message);
                }
                break;
            case Constants.testDriveAppontmentStatus:
                if (jObject.optInt("dataFlow") == Constants.FLOW) {
                    ((FragmentTestDriveDetail)fragment2).setResponseAppointment(jObject);
                } else {
                    showDialog(message);
                }
                break;
            default:
                break;
        }
    }
    public void showDialog(String msg){
        final AlertDialog.Builder builder = new AlertDialog.Builder(DealerTestDriveDetailActivity.this);
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

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startMyActivity(TestDriveActivityDealer.class,null);
        finish();
    }
}
