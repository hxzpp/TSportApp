package com.xinhuamm.xinhuasdk.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.xinhuamm.xinhuasdk.R;
import com.xinhuamm.xinhuasdk.util.HDevice;
import com.xinhuamm.xinhuasdk.widget.load.Loading;

public class EmptyLayout extends LinearLayout implements
        View.OnClickListener {

    public static final int HIDE_LAYOUT = 4;
    public static final int NETWORK_ERROR = 1;
    public static final int NETWORK_LOADING = 2;
    public static final int NODATA = 3;
    public static final int NODATA_ENABLE_CLICK = 5;
    public static final int NO_LOGIN = 6;

    private Loading mLoading;
    private boolean clickEnable = true;
    private final Context context;
    public ImageView img;
    private OnClickListener listener;
    private int mErrorState;
    private String strNoDataContent = "";
    private TextView tv;

    public EmptyLayout(Context context) {
        super(context);
        this.context = context;
        init();
    }

    public EmptyLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        init();
    }

    private void init() {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.view_error_layout, this, false);
        img = (ImageView) view.findViewById(R.id.img_error_layout);
        tv = (TextView) view.findViewById(R.id.tv_error_layout);
        mLoading = (Loading) view.findViewById(R.id.animProgress);
        setOnClickListener(this);
        img.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                if (clickEnable) {
                    if (listener != null)
                        listener.onClick(v);
                }
            }
        });
        addView(view);
        changeErrorLayoutBgMode(context);
    }

    public void changeErrorLayoutBgMode(Context context1) {
    }

    public void dismiss() {
        mErrorState = HIDE_LAYOUT;
        setVisibility(View.GONE);
    }

    public int getErrorState() {
        return mErrorState;
    }

    public boolean isLoadError() {
        return mErrorState == NETWORK_ERROR;
    }

    public boolean isLoading() {
        return mErrorState == NETWORK_LOADING;
    }

    @Override
    public void onClick(View v) {
        if (clickEnable) {
            if (listener != null)
                listener.onClick(v);
        }
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        onSkinChanged();
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
    }

    public void onSkinChanged() {
    }

    public void setErrorMessage(String msg) {
        tv.setText(msg);
    }


    /**
     * 新添设置背景
     */
    public void setErrorImag(int imgResource) {
        img.setImageResource(imgResource);
    }

    public void setErrorType(int i) {
        setVisibility(View.VISIBLE);
        switch (i) {
            case NETWORK_ERROR:
                mErrorState = NETWORK_ERROR;
                if (HDevice.hasInternet(context)) {
                    tv.setText(R.string.error_view_load_error_click_to_refresh);
                    img.setBackgroundResource(R.mipmap.ic_tip_fail);
                } else {
                    tv.setText(R.string.error_view_network_error_click_to_refresh);
                    img.setBackgroundResource(R.mipmap.ic_tip_fail);
                }
                img.setVisibility(View.VISIBLE);
                mLoading.stop();
                mLoading.setVisibility(View.GONE);
                clickEnable = true;
                break;
            case NETWORK_LOADING:
                mErrorState = NETWORK_LOADING;
                mLoading.setVisibility(View.VISIBLE);
                mLoading.start();
                img.setVisibility(View.GONE);
                tv.setText(R.string.error_view_loading);
                clickEnable = false;
                break;
            case NODATA:
                mErrorState = NODATA;
                img.setBackgroundResource(R.mipmap.ic_page_empty);
                img.setVisibility(View.VISIBLE);
                mLoading.stop();
                mLoading.setVisibility(View.GONE);
                setTvNoDataContent();
                clickEnable = true;
                break;
            case HIDE_LAYOUT:
                mLoading.stop();
                setVisibility(View.GONE);
                break;
            case NODATA_ENABLE_CLICK:
                mErrorState = NODATA_ENABLE_CLICK;
                img.setBackgroundResource(R.mipmap.ic_page_empty);
                img.setVisibility(View.VISIBLE);
                mLoading.stop();
                mLoading.setVisibility(View.GONE);
                setTvNoDataContent();
                clickEnable = true;
                break;
            default:
                break;
        }
    }

    public void setNoDataContent(String noDataContent) {
        strNoDataContent = noDataContent;
    }

    public void setOnLayoutClickListener(OnClickListener listener) {
        this.listener = listener;
    }

    public void setTvNoDataContent() {
        if (!strNoDataContent.equals(""))
            tv.setText(strNoDataContent);
        else
            tv.setText(R.string.error_view_no_data);
    }

    @Override
    public void setVisibility(int visibility) {
        if (visibility == View.GONE)
            mErrorState = HIDE_LAYOUT;
        super.setVisibility(visibility);
    }
}
