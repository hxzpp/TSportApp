package com.xinhuamm.xinhuasdk.base.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.trello.rxlifecycle2.components.support.RxFragment;
import com.xinhuamm.xinhuasdk.base.delegate.IFragment;
import com.xinhuamm.xinhuasdk.mvp.IPresenter;

import java.io.Serializable;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 因为java只能单继承,所以如果有需要继承特定Fragment的三方库,那你就需要自己自定义Fragment
 * 继承于这个特定的Fragment,然后按照将BaseFragment的格式,复制过去,记住一定要实现{@link IFragment}
 */
public abstract class HBaseFragment<P extends IPresenter> extends RxFragment implements IFragment, FragmentBackHandler {
    protected Context mContext;
    protected View mRoot;
    protected Bundle mBundle;
    private Unbinder mUnBinder;

    protected boolean isPrepared;
    protected boolean isLoadedOnce;

    @Inject
    protected P mPresenter;

    public HBaseFragment() {
        //必须确保在Fragment实例化时setArguments()
        setArguments(new Bundle());
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mContext = null;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBundle = getArguments();
        initBundle(mBundle);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (mRoot != null) {
            ViewGroup parent = (ViewGroup) mRoot.getParent();
            if (parent != null)
                parent.removeView(mRoot);
        } else {
            mRoot = inflater.inflate(getLayoutId(), container, false);
            // Do something
            onBindViewBefore(mRoot);
            // Bind view
            mUnBinder = ButterKnife.bind(this, mRoot);
            // Get savedInstanceState
            if (savedInstanceState != null)
                onRestartInstance(savedInstanceState);
            // Init
            initWidget(savedInstanceState);
            initData(savedInstanceState);
        }
        isPrepared = true;
        if (getUserVisibleHint()) {
            onLazyLoad();
        }
        return mRoot;
    }

    protected void onBindViewBefore(View root) {

    }

    protected abstract int getLayoutId();

    protected void initBundle(Bundle bundle) {

    }

    protected void onRestartInstance(Bundle savedInstanceState) {

    }

    protected void initWidget(Bundle savedInstanceState) {

    }

    protected void initData(Bundle savedInstanceState) {

    }

    protected <T extends View> T findViewById(int viewId) {
        return (T) mRoot.findViewById(viewId);
    }

    protected <T extends Serializable> T getBundleSerializable(String key) {
        if (mBundle == null) {
            return null;
        }
        return (T) mBundle.getSerializable(key);
    }

    @Override
    public boolean onBackPressed() {
        return false;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (!isPrepared)
            return;

        if (isVisibleToUser) {
            if (!isLoadedOnce) {
                onLazyLoad();// 懒加载
                isLoadedOnce = true;
            } else
                onResumeWithViewPager();
        } else {
            onPauseWithViewPager();
        }
    }

    /**
     * 懒加载,只和viewpager一起使用的时候调用
     */
    protected void onLazyLoad() {

    }

    /**
     * 页面不可见，只和viewpager一起使用的时候调用
     */
    protected void onPauseWithViewPager() {

    }

    /**
     * 页面可见，只和viewpager一起使用的时候调用
     */
    protected void onResumeWithViewPager() {

    }

    /**
     * 当fragment使用hide，show来控制切换的使用调用
     *
     * @param hidden
     */
    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
    }

    /**
     * 是否使用eventBus,默认为使用(true)，
     *
     * @return
     */
    @Override
    public boolean useEventBus() {
        return true;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mUnBinder != null && mUnBinder != Unbinder.EMPTY)
            mUnBinder.unbind();
        this.mUnBinder = null;
        if (mPresenter != null)
            mPresenter.onDestroy();//释放资源
        this.mPresenter = null;
        mBundle = null;
    }


    protected Fragment findFragment(String fragmentTag) {
        return getChildFragmentManager().findFragmentByTag(fragmentTag);
    }
}
