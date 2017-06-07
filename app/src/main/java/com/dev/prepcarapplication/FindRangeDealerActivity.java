package com.dev.prepcarapplication;

import android.os.Bundle;

import com.dev.prepcarapplication.baseClasses.BaseFragmentActivity;
import com.dev.prepcarapplication.fragment.FragmentFindRangeDealer;
import com.dev.prepcarapplication.fragment.FragmentNewMatcheDetail;

public class FindRangeDealerActivity extends BaseFragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_container);
        replaceFragement(FragmentFindRangeDealer.newInstanse(null), FragmentNewMatcheDetail.TAG);
    }

    @Override
    protected void initControls(Bundle savedInstanceState) {

    }

    @Override
    protected void setValueOnUI() {

    }
}
