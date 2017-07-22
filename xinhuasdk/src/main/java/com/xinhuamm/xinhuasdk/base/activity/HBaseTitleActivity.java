package com.xinhuamm.xinhuasdk.base.activity;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.view.View;
import android.view.ViewStub;

import com.xinhuamm.xinhuasdk.R;
import com.xinhuamm.xinhuasdk.widget.EmptyLayout;
import com.xinhuamm.xinhuasdk.widget.titlebar.TitleBar;

/**
 * 带有标题栏的activity
 */
public abstract class HBaseTitleActivity extends HBaseActivity {

    public TitleBar mTitleBar;
    public EmptyLayout mEmptyLayout;
    protected View mViewDivider;

    @Override
    protected int getContentView() {
        return R.layout.activity_base_title;
    }

    @Override
    protected void initWindow() {
        super.initWindow();
        ViewStub stub = (ViewStub) findViewById(R.id.lay_content);
        stub.setLayoutResource(getContentLayoutId());
        stub.inflate();
    }

    @Override
    protected void initWidget(Bundle savedInstanceState) {
        super.initWidget(savedInstanceState);
        // not null
        mTitleBar = (TitleBar) findViewById(R.id.nav_title_bar);
        mTitleBar.setTitleBarBackgroundRes(R.color.colorPrimary);
        mTitleBar.setTitleColor(getResources().getColor(R.color.black));
        mTitleBar.setTitle(getTitleName());
        mViewDivider=(View)findViewById(R.id.view_gradient_divider);
        initEmptyLayout();

    }

    protected void setBackButton(boolean yesOrNo) {
        if (yesOrNo) {
            mTitleBar.setLeftBtn(null, R.mipmap.ic_common_back, new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    scrollToFinishActivity();
                }
            });
        }
    }

    protected abstract
    @LayoutRes
    int getContentLayoutId();

    protected String getTitleName() {
        return "";
    }

    private void initEmptyLayout() {

        mEmptyLayout = (EmptyLayout) findViewById(R.id.error_empty_layout);
        mEmptyLayout.setOnLayoutClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mEmptyLayout.setErrorType(EmptyLayout.NETWORK_LOADING);
                onClickEmptyLayout();
            }
        });

    }

    protected void onClickEmptyLayout() {

    }

}
