package com.xinhuamm.xinhuasdk.base.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.View;

import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;
import com.xinhuamm.xinhuasdk.base.delegate.IActivity;
import com.xinhuamm.xinhuasdk.base.fragment.BackHandlerHelper;
import com.xinhuamm.xinhuasdk.base.parallaxbacklayout.ParallaxBackActivityHelper;
import com.xinhuamm.xinhuasdk.base.parallaxbacklayout.ParallaxBackLayout;
import com.xinhuamm.xinhuasdk.mvp.IPresenter;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 因为java只能单继承,所以如果有需要继承特定Activity的三方库,那你就需要自己自定义Activity
 * 继承于这个特定的Activity,然后按照将BaseActivity的格式,复制过去记住一定要实现{@link IActivity}
 */

public abstract class HBaseActivity<P extends IPresenter> extends RxAppCompatActivity implements IActivity {
    private ParallaxBackActivityHelper mHelper;
    protected Fragment mFragment;
    private Unbinder mUnbinder;
    @Inject
    protected P mPresenter;

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mHelper.onPostCreate();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mHelper = new ParallaxBackActivityHelper(this);
        if (initBundle(getIntent().getExtras())) {
            setContentView(getContentView());
            //绑定到butterknife
            mUnbinder = ButterKnife.bind(this);
            initWindow();

            initWidget(savedInstanceState);

            initData(savedInstanceState);
        } else {
            finish();
        }
    }

    protected abstract int getContentView();

    protected boolean initBundle(Bundle bundle) {
        return true;
    }

    protected void initWindow() {
    }

    protected void initWidget(Bundle savedInstanceState) {
    }

    protected void initData(Bundle savedInstanceState) {
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mHelper.onActivityDestroy();
        if (mUnbinder != null && mUnbinder != Unbinder.EMPTY)
            mUnbinder.unbind();
        this.mUnbinder = null;
        if (mPresenter != null)
            mPresenter.onDestroy();//释放资源
        this.mPresenter = null;
    }

    @Override
    public void onBackPressed() {
        if (!BackHandlerHelper.handleBackPress(this)) {
            if (isBackPressed()) {
                if (!getSupportFragmentManager().popBackStackImmediate()) {
                    scrollToFinishActivity();
                }
            }
        }
    }

    public boolean isBackPressed() {
        return true;
    }

    @Override
    @NonNull
    public View findViewById(int id) {
        View v = super.findViewById(id);
        if (v == null && mHelper != null)
            return mHelper.findViewById(id);
        return v;
    }

    public ParallaxBackLayout getBackLayout() {
        return mHelper.getBackLayout();
    }

    public void setBackEnable(boolean enable) {
        mHelper.setBackEnable(enable);
    }

    public void scrollToFinishActivity() {
        mHelper.scrollToFinishActivity();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        if (mFragment != null) {
            mFragment.onActivityResult(requestCode, resultCode, intent);
        }
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

    /**
     * 这个Activity是否会使用Fragment,框架会根据这个属性判断是否注册{@link android.support.v4.app.FragmentManager.FragmentLifecycleCallbacks}
     * 如果返回false,那意味着这个Activity不需要绑定Fragment,那你再在这个Activity中绑定继承于 {@link com.jess.arms.base.BaseFragment} 的Fragment将不起任何作用
     *
     * @return
     */
    @Override
    public boolean useFragment() {
        return true;
    }

    protected Fragment findFragment(String fragmentTag) {
        return getSupportFragmentManager().findFragmentByTag(fragmentTag);
    }

    protected void replaceFragment(int frameLayoutId, Fragment fragment,String fragmentTag) {
        if (fragment != null && !isFinishing()) {
            mFragment = fragment;
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(frameLayoutId, fragment, fragmentTag);
            transaction.commit();
        }
    }

    protected void addFragment(int frameLayoutId, Fragment fragment,String fragmentTag) {
        if (fragment != null && !isFinishing()) {
            mFragment = fragment;
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.add(frameLayoutId, fragment, fragmentTag);
            transaction.commit();
        }
    }


}
