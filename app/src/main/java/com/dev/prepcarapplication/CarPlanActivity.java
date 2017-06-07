package com.dev.prepcarapplication;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dev.prepcarapplication.baseClasses.BaseFragmentActivity;
import com.dev.prepcarapplication.baseClasses.Constants;
import com.dev.prepcarapplication.fragment.FragmentFavorites;
import com.dev.prepcarapplication.fragment.FragmentNewMatches;
import com.dev.prepcarapplication.preference.MySharedPreferences;

import org.json.JSONObject;

public class CarPlanActivity extends BaseFragmentActivity {
    RelativeLayout rel_newmaches, rel_favorites;
    TextView text_newmaches, text_favorites;
    RelativeLayout lin_menu, lin_search, lin_carplan, lin_chat, lin_profile;
    ImageView image_menu, image_search, image_carplan, image_chat, image_dot, image_profile;
    Bundle extras;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_container_car_plan);
        Intent i = getIntent();
        extras = i.getExtras();
        initControls(savedInstanceState);
        if (extras != null) {
            replaceFragement(FragmentNewMatches.newInstanse(extras), FragmentNewMatches.TAG);
        } else {
            replaceFragement(FragmentNewMatches.newInstanse(null), FragmentNewMatches.TAG);
        }
    }

    @Override
    protected void initControls(Bundle savedInstanceState) {
        rel_newmaches = (RelativeLayout) findViewById(R.id.rel_newmaches);
        rel_favorites = (RelativeLayout) findViewById(R.id.rel_favorites);
        text_favorites = (TextView) findViewById(R.id.text_favorites);
        text_newmaches = (TextView) findViewById(R.id.text_newmaches);
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
        text_newmaches.setTextColor(Color.parseColor("#293A92"));
        if (!MySharedPreferences.getInstance().getBoolean(this, Constants.isUnreadNotification, false))
            image_dot.setVisibility(View.GONE);

        lin_chat.setOnClickListener(new View.OnClickListener() {

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
        lin_menu.setOnClickListener(new View.OnClickListener() {

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

                Intent intent2 = new Intent(CarPlanActivity.this,
                        NavigateActivityBuyer.class)
                        .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                CarPlanActivity.this.finishAffinity();
                startActivity(intent2);
            }
        });
        lin_profile.setOnClickListener(new View.OnClickListener() {
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
                Intent intent2 = new Intent(CarPlanActivity.this,
                        ProfileActivityBuyer.class)
                        .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                CarPlanActivity.this.finishAffinity();
                startActivity(intent2);
            }
        });

        lin_search.setOnClickListener(new View.OnClickListener() {

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
                Intent intent22 = new Intent(CarPlanActivity.this,
                        SearchActivityBuyer.class)
                        .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                CarPlanActivity.this.finishAffinity();
                startActivity(intent22);
            }
        });

        rel_newmaches.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                text_newmaches.setTextColor(Color.parseColor("#293A92"));
                text_favorites.setTextColor(Color.parseColor("#c4c4c4"));
                if (extras != null) {
                    replaceFragement(FragmentNewMatches.newInstanse(extras), FragmentNewMatches.TAG);
                } else {
                    replaceFragement(FragmentNewMatches.newInstanse(null), FragmentNewMatches.TAG);
                }
            }
        });
        rel_favorites.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                text_newmaches.setTextColor(Color.parseColor("#c4c4c4"));
                text_favorites.setTextColor(Color.parseColor("#293A92"));
                replaceFragement(FragmentFavorites.newInstanse(null), FragmentFavorites.TAG);
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
        if (super.callBackFromApi(object, fragment, requstCode)) {
            commonCallBack(object, requstCode, fragment);
        }
        return true;
    }

    private void commonCallBack(Object object, int requestCode, Object fragment2) {
        JSONObject jObject;
        String message;
        jObject = (JSONObject) object;
        message = jObject.optString(Constants.message);
        switch (requestCode) {
            case Constants.getNewMATCHES:
                if (jObject.optInt("dataflow", Constants.NOT_FLOW) == Constants.FLOW) {
                    ((FragmentNewMatches) fragment2).setresponce(jObject);
                } else {
                    showDialog(message);
                }
                break;
            case Constants.findMatches:
                if (jObject.optInt("dataflow", Constants.NOT_FLOW) == Constants.FLOW) {
                    ((FragmentNewMatches) fragment2).setresponce(jObject);
                } else {
                    showDialog(message);
                }
                break;
            case Constants.setfav:
                if (jObject.optInt("dataflow", Constants.NOT_FLOW) == Constants.FLOW) {
                    ((FragmentFavorites) fragment2).setresponceFav(jObject);
                }
                break;
            case Constants.setRating:
                if (jObject.optInt("dataflow", Constants.NOT_FLOW) == Constants.FLOW) {
                    Log.e(TAG, "commonCallBack: " + jObject.toString() + "dfdslfk");
                }
                break;
            default:
                break;


        }
    }

    public void showDialog(String msg) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(CarPlanActivity.this);
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
