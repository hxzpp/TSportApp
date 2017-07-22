package com.xinhuamm.xinhuasdk.util;

import android.content.Context;
import android.graphics.PointF;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.LinearSmoothScroller;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;

/**
 * 控制滑动速度的LinearLayoutManager
 */
public class ScrollSpeedLinearLayoutManger extends LinearLayoutManager {
    private float MILLISECONDS_PER_INCH = 0.03f;
    private Context contxt;

    public ScrollSpeedLinearLayoutManger(Context context) {
        super(context);
        this.contxt = context;
        setSpeedSlow();
    }

    @Override
    public void smoothScrollToPosition(RecyclerView recyclerView, RecyclerView.State state, int position) {
        LinearSmoothScroller linearSmoothScroller =
                new LinearSmoothScroller(recyclerView.getContext()) {
                    @Override
                    public PointF computeScrollVectorForPosition(int targetPosition) {
                        return ScrollSpeedLinearLayoutManger.this
                                .computeScrollVectorForPosition(targetPosition);
                    }

                    //This returns the milliseconds it takes to
                    //scroll one pixel.
                    @Override
                    protected float calculateSpeedPerPixel(DisplayMetrics displayMetrics) {
                        return MILLISECONDS_PER_INCH / displayMetrics.density;
                        //返回滑动一个pixel需要多少毫秒
                    }

                    @Override
                    protected int calculateTimeForScrolling(int dx) {
                        // 此函数计算滚动dx的距离需要多久，当要滚动的距离很大时，比如说52000，
                        // 经测试，系统会多次调用此函数，每10000距离调一次，所以总的滚动时间
                        // 是多次调用此函数返回的时间的和，所以修改每次调用该函数时返回的时间的
                        // 大小就可以影响滚动需要的总时间，可以直接修改些函数的返回值，也可以修改
                        // dx的值，这里暂定使用后者.
                        // (See LinearSmoothScroller.TARGET_SEEK_SCROLL_DISTANCE_PX)
                        if (dx > 3000) {
                            dx = 3000;
                        }
                        return super.calculateTimeForScrolling(dx);
                    }

                };
        linearSmoothScroller.setTargetPosition(position);
        startSmoothScroll(linearSmoothScroller);
    }


    public void setSpeedSlow() {
        //自己在这里用density去乘，希望不同分辨率设备上滑动速度相同
        //0.3f是自己估摸的一个值，可以根据不同需求自己修改
        MILLISECONDS_PER_INCH = contxt.getResources().getDisplayMetrics().density * 0.3f;
    }

    public void setSpeedFast() {
        MILLISECONDS_PER_INCH = contxt.getResources().getDisplayMetrics().density * 0.03f;
    }
}