package com.dev.prepcarapplication;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dev.prepcarapplication.baseClasses.BaseFragmentActivity;
import com.dev.prepcarapplication.baseClasses.Constants;
import com.dev.prepcarapplication.fragment.FragmentCarList;
import com.dev.prepcarapplication.fragment.FragmentChangePassword;
import com.dev.prepcarapplication.fragment.FragmentContactUs;
import com.dev.prepcarapplication.fragment.FragmentEditProfile;
import com.dev.prepcarapplication.preference.MySharedPreferences;

import org.json.JSONObject;

public class ProfileActivityBuyer extends BaseFragmentActivity {
    public static TextView title_name;
    RelativeLayout rel_changepass, rel_profile, rel_carlist;
    TextView text_changepass, text_profile, text_carlist, text_logout;
    RelativeLayout lin_menu, lin_search, lin_carplan, lin_chat, lin_profile;
    ImageView image_menu, image_search, image_carplan, image_chat, image_dot, image_profile;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_container_profile_buyer);
        initControls(savedInstanceState);
        Bundle bundle = new Bundle();
        bundle.putString(Constants.type, "4");
        if (MySharedPreferences.getInstance().getBoolean(ProfileActivityBuyer.this, Constants.fb, false)) {
            replaceFragement(FragmentEditProfile.newInstanse(bundle), FragmentEditProfile.TAG);
        } else {
            replaceFragement(FragmentChangePassword.newInstanse(bundle), FragmentChangePassword.TAG);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //	getMenuInflater().inflate(R.menu.profile, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void initControls(Bundle savedInstanceState) {
        text_logout = (TextView) findViewById(R.id.text_logout);
        lin_carplan = (RelativeLayout) findViewById(R.id.lin_carplan);
        lin_chat = (RelativeLayout) findViewById(R.id.lin_chat);
        lin_menu = (RelativeLayout) findViewById(R.id.lin_menu);
        lin_profile = (RelativeLayout) findViewById(R.id.lin_profile);
        lin_search = (RelativeLayout) findViewById(R.id.lin_search);
        image_carplan = (ImageView) findViewById(R.id.image_carplan);
        image_chat = (ImageView) findViewById(R.id.image_chat);
        image_dot = (ImageView) findViewById(R.id.image_dot);
        image_menu = (ImageView) findViewById(R.id.image_menu);
        image_profile = (ImageView) findViewById(R.id.image_profile);
        image_search = (ImageView) findViewById(R.id.image_search);
        lin_profile.setBackgroundColor(Color.parseColor("#c4c4c4"));
        image_profile.setBackgroundResource(R.drawable.icon_white_usere);

        if (!MySharedPreferences.getInstance().getBoolean(this, Constants.isUnreadNotification, false))
            image_dot.setVisibility(View.GONE);

        text_logout.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                MySharedPreferences.getInstance().putBooleanKeyValue(ProfileActivityBuyer.this, Constants.fb, false);
                MySharedPreferences.getInstance().putBooleanKeyValue(ProfileActivityBuyer.this, Constants.ISREMEMBER, false);
                MySharedPreferences.getInstance().putIntegerKeyValue(ProfileActivityBuyer.this, Constants.USER_ID, 0);
                MySharedPreferences.getInstance().putIntegerKeyValue(ProfileActivityBuyer.this, Constants.role_id, 0);
                MySharedPreferences.getInstance().putStringKeyValue(ProfileActivityBuyer.this, Constants.EMAIL, "");
                Intent intent2 = new Intent(ProfileActivityBuyer.this,
                        BeforeLoginActivity.class)
                        .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                ProfileActivityBuyer.this.finishAffinity();
                startActivity(intent2);

            }
        });
        lin_carplan.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                lin_menu.setBackgroundColor(Color.parseColor("#FFFFFF"));
                image_menu.setBackgroundResource(R.drawable.icon_search);

                lin_search.setBackgroundColor(Color.parseColor("#FFFFFF"));
                image_search.setBackgroundResource(R.drawable.icon_search);

                lin_chat.setBackgroundColor(Color.parseColor("#FFFFFF"));
                image_chat.setBackgroundResource(R.drawable.icon_notification);

                lin_profile.setBackgroundColor(Color.parseColor("#FFFFFF"));
                image_profile.setBackgroundResource(R.drawable.icon_user);

                Intent intent2 = new Intent(ProfileActivityBuyer.this,
                        CarPlanActivity.class)
                        .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                ProfileActivityBuyer.this.finishAffinity();
                startActivity(intent2);
                finish();
            }
        });
        lin_chat.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                lin_chat.setBackgroundColor(Color.parseColor("#c4c4c4"));
                image_chat.setBackgroundResource(R.drawable.icon_white_notification);

                lin_search.setBackgroundColor(Color.parseColor("#FFFFFF"));
                image_search.setBackgroundResource(R.drawable.icon_search);

                lin_menu.setBackgroundColor(Color.parseColor("#FFFFFF"));
                image_menu.setBackgroundResource(R.drawable.icon_manu);

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
                image_menu.setBackgroundResource(R.drawable.icon_white_manu);

                lin_search.setBackgroundColor(Color.parseColor("#FFFFFF"));
                image_search.setBackgroundResource(R.drawable.icon_search);

                lin_chat.setBackgroundColor(Color.parseColor("#FFFFFF"));
                image_chat.setBackgroundResource(R.drawable.icon_notification);

                lin_profile.setBackgroundColor(Color.parseColor("#FFFFFF"));
                image_profile.setBackgroundResource(R.drawable.icon_user);

                Intent intent2 = new Intent(ProfileActivityBuyer.this,
                        NavigateActivityBuyer.class)
                        .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                ProfileActivityBuyer.this.finishAffinity();
                startActivity(intent2);
            }
        });

        lin_search.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                lin_search.setBackgroundColor(Color.parseColor("#c4c4c4"));
                image_search.setBackgroundResource(R.drawable.icon_white_search);

                lin_menu.setBackgroundColor(Color.parseColor("#FFFFFF"));
                image_menu.setBackgroundResource(R.drawable.icon_manu);

                lin_chat.setBackgroundColor(Color.parseColor("#FFFFFF"));
                image_chat.setBackgroundResource(R.drawable.icon_notification);

                lin_profile.setBackgroundColor(Color.parseColor("#FFFFFF"));
                image_profile.setBackgroundResource(R.drawable.icon_user);
                Intent intent22 = new Intent(ProfileActivityBuyer.this,
                        SearchActivityBuyer.class)
                        .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                ProfileActivityBuyer.this.finishAffinity();
                startActivity(intent22);
            }
        });
        text_carlist = (TextView) findViewById(R.id.text_carlist);
        text_changepass = (TextView) findViewById(R.id.text_changepass);
        text_profile = (TextView) findViewById(R.id.text_profile);
        title_name = (TextView) findViewById(R.id.title_name);

        rel_carlist = (RelativeLayout) findViewById(R.id.rel_carlist);
        rel_changepass = (RelativeLayout) findViewById(R.id.rel_changepass);
        rel_profile = (RelativeLayout) findViewById(R.id.rel_profile);
        text_changepass.setTextColor(Color.parseColor("#293A92"));
        text_profile.setTextColor(Color.parseColor("#c4c4c4"));
        text_carlist.setTextColor(Color.parseColor("#c4c4c4"));
        if (MySharedPreferences.getInstance().getBoolean(ProfileActivityBuyer.this, Constants.fb, false)) {
            rel_changepass.setVisibility(View.GONE);
            title_name.setText("Edit Profile");
        } else {
            title_name.setText("Change Password");
        }
        rel_changepass.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                title_name.setText("Change Password");
                text_changepass.setTextColor(Color.parseColor("#293A92"));
                text_profile.setTextColor(Color.parseColor("#c4c4c4"));
                text_carlist.setTextColor(Color.parseColor("#c4c4c4"));
                replaceFragement(FragmentChangePassword.newInstanse(null), FragmentChangePassword.TAG);
            }
        });
        rel_profile.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                title_name.setText("Profile");
                text_changepass.setTextColor(Color.parseColor("#c4c4c4"));
                text_profile.setTextColor(Color.parseColor("#293A92"));
                text_carlist.setTextColor(Color.parseColor("#c4c4c4"));
                Bundle bundle = new Bundle();
                bundle.putString(Constants.type, "4");
                replaceFragement(FragmentEditProfile.newInstanse(bundle), FragmentEditProfile.TAG);
            }
        });

        rel_carlist.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                title_name.setText("Car List");
                text_changepass.setTextColor(Color.parseColor("#c4c4c4"));
                text_profile.setTextColor(Color.parseColor("#c4c4c4"));
                text_carlist.setTextColor(Color.parseColor("#293A92"));
                Bundle bundle = new Bundle();
                bundle.putString(Constants.type, "4");
                replaceFragement(FragmentCarList.newInstanse(bundle), FragmentCarList.TAG);
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

            case Constants.changPasword:
                if (jObject.optInt(Constants.status, Constants.NOT_FLOW) == Constants.FLOW) {
                    ((FragmentChangePassword) fragment2).setrespponce(jObject);
                } else {

                    showDialog(message);
                }
                break;
            case Constants.getprofile:
                if (jObject.optInt(Constants.dataToFollow, Constants.NOT_FLOW) == Constants.FLOW) {
                    ((FragmentEditProfile) fragment2).setrespponce(jObject);
                } else {

                    showDialog(message);
                }
                break;
            case Constants.editProfile:
                if (jObject.optInt(Constants.dataToFollow, Constants.NOT_FLOW) == Constants.FLOW) {
                    ((FragmentEditProfile) fragment2).setrespponceEditprofile(jObject);
                } else {

                    showDialog(message);
                }
                break;
            case Constants.carlist:
                if (jObject.optInt("dataFlow", Constants.NOT_FLOW) == Constants.FLOW) {
                    ((FragmentCarList) fragment2).setrespponce(jObject);
                } else {
                    showDialog(message);
                }
                break;
            case Constants.contactUs:
                if (jObject.optInt("dataflow", Constants.NOT_FLOW) == Constants.FLOW) {
                    ((FragmentContactUs) fragment2).setResponseContact(jObject);
                } else {
                    showDialog(message);
                }
                break;
        }
    }

    public void showDialog(String msg) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(ProfileActivityBuyer.this);
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
