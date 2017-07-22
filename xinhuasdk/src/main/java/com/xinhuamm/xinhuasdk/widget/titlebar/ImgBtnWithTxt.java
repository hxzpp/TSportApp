package com.xinhuamm.xinhuasdk.widget.titlebar;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.xinhuamm.xinhuasdk.R;


public class ImgBtnWithTxt extends LinearLayout {

    private TextView mTextView;
    private ImageView mImgView;
    private LinearLayout mContainer;
    private float mTextSize = 16;
    private int mTextColor = 0xffffffff;

    public ImgBtnWithTxt(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);

        mContainer = (LinearLayout) LayoutInflater.from(context).inflate(
                R.layout.view_image_button, this, true);

        mImgView = (ImageView) mContainer.findViewById(R.id.title_btn_image);
        mTextView = (TextView) mContainer.findViewById(R.id.title_btn_text);

        TypedArray typedArray = context.obtainStyledAttributes(attributeSet, R.styleable.ImgBtnWithTxt);
        mTextSize = typedArray.getFloat(R.styleable.ImgBtnWithTxt_imgBtnTxtSize, mTextSize);
        mTextColor = typedArray.getColor(R.styleable.ImgBtnWithTxt_imgBtnTxtColor, mTextColor);

        mTextView.setTextSize(mTextSize);
        mTextView.setTextColor(mTextColor);

        setClickable(true);
    }

    public final void setOnlyImage(int resID) {
        setVisibility(View.VISIBLE);
        mImgView.setImageResource(resID);
        mImgView.setDuplicateParentStateEnabled(true);
        mImgView.setVisibility(View.VISIBLE);

        mTextView.setVisibility(View.GONE);
    }

    public final void setOnlyText(int resid) {
        setVisibility(View.VISIBLE);
        mTextView.setText(resid);
        mTextView.setVisibility(View.VISIBLE);

        mImgView.setVisibility(View.GONE);
    }

    public final void setOnlyText(String str) {
        setVisibility(View.VISIBLE);
        mTextView.setText(str);
        mTextView.setVisibility(View.VISIBLE);

        mImgView.setVisibility(View.GONE);
    }

    public final void setBackBtn(int resId) {
        setVisibility(View.VISIBLE);

        mTextView.setText(resId);
        Drawable imgOff = getContext().getResources().getDrawable(R.drawable.btn_back_selector);
        imgOff.setBounds(0, 0, imgOff.getMinimumWidth(),imgOff.getMinimumHeight());
        mTextView.setCompoundDrawables(imgOff, null, null, null);
        mTextView.setCompoundDrawablePadding(10);
        mTextView.setVisibility(View.VISIBLE);

        mImgView.setVisibility(View.GONE);
    }

    public final void setBackBtn(String st) {
        setVisibility(View.VISIBLE);

        mTextView.setText(st);
        Drawable img_off = getContext().getResources().getDrawable(R.drawable.btn_back_selector);
        img_off.setBounds(0, 0, img_off.getMinimumWidth(),img_off.getMinimumHeight());
        mTextView.setCompoundDrawables(img_off, null, null, null);
        mTextView.setCompoundDrawablePadding(10);
        mTextView.setVisibility(View.VISIBLE);

        mImgView.setVisibility(View.GONE);
    }

    public String getTitle() {
        return mTextView.getText().toString();
    }

    public void setTextColor(int color) {
        mTextView.setTextColor(color);
    }

    public void setTextEnabled(boolean enabled) {
        mTextView.setEnabled(enabled);
    }

    public void setTextViewBold() {
        mTextView.setTypeface(Typeface.DEFAULT_BOLD);
    }

    public boolean isButtonVisible() {
        return getVisibility() == View.VISIBLE &&
                (mImgView.getVisibility() == View.VISIBLE ||
                        mTextView.getVisibility() == View.VISIBLE);
    }

}
