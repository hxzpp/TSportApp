package com.xinhuamm.xinhuasdk.base.fragment;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.view.View;
import android.view.ViewStub;

import com.xinhuamm.xinhuasdk.R;
import com.xinhuamm.xinhuasdk.base.parallaxbacklayout.ParallaxActivityBase;
import com.xinhuamm.xinhuasdk.widget.EmptyLayout;
import com.xinhuamm.xinhuasdk.widget.titlebar.TitleBar;

/**
 * 带有标题栏的fragment
 */
public abstract class HBaseTitleFragment extends HBaseFragment {

    protected TitleBar mTitleBar;
    protected EmptyLayout mEmptyLayout;
    protected View mViewDivider;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_base_title;
    }

    @Override
    protected void onBindViewBefore(View root) {
        super.onBindViewBefore(root);
        // on before onBindViewBefore call
        ViewStub stub = (ViewStub) root.findViewById(R.id.lay_content);
        stub.setLayoutResource(getContentLayoutId());
        stub.inflate();
    }

    @Override
    protected void initWidget(Bundle savedInstanceState) {
        super.initWidget(savedInstanceState);
        // not null
        mTitleBar = (TitleBar) mRoot.findViewById(R.id.nav_title_bar);
        mTitleBar.setTitleBarBackgroundRes(R.color.colorPrimary);
        mTitleBar.setTitleColor(getResources().getColor(R.color.black));
        mTitleBar.setTitle(getTitleName());
        mViewDivider=(View)mRoot.findViewById(R.id.view_gradient_divider);
        initEmptyLayout();
    }

    protected void setBackButton(boolean yesOrNo) {
        if (yesOrNo) {
            mTitleBar.setLeftBtn(null, R.mipmap.ic_common_back, new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mContext instanceof ParallaxActivityBase)
                        ((ParallaxActivityBase) mContext).scrollToFinishActivity();
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

        mEmptyLayout = (EmptyLayout) mRoot.findViewById(R.id.error_empty_layout);
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
