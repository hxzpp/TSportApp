package com.xinhuamm.xinhuasdk.widget.dialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.annotation.StyleRes;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import com.xinhuamm.xinhuasdk.R;

/**
 * Created by liuyiyuan on 2016/6/14.
 * <p>警告、温馨提醒等需要用户确认的对话框</p>
 */
public class ConfirmDialog extends Dialog {

    private TextView tvCancel, tvSure;
    private OnActionBtnClickListener actionBtnClickListener;
    private Context context;  //上下文环境

    private TextView tvTitle, tvContent;  // 通知标题栏
    private View lineDiveBtn;  //操作按钮分割线

    public enum DialogType {
        WARNING, REMIND
    }

    private DialogType dialogType = DialogType.REMIND;  //默认的是提醒框

    public ConfirmDialog(Context _context) {
        this(_context, R.style.user_default_dialog);

    }

    public ConfirmDialog(@NonNull Context context, @StyleRes int themeResId) {
        super(context, themeResId);
        this.context = context;
        initView();
    }

    private void initView() {
        Window window = this.getWindow();
        window.setContentView(R.layout.confirm_dialog_layout);
        tvTitle = (TextView) window.findViewById(R.id.tvTitle);
        tvCancel = (TextView) window.findViewById(R.id.cancelTV);
        tvSure = (TextView) window.findViewById(R.id.sureTV);
        tvContent = (TextView) window.findViewById(R.id.tvContent);
        lineDiveBtn = window.findViewById(R.id.lineBottom);

        tvCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (actionBtnClickListener != null) {
                    actionBtnClickListener.onCancel();
                }
                dismiss();
            }
        });
        tvSure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (actionBtnClickListener != null) {
                    actionBtnClickListener.onSure();
                }
                dismiss();
            }
        });

    }

    /**
     * 设置对话框的显示样式
     *
     * @param dialogType    对话框类型
     * @param showCancelBtn 是否需要需要按钮
     */
    public void setDigShowStyle(DialogType dialogType, boolean showCancelBtn, boolean showTitle) {
        this.dialogType = dialogType;
        tvTitle.setTextColor(dialogType == DialogType.WARNING ? Color.RED : Color.parseColor("#323232"));
        tvCancel.setVisibility(showCancelBtn ? View.VISIBLE : View.GONE);
        lineDiveBtn.setVisibility(showCancelBtn ? View.VISIBLE : View.GONE);
        tvTitle.setVisibility(showTitle ? View.VISIBLE : View.GONE);
    }

    /**
     * 设置对话框的显示样式
     * 只显示一个按钮
     *
     */
    public void setDigShowOneBtn(String title, String content, String btnText, View.OnClickListener listener) {
        tvTitle.setTextColor(dialogType == DialogType.WARNING ? Color.RED : Color.parseColor("#323232"));
        tvTitle.setText(title);
        if (TextUtils.isEmpty(content)) {
            tvContent.setVisibility(View.GONE);
        } else {
            tvContent.setVisibility(View.VISIBLE);
            tvContent.setText(content);
        }
        tvCancel.setVisibility(View.GONE);
        tvSure.setVisibility(View.VISIBLE);
        tvSure.setText(btnText);
        if(listener!=null) {
            tvSure.setOnClickListener(listener);
        }else {
            tvSure.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dismiss();
                }
            });
        }
        lineDiveBtn.setVisibility(View.GONE);
    }

    /**
     * 设置对话框的显示文字内容
     *
     * @param title
     * @param msg
     * @param sureTxt
     * @param cancelTxt
     */
    public void setDigViewTxt(String title, String msg, String sureTxt, String cancelTxt) {
        tvTitle.setText(title == null ? "" : title);
        if (msg == null || msg.equals("")) {
            tvContent.setVisibility(View.GONE);
        } else {
            tvContent.setVisibility(View.VISIBLE);
            tvContent.setText(msg);
        }
        tvCancel.setText(cancelTxt == null ? "取消" : cancelTxt);
        tvSure.setText(sureTxt == null ? "确定" : sureTxt);
    }


    public OnActionBtnClickListener getActionBtnClickListener() {
        return actionBtnClickListener;
    }

    public void setActionBtnClickListener(OnActionBtnClickListener actionBtnClickListener) {
        this.actionBtnClickListener = actionBtnClickListener;
    }

    /**
     * 设置确定按钮、取消按钮的文字颜色
     *
     * @param sureTxtColorRes   确定按钮的文字颜色
     * @param cancelTxtColorRes 取消按钮的文字颜色
     * @param titleColorRes     标题颜色
     * @param msgColorRes       内容的颜色
     */
    public void setDialogTxtColor(int sureTxtColorRes, int cancelTxtColorRes, int titleColorRes, int msgColorRes) {
        try {
            tvSure.setTextColor(context.getResources().getColor(sureTxtColorRes));
            tvCancel.setTextColor(context.getResources().getColor(cancelTxtColorRes));
            tvTitle.setTextColor(context.getResources().getColor(titleColorRes));
            tvContent.setTextColor(context.getResources().getColor(msgColorRes));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public TextView getTvContent() {
        return tvContent;
    }

    public TextView getTvTitle(){
        return tvTitle;
    }

    public interface OnActionBtnClickListener {
        void onSure();

        void onCancel();
    }


}
