package com.dev.prepcarapplication.fragment;

import android.Manifest;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.darsh.multipleimageselect.activities.AlbumSelectActivity;
import com.darsh.multipleimageselect.models.Image;
import com.dev.prepcarapplication.BuyerNewCarActivity;
import com.dev.prepcarapplication.R;
import com.dev.prepcarapplication.adapter.GridViewAdapter;
import com.dev.prepcarapplication.baseClasses.BaseFragment;
import com.dev.prepcarapplication.baseClasses.Constants;
import com.dev.prepcarapplication.bean.BuyerList;
import com.dev.prepcarapplication.networkTask.ApiManager;
import com.dev.prepcarapplication.networkTask.URLsClass;
import com.dev.prepcarapplication.networkTask.WebServiceHandler;
import com.dev.prepcarapplication.networkTask.WebServiceListener;
import com.dev.prepcarapplication.preference.MySharedPreferences;
import com.dev.prepcarapplication.utilities.ToastCustomClass;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.MultipartBody;

public class FragmentCurrentCar extends BaseFragment {
    public static String TAG = "CURRENTCAR";
    Spinner spiner_condition;
    EditText spiner_year, spiner_make, spiner_model;
    EditText edit_milage, edit_kbbvalue, edit_picpath;
    public static Button buton_browse, buton_curentcar;
    ArrayList<BuyerList> arrayList, arrayList1, arrayList2;
    public static ArrayList<String> imagesList;
    String path = "";
    TextView text_seekbar;
    TextView icHome;
    SeekBar curentcar_seek;
    private String byteImage = "", year = "", make = "", model = "",
            condition = "";
    ImageView back;
    List<String> categories1;
    int sendcondition;
    RecyclerView grid_carpic;
    GridViewAdapter adapter;
    JSONArray sendimages,sendOldImages, sendpathjsonarray;
    ArrayList<String> imageList;
    String encodedString;
    int REQUEST_STORAGE = 1;
    StringBuilder stringBuilder = null;
    ArrayList<Uri> mArrayUri = new ArrayList<Uri>();
    public static List<Uri> uris;
    ArrayList<Image> images;
    public static int imagesnumber = 0;

    public static FragmentCurrentCar newInstanse(Bundle bundle) {
        FragmentCurrentCar fragment = new FragmentCurrentCar();
        if (bundle != null) {
            fragment.setArguments(bundle);
        }
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        requestStoragePermission();
        callingservice();
    }

    private void requestStoragePermission() {
        if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_STORAGE);
        } else {
            Log.i("Main", "Storage permissions have already been granted. Download the file");
        }
    }

    private void callingservice() {
        // TODO Auto-generated method stub
        ApiManager.getInstance().getCurrentcarDetail(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        View view = inflater.inflate(R.layout.fragment_current_car, null);
        initUi(view);
        setListener();
        return view;
    }

    private void callingerviceYear() {
        // TODO Auto-generated method stub
        ApiManager.getInstance().getYear(this);
    }

    private void callingerviceMake(String year) {
        // TODO Auto-generated method stub
        ApiManager.getInstance().getMake(this, year);
    }

    private void callingerviceModel(String year, String make) {
        // TODO Auto-generated method stub
        ApiManager.getInstance().getModel(this, year, make);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.buton_browse:

                chooseSelection();

                break;
            case R.id.buton_curentcar:
                if (spiner_year.getText().toString().trim().equals("")) {
                    ToastCustomClass.showToast(getActivity(),
                            "please enter year");
                } else if (spiner_make.getText().toString().trim().equals("")) {
                    ToastCustomClass.showToast(getActivity(),
                            "please enter brand name");
                } else if (spiner_model.getText().toString().trim().equals("")) {
                    ToastCustomClass.showToast(getActivity(),
                            "please enter car model");
                } else {
                    callingServiceCurrentCar();
                }
                break;
            case R.id.image_back:
                getActivity().finish();
                break;

            default:
                break;
        }
    }

    private void callingServiceCurrentCar() {
        // TODO Auto-generated method stub
        buton_curentcar.setClickable(false);
        GetJsonarray();
        year = spiner_year.getText().toString();
        make = spiner_make.getText().toString();
        model = spiner_model.getText().toString();
        condition = spiner_condition.getSelectedItem().toString();
        if (!path.equals("") && path != null) {
            byteImage = getStringImage(BitmapFactory.decodeFile(path));
        }
        Log.i("images====", String.valueOf(sendimages.length()));
        getCurrentCar( year, make, model,
                text_seekbar.getText().toString().trim(),
                edit_milage.getText().toString().trim(), condition,
                edit_kbbvalue.getText().toString().trim(), sendimages.toString());
        /*ApiManager.getInstance().getCurrentCar(this, year, make, model,
                text_seekbar.getText().toString().trim(),
                edit_milage.getText().toString().trim(), condition,
                edit_kbbvalue.getText().toString().trim(), sendimages.toString());*/
    }

    private void getCurrentCar(String year, String make, String model, String stillowd, String mileage, String condition, String kbbvalue, String s) {

        MultipartBody.Builder builder;

        builder = WebServiceHandler.createMultiPartBuilder(new String[]{"year", "make", "model", "still_owd", "mileage",
                        "car_condition", "kbb_value_estimate", "buyer_id","temp_old_url"}
                , new String[]{year,make,
                        model,stillowd, mileage,
                        condition, kbbvalue,
                        String.valueOf(MySharedPreferences.getInstance().getInteger(getActivity(), Constants.USER_ID,0)),
                        sendOldImages.toString().replace("[","").replace("]","").replace("\"","").replace("\\","")+""}, imageList,true,"image[]");

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
                                startMyActivity(BuyerNewCarActivity.class, null);
                                getActivity().finish();
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                });
            }
        };
        try {
            webServiceHandler.postMultiPart(URLsClass.service_type_current_car,builder);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    protected void initUi(View view) {
        uris = new ArrayList<>();
        sendpathjsonarray = new JSONArray();
        sendimages = new JSONArray();
        imagesList = new ArrayList<String>();
        sendOldImages = new JSONArray();
        imageList = new ArrayList<>();
        grid_carpic = (RecyclerView) view.findViewById(R.id.grid_carpic);
        back = (ImageView) view.findViewById(R.id.image_back);
        curentcar_seek = (SeekBar) view.findViewById(R.id.curentcar_seek);
        text_seekbar = (TextView) view.findViewById(R.id.text_seekbar);
        arrayList = new ArrayList<BuyerList>();
        arrayList1 = new ArrayList<BuyerList>();
        arrayList2 = new ArrayList<BuyerList>();
        edit_milage = (EditText) view.findViewById(R.id.edit_milage);
        edit_kbbvalue = (EditText) view.findViewById(R.id.edit_kbbvalue);
        edit_picpath = (EditText) view.findViewById(R.id.edit_picpath);
        icHome = (TextView) view.findViewById(R.id.icHome);
        spiner_condition = (Spinner) view.findViewById(R.id.spiner_condition);
        spiner_year = (EditText) view.findViewById(R.id.spiner_year);
        spiner_make = (EditText) view.findViewById(R.id.spiner_make);
        spiner_model = (EditText) view.findViewById(R.id.spiner_model);
        buton_browse = (Button) view.findViewById(R.id.buton_browse);
        buton_curentcar = (Button) view.findViewById(R.id.buton_curentcar);
        grid_carpic.requestFocus();
        grid_carpic.setOnTouchListener(new View.OnTouchListener() {
            // Setting on Touch Listener for handling the touch inside ScrollView
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                // Disallow the touch request for parent scroll on touch of child view
                v.getParent().requestDisallowInterceptTouchEvent(true);
                return false;
            }
        });
        categories1 = new ArrayList<String>();
        categories1.add("Like new");
        categories1.add("Great");
        categories1.add("Fair");
        categories1.add("Poor");
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(
                getActivity(), android.R.layout.simple_spinner_item,
                categories1);
        dataAdapter
                .setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spiner_condition.setAdapter(dataAdapter);


        icHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startMyActivityTop(BuyerNewCarActivity.class, null);
                getActivity().finish();
            }
        });

        curentcar_seek
                .setOnSeekBarChangeListener(new OnSeekBarChangeListener() {

                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar) {
                    }

                    @Override
                    public void onStartTrackingTouch(SeekBar seekBar) {
                    }

                    @Override
                    public void onProgressChanged(SeekBar seekBar,
                                                  int progress, boolean fromUser) {
                        // TODO Auto-generated method stub
                        if (progress == 0) {
                            text_seekbar.setVisibility(View.GONE);
                        } else if (progress == 30000) {
                            text_seekbar.setVisibility(View.GONE);
                        } else {
                            progress = ((int) Math.round(progress / 500)) * 500;
                            seekBar.setProgress(progress);
                            text_seekbar.setVisibility(View.VISIBLE);
                            text_seekbar.setText(String.valueOf(progress));
                        }
                    }
                });
    }

    @Override
    protected void setValueOnUi() {
    }

    @Override
    protected void setListener() {
        buton_browse.setOnClickListener(this);
        buton_curentcar.setOnClickListener(this);
        back.setOnClickListener(this);

    }

    @Override
    public boolean onBackPressedListener() {
        // TODO Auto-generated method stub
        return false;
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
                    adapter = new GridViewAdapter(getActivity(), imagesList, true, "car");
                    grid_carpic.setAdapter(adapter);
                }
                if (imagesList.size() == 5) {
                    buton_browse.setVisibility(View.GONE);
                } else {
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
                    adapter = new GridViewAdapter(getActivity(), imagesList, true, "car");
                    grid_carpic.setAdapter(adapter);
                }
                if (imagesList.size() == 5) {
                    buton_browse.setVisibility(View.GONE);
                } else {
                }
            }
        }

    }

    public String getStringImage(Bitmap bmp) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.JPEG, 80, baos);
        byte[] imageBytes = baos.toByteArray();
        String encodedImage = Base64.encodeToString(imageBytes, Base64.DEFAULT);
        return encodedImage;
    }

    public void setresponceFinalResponce(JSONObject jObject) {
        // TODO Auto-generated method stub
        if (jObject.optInt("status") == 1 && jObject.optInt("dataflow") == 1) {
            startMyActivity(BuyerNewCarActivity.class, null);
            getActivity().finish();
        }
    }

    public void setresponceFinalResponcegetdetail(JSONObject jObject) {
        // TODO Auto-generated method stub
        callingerviceYear();

        if (jObject.optInt("status") == 1 && jObject.optInt("dataflow") == 1) {
            try {
                text_seekbar.setText(jObject.optString("still_owd"));
                edit_milage.setText(jObject.optString("mileage"));
                edit_kbbvalue.setText(jObject.optString("kbb_value_estimate"));
                edit_picpath.setText(jObject.optString("car_photos"));
                condition = jObject.optString("car_condition");
                for (int i = 0; i < categories1.size(); i++) {
                    if (categories1.get(i).equals(condition)) {
                        sendcondition = i;
                    }
                }
                spiner_condition.setSelection(sendcondition);

                year = jObject.optString("year");

                make = jObject.optString("make");

                model = jObject.optString("model");
                spiner_year.setText(year);
                spiner_make.setText(make);
                spiner_model.setText(model);
                JSONArray imageaarraayy = jObject.optJSONArray("car_photos");
                if (imageaarraayy != null) {
                    for (int i = 0; i < imageaarraayy.length(); i++) {
                        imagesList.add(imageaarraayy.optString(i));
                    }
                }
                if (imagesList.size() == 5) {
                    buton_browse.setVisibility(View.GONE);
                }
                setGridDATa(imagesList);
                imagesnumber = imagesList.size();
                curentcar_seek.setProgress(Integer.parseInt(jObject.optString("still_owd")));
            } catch (NumberFormatException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    private void setGridDATa(ArrayList<String> categories1) {
        grid_carpic.setHasFixedSize(true);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity().getApplicationContext(), LinearLayoutManager.HORIZONTAL, false);
        grid_carpic.setLayoutManager(mLayoutManager);
        ;
        adapter = new GridViewAdapter(getActivity(), categories1, true, "car");
        grid_carpic.setAdapter(adapter);
    }

    public void GetJsonarray() {

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
        }
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

    public void showDialog(String msg) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
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

    public void getMultipleImages() {

        Intent intent = new Intent(getActivity(), AlbumSelectActivity.class);
//set limit on number of images that can be selected, default is 10
        intent.putExtra("limit", (5 - imagesnumber));
        startActivityForResult(intent, 10987);
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
        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(cameraIntent, 12);
    }

    public Uri getImageUri(Context inContext, Bitmap inImage) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        String path = MediaStore.Images.Media.insertImage(inContext.getContentResolver(), inImage, "Title", null);
        return Uri.parse(path);
    }

    public static void callbrowsebuttonShow() {
        buton_browse.setVisibility(View.GONE);
    }
}
