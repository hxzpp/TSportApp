package com.transportmm.tsportapp;

import com.xinhuamm.xinhuasdk.base.fragment.HBaseFragment;
import com.xinhuamm.xinhuasdk.di.component.AppComponent;

public class HomeFragment extends HBaseFragment {

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    protected void onLazyLoad() {
//        XApi.getInstance().getRetrofit("baidu",true).
    }

    @Override
    public void setupFragmentComponent(AppComponent appComponent) {

    }

    @Override
    public void setData(Object data) {

    }
}
