package com.dev.prepcarapplication.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dev.prepcarapplication.R;
import com.dev.prepcarapplication.bean.IKnowIWantModel;

import java.util.ArrayList;

/**
 * Created by Dev on 2/3/2017.
 */

public class IknowWhatIWantAdapter extends RecyclerView.Adapter<IknowWhatIWantAdapter.MyViewHolder> {


    LayoutInflater inflater;
    CustomAdapter adapter;
    ArrayList<IKnowIWantModel> arrayList;
    Activity activity;
    Button buton_addmore;
    LinearLayout insertLayout;

    public IknowWhatIWantAdapter(Activity activity, ArrayList<IKnowIWantModel> arrayList, Button buton_addmore, LinearLayout insertLayout) {

        this.arrayList = arrayList;
        this.activity = activity;
        this.insertLayout = insertLayout;
        this.buton_addmore = buton_addmore;
        inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = inflater.inflate(R.layout.i_know_what_i_want_adapter, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        IKnowIWantModel model = arrayList.get(position);

        Log.e("model", "model" + model.getMake());

        holder.et_idelcar.setText(model.getCondition());
        holder.et_year.setText(model.getYear());
        holder.et_make.setText(model.getMake());
        holder.et_model.setText(model.getModel());
        holder.edit_comment.setText(model.getComment());
        holder.et_features.setText(model.getFeature());
        holder.etColor.setText(model.getColor());

        holder.textCar.setText("IDEAL CAR " + String.valueOf(position + 1));

        if (arrayList.size() > 4) {
            buton_addmore.setVisibility(View.GONE);


        } else {
            buton_addmore.setVisibility(View.VISIBLE);
        }
        if (arrayList.size() == 0) {

            buton_addmore.setText(R.string.add);
            insertLayout.setVisibility(View.VISIBLE);

        } else {
            buton_addmore.setText(R.string.addmore);


        }


        holder.remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                arrayList.remove(position);
                if (arrayList.size() == 0) {
                    buton_addmore.setText(R.string.add);

                }

                notifyDataSetChanged();

            }
        });

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {


        EditText et_idelcar, et_year, et_make, et_model, edit_comment, et_features, etColor;
        TextView remove, textCar;

        public MyViewHolder(View itemView) {
            super(itemView);
            et_idelcar = (EditText) itemView.findViewById(R.id.et_idelcar);
            et_year = (EditText) itemView.findViewById(R.id.et_year);
            et_make = (EditText) itemView.findViewById(R.id.et_make);
            et_model = (EditText) itemView.findViewById(R.id.et_model);
            etColor = (EditText) itemView.findViewById(R.id.etColor);
            edit_comment = (EditText) itemView.findViewById(R.id.edit_comment);
            et_features = (EditText) itemView.findViewById(R.id.et_features);
            remove = (TextView) itemView.findViewById(R.id.remove);
            textCar = (TextView) itemView.findViewById(R.id.textCar);

        }
    }


}
