package com.transportmm.tsportapp;

import com.xinhuamm.xinhuasdk.base.fragment.HBaseFragment;
import com.xinhuamm.xinhuasdk.di.component.AppComponent;

public class MyFragment extends HBaseFragment {

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_my;
    }


    @Override
    protected void onLazyLoad() {

    }

    @Override
    public void setupFragmentComponent(AppComponent appComponent) {

    }

    @Override
    public void setData(Object data) {

    }
}
