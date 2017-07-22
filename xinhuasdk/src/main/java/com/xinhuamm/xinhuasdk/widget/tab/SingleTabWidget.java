package com.xinhuamm.xinhuasdk.widget.tab;

import android.content.Context;
import android.content.res.Resources;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TabWidget;
import android.widget.TextView;

import com.xinhuamm.xinhuasdk.R;

import java.util.ArrayList;
import java.util.List;


public class SingleTabWidget extends TabWidget {

    public static class Tab {
        private Fragment mFragment;
        private Class<?> mClx;
        private int titleId;
        private int tabNormalDrawableId;
        private int tabFocusDrawableId;
        private int textNormalColorId;
        private int textFocusColorId;




        public Tab(Class<?> clx, int tabName, int selectorId, int focusDrawableId,
                   int textNormalColorId, int textFocusColorId) {
            this.mClx = clx;
            this.titleId = tabName;
            this.tabNormalDrawableId = selectorId;
            this.tabFocusDrawableId = focusDrawableId;
            this.textNormalColorId = textNormalColorId;
            this.textFocusColorId = textFocusColorId;
        }

        public Fragment getFragment() {
            return mFragment;
        }

        public void setFragment(Fragment mFragment) {
            this.mFragment = mFragment;
        }

        public Class<?> getClx() {
            return mClx;
        }

        public void setClx(Class<?> mClx) {
            this.mClx = mClx;
        }
    }


    private int mLayoutId;
    private int mSelectedTab = -1;

    private List<Tab> mTabs = new ArrayList<>();

    public interface OnTabChangedListener {
        void onTabChanged(int oldIndex,int tabIndex);
    }

    private OnTabChangedListener mOnTabChangedListener;

    public SingleTabWidget(Context context) {
        super(context);
        init();
    }

    public SingleTabWidget(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public SingleTabWidget(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public SingleTabWidget(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    private void init() {
        setStripEnabled(false);
        setDividerDrawable(null);
        mLayoutId = R.layout.tab_item_layout;
    }

    public void setLayout(int layoutResId) {
        mLayoutId = layoutResId;
    }


    public void addTab(Tab tab) {
        Resources resource = getResources();
        String title = resource.getString(tab.titleId);

        View view = LayoutInflater.from(getContext()).inflate(mLayoutId, this, false);
        if (view == null) {
            throw new RuntimeException(
                    "You must call 'setLayout(int layoutResId)' to initialize the tab.");
        } else {
            LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
            layoutParams.width = 0;
            layoutParams.height = LayoutParams.MATCH_PARENT;//.WRAP_CONTENT;
            layoutParams.weight = 1.0f;
            view.setLayoutParams(layoutParams);
        }

        if (view instanceof TextView) {
            if (tab.textNormalColorId > 0) {
                ((TextView) view).setCompoundDrawablesWithIntrinsicBounds(0,
                        tab.textNormalColorId, 0, 0);
            }

            if (!TextUtils.isEmpty(title)) {
                ((TextView) view).setText(title);
            }
        } else if (view instanceof ImageView) {
            if (tab.tabNormalDrawableId > 0) {
                ((ImageView) view).setImageResource(tab.tabNormalDrawableId);
            }
        } else {
            final TextView textView = (TextView) view.findViewById(R.id.tab_item_layout_title);
            if (textView == null) {
                throw new RuntimeException(
                        "Your layout must have a TextView whose id attribute is 'android.R.id.title'");
            } else {
                textView.setTextColor(resource.getColor(tab.textNormalColorId));
                textView.setText(title);
            }

            ImageView imageView = (ImageView) view.findViewById(R.id.tab_item_layout_icon);
            if (imageView == null) {
                throw new RuntimeException(
                        "Your layout must have a ImageView whose id attribute is 'android.R.id.icon'");
            } else {
                imageView.setImageResource(tab.tabNormalDrawableId);
            }
        }

        addView(view);
        mTabs.add(tab);
        view.setOnClickListener(new TabClickListener(getTabCount() - 1));
        view.setOnFocusChangeListener(this);
    }

    public void setOnTabChangedListener(OnTabChangedListener listener) {
        mOnTabChangedListener = listener;
    }

    @Override
    public void onFocusChange(View view, boolean hasFocus) {
    }

    private void changeFocus(int index) {
        changeTabStateUnSelected(mSelectedTab);
        mSelectedTab = index;
        setCurrentTab(index);
        changeTabStateSelected(mSelectedTab);
    }

    public void selectTab(int index) {
        changeFocus(index);
    }

    private class TabClickListener implements OnClickListener {
        private final int mIndex;

        public TabClickListener(int index) {
            mIndex = index;
        }

        @Override
        public void onClick(View view) {
            if (mOnTabChangedListener != null && mIndex != mSelectedTab) {
                mOnTabChangedListener.onTabChanged(mSelectedTab,mIndex);
//                changeFocus(mIndex);
            }
        }
    }

    private void changeTabStateSelected(int index) {
        if (index >= 0 && index < mTabs.size()) {
            View view = this.getChildTabViewAt(index);
            Tab tab = mTabs.get(index);

            final TextView textView = (TextView) view.findViewById(R.id.tab_item_layout_title);
            if (textView != null) {
                textView.setTextColor(getResources().getColor(tab.textFocusColorId));
            }

            final ImageView imageView = (ImageView) view.findViewById(R.id.tab_item_layout_icon);
            if (imageView != null) {
                imageView.setImageResource(tab.tabFocusDrawableId);
            }
        }
    }

    private void changeTabStateUnSelected(int index) {
        if (index >= 0 && index < mTabs.size()) {
            View view = this.getChildTabViewAt(index);
            if (view != null) {
                Tab tab = mTabs.get(index);

                final TextView textView = (TextView) view.findViewById(R.id.tab_item_layout_title);
                if (textView != null) {
                    textView.setTextColor(getResources().getColor(tab.textNormalColorId));
                }

                ImageView imageView = (ImageView) view.findViewById(R.id.tab_item_layout_icon);
                if (imageView != null) {
                    imageView.setImageResource(tab.tabNormalDrawableId);
                }
            }

        }
    }



}
