package com.transportmm.tsportapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.MenuItem;

import com.transportmm.tsportapp.mvp.ui.account.activity.LoginActivity;
import com.transportmm.tsportapp.mvp.ui.main.fragment.HomeFragment;
import com.transportmm.tsportapp.mvp.ui.main.fragment.MyFragment;
import com.xinhuamm.xinhuasdk.base.fragment.HBaseFragment;
import com.xinhuamm.xinhuasdk.di.component.AppComponent;
import com.xinhuamm.xinhuasdk.widget.navigation.BottomNavigationViewEx;
import com.xinhuamm.xinhuasdk.widget.viewpager.FixedViewPager;

import butterknife.BindView;

public class MainFragment extends HBaseFragment {

    private final static String KEY_CURRENT_POSITION = "KEY_CURRENT_POSITION";

    @BindView(R.id.viewpager_content_view)
    FixedViewPager mViewPager;
    @BindView(R.id.nav_bottom_view)
    BottomNavigationViewEx mBottomNavigationView;

    private int mCurrentPosition = 0;

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
                    startActivity(new Intent(mContext, LoginActivity.class));
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

        mViewPager.setAdapter(new FragmentPagerAdapter(getChildFragmentManager()) {

            @Override
            public Fragment getItem(int position) {

                Fragment fragment = findFragment(makeFragmentName(mViewPager.getId(), position));
                if (position == 0) {
                    return fragment == null ? new HomeFragment() : fragment;
                } else if (position == 1) {
                    return fragment == null ? new ManageFragment() : fragment;
                } else {
                    return fragment == null ? new MyFragment() : fragment;
                }
            }

            @Override
            public int getCount() {
                return 3;
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

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(KEY_CURRENT_POSITION, mBottomNavigationView.getCurrentItem());
    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
        if (savedInstanceState != null) {
            mCurrentPosition = savedInstanceState.getInt(KEY_CURRENT_POSITION);
            mBottomNavigationView.setCurrentItem(mCurrentPosition);
        }
    }

    private static String makeFragmentName(int viewId, long id) {
        return "android:switcher:" + viewId + ":" + id;
    }
}
