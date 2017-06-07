package com.dev.prepcarapplication.fragment;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.dev.prepcarapplication.DealerSelectCarActivity;
import com.dev.prepcarapplication.R;
import com.dev.prepcarapplication.adapter.GridViewAdapter;
import com.dev.prepcarapplication.baseClasses.BaseFragment;
import com.dev.prepcarapplication.baseClasses.Constants;
import com.dev.prepcarapplication.networkTask.ApiManager;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

/**
 * Created by this on 2/22/2017.
 */

public class FragmentAcceptBuyerCarPlan extends BaseFragment implements GridViewAdapter.OnItemClickListener{
    public static  String TAG="Accept";
    static  String buyerId="";
    TextView dis_shopnote,dis_discount,dis_smallchldren,dis_height,dis_mainuses,dis_firstcar,dis_identy,
            fin_downpayment,fin_budget,fin_choseyourown,fin_monthlypayment,fin_estcreditscore,fin_amountstudentloan,fin_studentloan,fin_amountmortgage,fin_mortgage,fin_amountrent,fin_rent,fin_monthlyincome,
            curcar_deatil,curcar_condition,curcar_milage,curcar_stilowned,curcar_model,curcar_make,curcar_year,
            iam_brandeuropn,iam_brandasian,iam_brandamerica,iam_prefrence,iam_trasmision,iam_exteriourcolur,iam_milage,iam_style,iam_type,iam_yearrangemax,iam_yearrangemin,
            iknow_search,iknow_coments,iknow_features,iknow_model,iknow_make,iknow_year, iknow_color,iknow_cartype,fin_doyouneed;
    RecyclerView grid_carpic;
    GridViewAdapter adapter;
    public static ArrayList<String> imagesList;
    JSONArray picsarray;
    Button accept_senmatches;

    public static FragmentAcceptBuyerCarPlan newInstanse(Bundle bundle) {
        FragmentAcceptBuyerCarPlan fragment = new FragmentAcceptBuyerCarPlan();
        if (bundle != null) {
            fragment.setArguments(bundle);
            if(bundle.containsKey(Constants.buyerid)){
                buyerId=bundle.getString(Constants.buyerid);
            }
            else{
                buyerId="";
            }
        }else{
            buyerId="";
        }

        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        View view = inflater.inflate(R.layout.fragment_carplan, null);
        initUi(view);
        setListener();
        callingServicde();

        return view;
    }

    private void callingServicde() {
        ApiManager.getInstance().acceptRequestcarplan(this,buyerId);
    }

    @Override
    protected void initUi(View view) {
        fin_doyouneed=(TextView)view.findViewById(R.id.fin_doyouneed);
        accept_senmatches=(Button)view.findViewById(R.id.accept_senmatches);
        imagesList=new ArrayList<>();
        grid_carpic=(RecyclerView)view.findViewById(R.id.grid_carpic);
        dis_discount=(TextView)view.findViewById(R.id.dis_discount);
        dis_firstcar=(TextView)view.findViewById(R.id.dis_firstcar);
        dis_height=(TextView)view.findViewById(R.id.dis_height);
        dis_identy=(TextView)view.findViewById(R.id.dis_identy);
        dis_mainuses=(TextView)view.findViewById(R.id.dis_mainuses);
        dis_shopnote=(TextView)view.findViewById(R.id.dis_shopnote);
        dis_smallchldren=(TextView)view.findViewById(R.id.dis_smallchldren);

        fin_amountmortgage=(TextView)view.findViewById(R.id.fin_amountmortgage);
        fin_amountrent=(TextView)view.findViewById(R.id.fin_amountrent);
        fin_amountstudentloan=(TextView)view.findViewById(R.id.fin_amountstudentloan);
        fin_budget=(TextView)view.findViewById(R.id.fin_budget);
        fin_choseyourown=(TextView)view.findViewById(R.id.fin_choseyourown);
        fin_downpayment=(TextView)view.findViewById(R.id.fin_downpayment);
        fin_estcreditscore=(TextView)view.findViewById(R.id.fin_estcreditscore);
        fin_monthlyincome=(TextView)view.findViewById(R.id.fin_monthlyincome);
        fin_monthlypayment=(TextView)view.findViewById(R.id.fin_monthlypayment);
        fin_mortgage=(TextView)view.findViewById(R.id.fin_mortgage);
        fin_rent=(TextView)view.findViewById(R.id.fin_rent);
        fin_studentloan=(TextView)view.findViewById(R.id.fin_studentloan);

        curcar_condition=(TextView)view.findViewById(R.id.curcar_condition);
        curcar_deatil=(TextView)view.findViewById(R.id.curcar_deatil);
        curcar_make=(TextView)view.findViewById(R.id.curcar_make);
        curcar_milage=(TextView)view.findViewById(R.id.curcar_milage);
        curcar_model=(TextView)view.findViewById(R.id.curcar_model);
        curcar_stilowned=(TextView)view.findViewById(R.id.curcar_stilowned);
        curcar_year=(TextView)view.findViewById(R.id.curcar_year);

        iam_brandamerica=(TextView)view.findViewById(R.id.iam_brandamerica);
        iam_brandeuropn=(TextView)view.findViewById(R.id.iam_brandeuropn);
        iam_exteriourcolur=(TextView)view.findViewById(R.id.iam_exteriourcolur);
        iam_milage=(TextView)view.findViewById(R.id.iam_milage);
        iam_prefrence=(TextView)view.findViewById(R.id.iam_prefrence);
        iam_style=(TextView)view.findViewById(R.id.iam_style);
        iam_trasmision=(TextView)view.findViewById(R.id.iam_trasmision);
        iam_type=(TextView)view.findViewById(R.id.iam_type);
        iam_yearrangemax=(TextView)view.findViewById(R.id.iam_yearrangemax);
        iam_yearrangemin=(TextView)view.findViewById(R.id.iam_yearrangemin);
        iam_brandasian=(TextView)view.findViewById(R.id.iam_brandasian);

        iknow_cartype=(TextView)view.findViewById(R.id.iknow_cartype);
        iknow_coments=(TextView)view.findViewById(R.id.iknow_coments);
        iknow_features=(TextView)view.findViewById(R.id.iknow_features);
        iknow_make=(TextView)view.findViewById(R.id.iknow_make);
        iknow_model=(TextView)view.findViewById(R.id.iknow_model);
        //iknow_search=(TextView)view.findViewById(R.id.iknow_search);
        iknow_year=(TextView)view.findViewById(R.id.iknow_year);
        iknow_color =(TextView)view.findViewById(R.id.iknow_color);

    }

    @Override
    protected void setValueOnUi() {

    }

    @Override
    protected void setListener() {
        accept_senmatches.setOnClickListener(this);
    }

    @Override
    public boolean onBackPressedListener() {
        return false;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.accept_senmatches:
            //    startMyActivity(DealerSelectCarActivity.class, null);
                Intent intent=new Intent(getActivity(), DealerSelectCarActivity.class);
                intent.putExtra(Constants.buyerid,buyerId);
                startActivity(intent);
                break;
        }
    }

    public void setrespponce(JSONObject jObject) {
        if(jObject.optInt("dataFlow")==1 && jObject.optInt("status")==1){
            JSONObject response=jObject.optJSONObject("response");
            JSONArray discoveryData=response.optJSONArray("discoveryData");
            dis_identy.setText(discoveryData.optJSONObject(0).optString("identify"));
            dis_firstcar.setText(discoveryData.optJSONObject(0).optString("first_car"));
          //  dis_mainuses.setText(discoveryData.optJSONObject(0).optString("main_use"));
            System.out.println("before"+discoveryData.optJSONObject(0).optString("main_use"));
            String test=(discoveryData.optJSONObject(0).optString("main_use"));
            System.out.println("replace"+test.replaceAll("[\\p{Ps}\\p{Pe}]", ""));
            test=test.replaceAll("[\\p{Ps}\\p{Pe}]", "");
            test=test.replaceAll("\"","");

            dis_mainuses.setText(test);
            dis_height.setText(discoveryData.optJSONObject(0).optString("height"));
            dis_smallchldren.setText(discoveryData.optJSONObject(0).optString("small_children"));
            dis_discount.setText(discoveryData.optJSONObject(0).optString("discount"));
            dis_shopnote.setText(discoveryData.optJSONObject(0).optString("shopping_notes"));

            JSONArray financesData=response.optJSONArray("financesData");
            fin_monthlyincome.setText(financesData.optJSONObject(0).optString("est_monthly_income"));
            fin_rent.setText(financesData.optJSONObject(0).optString("rent"));
            fin_amountrent.setText(financesData.optJSONObject(0).optString("rent_amount"));
            fin_mortgage.setText(financesData.optJSONObject(0).optString("mortgage"));
            fin_amountmortgage.setText(financesData.optJSONObject(0).optString("amount1"));
            fin_studentloan.setText(financesData.optJSONObject(0).optString("student_loans"));
            fin_amountstudentloan.setText(financesData.optJSONObject(0).optString("amount2"));
            fin_estcreditscore.setText(financesData.optJSONObject(0).optString("est_credit_score"));
            fin_monthlypayment.setText(financesData.optJSONObject(0).optString("suggested_monthly_payment").trim());
            fin_choseyourown.setText(financesData.optJSONObject(0).optString("choose_your_own"));
            fin_budget.setText(financesData.optJSONObject(0).optString("budget"));
            fin_downpayment.setText(financesData.optJSONObject(0).optString("down_payment"));
            fin_doyouneed.setText(financesData.optJSONObject(0).optString("do_you_need_finance"));

            JSONArray current_carData=response.optJSONArray("current_carData");
            curcar_year.setText(current_carData.optJSONObject(0).optString("year"));
            curcar_make.setText(current_carData.optJSONObject(0).optString("make"));
            curcar_model.setText(current_carData.optJSONObject(0).optString("model"));
            curcar_stilowned.setText(current_carData.optJSONObject(0).optString("still_owd"));
            curcar_milage.setText(current_carData.optJSONObject(0).optString("mileage"));
            curcar_condition.setText(current_carData.optJSONObject(0).optString("car_condition"));
            curcar_deatil.setText(current_carData.optJSONObject(0).optString("kbb_value_estimate"));
            if (current_carData.optJSONObject(0).has("car_photos")){
            picsarray=current_carData.optJSONObject(0).optJSONArray("car_photos");
            if (picsarray.length() > 0) {
                for (int i = 0; i < picsarray.length(); i++) {
                    imagesList.add(picsarray.optString(i));
                }
            }setData();}

            JSONArray new_carData=response.optJSONArray("new_carData");
            iam_yearrangemin.setText(new_carData.optJSONObject(0).optString("year_range"));
            iam_yearrangemax.setText(new_carData.optJSONObject(0).optString("year_range_max"));

            String test1=(new_carData.optJSONObject(0).optString("type"));
            test1=test1.replaceAll("[\\p{Ps}\\p{Pe}]", "");
            test1=test1.replaceAll("\"","");
            iam_type.setText(test1);

            String test2=(new_carData.optJSONObject(0).optString("styles"));
            test2=test2.replaceAll("[\\p{Ps}\\p{Pe}]", "");
            test2=test2.replaceAll("\"","");
            iam_style.setText(test2);
            iam_milage.setText(new_carData.optJSONObject(0).optString("mileage"));

            String test3=(new_carData.optJSONObject(0).optString("color"));
            test3=test3.replaceAll("[\\p{Ps}\\p{Pe}]", "");
            test3=test3.replaceAll("\"","");
            iam_exteriourcolur.setText(test3);
            iam_trasmision.setText(new_carData.optJSONObject(0).optString("transmission"));

            String test4=(new_carData.optJSONObject(0).optString("preferences"));
            test4=test4.replaceAll("[\\p{Ps}\\p{Pe}]", "");
            test4=test4.replaceAll("\"","");
            iam_prefrence.setText(test4);

            String test5=(new_carData.optJSONObject(0).optString("brand_americans"));
            test5=test5.replaceAll("[\\p{Ps}\\p{Pe}]", "");
            test5=test5.replaceAll("\"","");
            iam_brandamerica.setText(test5);

            String test6=(new_carData.optJSONObject(0).optString("brand_asians"));
            test6=test6.replaceAll("[\\p{Ps}\\p{Pe}]", "");
            test6=test6.replaceAll("\"","");
            iam_brandasian.setText(test6);

            String test7=(new_carData.optJSONObject(0).optString("brand_europeans"));
            test7=test7.replaceAll("[\\p{Ps}\\p{Pe}]", "");
            test7=test7.replaceAll("\"","");
            iam_brandeuropn.setText(test7);

            String test8=(new_carData.optJSONObject(0).optString("new"));
            test8=test8.replaceAll("[\\p{Ps}\\p{Pe}]", "");
            test8=test8.replaceAll("\"","");
            iknow_cartype.setText(test8);

            String test9=(new_carData.optJSONObject(0).optString("year"));
            test9=test9.replaceAll("[\\p{Ps}\\p{Pe}]", "");
            test9=test9.replaceAll("\"","");
            iknow_year.setText(test9);

            String test10=(new_carData.optJSONObject(0).optString("make"));
            test10=test10.replaceAll("[\\p{Ps}\\p{Pe}]", "");
            test10=test10.replaceAll("\"","");
            iknow_make.setText(test10);

            String test11=(new_carData.optJSONObject(0).optString("model"));
            test11=test11.replaceAll("[\\p{Ps}\\p{Pe}]", "");
            test11=test11.replaceAll("\"","");
            iknow_model.setText(test11);

            String test12=(new_carData.optJSONObject(0).optString("features"));
            test12=test12.replaceAll("[\\p{Ps}\\p{Pe}]", "");
            test12=test12.replaceAll("\"","");
            iknow_features.setText(test12);

            String test13=(new_carData.optJSONObject(0).optString("comment").trim());
            test13=test13.replaceAll("[\\p{Ps}\\p{Pe}]", "");
            test13=test13.replaceAll("\"","");
            iknow_coments.setText(test13.trim());

            String test14=(new_carData.optJSONObject(0).optString("color").trim());
            test14=test14.replaceAll("[\\p{Ps}\\p{Pe}]", "");
            test14=test14.replaceAll("\"","");
            iknow_color.setText(test14.trim());

         //   iknow_search.setText(new_carData.optJSONObject(0).optString(""));
        }
    }
    public void setData(){
        grid_carpic.setHasFixedSize(true);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity().getApplicationContext(), LinearLayoutManager.HORIZONTAL, false);
        grid_carpic.setLayoutManager(mLayoutManager);
        adapter = new GridViewAdapter(getActivity(), imagesList, false,"",this);
        grid_carpic.setAdapter(adapter);
    }

    @Override
    public void onItemClick(int position) {

        showView(imagesList.get(position));
    }
    private void showView(String path) {

        final Dialog dialog = new Dialog(getActivity());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(true);
        dialog.setContentView(R.layout.fragment_full_image);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));

        ImageView docView = (ImageView) dialog.findViewById(R.id.docView);
        ImageView closeImage = (ImageView) dialog.findViewById(R.id.closeImage);

        if(path.contains("http")){
            Picasso.with(getActivity()).load(path)
                    .into(docView);
        }else{
            Bitmap myBitmap = BitmapFactory.decodeFile(path);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            myBitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
            docView.setImageBitmap(myBitmap);
        }

        closeImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();

    }
}
