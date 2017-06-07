package com.dev.prepcarapplication.fragment;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.darsh.multipleimageselect.activities.AlbumSelectActivity;
import com.darsh.multipleimageselect.models.Image;
import com.dev.prepcarapplication.NavigateActivityDealer;
import com.dev.prepcarapplication.ProfileActivityDealer;
import com.dev.prepcarapplication.R;
import com.dev.prepcarapplication.adapter.GridViewAdapter;
import com.dev.prepcarapplication.adapter.MultiSelectionSpinner;
import com.dev.prepcarapplication.baseClasses.BaseFragment;
import com.dev.prepcarapplication.baseClasses.Constants;
import com.dev.prepcarapplication.bean.BuyerList;
import com.dev.prepcarapplication.networkTask.ApiManager;
import com.dev.prepcarapplication.networkTask.URLsClass;
import com.dev.prepcarapplication.networkTask.WebServiceHandler;
import com.dev.prepcarapplication.networkTask.WebServiceListener;
import com.dev.prepcarapplication.preference.MySharedPreferences;
import com.dev.prepcarapplication.utilities.CameraGallery;
import com.dev.prepcarapplication.utilities.ToastCustomClass;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import eu.janmuller.android.simplecropimage.CropImage;
import okhttp3.MultipartBody;

public class FragmentDealerNewCar extends BaseFragment {
    public static String TAG = "DEALER NEW CAR";
    ArrayList<BuyerList> arrayList, arrayList1, arrayList2;
    EditText spiner_year1, spiner_make1, spiner_model1;
    RelativeLayout lin_manual, lin_automatic;
    EditText  edit_stocknumber, edit_dealer_picpath;
    Spinner edit_exteriorcolur;
    public static Button buton_dealer_browse, buton_uploadcar;
    TextView text_manual, text_automatic;
    String path = "", year = "", make = "", model = "", transmission = "", recyear = "", recmodel = "", recmake = "", reccolur = "", rectrasmision = "", recstocknumber = "";
    static String roleId = "", classType = "", carid = "";
    int REQUEST_STORAGE = 1;
    ImageView back;
    public static int imagesnumber = 0;
    RecyclerView grid_carpic;
    GridViewAdapter adapter;
    JSONArray sendimages,sendOldImages, sendpathjsonarray, reccarpic,jsonArrayFeatures;
    ArrayList<String> imageList;
    public static ArrayList<String> imagesList;
    ArrayList<Image> images;
    String encodedString;
    ArrayList<String> categories1;
    List<String> listcolur;
    AlertDialog alert;
    MultiSelectionSpinner spiner_feature;
    List<String> categories13;

    private void requestStoragePermission() {
        if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_STORAGE);
        } else {
            Log.i("Main", "Storage permissions have already been granted. Download the file");
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (alert!=null && alert.isShowing()){
            alert.dismiss();
        }
    }

    public static FragmentDealerNewCar newInstanse(Bundle bundle) {
        FragmentDealerNewCar fragment = new FragmentDealerNewCar();
        if (bundle != null) {
            fragment.setArguments(bundle);
            if (bundle.containsKey(Constants.type)) {
                roleId = bundle.getString(Constants.type);
                classType = bundle.getString("classtype");
                carid = "";
            } else if (bundle.containsKey("classtype")) {
                classType = bundle.getString("classtype");
                if (bundle.containsKey("carid")) {
                    carid = bundle.getString("carid");
                }else{
                    carid = "";
                }
            } else {
                roleId = "";
                carid = "";
            }
        } else {
            roleId = "";
            carid = "";
        }
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        imagesnumber=0;
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        View view = inflater.inflate(R.layout.fragment_add_new_car,
                null);
        requestStoragePermission();
        initUi(view);
        setListener();
        if (!carid.equals("")) {
            callingServiceGetcardetial();
        }
        return view;
    }


    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub
        switch (v.getId()) {
            case R.id.lin_manual:
                transmission = "manual";
                lin_manual.setBackgroundResource(R.drawable.buttonshape);
                text_manual.setTextColor(Color.WHITE);
                lin_automatic.setBackgroundResource(R.drawable.discoveryshape);
                text_automatic.setTextColor(Color.parseColor("#c4c4c4"));
                break;
            case R.id.lin_automatic:
                transmission = "automatic";
                lin_manual.setBackgroundResource(R.drawable.discoveryshape);
                text_manual.setTextColor(Color.parseColor("#c4c4c4"));
                lin_automatic.setBackgroundResource(R.drawable.buttonshape);
                text_automatic.setTextColor(Color.WHITE);
                break;
            case R.id.buton_dealer_browse:
                chooseSelection();
                break;
            case R.id.buton_uploadcar:
                if (transmission.equals("")) {
                    ToastCustomClass.showToast(getActivity(), "Please select trasmission value");
                }else if (imagesnumber==0){
                    ToastCustomClass.showToast(getActivity(), "Please upload atleast one image of car");
                }else {
                    buton_uploadcar.setClickable(false);
                    callingServiceUploadCar();
                }
                break;
            case R.id.image_back:
                getActivity().finish();
                break;
            default:
                break;
        }
    }

    private void callingServiceUploadCar() {
        JSONArray featureArray = new JSONArray();
        String srFeature="";
        year = spiner_year1.getText().toString().trim();
        make = spiner_make1.getText().toString().trim();
        model = spiner_model1.getText().toString().trim();
        srFeature = spiner_feature.getSelectedItem().toString();
        featureArray = new JSONArray(Arrays.asList(srFeature.split(",")));
        if (year.equals("")){
            ToastCustomClass.showToast(getActivity(),"Enter year of manufacturing");
        }else if (make.equals("")){
            ToastCustomClass.showToast(getActivity(),"Enter maker of the car");
        }else if (model.equals("")){
            ToastCustomClass.showToast(getActivity(),"Enter model of the car");
        }else if(srFeature.equals("")){
            ToastCustomClass.showToast(getActivity(),"Please select features for the car");
        }else {
            GetJsonarray();
            if (carid.equals("")) {
                /*ApiManager.getInstance().getUploadNewCar(this, year, make, model, transmission, edit_exteriorcolur.getSelectedItem().toString(),
                        edit_stocknumber.getText().toString()+"", sendimages.toString(),featureArray.toString());
                */getUploadNewCar(year, make, model, transmission, edit_exteriorcolur.getSelectedItem().toString(),
                        edit_stocknumber.getText().toString()+"", sendimages.toString(),featureArray.toString());
            } else {
                /*ApiManager.getInstance().getEditNewCar(this, year, make, model, transmission, edit_exteriorcolur.getSelectedItem().toString(),
                        edit_stocknumber.getText().toString()+"", sendimages.toString(), carid,featureArray.toString());*/
                getEditNewCar( year, make, model, transmission, edit_exteriorcolur.getSelectedItem().toString(),
                        edit_stocknumber.getText().toString()+"", sendimages.toString(), carid,featureArray.toString());
            }
        }
    }

    private void getEditNewCar(String year, String make, String model, String transmission, String exterior_color, String stock_number, String s2, String carid, String features) {
        MultipartBody.Builder builder;

        builder = WebServiceHandler.createMultiPartBuilder(new String[]{"model_year", "make", "model_type", "transmission", "exterior_color",
                        "stock_number", "features", "dealer_id","car_id","temp_old_url"}
                , new String[]{year,make,
                        model,transmission, exterior_color,
                        stock_number, features,
                        String.valueOf(MySharedPreferences.getInstance().getInteger(getActivity(), Constants.USER_ID,0)),
                        carid,sendOldImages.toString().replace("[","").replace("]","").replace("\"","").replace("\\","")}, imageList,true,"car_pic[]");

        WebServiceHandler webServiceHandler = new WebServiceHandler(getActivity());
        webServiceHandler.serviceListener = new WebServiceListener() {
            @Override
            public void onResponse(final String response) {
                Log.e(TAG, "onResponse: " + response);
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        try {
                             JSONObject jObject = new JSONObject(response);
                            if (jObject.optInt("status") == 1 && jObject.optInt("dataflow") == 1) {
                                showDialog("Your car detail successfully added.");
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                });
            }
        };
        try {
            webServiceHandler.postMultiPart(URLsClass.service_type_car_edit,builder);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void getUploadNewCar(String year, String make, String model, String transmission, String exterior_color, String stock_number, String s2, String features) {
        MultipartBody.Builder builder;

        builder = WebServiceHandler.createMultiPartBuilder(new String[]{"model_year", "make", "model_type", "transmission", "exterior_color",
                        "stock_number", "features", "dealer_id"}
                , new String[]{year,make,
                        model,transmission, exterior_color,
                        stock_number, features,
                        String.valueOf(MySharedPreferences.getInstance().getInteger(getActivity(), Constants.USER_ID,0))}, imageList,true,"car_pic[]");

        WebServiceHandler webServiceHandler = new WebServiceHandler(getActivity());
        webServiceHandler.serviceListener = new WebServiceListener() {
            @Override
            public void onResponse(final String response) {
                Log.e(TAG, "onResponse: " + response);
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        try {
                            final JSONObject object = new JSONObject(response);
                            showDialog("Your car detail successfully updated.");

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                });
            }
        };
        try {
            webServiceHandler.postMultiPart(URLsClass.service_type_car_add,builder);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //temp_old_url
    @Override
    protected void initUi(View view) {
        // TODO Auto-generated method stub
        listcolur=new ArrayList<>();
        sendpathjsonarray = new JSONArray();
        sendimages = new JSONArray();
        sendOldImages = new JSONArray();
        imageList = new ArrayList<>();
        imagesList = new ArrayList<String>();
        grid_carpic = (RecyclerView) view.findViewById(R.id.grid_carpic);
        back = (ImageView) view.findViewById(R.id.image_back);
        arrayList = new ArrayList<BuyerList>();
        arrayList1 = new ArrayList<BuyerList>();
        arrayList2 = new ArrayList<BuyerList>();
        spiner_year1 = (EditText) view.findViewById(R.id.spiner_dyear1);
        spiner_make1 = (EditText) view.findViewById(R.id.spiner_dmake1);
        spiner_feature = (MultiSelectionSpinner) view.findViewById(R.id.et_features);
        spiner_model1 = (EditText) view.findViewById(R.id.spiner_dmodel1);
        edit_dealer_picpath = (EditText) view.findViewById(R.id.edit_picpath);
        edit_exteriorcolur = (Spinner) view.findViewById(R.id.edit_exteriorcolur);
        edit_stocknumber = (EditText) view.findViewById(R.id.edit_stocknumber);
        lin_manual = (RelativeLayout) view.findViewById(R.id.lin_manual);
        lin_automatic = (RelativeLayout) view.findViewById(R.id.lin_automatic);
        text_manual = (TextView) view.findViewById(R.id.text_manual);
        text_automatic = (TextView) view.findViewById(R.id.text_automatic);
        buton_dealer_browse = (Button) view.findViewById(R.id.buton_dealer_browse);
        buton_uploadcar = (Button) view.findViewById(R.id.buton_uploadcar);

        view.findViewById(R.id.icHome).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startMyActivityTop(NavigateActivityDealer.class, null);
                getActivity().finish();
            }
        });

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


        categories13 = new ArrayList<>();
        categories13.add("Sunroof");
        categories13.add("Leather seats");
        categories13.add("Back-up camera");
        categories13.add("Navigation");
        categories13.add("KeyLess entry");
        categories13.add("Apple CarPlay");
        categories13.add("Bluetooth");
        categories13.add("Heated seats");
        categories13.add("Disability equipped");
        categories13.add("Premium audio");
        categories13.add("3rd row seat");

        ArrayAdapter<String> dataAdapter1 = new ArrayAdapter<String>(
                getActivity(), android.R.layout.simple_spinner_item,
                categories1);
        dataAdapter1
                .setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        edit_exteriorcolur.setAdapter(dataAdapter1);

        ArrayAdapter<String> dataAdapter33 = new ArrayAdapter<String>(
                getActivity(), android.R.layout.simple_spinner_item,
                categories13);
        dataAdapter33
                .setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spiner_feature.setItems(categories13);

    }

    @Override
    protected void setValueOnUi() {
    }

    @Override
    protected void setListener() {
        buton_dealer_browse.setOnClickListener(this);
        buton_uploadcar.setOnClickListener(this);
        lin_manual.setOnClickListener(this);
        lin_automatic.setOnClickListener(this);
        back.setOnClickListener(this);
    }

    @Override
    public boolean onBackPressedListener() {
        // TODO Auto-generated method stub
        return false;
    }

    private void callingServiceGetcardetial() {
        ApiManager.getInstance().getCarDetail(this, carid);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 10987) {
            if (data != null) {
                images = data.getParcelableArrayListExtra("images");
                System.out.println("size====" + images.size());
                if (images.size() > 0) {
                    for (int i = 0; i < images.size(); i++) {
                        imagesList.add(imagesList.size(), images.get(i).path);
                    }
                    imagesnumber = imagesList.size();
                    grid_carpic.setHasFixedSize(true);
                    RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity().getApplicationContext(), LinearLayoutManager.HORIZONTAL, false);
                    grid_carpic.setLayoutManager(mLayoutManager);
                    adapter = new GridViewAdapter(getActivity(), imagesList, true, "newcar");
                    grid_carpic.setAdapter(adapter);
                }
                if (imagesList.size() == 5) {
                    buton_dealer_browse.setVisibility(View.GONE);
                }
            }
        }
        if (requestCode == 12) {
            if (data != null) {
                Bitmap photo = (Bitmap) data.getExtras().get("data");
                Uri tempUri = getImageUri(getActivity().getApplicationContext(), photo);
                String realpath = FilePath.getPath(getActivity(), tempUri);
                System.out.println("camerapath====" + realpath);

                if (realpath != null) {
                    imagesList.add(imagesList.size(), realpath);
                    imagesnumber++;
                    grid_carpic.setHasFixedSize(true);
                    RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity().getApplicationContext(), LinearLayoutManager.HORIZONTAL, false);
                    grid_carpic.setLayoutManager(mLayoutManager);
                    adapter = new GridViewAdapter(getActivity(), imagesList, true, "newcar");
                    grid_carpic.setAdapter(adapter);
                }
                if (imagesList.size() == 5) {
                    buton_dealer_browse.setVisibility(View.GONE);
                }
            }
        }


        Log.i("onActivityResult", "resultCode==" + resultCode
                + "  requestCode==" + requestCode + " Activity.RESULT_OK="
                + Activity.RESULT_OK);
        if (resultCode == Activity.RESULT_OK) {
            switch (requestCode) {

                case CameraGallery.REQUEST_CODE_GALLERY: {
                    Log.i("REQUEST_CODE_GALLERY", "REQUEST_CODE_GALLERY");
                    try {
                        CameraGallery cameraAct = new CameraGallery();
                        String realpath;
                        realpath = FilePath.getPath(getActivity(), data.getData());
                        Log.i("PATHSSSSSSSS", "===" + realpath);
                        cameraAct.startCropImage(getActivity(), this, realpath);
                    } catch (Exception e) {
                        Log.e(TAG, "Error while creating temp file", e);
                    }
                    break;
                }
                case CameraGallery.REQUEST_CODE_TAKE_PICTURE: {
                    //	Log.i("REQUEST_CODE_TAKE_PICTURE", "REQUEST_CODE_TAKE_PICTURE");
                    CameraGallery cameraAct = new CameraGallery();
                    if (CameraGallery.mFileTemp != null) {
                        cameraAct.startCropImage(getActivity(), this,
                                CameraGallery.mFileTemp.getPath());
                    }
                    break;
                }
                case CameraGallery.REQUEST_CODE_CROP_IMAGE: {
                    Log.i("REQUEST_CODE_CROP_IMAGE", "REQUEST_CODE_CROP_IMAGE");
                    String path = data.getStringExtra(CropImage.IMAGE_PATH);
                    if (path == null) {
                        return;
                    } else {
                        try {
                            this.path = path;
                            edit_dealer_picpath.setText(path);
                            CameraGallery.mFileTemp = null;
                        } catch (Exception e) {
                            e.printStackTrace();
                            ToastCustomClass.showToast(getActivity(),
                                    "Something went wrong!");
                        }
                    }
                    break;
                }
            }
        }
    }

    public String getStringImage(Bitmap bmp) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] imageBytes = baos.toByteArray();
        String encodedImage = Base64.encodeToString(imageBytes, Base64.DEFAULT);
        return encodedImage;
    }

    public void setresponceupload(JSONObject jObject) {
        // TODO Auto-generated method stub
        buton_uploadcar.setClickable(true);
        if (jObject.optInt("status") == 1 && jObject.optInt("dataflow") == 1) {
            showDialog("Your car detail successfully added.");
        }
    }

    public void showDialog(String msg) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(
                getActivity());
        builder.setMessage(msg).setCancelable(false)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        AlertDialog alert = builder.create();
                        alert.dismiss();

                        if (classType.equals("selectcar")) {
                            getActivity().finish();
                        } else if (classType.equals("carlist")) {
                            Intent intent2 = new Intent(getActivity(),
                                    ProfileActivityDealer.class)
                                    .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            getActivity().finishAffinity();
                            startActivity(intent2);
                        }

                    }
                });
        AlertDialog alert = builder.create();
        alert.show();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_STORAGE) {
            // If request is cancelled, the result arrays are empty.
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            } else {
                Toast.makeText(getActivity(), "You now can not download the file!", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void chooseSelection() {
        final Dialog dialog = new Dialog(getActivity());
        dialog.setContentView(R.layout.custom_dialog_img_source);
        dialog.setTitle("Title...");

        // set the custom dialog components - text, image and button
        Button btn_Camera = (Button) dialog.findViewById(R.id.btn_1);
        Button btn_Gallery = (Button) dialog.findViewById(R.id.btn_2);
        Button btn_cancle = (Button) dialog.findViewById(R.id.btn_3);
        btn_Camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                dispatchTakePictureIntent();
            }
        });
        btn_Gallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                getMultipleImages();
            }
        });
        btn_cancle.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                dialog.dismiss();
            }
        });
        dialog.getWindow().setBackgroundDrawable(
                new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.show();
    }

    private void dispatchTakePictureIntent() {
        Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(cameraIntent, 12);
    }

    public void getMultipleImages() {
        Intent intent = new Intent(getActivity(), AlbumSelectActivity.class);
//set limit on number of images that can be selected, default is 10
        intent.putExtra("limit", (5 - imagesnumber));
        startActivityForResult(intent, 10987);
    }

    public Uri getImageUri(Context inContext, Bitmap inImage) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        String path = MediaStore.Images.Media.insertImage(inContext.getContentResolver(), inImage, "Title", null);
        return Uri.parse(path);
    }

    public void GetJsonarray() {
        ProgressDialog dialog = new ProgressDialog(getActivity());
        dialog.setCancelable(false);
        dialog.setMessage("Please Wait ...");
        dialog.show();
        if (imagesList.size() > 0) {
            for (int i = 0; i < imagesList.size(); i++) {
                if (imagesList.get(i).contains("http")) {
                    sendOldImages.put(imagesList.get(i));
                } else {
                    /*Bitmap bm = BitmapFactory.decodeFile(imagesList.get(i));
                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                    bm.compress(Bitmap.CompressFormat.JPEG, 80, baos); //bm is the bitmap object
                    byte[] b = baos.toByteArray();
                    encodedString = Base64.encodeToString(b, Base64.NO_WRAP);*/
                    imageList.add(imagesList.get(i));
                }

            }//end of for
            if (dialog.isShowing()){
                dialog.dismiss();
            }
        }
    }

    public void setresponcecarDeatil(JSONObject jObject) {

        if (jObject.optInt("status") == 1 && jObject.optInt("dataFlow") == 1) {
            JSONArray response = jObject.optJSONArray("response");
            recyear = response.optJSONObject(0).optString("model_year");
            recmake = response.optJSONObject(0).optString("make");
            recmodel = response.optJSONObject(0).optString("model_type");
            spiner_year1.setText(recyear);
            spiner_make1.setText(recmake);
            spiner_model1.setText(recmodel);
            rectrasmision = response.optJSONObject(0).optString("transmission");
            reccolur = response.optJSONObject(0).optString("exterior_color");
            recstocknumber = response.optJSONObject(0).optString("stock_number");
            reccarpic = response.optJSONObject(0).optJSONArray("car_pic");
            jsonArrayFeatures = response.optJSONObject(0).optJSONArray("features");
            setDATA();
        }

    }

    private void setDATA() {

        if (rectrasmision.equals("automatic")) {
            transmission = "automatic";
            lin_manual.setBackgroundResource(R.drawable.discoveryshape);
            text_manual.setTextColor(Color.parseColor("#c4c4c4"));
            lin_automatic.setBackgroundResource(R.drawable.buttonshape);
            text_automatic.setTextColor(Color.WHITE);
        } else if (rectrasmision.equals("manual")) {
            transmission = "manual";
            lin_manual.setBackgroundResource(R.drawable.buttonshape);
            text_manual.setTextColor(Color.WHITE);
            lin_automatic.setBackgroundResource(R.drawable.discoveryshape);
            text_automatic.setTextColor(Color.parseColor("#c4c4c4"));
        }
       // edit_exteriorcolur.setText(reccolur);
        int setposdiscount=0;
        for (int i = 0; i < categories1.size(); i++) {
            if (categories1.get(i).equals(reccolur)) {
                setposdiscount = i;
            }
        }
        edit_exteriorcolur.setSelection(setposdiscount);
        edit_stocknumber.setText(recstocknumber);
       /* for (int i = 0; i < arrayList.size(); i++) {
            if (arrayList.get(i).equals(recyear)) {
                 sendyear = i;
            }
        }
        spiner_year1.setSelection(sendyear);*/
       if (jsonArrayFeatures!= null){
           List<String> list = new ArrayList<String>();
           Log.e(TAG, "setDATA: "+jsonArrayFeatures.length() );
           for (int i = 0; i < jsonArrayFeatures.length(); i++) {
               list.add(jsonArrayFeatures.optString(i).trim());

           }
           spiner_feature.setSelection(list);
           Log.e(TAG, "setDATA: "+list.size() );

       }
        if (reccarpic != null) {
            for (int i = 0; i < reccarpic.length(); i++) {
                imagesList.add(reccarpic.optString(i));
            }
        }
        if (imagesList.size() == 5) {
            buton_dealer_browse.setVisibility(View.GONE);
        }
        setGridDATa(imagesList);
        imagesnumber = imagesList.size();
    }
    private void setGridDATa(ArrayList<String> categories1) {
        grid_carpic.setHasFixedSize(true);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity().getApplicationContext(), LinearLayoutManager.HORIZONTAL, false);
        grid_carpic.setLayoutManager(mLayoutManager);

        adapter = new GridViewAdapter(getActivity(), categories1, true,"newcar");
        grid_carpic.setAdapter(adapter);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (alert!=null && alert.isShowing()){
            alert.dismiss();
        }
    }
}
