package com.transportmm.tsportapp.mvp.ui.search.activity;

import android.os.Bundle;

import com.transportmm.tsportapp.R;
import com.xinhuamm.xinhuasdk.base.activity.HBaseActivity;
import com.xinhuamm.xinhuasdk.di.component.AppComponent;


public class SearchActivity extends HBaseActivity {

    @Override
    public void setupActivityComponent(AppComponent appComponent) {
    }

    @Override
    protected int getContentView() {
         return R.layout.activity_search;
    }

    @Override
    public void initData(Bundle savedInstanceState) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

}
