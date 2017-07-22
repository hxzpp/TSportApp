//package com.xinhuamm.xinhuasdk.widget.refresh_recyclerview.view;
//
//import android.content.Context;
//import android.util.AttributeSet;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.widget.FrameLayout;
//
//import com.facebook.drawee.view.SimpleDraweeView;
//import com.xinhuamm.xinhuasdk.R;
//import com.xinhuamm.xinhuasdk.fresco.HFresco;
//import com.xinhuamm.xinhuasdk.widget.ptr.PtrFrameLayout;
//import com.xinhuamm.xinhuasdk.widget.ptr.PtrUIHandler;
//import com.xinhuamm.xinhuasdk.widget.ptr.indicator.PtrIndicator;
//
//
//public class RefreshHeaderGif extends FrameLayout implements PtrUIHandler {
//    private SimpleDraweeView mRotateView;
//    public RefreshHeaderGif(Context context) {
//        super(context);
//        initViews(null);
//    }
//
//    public RefreshHeaderGif(Context context, AttributeSet attrs) {
//        super(context, attrs);
//        initViews(attrs);
//    }
//
//    public RefreshHeaderGif(Context context, AttributeSet attrs, int defStyle) {
//        super(context, attrs, defStyle);
//        initViews(attrs);
//    }
//
//    protected void initViews(AttributeSet attrs) {
//        View header = LayoutInflater.from(getContext()).inflate(R.layout.view_header_refresh_gif, this);
//        mRotateView =(SimpleDraweeView) header.findViewById(R.id.ptr_classic_header_rotate_view);
//        HFresco.with(mRotateView).load(R.mipmap.ic_head_loading);
//        resetView();
//    }
//
//    @Override
//    protected void onDetachedFromWindow() {
//        super.onDetachedFromWindow();
//    }
//
//    private void resetView() {
//        hideRotateView();
//    }
//
//    private void hideRotateView() {
//        mRotateView.setVisibility(INVISIBLE);
//    }
//
//    @Override
//    public void onUIReset(PtrFrameLayout frame) {
//        resetView();
//    }
//
//    @Override
//    public void onUIRefreshPrepare(PtrFrameLayout frame) {
//        mRotateView.setVisibility(VISIBLE);
//    }
//
//    @Override
//    public void onUIRefreshBegin(PtrFrameLayout frame) {
//    }
//
//    @Override
//    public void onUIRefreshComplete(PtrFrameLayout frame) {
//        hideRotateView();
//    }
//
//    @Override
//    public void onUIPositionChange(PtrFrameLayout frame, boolean isUnderTouch, byte status, PtrIndicator ptrIndicator) {
//
//    }
//
//}
