//package com.xinhuamm.xinhuasdk.widget.refresh_recyclerview.view;
//
//import android.content.Context;
//import android.view.animation.Interpolator;
//import android.view.animation.LinearInterpolator;
//import android.widget.FrameLayout;
//
//import com.xinhuamm.xinhuasdk.widget.refresh_recyclerview.RecyclerMode;
//
//
//public abstract class RefreshLoadingLayout extends FrameLayout {
//
//    static final int ROTATION_ANIMATION_DURATION = 1200;
//
//    static final Interpolator ANIMATION_INTERPOLATOR = new LinearInterpolator();
//
//    protected Context mContext;
//    protected RecyclerMode mode;
//
//    public RefreshLoadingLayout(Context context, RecyclerMode mode) {
//        super(context);
//        this.mContext = context;
//        this.mode = mode;
//
//        init();
//
//        reset();
//    }
//
//    protected void init() {
//    }
//
//    public final void onRefresh() {
//        onRefreshImpl();
//    }
//
//    public void reset() {
//        onResetImpl();
//    }
//
//    protected abstract void onRefreshImpl();
//
//    protected abstract void onResetImpl();
//
//}
