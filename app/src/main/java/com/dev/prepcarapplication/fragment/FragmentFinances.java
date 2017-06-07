package com.dev.prepcarapplication.fragment;

import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.Spinner;
import android.widget.TextView;

import com.dev.prepcarapplication.CarPlanActivity;
import com.dev.prepcarapplication.CurrentCarActivity;
import com.dev.prepcarapplication.R;
import com.dev.prepcarapplication.baseClasses.BaseFragment;
import com.dev.prepcarapplication.networkTask.ApiManager;
import com.dev.prepcarapplication.utilities.ToastCustomClass;

import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class FragmentFinances extends BaseFragment {

    public static String TAG = "FINANCES";
    Button buton_finance;
    EditText edit_dob, edit_amount1, edit_amount, edit_income, spiner_payment, edit_amountrent;
    Spinner spiner_own, spiner_budget;
    RelativeLayout lin_yesrent, lin_norent, lin_yesmortgage, lin_nomortgage,
            lin_yesstuloan, lin_nostuloan, lin_yesfinance, lin_nofinance;
    TextView text_yesrent, text_norent, text_yesmortgage, text_nomortgage, text_seekbar, text_seekbar2,
            text_yesstuloan, text_nostuloan, text_amount, text_amount1, text_yesfianance, text_nofianance, text_amountrent;
    String monthlypayment = "", choseyourown = "", budget = "", rent = "", mortage = "", studentloans = "", do_you_need_finance = "";
    int sendmonthlypayment, sendyourown, sendbudget;
    Button buton_tax;
    SeekBar seek_payment, seek_credit;
    ImageView icHome;
    ImageView back;
    List<String> categories1, categories2;
    LinearLayout layoutFinance,layoutChooseOwn;
    private Calendar calendar;
    private int year, month, day;
    private DatePickerDialog fromDatePickerDialog;
    private SimpleDateFormat dateFormatter;

    public static FragmentFinances newInstanse(Bundle bundle) {
        FragmentFinances fragment = new FragmentFinances();
        if (bundle != null) {
            fragment.setArguments(bundle);
        }
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        View view = inflater.inflate(R.layout.fragment_finances, null);
        initUi(view);
        setListener();
        callingServicegetDiscovery();
        yesButtonClick();
        return view;
    }

    private void callingServicegetDiscovery() {
        // TODO Auto-generated method stub
        ApiManager.getInstance().getFinanceDetail(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.lin_norent:
//              text_amountrent.setVisibility(View.GONE);
                onRentNoClick();
                break;
            case R.id.lin_yesrent:
              //  text_amountrent.setVisibility(View.VISIBLE);
                if (do_you_need_finance.equals("yes"))
                onRentYesClick();
                break;
            case R.id.lin_nomortgage:
                onMortgageNoClick();
                break;
            case R.id.lin_yesmortgage:
                if (do_you_need_finance.equals("yes"))
                onMortgageYesClick();
                break;
            case R.id.lin_nostuloan:
                onStudentLoanNoClick();
                break;
            case R.id.lin_yesstuloan:
                if (do_you_need_finance.equals("yes"))
                onStudentLoanYesClick();
                break;
            case R.id.buton_finance:
            /*if(mortage.equals("yes")){
                if(edit_amount.getText().toString().trim().equals("")){
					ToastCustomClass.showToast(getActivity(), "Please enter mortage amount");
				}else if(edit_amount1.getText().toString().trim().equals("")){
					ToastCustomClass.showToast(getActivity(), "Please enter student loan amount");
				}
					
				
			}
			else{*/
                calingserviceFinanaces();//}
			/*if(edit_income.getText().toString().trim().equals("")){
				ToastCustomClass.showToast(getActivity(), "please enter EST monthaly income");
			}
			else if(rent.equals("")){
				ToastCustomClass.showToast(getActivity(), "please enter rent option");
			}
			else if(mortage.equals("")){
				ToastCustomClass.showToast(getActivity(), "please enter mortage option");
			}*/
			/*else if(edit_amount.getText().toString().trim().equals("")){
				ToastCustomClass.showToast(getActivity(), "please enter amount");
			}*/
			/*else if(studentloans.equals("")){
				ToastCustomClass.showToast(getActivity(), "please select student loan");
			}*/
			/*else if(edit_amount1.getText().toString().trim().equals("")){
				ToastCustomClass.showToast(getActivity(), "please enter EST monthaly income");
			}*/
			/*else if(text_seekbar.getText().toString().trim().equals("")){
				ToastCustomClass.showToast(getActivity(), "please select EST credit score");
			}*/
			/*else if(monthlypayment.equals("")){
				ToastCustomClass.showToast(getActivity(), "please enter EST monthaly income");
			}
			else if(choseyourown.equals("")){
				ToastCustomClass.showToast(getActivity(), "please select choose your own ");
			}
			else if(budget.equals("")){
				ToastCustomClass.showToast(getActivity(), "please select your budget");
			}*/
			/*else if(text_seekbar2.getText().toString().trim().equals("")){
				ToastCustomClass.showToast(getActivity(), "please select your down payment ");
			}
			else if(edit_dob.getText().toString().trim().equals("")){
				ToastCustomClass.showToast(getActivity(), "please enter your DOB");
			}
			else{*/

                //}
                break;
            case R.id.image_back:
                getActivity().finish();
                break;
            case R.id.lin_yesfinance:
                yesButtonClick();
                break;
            case R.id.lin_nofinance:
              noButtonClick();
                break;
            case R.id.edit_dob:
                calendar = Calendar.getInstance();
                openCalender();
                break;
            case R.id.buton_tax:
                if(edit_amountrent.getText().toString().trim().equals("")){
                    ToastCustomClass.showToast(getActivity(),"please enter rent amount");
                }
               else if(edit_amount.getText().toString().trim().equals("")){
                    ToastCustomClass.showToast(getActivity(),"please enter mortage amount");
                }
                else if(edit_amount1.getText().toString().trim().equals("")){
                    ToastCustomClass.showToast(getActivity(),"please enter student loan amount");
                }
                else{
                calculateMomnthlypaymenty();}
                break;
            case R.id.edit_amount:
                /*Log.i("rent===",rent);
                if(rent.equals("no")){
                    edit_amount.setEnabled(false);
                }else{
                    edit_amount.setEnabled(true);
                }*/
              //  ToastCustomClass.showToast(getActivity(),"click");
                break;
            case R.id.icHome:
                startMyActivityTop(CarPlanActivity.class,null);
                getActivity().finish();
                break;

            default:
                break;
        }
    }

    private void onStudentLoanYesClick() {
        edit_amount1.setEnabled(true);
        studentloans = "yes";
        lin_yesstuloan.setBackgroundResource(R.drawable.financebuttonshape);
        text_yesstuloan.setTextColor(Color.WHITE);
        lin_nostuloan.setBackgroundResource(R.drawable.discoveryshape);
        text_nostuloan.setTextColor(Color.parseColor("#c4c4c4"));
    }

    private void onStudentLoanNoClick() {
        edit_amount1.setText("");
        edit_amount1.setEnabled(false);
        studentloans = "no";
        lin_nostuloan.setBackgroundResource(R.drawable.financebuttonshape);
        text_nostuloan.setTextColor(Color.WHITE);
        lin_yesstuloan.setBackgroundResource(R.drawable.discoveryshape);
        text_yesstuloan.setTextColor(Color.parseColor("#c4c4c4"));
    }

    private void onMortgageYesClick() {
        edit_amount.setEnabled(true);
        mortage = "yes";
        lin_yesmortgage.setBackgroundResource(R.drawable.financebuttonshape);
        text_yesmortgage.setTextColor(Color.WHITE);
        lin_nomortgage.setBackgroundResource(R.drawable.discoveryshape);
        text_nomortgage.setTextColor(Color.parseColor("#c4c4c4"));
    }

    private void onMortgageNoClick() {
        edit_amount.setText("");
        edit_amount.setEnabled(false);
        mortage = "no";
        lin_nomortgage.setBackgroundResource(R.drawable.financebuttonshape);
        text_nomortgage.setTextColor(Color.WHITE);
        lin_yesmortgage.setBackgroundResource(R.drawable.discoveryshape);
        text_yesmortgage.setTextColor(Color.parseColor("#c4c4c4"));

    }

    private void onRentYesClick() {
        edit_amountrent.setEnabled(true);
        rent = "yes";
        lin_yesrent.setBackgroundResource(R.drawable.financebuttonshape);
        text_yesrent.setTextColor(Color.WHITE);
        lin_norent.setBackgroundResource(R.drawable.discoveryshape);
        text_norent.setTextColor(Color.parseColor("#c4c4c4"));
    }

    private void onRentNoClick() {
        edit_amountrent.setEnabled(false);
        edit_amount.setText("");
        rent = "no";
        lin_norent.setBackgroundResource(R.drawable.financebuttonshape);
        text_norent.setTextColor(Color.WHITE);
        lin_yesrent.setBackgroundResource(R.drawable.discoveryshape);
        text_yesrent.setTextColor(Color.parseColor("#c4c4c4"));
    }

    private void yesButtonClick() {
        do_you_need_finance = "yes";
        lin_yesfinance.setBackgroundResource(R.drawable.financebuttonshape);
        text_yesfianance.setTextColor(Color.WHITE);
        lin_nofinance.setBackgroundResource(R.drawable.discoveryshape);
        text_nofianance.setTextColor(Color.parseColor("#c4c4c4"));
        onMortgageYesClick();
        onRentYesClick();
        onStudentLoanYesClick();
        edit_income.setEnabled(true);
        seek_credit.setEnabled(true);
        buton_tax.setEnabled(true);
        seek_payment.setEnabled(true);
        spiner_own.setEnabled(true);
    }

    private void noButtonClick() {
        do_you_need_finance = "no";
        lin_nofinance.setBackgroundResource(R.drawable.financebuttonshape);
        text_nofianance.setTextColor(Color.WHITE);
        lin_yesfinance.setBackgroundResource(R.drawable.discoveryshape);
        text_yesfianance.setTextColor(Color.parseColor("#c4c4c4"));
        onMortgageNoClick();
        onRentNoClick();
        onStudentLoanNoClick();
        edit_income.setEnabled(false);
        seek_credit.setEnabled(false);
        buton_tax.setEnabled(false);
        seek_payment.setEnabled(false);
        spiner_own.setEnabled(false);
    }

    private void calculateMomnthlypaymenty() {

        int value = (Integer.parseInt(edit_income.getText().toString().trim()) - Integer.parseInt(edit_amountrent.getText().toString().trim())
                - Integer.parseInt(edit_amount.getText().toString().trim()) - Integer.parseInt(edit_amount1.getText().toString().trim())) * 15 / 100;
        System.out.println("cal====" + value);
        spiner_payment.setText(String.valueOf(value));
    }

    private void calingserviceFinanaces() {
        budget = (String) spiner_budget.getSelectedItem();
        choseyourown = (String) spiner_own.getSelectedItem();
        monthlypayment = (String) spiner_payment.getText().toString().trim();
        ApiManager.getInstance().getFiannaces(this, edit_income.getText().toString().trim(), rent, mortage,
                edit_amount.getText().toString().trim(), studentloans, edit_amount1.getText().toString().trim(),
                text_seekbar.getText().toString(),
                monthlypayment, choseyourown, budget,
                text_seekbar2.getText().toString(), edit_dob.getText().toString().trim(), do_you_need_finance,
                edit_amountrent.getText().toString().trim());
    }

    @Override
    protected void initUi(View view) {
        // TODO Auto-generated method stub
        buton_tax=(Button)view.findViewById(R.id.buton_tax);
        //dateFormatter = new SimpleDateFormat("mm/dd/yyyy", Locale.US);
        text_amountrent = (TextView) view.findViewById(R.id.text_amountrent);
        edit_amountrent = (EditText) view.findViewById(R.id.edit_amountrent);
        dateFormatter = new SimpleDateFormat("EEE, d MMM, yy");
        text_nofianance = (TextView) view.findViewById(R.id.text_nofianace);
        text_yesfianance = (TextView) view.findViewById(R.id.text_yesfianace);
        lin_yesfinance = (RelativeLayout) view.findViewById(R.id.lin_yesfinance);
        lin_nofinance = (RelativeLayout) view.findViewById(R.id.lin_nofinance);
        back = (ImageView) view.findViewById(R.id.image_back);
        text_amount = (TextView) view.findViewById(R.id.text_amount);
        text_amount1 = (TextView) view.findViewById(R.id.text_amount1);
        icHome = (ImageView) view.findViewById(R.id.icHome);
        seek_credit = (SeekBar) view.findViewById(R.id.seek_credit);
        seek_payment = (SeekBar) view.findViewById(R.id.seek_payment);
        text_seekbar = (TextView) view.findViewById(R.id.text_seekbar);
        text_seekbar2 = (TextView) view.findViewById(R.id.text_seekbar2);
        buton_finance = (Button) view.findViewById(R.id.buton_finance);
        edit_amount = (EditText) view.findViewById(R.id.edit_amount);
        edit_amount1 = (EditText) view.findViewById(R.id.edit_amount1);
        edit_dob = (EditText) view.findViewById(R.id.edit_dob);
        edit_income = (EditText) view.findViewById(R.id.edit_income);
        spiner_budget = (Spinner) view.findViewById(R.id.spiner_budget);
        spiner_own = (Spinner) view.findViewById(R.id.spiner_own);
        spiner_payment = (EditText) view.findViewById(R.id.spiner_payment);
        layoutFinance = (LinearLayout)view.findViewById(R.id.ll_finance);
        layoutChooseOwn = (LinearLayout)view.findViewById(R.id.ll_choose_own);

        categories1 = new ArrayList<String>();
        categories1.add("Under $200");
        categories1.add("Under $300");
        categories1.add("Under $400");
        categories1.add("Under $500");
        categories1.add("Under $700");
        categories1.add("Under $1000");
        categories1.add("Under $1500");
        categories1.add("No max");
        ArrayAdapter<String> dataAdapter1 = new ArrayAdapter<String>(
                getActivity(), android.R.layout.simple_spinner_item,
                categories1);
        dataAdapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spiner_own.setAdapter(dataAdapter1);

        categories2 = new ArrayList<String>();
        categories2.add("Under $5k");
        categories2.add("Under $10k");
        categories2.add("Under $15k");
        categories2.add("Under $20k");
        categories2.add("Under $25k");
        categories2.add("Under $30k");
        categories2.add("Under $35k");
        categories2.add("Under $50k");
        categories2.add("Under $75k");
        categories2.add("Under $100k");
        categories2.add("No max");
        ArrayAdapter<String> dataAdapter2 = new ArrayAdapter<String>(
                getActivity(), android.R.layout.simple_spinner_item,
                categories2);
        dataAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spiner_budget.setAdapter(dataAdapter2);

        lin_nomortgage = (RelativeLayout) view.findViewById(R.id.lin_nomortgage);
        lin_norent = (RelativeLayout) view.findViewById(R.id.lin_norent);
        lin_nostuloan = (RelativeLayout) view.findViewById(R.id.lin_nostuloan);
        text_nomortgage = (TextView) view.findViewById(R.id.text_nomortgage);
        text_norent = (TextView) view.findViewById(R.id.text_norent);
        text_nostuloan = (TextView) view.findViewById(R.id.text_nostuloan);
        text_yesmortgage = (TextView) view.findViewById(R.id.text_yesmortgage);
        text_yesrent = (TextView) view.findViewById(R.id.text_yesrent);
        text_yesstuloan = (TextView) view.findViewById(R.id.text_yesstuloan);
        lin_yesmortgage = (RelativeLayout) view.findViewById(R.id.lin_yesmortgage);
        lin_yesrent = (RelativeLayout) view.findViewById(R.id.lin_yesrent);
        lin_yesstuloan = (RelativeLayout) view.findViewById(R.id.lin_yesstuloan);
        seek_credit.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress,
                                          boolean fromUser) {
                if (progress == 0 || progress == 500) {
                    text_seekbar.setVisibility(View.GONE);
                }else {
                    progress = ((int)Math.round(progress/5))*5;
                    seekBar.setProgress(progress);
                    text_seekbar.setVisibility(View.VISIBLE);
                    text_seekbar.setText(String.valueOf(350 + progress));
                }
            }
        });
      //  seek_payment.incrementProgressBy(500);
        seek_payment.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress,
                                          boolean fromUser) {
                if (progress == 0 || progress == 20000) {
                    text_seekbar2.setVisibility(View.GONE);
                } else {
                    progress = ((int)Math.round(progress/500))*500;
                    seekBar.setProgress(progress);
                    text_seekbar2.setVisibility(View.VISIBLE);
                    text_seekbar2.setText(String.valueOf(progress));
                }
            }
        });

    }

    @Override
    protected void setValueOnUi() {
    }

    @Override
    protected void setListener() {
        // TODO Auto-generated method stub
        lin_yesmortgage.setOnClickListener(this);
        lin_yesrent.setOnClickListener(this);
        lin_yesstuloan.setOnClickListener(this);
        lin_nostuloan.setOnClickListener(this);
        lin_norent.setOnClickListener(this);
        lin_nomortgage.setOnClickListener(this);
        buton_finance.setOnClickListener(this);
        back.setOnClickListener(this);
        lin_yesfinance.setOnClickListener(this);
        lin_nofinance.setOnClickListener(this);
        edit_dob.setOnClickListener(this);
        buton_tax.setOnClickListener(this);
        edit_amount.setOnClickListener(this);
        icHome.setOnClickListener(this);
    }

    @Override
    public boolean onBackPressedListener() {
        // TODO Auto-generated method stub
        return false;
    }

    public void setresponce(JSONObject jObject) {
        // TODO Auto-generated method stub
        if (jObject.optInt("status") == 1 && jObject.optInt("dataflow") == 1) {
            startMyActivity(CurrentCarActivity.class, null);
            getActivity().finish();
        }
    }

    private void openCalender() {
        // TODO Auto-generated method stub
        Calendar newCalendar = Calendar.getInstance();
        fromDatePickerDialog = new DatePickerDialog(getActivity(),
                new OnDateSetListener() {

                    public void onDateSet(DatePicker view, int year,
                                          int monthOfYear, int dayOfMonth) {
                        calendar = Calendar.getInstance();
                        calendar.set(year, monthOfYear, dayOfMonth);
                        edit_dob.setText(dateFormatter.format(calendar.getTime()));
                    }

                }, newCalendar.get(Calendar.YEAR), newCalendar
                .get(Calendar.MONTH), newCalendar
                .get(Calendar.DAY_OF_MONTH));
        fromDatePickerDialog.show();
        fromDatePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());

    }

    public void setresponcegetFinance(JSONObject jObject) {
        // TODO Auto-generated method stub
        if (jObject.optInt("status") == 1 && jObject.optInt("dataflow") == 1) {
            try {
                do_you_need_finance = jObject.optString("do_you_need_finance");
                if (do_you_need_finance.equals("yes")) {
                    lin_yesfinance.setBackgroundResource(R.drawable.financebuttonshape);
                    text_yesfianance.setTextColor(Color.WHITE);
                    lin_nofinance.setBackgroundResource(R.drawable.discoveryshape);
                    text_nofianance.setTextColor(Color.parseColor("#c4c4c4"));
                } else if (do_you_need_finance.equals("no")) {
                    lin_nofinance.setBackgroundResource(R.drawable.financebuttonshape);
                    text_nofianance.setTextColor(Color.WHITE);
                    lin_yesfinance.setBackgroundResource(R.drawable.discoveryshape);
                    text_yesfianance.setTextColor(Color.parseColor("#c4c4c4"));
                }
                edit_income.setText(jObject.optString("estmonthlyincome"));
                rent = jObject.optString("rent");
                if (rent.equals("yes")) {
                    edit_amountrent.setVisibility(View.VISIBLE);
                    text_amountrent.setVisibility(View.VISIBLE);
                    lin_yesrent.setBackgroundResource(R.drawable.financebuttonshape);
                    text_yesrent.setTextColor(Color.WHITE);
                    lin_norent.setBackgroundResource(R.drawable.discoveryshape);
                    text_norent.setTextColor(Color.parseColor("#c4c4c4"));
                } else if (rent.equals("no")) {
                    edit_amountrent.setVisibility(View.GONE);
                    text_amountrent.setVisibility(View.GONE);
                    lin_norent.setBackgroundResource(R.drawable.financebuttonshape);
                    text_norent.setTextColor(Color.WHITE);
                    lin_yesrent.setBackgroundResource(R.drawable.discoveryshape);
                    text_yesrent.setTextColor(Color.parseColor("#c4c4c4"));
                }
                mortage = jObject.optString("mortgage");
                if (mortage.equals("yes")) {
                    edit_amount.setVisibility(View.VISIBLE);
                    text_amount.setVisibility(View.VISIBLE);
                    mortage = "yes";
                    lin_yesmortgage.setBackgroundResource(R.drawable.financebuttonshape);
                    text_yesmortgage.setTextColor(Color.WHITE);
                    lin_nomortgage.setBackgroundResource(R.drawable.discoveryshape);
                    text_nomortgage.setTextColor(Color.parseColor("#c4c4c4"));
                } else if (mortage.equals("no")) {
                    text_amount.setVisibility(View.GONE);
                    edit_amount.setVisibility(View.GONE);
                    mortage = "no";
                    lin_nomortgage.setBackgroundResource(R.drawable.financebuttonshape);
                    text_nomortgage.setTextColor(Color.WHITE);
                    lin_yesmortgage.setBackgroundResource(R.drawable.discoveryshape);
                    text_yesmortgage.setTextColor(Color.parseColor("#c4c4c4"));
                }
                studentloans = jObject.optString("studentloans");
                if (studentloans.equals("yes")) {
                    edit_amount1.setVisibility(View.VISIBLE);
                    text_amount1.setVisibility(View.VISIBLE);
                    studentloans = "yes";
                    lin_yesstuloan.setBackgroundResource(R.drawable.financebuttonshape);
                    text_yesstuloan.setTextColor(Color.WHITE);
                    lin_nostuloan.setBackgroundResource(R.drawable.discoveryshape);
                    text_nostuloan.setTextColor(Color.parseColor("#c4c4c4"));
                } else if (studentloans.equals("no")) {
                    edit_amount1.setVisibility(View.GONE);
                    text_amount1.setVisibility(View.GONE);
                    studentloans = "no";
                    lin_nostuloan.setBackgroundResource(R.drawable.financebuttonshape);
                    text_nostuloan.setTextColor(Color.WHITE);
                    lin_yesstuloan.setBackgroundResource(R.drawable.discoveryshape);
                    text_yesstuloan.setTextColor(Color.parseColor("#c4c4c4"));
                }
                edit_dob.setText(jObject.optString("dob"));
                edit_amount.setText(jObject.optString("amount1"));
                edit_amount1.setText(jObject.optString("amount2"));
                spiner_payment.setText(jObject.optString("suggestedmonthlypayment"));
                text_seekbar.setText(jObject.optString("estcreditscore"));
                text_seekbar2.setText(jObject.optString("down_payment"));
                edit_amountrent.setText(jObject.optString("rent_amount"));

              //  seek_payment.incrementProgressBy(500);
                spiner_payment.setText(jObject.optString("suggestedmonthlypayment"));

                monthlypayment = jObject.optString("suggestedmonthlypayment");
                for (int i = 0; i < categories1.size(); i++) {
                    if (categories1.get(i).equals(monthlypayment)) {
                        sendmonthlypayment = i;
                    }
                }
              //  spiner_payment.setSelection(sendmonthlypayment);
                choseyourown = jObject.optString("chooseyourown");
                for (int i = 0; i < categories1.size(); i++) {
                    if (categories1.get(i).equals(choseyourown)) {
                        sendyourown = i;
                    }
                }
                spiner_own.setSelection(sendyourown);
                budget = jObject.optString("budget");
                for (int i = 0; i < categories2.size(); i++) {
                    if (categories2.get(i).equals(budget)) {
                        sendbudget = i;
                    }
                }
                spiner_budget.setSelection(sendbudget);
                seek_credit.setProgress(Integer.parseInt(jObject.optString("estcreditscore")) - 350);
                seek_payment.setProgress(Integer.parseInt(jObject.optString("down_payment")));
            } catch (NumberFormatException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

}
