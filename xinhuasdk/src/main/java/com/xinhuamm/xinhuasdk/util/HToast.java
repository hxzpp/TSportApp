package com.xinhuamm.xinhuasdk.util;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

/**
 * 吐司统一通这个
 */

public class HToast {

    private static Toast mToast;
    private static Context mContext;

    public static void init(Context context) {
        mContext = context;
    }

    private static void makeText(CharSequence text, int gravity, int duration) {

        if (mToast == null) {
            mToast = Toast.makeText(mContext, text, duration);
        } else {
            mToast.setDuration(duration);
            mToast.setText(text);
        }
        if (gravity != 0) {
            mToast.setGravity(gravity, 0, 0);
        } else {
            mToast.setGravity(Gravity.BOTTOM, 0, 200);
        }
        mToast.show();
    }

    public static void show(CharSequence message, int duration) {
        makeText(message, 0, duration);
    }


    public static void showLong(int res) {
        showLong(mContext.getText(res));
    }

    public static void showLong(CharSequence message) {
        makeText(message, 0, Toast.LENGTH_LONG);
    }


    public static void showShort(int res) {
        showShort(mContext.getText(res));
    }

    public static void showShort(CharSequence message) {
        makeText( message, 0, Toast.LENGTH_SHORT);
    }

    public static void showInGravity(int gravity, int res) {
        showInGravity(gravity, mContext.getText(res));
    }

    public static void showInGravity(int gravity,
                                     CharSequence message) {
        makeText(message, gravity, Toast.LENGTH_SHORT);
    }

    public static void showWithIcon(int drawable, int res) {
        showWithIcon(drawable, mContext.getText(res));
    }

    public static void showWithIcon( int drawable,
                                    CharSequence message) {
        Toast toast = getToast(drawable, message);
        toast.show();
    }

    public static void showWithIconInGravity(int drawable, int gravity, int res) {
        showWithIconInGravity(drawable, gravity,
                mContext.getText(res));
    }

    public static void showWithIconInGravity(int drawable, int gravity, CharSequence message) {
        Toast toast = getToast(drawable, message);
        toast.setGravity(gravity, 0, 0);
        toast.show();
    }

    private static Toast getToast(int drawable,
                                  CharSequence message) {
        Toast toast = Toast.makeText(mContext, message, Toast.LENGTH_SHORT);
        LinearLayout linearLayout = (LinearLayout) toast.getView();
        ImageView iv = new ImageView(mContext);
        iv.setImageResource(drawable);
        linearLayout.addView(iv, 0);
        return toast;
    }

    public static void showCustomToast(int layoutId, int drawable,
                                       CharSequence message, int gravity) {
        Toast toast = new Toast(mContext);
        LinearLayout view = (LinearLayout) LayoutInflater.from(mContext)
                .inflate(layoutId, null);
        if (drawable != 0) {
            ImageView iv = (ImageView) view.getChildAt(0);
            iv.setImageResource(drawable);
        }
        if (message != null) {
            TextView tv = (TextView) view.getChildAt(1);
            tv.setText(message);
        }
        if (gravity != 0) {
            toast.setGravity(gravity, 0, 0);
        }
        toast.setView(view);
        toast.show();
    }

}
