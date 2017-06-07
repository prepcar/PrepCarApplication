package com.dev.prepcarapplication.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.dev.prepcarapplication.CarPlanActivity;
import com.dev.prepcarapplication.R;
import com.dev.prepcarapplication.SearchActivityBuyer;
import com.dev.prepcarapplication.adapter.IknowWhatIWantAdapter;
import com.dev.prepcarapplication.adapter.MultiSelectionSpinner;
import com.dev.prepcarapplication.baseClasses.BaseFragment;
import com.dev.prepcarapplication.baseClasses.Constants;
import com.dev.prepcarapplication.bean.BuyerList;
import com.dev.prepcarapplication.bean.IKnowIWantModel;
import com.dev.prepcarapplication.networkTask.ApiManager;
import com.dev.prepcarapplication.utilities.UtilsClass;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class FragmentIKnowWhatIWant extends BaseFragment {
    public static String TAG = "IKNOW";
    Button buton_addmore, buton_iknow; /*buton_remove*/
    List<String> categories13;
    List<String> arrColors;
    EditText edit_model, edit_comment, edit_comment1, edit_carsearch;
    EditText spiner_year, spiner_make, spiner_model;
    MultiSelectionSpinner spiner_idelcar, spiner_feature;
    ArrayList<BuyerList> arrayList, arrayList2, arrayList1;
    ImageView back;
    JSONObject objectYear, objectComment, objectFeature, objectMake, objectModel, objectCondition;
    RecyclerView rec_Items;
    List<String> categories1;
    JSONArray arrayYear, arrayComment, arrayFeature, arrayMake, arrayModel, arrayCondition,arrayColor;
    List<String> categories5;
    ArrayList<IKnowIWantModel> arrayListItems;
    //  int nestedlayout = 0;
    LinearLayout insertLayout;
    MultiSelectionSpinner spnColor;
    IknowWhatIWantAdapter adapter;
    ImageView icHome;

    public static FragmentIKnowWhatIWant newInstanse(Bundle bundle) {
        FragmentIKnowWhatIWant fragment = new FragmentIKnowWhatIWant();
        if (bundle != null) {
            fragment.setArguments(bundle);
        }
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        View view = inflater.inflate(R.layout.fragment_iknow_what_iwant, null);
        initUi(view);
        setListener();
        callingervice();

        return view;
    }

    private void callingervice() {
        // TODO Auto-generated method stub
        ApiManager.getInstance().getIKNOW(this);
    }


    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub
        switch (v.getId()) {
            case R.id.image_back:
                getActivity().finish();
                break;
            case R.id.buton_iknowi:
                //  if (arrayListItems != null && arrayListItems.size() > 0) {
                GetAllDAta();
                //  }
                break;
            case R.id.icHome:
                startMyActivityTop(CarPlanActivity.class,null);
                getActivity().finish();
                break;
            default:
                break;
        }
    }

    @Override
    protected void initUi(View view) {
        // TODO Auto-generated method stub
        categories13 = new ArrayList<>();
        back = (ImageView) view.findViewById(R.id.image_back);
        arrayList = new ArrayList<BuyerList>();
        arrayList1 = new ArrayList<BuyerList>();
        arrayList2 = new ArrayList<BuyerList>();
        buton_addmore = (Button) view.findViewById(R.id.buton_addmore);
        //  buton_remove = (Button) view.findViewById(R.id.buton_remove);
        buton_iknow = (Button) view.findViewById(R.id.buton_iknowi);
        edit_carsearch = (EditText) view.findViewById(R.id.edit_carsearch);
        edit_comment = (EditText) view.findViewById(R.id.edit_comment);
        edit_model = (EditText) view.findViewById(R.id.edit_model);
        spiner_feature = (MultiSelectionSpinner) view.findViewById(R.id.et_features);
        spiner_idelcar = (MultiSelectionSpinner) view.findViewById(R.id.spiner_idelcar);
        spiner_make = (EditText) view.findViewById(R.id.spiner_make);
        spiner_model = (EditText) view.findViewById(R.id.spiner_model);
        spnColor = (MultiSelectionSpinner) view.findViewById(R.id.spnColor);
        spiner_year = (EditText) view.findViewById(R.id.spiner_year);
        rec_Items = (RecyclerView) view.findViewById(R.id.rec_Items);
        icHome = (ImageView)  view.findViewById(R.id.icHome);
        insertLayout = (LinearLayout) view.findViewById(R.id.insertLayout);
//edit
        categories13.add("Sunroof/Moonroof");
        categories13.add("Leather seats");
        categories13.add("Back-up camera");
        categories13.add("Navigation");
        categories13.add("Keyless entry");
        categories13.add("Apple CarPlay/Android Auto");
        categories13.add("Bluetooth");
        categories13.add("Heated seats");
        categories13.add("Remote start");
        categories13.add("Parking assist");
        categories13.add("Premium audio");
        categories13.add("3rd row seat");


        arrColors = new ArrayList<String>();
        arrColors.add("White");
        arrColors.add("Black");
        arrColors.add("Blue");
        arrColors.add("Green");
        arrColors.add("Pink");
        arrColors.add("Yellow");
        arrColors.add("Gray");
        arrColors.add("Silver");
        arrColors.add("Brown");
        arrColors.add("Red");
        arrColors.add("Purple");
        arrColors.add("No preferences");


        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(
                getActivity(), android.R.layout.simple_spinner_item,
                arrColors);
        dataAdapter
                .setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnColor.setItems(arrColors);

        ArrayAdapter<String> dataAdapter33 = new ArrayAdapter<String>(
                getActivity(), android.R.layout.simple_spinner_item,
                categories13);
        dataAdapter33
                .setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spiner_feature.setItems(categories13);
        arrayListItems = new ArrayList<>();
        categories1 = new ArrayList<String>();
        categories1.add("New");
        categories1.add("Used");
        categories1.add("lease");
        ArrayAdapter<String> dataAdapter1 = new ArrayAdapter<String>(
                getActivity(), android.R.layout.simple_spinner_item, categories1);
        dataAdapter1
                .setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spiner_idelcar.setItems(categories1);

        categories5 = new ArrayList<String>();
        categories5.add("Features");
        ArrayAdapter<String> dataAdapter5 = new ArrayAdapter<String>(
                getActivity(), android.R.layout.simple_spinner_item, categories5);
        dataAdapter5
                .setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);


        buton_addmore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (!UtilsClass.isConnectingToInternet(getActivity())) {
                    UtilsClass.plsStartInternet(getActivity());
                    return;
                }

                buton_addmore.setText(R.string.addmore);


                if (insertLayout.getVisibility() == View.VISIBLE) {

                    IKnowIWantModel model = new IKnowIWantModel();


                    int condiPosition = spiner_idelcar.getSelectedItemPosition();

                    String srCondtion, srFeature, srYear, srMake, srModel, srComment, srColor;

                    srCondtion = categories1.get(condiPosition);
                    srFeature = spiner_feature.getSelectedItem().toString();
                    srYear = spiner_year.getText().toString();
                    srMake = spiner_make.getText().toString();
                    srModel = spiner_model.getText().toString();
                    srComment = edit_comment.getText().toString();
                    srColor = spnColor.getSelectedItem().toString();


                    if (srCondtion.length() > 0 && srFeature.length() > 0
                            && srYear.length() > 0 && srMake.length() > 0 && srModel.length() > 0 && srComment.length() > 0 && srColor.length() > 0)

                    {

                        model.setCondition(spiner_idelcar.getSelectedItemsAsString());
                        model.setFeature(spiner_feature.getSelectedItem().toString());
                        model.setMake(spiner_make.getText().toString());
                        model.setModel(spiner_model.getText().toString());
                        model.setYear(spiner_year.getText().toString());
                        model.setComment(edit_comment.getText().toString());
                        model.setColor(spnColor.getSelectedItem().toString());

                        arrayListItems.add(model);


                        rec_Items.setHasFixedSize(true);
                        rec_Items.setLayoutManager(new LinearLayoutManager(getActivity()));
                        adapter = new IknowWhatIWantAdapter(getActivity(), arrayListItems, buton_addmore, insertLayout);
                        rec_Items.setAdapter(adapter);


                        if (arrayListItems.size() > 0) {
                            if (arrayListItems.size() > 4) {
                                buton_addmore.setVisibility(View.GONE);
                            } else {
                                buton_addmore.setVisibility(View.VISIBLE);
                            }

                            rec_Items.setVisibility(View.VISIBLE);
                        } else {

                            rec_Items.setVisibility(View.GONE);
                        }
                        insertLayout.setVisibility(View.GONE);
                    } else {

                        Toast.makeText(getActivity(), "Please enter all fields", Toast.LENGTH_SHORT).show();

                    }


                } else {
                    insertLayout.setVisibility(View.VISIBLE);
                    edit_comment.setText("");
                    spiner_idelcar.setItems(categories1);
                    spiner_feature.setItems(categories13);
                }

                //     }


            }
        });
    }

    @Override
    protected void setValueOnUi() {

    }

    @Override
    protected void setListener() {
        back.setOnClickListener(this);
        buton_iknow.setOnClickListener(this);
        icHome.setOnClickListener(this);
    }

    @Override
    public boolean onBackPressedListener() {
        return false;
    }

    public void GetAllDAta() {
        objectFeature = new JSONObject();
        objectComment = new JSONObject();
        objectMake = new JSONObject();
        objectModel = new JSONObject();
        objectCondition = new JSONObject();
        objectYear = new JSONObject();

        arrayYear = new JSONArray();
        arrayComment = new JSONArray();
        arrayFeature = new JSONArray();
        arrayMake = new JSONArray();
        arrayModel = new JSONArray();
        arrayCondition = new JSONArray();
        arrayYear = new JSONArray();
        arrayColor = new JSONArray();

        if (arrayListItems.size() > 0) {
            for (int i = 0; i < arrayListItems.size(); i++) {
                arrayYear.put(arrayListItems.get(i).getYear());
                arrayComment.put(arrayListItems.get(i).getComment());
                arrayFeature.put(arrayListItems.get(i).getFeature());
                arrayMake.put(arrayListItems.get(i).getMake());
                arrayModel.put(arrayListItems.get(i).getModel());
                arrayCondition.put(arrayListItems.get(i).getCondition());
                arrayColor.put(arrayListItems.get(i).getColor());
            }


            String srYear = "", srComment = "", srFeature = "", srMake = "", srModelm = "", srIdelcar = "";

            String srModel = edit_model.getText().toString();
            String srSearch = edit_carsearch.getText().toString();

            Log.e("objectYear", arrayYear.toString() + "mm");
            Log.e("objectComment", arrayComment.toString() + "mm");
            Log.e("objectFeature", arrayFeature.toString() + "mm");
            Log.e("objectMake", arrayMake.toString() + "mm");
            Log.e("objectModel", arrayModel.toString() + "mm");
            Log.e("objectCondition", arrayCondition.toString() + "mm");
            Log.e("srModel", srModel.toString() + "mm");
            Log.e("srSearch", srSearch.toString() + "mm");
            Log.e("srColor", arrayColor.toString() + "mm");

            ApiManager.getInstance().getIKNOW(this, "test", arrayCondition.toString(), arrayYear.toString()
                    , arrayMake.toString(), arrayModel.toString(), arrayFeature.toString(), arrayComment.toString(), srSearch.toString(),arrayColor.toString());


            //  }
        } else {
            String srYear = "", srComment = "", srFeature = "", srMake = "", srModelm = "", srIdelcar = "";

            String srModel = edit_model.getText().toString();
            String srSearch = edit_carsearch.getText().toString();


            srYear = spiner_year.getText().toString();
            srComment = edit_comment.getText().toString();
            srFeature = spiner_feature.getSelectedItem().toString();
            srMake = spiner_make.getText().toString();
            srModelm = spiner_model.getText().toString();
            srIdelcar = spiner_idelcar.getSelectedItem().toString();

            arrayYear.put(spiner_year.getText().toString());
            arrayComment.put(edit_comment.getText().toString());
            arrayFeature.put(spiner_feature.getSelectedItem().toString().trim());
            arrayMake.put(spiner_make.getText().toString());
            arrayModel.put(spiner_model.getText().toString());
            arrayCondition.put(spiner_idelcar.getSelectedItem().toString());
            arrayColor.put(spnColor.getSelectedItem().toString());

            if (srYear.length() > 0 && srComment.length() > 0 && srFeature.length() > 0 &&
                    srMake.length() > 0 && srModelm.length() > 0 && srIdelcar.length() > 0 && srSearch.length() > 0) {

                Log.e("objectYear", arrayYear.toString() + "mm");
                Log.e("objectComment", arrayComment.toString() + "mm");
                Log.e("objectFeature", arrayFeature.toString() + "mm");
                Log.e("objectMake", arrayMake.toString() + "mm");
                Log.e("objectModel", arrayModel.toString() + "mm");
                Log.e("objectCondition", arrayCondition.toString() + "mm");
                Log.e("srModel", srModel.toString() + "mm");
                Log.e("srSearch", srSearch.toString() + "mm");
                Log.e("srColor", arrayColor.toString() + "mm");

                ApiManager.getInstance().getIKNOW(this, srModel.toString(), arrayCondition.toString(), arrayYear.toString()
                        , arrayMake.toString(), arrayModel.toString(), arrayFeature.toString(), arrayComment.toString(), srSearch.toString(),arrayColor.toString());

            } else {
                if (srComment.length() > 0)
                    Toast.makeText(getActivity(), "Please click on ADD button first", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(getActivity(), "Please enter all fields", Toast.LENGTH_SHORT).show();

            }


        }
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

    private void callingerviceYear1() {
        // TODO Auto-generated method stub
        ApiManager.getInstance().getYear1(this);
    }

    private void callingerviceMake1(String year) {
        // TODO Auto-generated method stub
        ApiManager.getInstance().getMake1(this, year);
    }

    private void callingerviceModel1(String year, String make) {
        // TODO Auto-generated method stub
        ApiManager.getInstance().getModel1(this, year, make);
    }

    public void setresponceFianl(JSONObject jObject) {
        if (jObject.optInt("dataflow", Constants.NOT_FLOW) == Constants.FLOW) {
            Intent intent2 = new Intent(getActivity(),
                    SearchActivityBuyer.class)
                    .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            getActivity().finishAffinity();
            startActivity(intent2);
        }
    }

    public void setresponceFianlAllData(JSONObject jObject) {
        try {

            String specificmodel = jObject.optString("specificmodel");
            String car_search = jObject.optString("car_search");

            edit_model.setText(specificmodel + "");
            edit_carsearch.setText(car_search + "");


            if (jObject.optInt("dataflow", Constants.NOT_FLOW) == Constants.FLOW) {

                JSONArray arrNew = jObject.getJSONArray("new");
                JSONArray arrYear = jObject.getJSONArray("year");
                JSONArray arrMake = jObject.getJSONArray("make");
                JSONArray arrModel = jObject.getJSONArray("model");
                JSONArray arrFeature = jObject.getJSONArray("features");
                JSONArray arrComment = jObject.getJSONArray("comment");
                JSONArray arrColor = jObject.getJSONArray("color");


                for (int i = 0; i < arrNew.length(); i++) {
                    IKnowIWantModel model = new IKnowIWantModel();
                    model.setCondition(arrNew.get(i).toString());
                    model.setModel(arrModel.get(i).toString());
                    model.setMake(arrMake.get(i).toString());
                    model.setFeature(arrFeature.get(i).toString());
                    model.setComment(arrComment.get(i).toString());
                    model.setYear(arrYear.get(i).toString());
                    model.setColor(arrColor.get(i).toString());
                    arrayListItems.add(model);

                }


                if (arrayListItems.size() > 4) {

                    insertLayout.setVisibility(View.GONE);

                } else {

                    insertLayout.setVisibility(View.VISIBLE);
                }
                if (arrayListItems.size() == 0) {
                    buton_addmore.setText(R.string.add);
                    insertLayout.setVisibility(View.VISIBLE);
                } else if (arrayListItems.size() > 0) {
                    buton_addmore.setText(R.string.addmore);
                    insertLayout.setVisibility(View.GONE);
                }

                rec_Items.setHasFixedSize(true);
                rec_Items.setLayoutManager(new LinearLayoutManager(getActivity()));
                adapter = new IknowWhatIWantAdapter(getActivity(), arrayListItems, buton_addmore, insertLayout);
                rec_Items.setAdapter(adapter);


            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

}
