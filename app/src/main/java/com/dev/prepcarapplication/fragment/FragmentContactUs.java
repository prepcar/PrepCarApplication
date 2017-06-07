package com.dev.prepcarapplication.fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.dev.prepcarapplication.ProfileActivityBuyer;
import com.dev.prepcarapplication.ProfileActivityDealer;
import com.dev.prepcarapplication.R;
import com.dev.prepcarapplication.baseClasses.BaseFragment;
import com.dev.prepcarapplication.baseClasses.Constants;
import com.dev.prepcarapplication.networkTask.ApiManager;
import com.dev.prepcarapplication.preference.MySharedPreferences;

import org.json.JSONObject;

public class FragmentContactUs extends BaseFragment {
    public static String TAG = "EDITPROFILE";
    EditText profile_name, profile_email, profile_message;
    Button btnDone;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        View view = inflater.inflate(R.layout.fragment_contact_us, null);
        if (MySharedPreferences.getInstance().getInteger(getActivity(), Constants.role_id,0)==3){
            ProfileActivityDealer.title_name.setText("Contact us");
        }else{
            ProfileActivityBuyer.title_name.setText("Contact us");
        }
        initUi(view);
        setListener();
        return view;
    }

    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub
        switch (v.getId()) {
            case R.id.profile_submit:
                if (profile_name.getText().toString().trim().equals("")) {
                    profile_name.setError("Please enter name");
                } else if (profile_email.getText().toString().trim().equals("")) {
                    profile_email.setError("Please enter email");
                }else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(profile_email.getText().toString()).matches()){
                    profile_email.setError("Please enter valid email");
                } else if (profile_message.getText().toString().trim().equals("")) {
                    profile_message.setError("Please enter message");
                }else{
                    callingServiceContactUs();
                }
                break;
            default:
                break;
        }
    }

    private void callingServiceContactUs() {
        ApiManager.getInstance().contactUs(this,profile_name.getText().toString(),
                profile_email.getText().toString(),
                profile_message.getText().toString());
    }


    @Override
    protected void initUi(View view) {
        // TODO Auto-generated method stub
        profile_email = (EditText) view.findViewById(R.id.profile_email);
        profile_name = (EditText) view.findViewById(R.id.profile_name);
        profile_message = (EditText) view.findViewById(R.id.profile_message);
        btnDone = (Button) view.findViewById(R.id.profile_submit);

    }

    @Override
    protected void setValueOnUi() {
    }

    @Override
    protected void setListener() {
        // TODO Auto-generated method stub
    btnDone.setOnClickListener(this);
    }

    @Override
    public boolean onBackPressedListener() {
        // TODO Auto-generated method stub
        return false;
    }

    public void setResponseContact(JSONObject jObject) {

        showDialog("Your message is sent successfully");

    }

    public void showDialog(String msg){
        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage(msg)
                .setCancelable(false)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        AlertDialog alert = builder.create();
                        alert.dismiss();
                        profile_email.getText().clear();
                        profile_name.getText().clear();
                        profile_message.getText().clear();
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();
    }
}
