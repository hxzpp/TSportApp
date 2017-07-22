package com.transportmm.tsportapp;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.MenuItem;

import com.xinhuamm.xinhuasdk.base.fragment.HBaseFragment;
import com.xinhuamm.xinhuasdk.di.component.AppComponent;
import com.xinhuamm.xinhuasdk.widget.navigation.BottomNavigationViewEx;
import com.xinhuamm.xinhuasdk.widget.viewpager.FixedViewPager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class MainFragment extends HBaseFragment {
    @BindView(R.id.viewpager_content_view)
    FixedViewPager mViewPager;
    @BindView(R.id.nav_bottom_view)
    BottomNavigationViewEx mBottomNavigationView;

    private List<Fragment> mFragments;

    //底部菜单栏各个菜单项的点击事件处理
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home://
                    return true;
                case R.id.navigation_manage://
                    return true;
                case R.id.navigation_my://
                    return true;
            }
            return false;
        }

    };


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_main;
    }

    @Override
    protected void initWidget(Bundle savedInstanceState) {
        super.initWidget(savedInstanceState);
        mBottomNavigationView.enableItemShiftingMode(false);
        mBottomNavigationView.enableAnimation(true);
        mBottomNavigationView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        super.initData(savedInstanceState);
        mFragments = new ArrayList<>();
        mFragments.add(new HomeFragment());
        mFragments.add(new ManageFragment());
        mFragments.add(new MyFragment());

        mViewPager.setAdapter(new FragmentPagerAdapter(getChildFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return mFragments.get(position);
            }

            @Override
            public int getCount() {
                return mFragments.size();
            }

        });

        mBottomNavigationView.setupWithViewPager(mViewPager);

    }

    @Override
    public void setupFragmentComponent(AppComponent appComponent) {

    }

    @Override
    public void setData(Object data) {

    }
}
