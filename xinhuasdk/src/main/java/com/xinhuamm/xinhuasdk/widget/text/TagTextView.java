package com.xinhuamm.xinhuasdk.widget.text;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.TextView;

import com.xinhuamm.xinhuasdk.R;

public final class TagTextView extends TextView {

    private int strokeColor;
    private int strokeWidth;
    private int solidColor;
    private int radius;
    private int textColor;

    private GradientDrawable drawable;

    public TagTextView(Context context) {
        this(context, null);
    }

    public TagTextView(Context context, AttributeSet attrs) {
        super(context, attrs);

        strokeWidth = getResources().getDimensionPixelSize(R.dimen.tag_stroke_width);
        solidColor = getResources().getColor(R.color.white);
        radius = getResources().getDimensionPixelSize(R.dimen.tag_corner_radius);
        textColor = getResources().getColor(R.color.black);

        drawable = new GradientDrawable();
        update();
    }

    public void changeWholeColorByResId(int strokeColorResId, int strokeWidthResId,
                                        int solidColorResId, int radiusResId, int textColorResId) {
        strokeColor(getResources().getColor(strokeColorResId)).
                strokeWidth(getResources().getDimensionPixelSize(strokeWidthResId)).
                solidColor(getResources().getColor(solidColorResId)).
                radius(getResources().getDimensionPixelSize(radiusResId)).
                textColor(getResources().getColor(textColorResId)).invalidate();
    }

    private void changeWholeColor(int strokeColor, int strokeWidth, int solidColor, int radius, int textColor) {
        drawable.setStroke(strokeWidth, strokeColor);
        drawable.setCornerRadius(radius);
        drawable.setShape(GradientDrawable.RECTANGLE);
        drawable.setColor(solidColor);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            setBackground(drawable);
        } else {
            setBackgroundDrawable(drawable);
        }
        setTextColor(textColor);
    }

    public TagTextView strokeColor(int strokeColor) {
        this.strokeColor = strokeColor;
        return this;
    }

    public TagTextView strokeWidth(int strokeWidth) {
        this.strokeWidth = strokeWidth;
        return this;
    }

    public TagTextView solidColor(int solidColor) {
        this.solidColor = solidColor;
        return this;
    }

    public TagTextView radius(int radius) {
        this.radius = radius;
        return this;
    }

    public TagTextView textColor(int textColor) {
        this.textColor = textColor;
        return this;
    }

    public void update(){
        changeWholeColor(strokeColor, strokeWidth, solidColor, radius, textColor);
    }
}