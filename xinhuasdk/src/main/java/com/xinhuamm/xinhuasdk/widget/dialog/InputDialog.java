package com.xinhuamm.xinhuasdk.widget.dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.annotation.StyleRes;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import com.xinhuamm.xinhuasdk.R;


/**
 * 带edittext输入框
 */
public class InputDialog extends Dialog {

    private TextView tvCancel, tvSure,tvDigTitle;
    private EditText editText;
    private BtnClickListener btnClickListener;  //用户逆臣操作监听接口
    private Context context;  //上下文环境

    public InputDialog(Context _context) {
        this(_context, R.style.user_default_dialog);

    }

    public InputDialog(@NonNull Context context, @StyleRes int themeResId) {
        super(context, themeResId);
        this.context = context;
        initView();
    }

    private void initView() {
        View view = LayoutInflater.from(context).inflate(R.layout.input_dialog_layout, null);
        tvCancel = (TextView) view.findViewById(R.id.cancelTV);
        tvSure = (TextView) view.findViewById(R.id.sureTV);
        tvDigTitle=(TextView)view.findViewById(R.id.tvDigTitle);
        editText = (EditText) view.findViewById(R.id.editText);
        tvCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (btnClickListener != null) {
                    btnClickListener.onCancel();
                }
            }
        });
        tvSure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (btnClickListener != null) {
                    btnClickListener.onSure(editText.getText().toString());
                }
            }
        });
        setOnShowListener(new OnShowListener() {
            @Override
            public void onShow(DialogInterface dialog) {
                InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.showSoftInput(editText, InputMethodManager.SHOW_IMPLICIT);
            }
        });
        setContentView(view);
    }

    /**
     * 设置输入默认显示内容
     */
    public InputDialog setEditTextContent(String content) {
        editText.setText(content);
        if(!TextUtils.isEmpty(content)){
            editText.setSelection(content.length());
        }
        return this;
    }

    /**
     * 设置标题
     * @param title
     * @return
     */
    public InputDialog setTitletext(String title){
        tvDigTitle.setText(title);
        return this;
    }

    public InputDialog setEditText(String hint, boolean isSingleLine){
        editText.setSingleLine(isSingleLine);
        editText.setHint(hint);
        return this;
    }

    /**
     * 获取用户的输入内容
     *
     * @return
     */
    public String getEditTextContent() {
        return editText.getText().toString();
    }

    public interface BtnClickListener {
        void onCancel();

        void onSure(String content);
    }

    public BtnClickListener getNickDialogListener() {
        return btnClickListener;
    }

    public void setBtnClickListener(BtnClickListener btnClickListener) {
        this.btnClickListener = btnClickListener;
    }
}
