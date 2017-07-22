package com.xinhuamm.xinhuasdk.widget.titlebar;

import android.content.Context;
import android.support.annotation.ColorInt;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.xinhuamm.xinhuasdk.R;


public class TitleBar extends RelativeLayout {

    private Context mContext;
    private TextView mTitle;
    private ImgBtnWithTxt mLeftBtn;
    private ImgBtnWithTxt mRightBtn;
    private View mBar;

    public TitleBar(Context context) {
        super(context);
        init(context);
    }

    public TitleBar(Context context, AttributeSet set) {
        super(context, set);
        init(context);
    }

    private void init(Context context) {
        mContext = context;
        inflate(mContext, R.layout.title_bar, this);

        mBar = findViewById(R.id.title_bar_container);
        mTitle = (TextView)findViewById(R.id.title_text);
        mLeftBtn = (ImgBtnWithTxt)findViewById(R.id.title_left_btn);
        mRightBtn = (ImgBtnWithTxt)findViewById(R.id.title_right_btn);
    }

    public void setTitleBarBackgroundRes(int resId) {
        mBar.setBackgroundResource(resId);
    }

    public void setTitleBackgroundRes(int resId) {
        mTitle.setBackgroundResource(resId);
    }

    public void setTitle(String title){
        mTitle.setText(title);
    }

    public void setTitleColor(@ColorInt int color){
        mTitle.setTextColor(color);
    }

    public void setTitle(int titleResId){
        mTitle.setText(titleResId);
    }

    public void setLeftBtn(String txt, int logoResId, OnClickListener onClickListener) {
        if (txt == null) {
            setLeftBtnOnlyImage(logoResId);
        } else {
            setLeftBtnOnlyText(txt);
        }
        setLeftBtnOnClickListen(onClickListener);
    }

    public void setLeftBtn(int txtId, int logoResId, View.OnClickListener onClickListener) {
        if (txtId <= 0) {
            setLeftBtnOnlyImage(logoResId);
        } else {
            setLeftBtnOnlyText(txtId);
        }
        setLeftBtnOnClickListen(onClickListener);
    }

    public void setRightBtn(String txt, int logoResId, OnClickListener onClickListener) {
        if (txt == null) {
            setRightBtnOnlyImage(logoResId);
        } else {
            setRightBtnOnlyText(txt);
        }
        setRightBtnOnClickListen(onClickListener);
    }

    public void setRightBtn(int txtId, int logoResId, OnClickListener onClickListener) {
        if (txtId <= 0) {
            setRightBtnOnlyImage(logoResId);
        } else {
            setRightBtnOnlyText(txtId);
        }
        setRightBtnOnClickListen(onClickListener);
    }

    public void supportBackBtn(int txtId) {
        setBackBtn(txtId);
    }

    public void supportBackBtn(String txt) {
        setBackBtn(txt);
    }


    public void setLeftBtnOnlyImage(int resID) {
        mLeftBtn.setOnlyImage(resID);
    }

    public void setRightBtnOnlyImage(int resID) {
        mRightBtn.setOnlyImage(resID);
    }

    public void setLeftBtnOnlyText(String str) {
        mLeftBtn.setOnlyText(str);
    }

    public void setRightBtnOnlyText(String str) {
        mRightBtn.setOnlyText(str);
    }

    public void setLeftBtnOnlyText(int resID) {
        mLeftBtn.setOnlyText(resID);
    }

    public void setRightBtnOnlyText(int resID) {
        mRightBtn.setOnlyText(resID);
    }

    public void setBackBtn(int resId){
        mLeftBtn.setBackBtn(resId);
    }

    public void setBackBtn(String st){
        mLeftBtn.setBackBtn(st);
    }

    public void setLeftBtnOnClickListen(OnClickListener onClick){
        mLeftBtn.setOnClickListener(onClick);
    }

    public void setRightBtnOnClickListen(OnClickListener onClick){
        mRightBtn.setOnClickListener(onClick);
    }

    public String getLeftBtnText(){
        return mLeftBtn.getTitle();
    }

    public String getRightBtnText(){
        return mRightBtn.getTitle();
    }

    public void setLeftBtnTextColor(int color){
        mLeftBtn.setTextColor(color);
    }

    public void setRightBtnTextColor(int color){
        mRightBtn.setTextColor(color);
    }

    public void setLeftBtnTextEnabled(boolean enabled) {
        mLeftBtn.setTextEnabled(enabled);
    }

    public void setRightBtnTextEnabled(boolean enabled) {
        mRightBtn.setTextEnabled(enabled);
    }

    public void setLeftBtnTextViewBold() {
        mLeftBtn.setTextViewBold();
    }

    public void setRightBtnTextViewBold() {
        mRightBtn.setTextViewBold();
    }

    public boolean isLeftButtonVisible() {
        return mLeftBtn.isButtonVisible();
    }

    public boolean isRightButtonVisible() {
        return mRightBtn.isButtonVisible();
    }

    public TextView getTitle() {
        return mTitle;
    }

}
