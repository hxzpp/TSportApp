package com.xinhuamm.xinhuasdk.base.parallaxbacklayout;

import android.app.Activity;
import android.graphics.Canvas;
import android.view.View;

import java.util.Stack;

/**
 * Created by zewei on 2015-11-26.
 */
public class ParallaxBackActivityHelper {
    private static final Stack<ParallaxBackActivityHelper> sActivities = new Stack<>();

    public Activity getActivity() {
        return mActivity;
    }

    private Activity mActivity;

    private ParallaxBackLayout mParallaxBackLayout;

    public ParallaxBackActivityHelper(Activity activity) {
        mActivity = activity;
        mParallaxBackLayout = new ParallaxBackLayout(mActivity);
        sActivities.push(this);
    }

    public boolean hasSecondActivity() {
        return sActivities.size() >= 2;
    }

    public void onPostCreate() {
        mParallaxBackLayout.attachToActivity(this);
    }

    public void onActivityDestroy() {
        sActivities.remove(this);
    }

    public ParallaxBackActivityHelper getSecondActivity() {
        if (sActivities.size() >= 2) {
            int pos = sActivities.search(this);
            int lastPos = sActivities.size() - pos - 1;
            if (lastPos >= 0 && lastPos < sActivities.size())
                return sActivities.elementAt(lastPos);
        }

        return null;
    }

    public void drawThumb(Canvas canvas) {
        View decorChild = getBackLayout().getContentView();
        decorChild.draw(canvas);
    }

    public View findViewById(int id) {
        if (mParallaxBackLayout != null) {
            return mParallaxBackLayout.findViewById(id);
        }
        return null;
    }

    public void scrollToFinishActivity() {
        getBackLayout().scrollToFinishActivity();
    }

    public void setBackEnable(boolean enable) {
        getBackLayout().setEnableGesture(enable);
    }

    public ParallaxBackLayout getBackLayout() {
        return mParallaxBackLayout;
    }
}