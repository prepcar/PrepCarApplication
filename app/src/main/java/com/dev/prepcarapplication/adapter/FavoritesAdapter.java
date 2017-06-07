package com.dev.prepcarapplication.adapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.dev.prepcarapplication.R;
import com.dev.prepcarapplication.baseClasses.BaseFragmentActivity;
import com.dev.prepcarapplication.bean.NewMatchesBean;
import com.dev.prepcarapplication.networkTask.ApiManager;
import com.dev.prepcarapplication.utilities.EllipsizingTextView;
import com.dev.prepcarapplication.utilities.ToastCustomClass;
import com.facebook.CallbackManager;
import com.facebook.FacebookSdk;
import com.facebook.share.model.ShareContent;
import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.widget.ShareDialog;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;


public class FavoritesAdapter extends RecyclerView.Adapter<FavoritesAdapter.MyViewHolder> {
    LayoutInflater inflater;
    CustomAdapter adapter;
    ArrayList<NewMatchesBean> arrayList;
    Activity activity;
    BaseFragmentActivity frg;
    CallbackManager callbackManager;
    ShareDialog shareDialog;
    Dialog dialog;
    Context context;
    private String date;

    public FavoritesAdapter(Activity activity, ArrayList<NewMatchesBean> arrayList, Context context) {

        this.context =  context;
        this.arrayList = arrayList;
        this.activity = activity;

        inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.frg = (BaseFragmentActivity) activity;
        FacebookSdk.sdkInitialize(activity);
        callbackManager = CallbackManager.Factory.create();
        shareDialog = new ShareDialog(activity);
    }


    @Override
    public FavoritesAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.favorites_item, parent, false);
        return new MyViewHolder(view);
    }
    @Override
    public int getItemViewType(int position) {
        return position;
    }
    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
       final int pos =position;
        holder.text_card3.setText(arrayList.get(position).getFirst_name());

        holder.text_card1.setText("NEW: "+arrayList.get(position).getModel_year() + "-" + arrayList.get(position).getMake() + "-" + arrayList.get(position).getModel_type());
        holder.textLeaseOptions.setText(arrayList.get(position).getTerms().equals("")?"N/A":arrayList.get(position).getTerms());
        holder.textFinanceDeal.setText(arrayList.get(position).getMonths().equals("") &&
                arrayList.get(position).getMonthlyPayment().equals("")?"N/A":"$"+arrayList.get(position).getMonthlyPayment()
                +" per month for "+arrayList.get(position).getMonths()+" months");

        holder.textInterestRate.setText(arrayList.get(position).getInterestRate().equals("")?"N/A":arrayList.get(position).getInterestRate()+" %");
        holder.textDownpayment.setText(arrayList.get(position).getDownPayment().equals("")?"N/A":"$"+arrayList.get(position).getDownPayment());
        holder.textTradeInValue.setText(arrayList.get(position).getTradeInValue().equals("")?"N/A":"$"+arrayList.get(position).getTradeInValue());


        holder.textOtherPrice.setText("$"+arrayList.get(position).getBestPrice());
        holder.deletefav.setTag(position);
        holder.deletefav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int pos = (Integer) v.getTag();
                callingSerDelete(String.valueOf(arrayList.get(pos).getDealer_id()),String.valueOf(arrayList.get(pos).getRating_id()));
                arrayList.remove(pos);
                notifyDataSetChanged();
            }
        });
        holder.rel_share.setTag(position);
        holder.rel_testdrive.setTag(position);
        holder.rel_sendsimilar.setTag(position);
        holder.rel_contact.setTag(position);

        holder.rel_share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int pos = (Integer) v.getTag();
                if (arrayList.get(pos).getDeal_expire()!=1) {
                    ShareContent linkContent = new ShareLinkContent.Builder()
                            .setContentTitle("PrepCar")
                            .setContentDescription(arrayList.get(pos).getCar_pic())
                            .setContentUrl(Uri.parse("http://www.prepcar.com/"))
                            .build();

                    shareDialog.show(linkContent);
                }

            }
        });

        if (arrayList.get(position).getTest_drive_status().equals("1")){
           holder.tv_test_drive.setBackgroundColor(Color.parseColor("#5b9e1d"));
            holder.tv_test_drive.setTextColor(ContextCompat.getColor(context,R.color.white));
        }else if (arrayList.get(position).getTest_drive_status().equals("2")){
            holder.tv_test_drive.setBackgroundColor(Color.parseColor("#800080"));
            holder.tv_test_drive.setTextColor(ContextCompat.getColor(context,R.color.white));
        }else{
            holder.tv_test_drive.setBackgroundColor(ContextCompat.getColor(context,android.R.color.transparent));
        }
        holder.rel_testdrive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int pos = (Integer) v.getTag();
                if (arrayList.get(pos).getDeal_expire() != 1) {
                    if (arrayList.get(pos).getTest_drive_status().equals("4") || arrayList.get(pos).getTest_drive_status().equals("2"))
                    selectDate(activity, pos, arrayList.get(position).getModel_year() + " - " + arrayList.get(position).getMake() + " - " + arrayList.get(position).getModel_type());
                    else if (arrayList.get(pos).getTest_drive_status().equals("0"))
                        Toast.makeText(context, "Your request already sent to dealer", Toast.LENGTH_SHORT).show();
                    else
                        Toast.makeText(context, "Dealer already accepted your request", Toast.LENGTH_SHORT).show();
                }
            }
        });
        holder.rel_sendsimilar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int pos = (Integer) v.getTag();
                showDialogSendSimilar("sendsimilar", pos,arrayList.get(position).getModel_year() + " - " + arrayList.get(position).getMake() + " - " + arrayList.get(position).getModel_type());
            }
        });
        holder.rel_contact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int pos = (Integer) v.getTag();
                showdialogContact(arrayList.get(pos).getDealerPhone(),pos);
            }
        });
        if (!arrayList.get(position).getProfile_picture().equals("")) {
            Picasso.with(context).load(arrayList.get(position).getProfile_picture()).into(holder.cirImage);
        }
        ArrayList<String> arrayListImage = arrayList.get(position).getCarImages();
        Log.e("adapter", "onBindViewHolder: " + arrayListImage.size());
        ViewPagerAdapter mp = new ViewPagerAdapter(context, arrayListImage,arrayList.get(position).getNickname());
        holder.vp.setAdapter(mp);

        String message;
        if (arrayList.get(pos).getDescription().length()!=0) {
            message = "Description : "+ arrayList.get(pos).getDescription()
                    + ((arrayList.get(pos).getDisclaimer().length() == 0) ? "" : "\n\n" + "Disclaimer : " + arrayList.get(pos).getDisclaimer());
        }else{
            message = ((arrayList.get(pos).getDisclaimer().length() == 0) ? "" : "\n\n" + "Disclaimer : " + arrayList.get(pos).getDisclaimer());
        }
        holder.text_card5.setMaxLines(2);
        holder.text_card5.setText(message);
        holder.text_card5.setEllipsize(TextUtils.TruncateAt.END);

        holder.text_card5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String message;
                if (arrayList.get(pos).getDescription().length()!=0) {
                    message = "Description : "+ arrayList.get(pos).getDescription()
                            + ((arrayList.get(pos).getDisclaimer().length() == 0) ? "" : "\n\n" + "Disclaimer : " + arrayList.get(pos).getDisclaimer());
                }else{
                    message = ((arrayList.get(pos).getDisclaimer().length() == 0) ? "" : "\n\n" + "Disclaimer : " + arrayList.get(pos).getDisclaimer());
                }
                holder.text_card5.setMaxLines(Integer.MAX_VALUE);
                holder.text_card5.setText(message);

            }
        });

        if (arrayList.get(position).getDeal_expire()==1){
            holder.text_deal_expire.setVisibility(View.VISIBLE);
        }else{
            holder.text_deal_expire.setVisibility(View.GONE);
        }

        holder.rating.setRating(arrayList.get(position).getRating());


    }

    private void showDialog(String message) {
        if (message.trim().length()>0) {
            new AlertDialog.Builder(context)
                    .setMessage(message)
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    }).show();
        }
    }

    private void callingSerDelete(String dealerid,String rating_id) {
        ApiManager.getInstance().getDeletFav(frg, dealerid,rating_id);
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private ImageView cirImage, deletefav;
        ViewPager vp;
       final TextView text_card3, text_adres, text_card, text_card1, text_card4, textOtherPrice,textLeaseOptions
                ,textFinanceDeal,text_deal_expire;
        TextView textInterestRate,textDownpayment,textTradeInValue,tv_test_drive;
        EllipsizingTextView text_card5;
        RelativeLayout rel_share, rel_testdrive, rel_sendsimilar, rel_contact;
        RatingBar rating;
        private MyViewHolder(View view) {
            super(view);
            this.vp = (ViewPager) view.findViewById(R.id.pager);
            this.cirImage = (ImageView) view.findViewById(R.id.cirImage);
            this.text_card3 = (TextView) view.findViewById(R.id.text_card3);
            this.text_adres = (TextView) view.findViewById(R.id.text_adres);
            this.text_card = (TextView) view.findViewById(R.id.text_card);
            this.text_card4 = (TextView) view.findViewById(R.id.text_card4);
            this.text_card5 = (EllipsizingTextView) view.findViewById(R.id.text_card5);
            this.text_card1 = (TextView) view.findViewById(R.id.text_card1);
            this.deletefav = (ImageView) view.findViewById(R.id.deletefav);
            this.rel_share = (RelativeLayout) view.findViewById(R.id.rel_share);
            this.rel_testdrive = (RelativeLayout) view.findViewById(R.id.rel_testdrive);
            this.rel_sendsimilar = (RelativeLayout) view.findViewById(R.id.rel_sendsimilar);
            this.rel_contact = (RelativeLayout) view.findViewById(R.id.rel_contact);
            this.textOtherPrice = (TextView) view.findViewById(R.id.text_other_price);
            this.textLeaseOptions = (TextView) view.findViewById(R.id.text_lease_options);
            this.textFinanceDeal = (TextView) view.findViewById(R.id.text_finance_deal);
            this.textInterestRate = (TextView) view.findViewById(R.id.text_interest_rate);
            this.textDownpayment = (TextView) view.findViewById(R.id.text_downpayment);
            this.textTradeInValue = (TextView) view.findViewById(R.id.text_tradeinoffer);
            this.text_deal_expire = (TextView) view.findViewById(R.id.text_deal_expire);
            this.tv_test_drive = (TextView) view.findViewById(R.id.tv_test_drive);
            this.rating = (RatingBar) view.findViewById(R.id.rating);
        }
    }

    private void showDialogSendSimilar(final String text, final int pos,String car) {
        dialog = new Dialog(context);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.setContentView(R.layout.dialog_test_drive);
        TextView title = (TextView) dialog.findViewById(R.id.dialog_text);
        final EditText dialog_edit = (EditText) dialog.findViewById(R.id.dialog_edit);
        TextView dialog_savebut = (TextView) dialog.findViewById(R.id.dialog_savebut);
        final TextView dialog_cancel = (TextView) dialog.findViewById(R.id.dialog_cancel);
        if (text.equalsIgnoreCase("sendsimilar")){
            dialog_edit.setText("Please send more cars like this\n"+car);
        }
        dialog_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog_savebut.setOnClickListener(new View.OnClickListener() {
                @Override
            public void onClick(View v) {

                if (dialog_edit.getText().toString().trim().equals("")) {
                    ToastCustomClass.showToast(context, "Please enter a message");
                } else {
                    InputMethodManager imm = (InputMethodManager) context.getSystemService(v.getContext().INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(v.getApplicationWindowToken(), 0);
                    callingServiceMessages(text, pos, dialog_edit.getText().toString().trim());
                }
            }
        });
        dialog.show();
    }

    private void showDialogTestDrive( final String date,final String time, final int pos,String car) {
        final Dialog dialog = new Dialog(context);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.setContentView(R.layout.dialog_test_drive);
        TextView title = (TextView) dialog.findViewById(R.id.dialog_text);
        final EditText dialog_edit = (EditText) dialog.findViewById(R.id.dialog_edit);
        TextView dialog_savebut = (TextView) dialog.findViewById(R.id.dialog_savebut);
        final TextView dialog_cancel = (TextView) dialog.findViewById(R.id.dialog_cancel);
        title.setText("Schedule test drive for");
        dialog_edit.setText(car+"\n\nDate : "+date+"\n\nTime : "+time);
        dialog_edit.setFocusable(false);
        dialog_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog_savebut.setOnClickListener(new View.OnClickListener() {
                @Override
            public void onClick(View v) {

                if (dialog_edit.getText().toString().trim().equals("")) {
                    ToastCustomClass.showToast(context, "Please enter a message");
                } else {
                    InputMethodManager imm = (InputMethodManager) context.getSystemService(v.getContext().INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(v.getApplicationWindowToken(), 0);
                    ApiManager.getInstance().testDrive(frg, String.valueOf(arrayList.get(pos).getDealer_id()),
                            String.valueOf(arrayList.get(pos).getDeal_id()),
                            date,time,arrayList.get(pos).getCarplanId());
                    dialog.dismiss();
                }
            }
        });
        dialog.show();
    }

    private void showdialogContact(final String text, final int pos) {
        dialog = new Dialog(context);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.setContentView(R.layout.dialog_contact);
        dialog.setCancelable(true);
        LinearLayout call = (LinearLayout) dialog.findViewById(R.id.ll_call);
        LinearLayout message = (LinearLayout) dialog.findViewById(R.id.ll_message);

        call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:"+text));
                activity.startActivity(intent);
            }
        });
        message.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                try {
                    Intent smsIntent = new Intent(Intent.ACTION_SENDTO);
                    smsIntent.setType("vnd.android-dir/mms-sms");
                    smsIntent.setData(Uri.parse("sms:" + text));
                    activity.startActivity(smsIntent);
                }catch (ActivityNotFoundException e){
                    ToastCustomClass.showToast(activity,"No Application to handle message");
                }
            }
        });
        dialog.show();
    }

    private void callingServiceMessages(String text, int pos, String msg) {
        String type = "";
        if (text.equals("testdrive")) {
            type = "1";
        }
        if (text.equals("sendsimilar")) {
            type = "2";
        }
        if (text.equals("contact")) {
            type = "3";
        }
        ApiManager.getInstance().getMessage(frg, String.valueOf(arrayList.get(pos).getDealer_id()), type,
                String.valueOf(arrayList.get(pos).getCar_id()), msg);
        dialog.dismiss();

    }

    private void selectDate(Activity activity,final int pos,final String car) {
        long maxDate=0,minDate=0;
        // Get Current Date
        final Calendar c = Calendar.getInstance();
        int mYear = c.get(Calendar.YEAR);
        int mMonth = c.get(Calendar.MONTH);
        int mDay = c.get(Calendar.DAY_OF_MONTH);

        c.setTime(new Date());
        minDate = c.getTime().getTime();
        Log.e("minDate", String.valueOf(minDate));

        c.add(Calendar.DAY_OF_MONTH, +1); // Add 1 day to limit the date picker to next day only
        maxDate = c.getTime().getTime();
        Log.e("maxDate", String.valueOf(maxDate));
        DatePickerDialog datePickerDialog = new DatePickerDialog(activity,
                new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year,
                                          int monthOfYear, int dayOfMonth) {

                        date = year + "-" + (monthOfYear + 1) + "-" + dayOfMonth;
                        selectTime(pos,car);
                    }
                }, mYear, mMonth, mDay);

        datePickerDialog.getDatePicker().setMinDate(minDate);
        //datePickerDialog.getDatePicker().setMaxDate(maxDate);
        datePickerDialog.show();
    }

    private void selectTime(final int pos,final String car){
        Calendar mCurrentTime = Calendar.getInstance();
        int hour = mCurrentTime.get(Calendar.HOUR_OF_DAY);
        int minute = mCurrentTime.get(Calendar.MINUTE);
        TimePickerDialog mTimePicker;
        mTimePicker = new TimePickerDialog(activity, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {

                int hour = selectedHour;
                int minutes = selectedMinute;
                String timeSet = "";
                if (hour > 12) {
                    hour -= 12;
                    timeSet = "PM";
                } else if (hour == 0) {
                    hour += 12;
                    timeSet = "AM";
                } else if (hour == 12){
                    timeSet = "PM";
                }else{
                    timeSet = "AM";
                }

                String min = "";
                if (minutes < 10)
                    min = "0" + minutes ;
                else
                    min = String.valueOf(minutes);

                // Append in a StringBuilder
                String time = new StringBuilder().append(hour).append(':')
                        .append(min ).append(" ").append(timeSet).toString();

                showDialogTestDrive(date,time,pos,car);

            }
        }, hour, minute, false);//Yes 24 hour time
        mTimePicker.setTitle("Select Time");
        mTimePicker.show();
    }

}