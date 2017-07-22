package com.xinhuamm.xinhuasdk.base.fragment;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;

import com.xinhuamm.xinhuasdk.R;

/**
 * 带有标题栏，上tab，viewPager分页
 */
public abstract class HBaseViewPagerFragment extends HBaseTitleFragment {

    protected TabLayout mTabNav;

    protected ViewPager mBaseViewPager;

    @Override
    protected int getContentLayoutId() {
        return R.layout.fragment_base_viewpager;
    }

    @Override
    protected void initWidget(Bundle savedInstanceState) {
        super.initWidget(savedInstanceState);

        mTitleBar.setVisibility(View.GONE);//默认标题不显示
        mViewDivider.setVisibility(View.GONE);//默认不显示顶部阴影
        mTabNav= (TabLayout) findViewById(R.id.tab_nav);
        mBaseViewPager= (ViewPager) findViewById(R.id.base_viewPager);
        BaseViewPagerAdapter adapter = new BaseViewPagerAdapter(getChildFragmentManager(), getPagers());
        mBaseViewPager.setAdapter(adapter);
        mTabNav.setupWithViewPager(mBaseViewPager);
        mBaseViewPager.setCurrentItem(0, true);
    }

    protected abstract PagerInfo[] getPagers();

    public static class PagerInfo {
        private String title;
        private Class<?> clx;
        private Bundle args;

        public PagerInfo(String title, Class<?> clx, Bundle args) {
            this.title = title;
            this.clx = clx;
            this.args = args;
        }
    }

    public class BaseViewPagerAdapter extends FragmentPagerAdapter {
        private PagerInfo[] mInfoList;
        private Fragment mCurFragment;

        public BaseViewPagerAdapter(FragmentManager fm, PagerInfo[] infoList) {
            super(fm);
            mInfoList = infoList;
        }

        @Override
        public void setPrimaryItem(ViewGroup container, int position, Object object) {
            super.setPrimaryItem(container, position, object);
            if (object instanceof Fragment) {
                mCurFragment = (Fragment) object;
            }
        }

        public Fragment getCurFragment() {
            return mCurFragment;
        }

        @Override
        public Fragment getItem(int position) {
            PagerInfo info = mInfoList[position];
            return Fragment.instantiate(getContext(), info.clx.getName(), info.args);
        }

        @Override
        public int getCount() {
            return mInfoList.length;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mInfoList[position].title;
        }

        @Override
        public int getItemPosition(Object object) {
            return PagerAdapter.POSITION_NONE;
        }
    }
}
