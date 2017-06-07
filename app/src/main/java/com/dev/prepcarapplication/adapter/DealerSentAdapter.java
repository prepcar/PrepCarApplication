package com.dev.prepcarapplication.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.dev.prepcarapplication.R;
import com.dev.prepcarapplication.baseClasses.BaseFragmentActivity;
import com.dev.prepcarapplication.bean.NewMatchesBean;
import com.dev.prepcarapplication.networkTask.ApiManager;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class DealerSentAdapter extends ArrayAdapter<String> {
    ArrayList<NewMatchesBean> arrayList;
    BaseFragmentActivity frg;
    Context context;

    public DealerSentAdapter(Context  context, ArrayList<NewMatchesBean> arrayList) {
        super(context, 0);
        this.context=context;
        this.arrayList = arrayList;
        this.frg = (BaseFragmentActivity) context;
    }

    @Override
    public int getCount() {

        return arrayList.size();
    }

    @Nullable
    @Override
    public String getItem(int position) {
        return String.valueOf(position);
    }

    @Override
    public long getItemId(int position) {

        return position;
    }

    @Override
    public View getView(final int position, View contentView, ViewGroup parent) {
        final ViewHolder holder;

        if (contentView == null) {
            contentView = View.inflate(getContext(), R.layout.item_dealer_sent, null);
            holder = new ViewHolder(contentView);
            contentView.setTag(holder);
        } else {
            holder = (ViewHolder) contentView.getTag();
        }

        holder.text_card3.setText(arrayList.get(position).getFirst_name());

        holder.text_card1.setText("NEW:"+arrayList.get(position).getModel_year() + " - " + arrayList.get(position).getMake() + " - " + arrayList.get(position).getModel_type());
        holder.text_card5.setText(arrayList.get(position).getDescription());
        holder.textPrice.setText(arrayList.get(position).getBestPrice().equals("")?"N/A":"$ "+arrayList.get(position).getBestPrice());
        holder.textMonthlyPrice.setText(arrayList.get(position).getMonthlyPayment().equals("")?"N/A":"$ "+arrayList.get(position).getMonthlyPayment());

        if (arrayList.get(position).getNickname().trim().length()>0)
        holder.text_nickname.setText(arrayList.get(position).getNickname());
        else
            holder.text_nickname.setVisibility(View.GONE);

        if (!arrayList.get(position).getProfile_picture().equals("")) {
            Picasso.with(context).load(arrayList.get(position).getProfile_picture()).into(holder.cirImage);
        }
        if (!arrayList.get(position).getSingle_car_pic().equals("")) {
            Picasso.with(context)
                    .load(arrayList.get(position).getSingle_car_pic()).placeholder(R.drawable.loading)
                    .into(holder.maches_carpic);
        }

        holder.text_card5.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                    if (holder.text_card5.getLineCount()  >= 2) {
                        holder.btnSeeMore.setVisibility(View.VISIBLE);
                        holder.text_card5.setMaxLines(2);

                    }else{
                        holder.btnSeeMore.setVisibility(View.GONE);
                    }

            }
        });

        holder.text_expire.setTag(position);
        if (arrayList.get(position).getDeal_expire()==1){
            holder.text_expire.setChecked(true);
            holder.text_expire.setText("Sold");
        }else{
            holder.text_expire.setChecked(false);
            holder.text_expire.setText("Available");
        }
        holder.text_expire.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int pos = (Integer) v.getTag();
                if (holder.text_expire.isChecked()) {
                    callingServiceDelete(String.valueOf(arrayList.get(pos).getDeal_id()),"1");
                    arrayList.get(pos).setDeal_expire(1);
                    notifyDataSetChanged();
                }else{
                    callingServiceDelete(String.valueOf(arrayList.get(pos).getDeal_id()),"0");
                    arrayList.get(pos).setDeal_expire(0);
                    notifyDataSetChanged();
                }
            }
        });

        holder.btnSeeMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(context)
                        .setMessage(arrayList.get(position).getDescription())
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                }).show();
            }
        });
        return contentView;
    }

    private void callingServiceDelete(String deal_id,String status) {
        ApiManager.getInstance().DealExpire(frg, deal_id,status);
    }

    private static class ViewHolder {
        private ImageView maches_carpic, cirImage;
        TextView text_card3, text_adres, text_card, maches_miles, text_card1, text_card4, text_card5,text_nickname;
        TextView textPrice,textMonthlyPrice,btnSeeMore;
        CheckBox text_expire;
        private ViewHolder(View view) {
            this.maches_carpic = (ImageView) view.findViewById(R.id.maches_carpic);
            this.cirImage = (ImageView) view.findViewById(R.id.cirImage);
            this.text_card3 = (TextView) view.findViewById(R.id.text_card3);
            this.text_adres = (TextView) view.findViewById(R.id.text_adres);
            this.text_card = (TextView) view.findViewById(R.id.text_card);
            this.maches_miles = (TextView) view.findViewById(R.id.maches_miles);
            this.text_card4 = (TextView) view.findViewById(R.id.text_card4);
            this.text_card5 = (TextView) view.findViewById(R.id.text_card5);
            this.textPrice = (TextView) view.findViewById(R.id.text_cool_features);
            this.text_nickname = (TextView) view.findViewById(R.id.text_nickname);
            this.text_expire = (CheckBox) view.findViewById(R.id.text_expire);
            this.textMonthlyPrice = (TextView) view.findViewById(R.id.text_monthly_price);
            this.text_card1 = (TextView) view.findViewById(R.id.text_card1);
            this.btnSeeMore = (TextView) view.findViewById(R.id.text_see_more);

        }
    }

}

