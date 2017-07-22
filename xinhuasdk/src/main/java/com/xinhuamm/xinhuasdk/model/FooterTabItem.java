package com.xinhuamm.xinhuasdk.model;

public class FooterTabItem {

    public FooterTabItem(Class<?> clx, int defaultTextResId,
                         int selectedDrawableId, int focusDrawableIconId,
                         int normalTextColorId, int focusTextColorId ) {

        this.mClx = clx;
        this.defaultTextResId = defaultTextResId;
        this.selectedDrawableId = selectedDrawableId;
        this.focusDrawableIconId = focusDrawableIconId;
        this.normalTextColorId = normalTextColorId;
        this.focusTextColorId = focusTextColorId;
    }

    public void setTitleText(String title) {
        this.titleTxt = title;
    }

    public void test(){

    };

    public Class<?> mClx;
    public String titleTxt;
    public int defaultTextResId;
    public int selectedDrawableId;
    public int focusDrawableIconId;
    public int normalTextColorId;
    public int focusTextColorId;

}
