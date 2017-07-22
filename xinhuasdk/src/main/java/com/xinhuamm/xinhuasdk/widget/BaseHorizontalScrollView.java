package com.xinhuamm.xinhuasdk.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.HorizontalScrollView;

import com.xinhuamm.xinhuasdk.R;

/**
 * Created by xfg on 2016/7/3.
 */
public class BaseHorizontalScrollView extends HorizontalScrollView implements View.OnTouchListener{

    private boolean isScrollable = true;

    public BaseHorizontalScrollView(Context context) {
        super(context);
    }

    public BaseHorizontalScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context,attrs);
    }

    public BaseHorizontalScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context,attrs);
    }

    private void init(final Context context, final AttributeSet attrs){
        final TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.BaseHorizontalScrollView);
        isScrollable = typedArray.getBoolean(R.styleable.BaseHorizontalScrollView_hs_isscrollable, true);
        setOnTouchListener(this);
    }


    @Override
    public boolean onTouch(View v, MotionEvent event) {
        if(isScrollable)
         return false;
        else
            return  true;
    }
}
