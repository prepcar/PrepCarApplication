package com.dev.prepcarapplication;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.dev.prepcarapplication.baseClasses.BaseFragmentActivity;
import com.dev.prepcarapplication.baseClasses.Constants;
import com.dev.prepcarapplication.fragment.FragmentTestDriveDealer;
import com.dev.prepcarapplication.preference.MySharedPreferences;

import org.json.JSONObject;

public class TestDriveActivityDealer extends BaseFragmentActivity {
    RelativeLayout lin_menu, lin_search, lin_carplan, lin_chat, lin_profile;
    ImageView image_menu, image_search, image_carplan, image_chat, image_dot, image_profile;
    int role;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_container_search_buyer);
        initControls(savedInstanceState);
        replaceFragement(FragmentTestDriveDealer.newInstanse(null), FragmentTestDriveDealer.TAG);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void initControls(Bundle savedInstanceState) {
        lin_carplan = (RelativeLayout) findViewById(R.id.lin_carplan);
        lin_chat = (RelativeLayout) findViewById(R.id.lin_chat);
        lin_menu = (RelativeLayout) findViewById(R.id.lin_menu);
        lin_profile = (RelativeLayout) findViewById(R.id.lin_profile);
        lin_search = (RelativeLayout) findViewById(R.id.lin_search);
        image_carplan = (ImageView) findViewById(R.id.image_carplan);
        image_chat = (ImageView) findViewById(R.id.image_chat);
        image_menu = (ImageView) findViewById(R.id.image_menu);
        image_profile = (ImageView) findViewById(R.id.image_profile);
        image_search = (ImageView) findViewById(R.id.image_search);
        lin_search.setBackgroundColor(Color.parseColor("#c4c4c4"));
        image_search.setBackgroundResource(R.drawable.icon_white_calender);
        image_dot = (ImageView) findViewById(R.id.image_dot);


        if (!MySharedPreferences.getInstance().getBoolean(TestDriveActivityDealer.this, Constants.isUnreadNotification, false))
            image_dot.setVisibility(View.GONE);

        role = MySharedPreferences.getInstance().getInteger(TestDriveActivityDealer.this, Constants.role_id, 0);
        Log.e(TAG, "initControls: " + role);
        if (role == 4) {
            image_search.setBackgroundResource(R.drawable.icon_search);
            image_menu.setBackgroundResource(R.drawable.icon_manu);
        } else {
            image_search.setBackgroundResource(R.drawable.icon_white_calender);
            image_menu.setBackgroundResource(R.drawable.icon_home);
        }
        lin_carplan.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                lin_menu.setBackgroundColor(Color.parseColor("#FFFFFF"));
                image_menu.setBackgroundResource(R.drawable.icon_manu);

                lin_search.setBackgroundColor(Color.parseColor("#FFFFFF"));
                if (role == 4) {
                    image_search.setBackgroundResource(R.drawable.icon_search);
                    image_menu.setBackgroundResource(R.drawable.icon_manu);
                } else {
                    image_search.setBackgroundResource(R.drawable.icon_calender);
                    image_menu.setBackgroundResource(R.drawable.icon_home);
                }

                lin_chat.setBackgroundColor(Color.parseColor("#FFFFFF"));
                image_chat.setBackgroundResource(R.drawable.icon_notification);
                lin_profile.setBackgroundColor(Color.parseColor("#FFFFFF"));
                image_profile.setBackgroundResource(R.drawable.icon_user);

                if (role == 4) {
                    Intent intent2 = new Intent(TestDriveActivityDealer.this,
                            CarPlanActivity.class)
                            .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    TestDriveActivityDealer.this.finishAffinity();
                    startActivity(intent2);
                    finish();
                }
            }
        });
        lin_chat.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                lin_chat.setBackgroundColor(Color.parseColor("#c4c4c4"));
                image_chat.setBackgroundResource(R.drawable.icon_white_notification);

                lin_search.setBackgroundColor(Color.parseColor("#FFFFFF"));
                image_search.setBackgroundResource(R.drawable.icon_calender);

                lin_menu.setBackgroundColor(Color.parseColor("#FFFFFF"));
                image_menu.setBackgroundResource(R.drawable.icon_home);

                lin_profile.setBackgroundColor(Color.parseColor("#FFFFFF"));
                image_profile.setBackgroundResource(R.drawable.icon_user);
                startMyActivity(NotificationActivityBuyer.class, null);
                finish();
            }
        });
        lin_menu.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                lin_menu.setBackgroundColor(Color.parseColor("#c4c4c4"));
                image_menu.setBackgroundResource(R.drawable.icon_white_home);

                lin_search.setBackgroundColor(Color.parseColor("#FFFFFF"));
                image_search.setBackgroundResource(R.drawable.icon_search);

                lin_chat.setBackgroundColor(Color.parseColor("#FFFFFF"));
                image_chat.setBackgroundResource(R.drawable.icon_notification);

                lin_profile.setBackgroundColor(Color.parseColor("#FFFFFF"));
                image_profile.setBackgroundResource(R.drawable.icon_user);
                if (role == 4) {
                    Intent intent2 = new Intent(TestDriveActivityDealer.this,
                            NavigateActivityBuyer.class)
                            .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    TestDriveActivityDealer.this.finishAffinity();
                    startActivity(intent2);
                } else {
                    Intent intent2 = new Intent(TestDriveActivityDealer.this,
                            NavigateActivityDealer.class)
                            .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    TestDriveActivityDealer.this.finishAffinity();
                    startActivity(intent2);
                }
            }
        });


        lin_profile.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                lin_profile.setBackgroundColor(Color.parseColor("#c4c4c4"));
                image_profile.setBackgroundResource(R.drawable.icon_white_usere);

                lin_search.setBackgroundColor(Color.parseColor("#FFFFFF"));
                image_search.setBackgroundResource(R.drawable.icon_search);

                lin_chat.setBackgroundColor(Color.parseColor("#FFFFFF"));
                image_chat.setBackgroundResource(R.drawable.icon_notification);

                lin_menu.setBackgroundColor(Color.parseColor("#FFFFFF"));
                image_menu.setBackgroundResource(R.drawable.icon_manu);
                if (role == 4) {
                    Intent intent2 = new Intent(TestDriveActivityDealer.this,
                            ProfileActivityBuyer.class)
                            .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    TestDriveActivityDealer.this.finishAffinity();
                    startActivity(intent2);
                } else {
                    Intent intent2 = new Intent(TestDriveActivityDealer.this,
                            ProfileActivityDealer.class)
                            .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    TestDriveActivityDealer.this.finishAffinity();
                    startActivity(intent2);
                }
            }
        });
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

        commonCallBack(object, requstCode, fragment);

        return true;
    }

    private void commonCallBack(Object object, int requestCode, Object fragment2) {
        JSONObject jObject;
        String message = "";
        jObject = (JSONObject) object;
        message = jObject.optString(Constants.message);
        switch (requestCode) {

            case Constants.allTestDrive:
                if (jObject.optInt(Constants.status, Constants.NOT_FLOW) == Constants.FLOW) {

                    ((FragmentTestDriveDealer) fragment2).setRespponce(jObject);
                } else {

                    showDialog(message);
                }
                break;
            default:
                break;

        }
    }

    public void showDialog(String msg) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(TestDriveActivityDealer.this);
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
