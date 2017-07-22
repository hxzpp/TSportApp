//package com.xinhuamm.xinhuasdk.widget.refresh_recyclerview.adapter;
//
//import android.content.Context;
//import android.databinding.ViewDataBinding;
//import android.support.v7.widget.RecyclerView;
//import android.util.SparseArray;
//import android.view.View;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.ImageButton;
//import android.widget.ImageView;
//import android.widget.TextView;
//
//import com.facebook.drawee.view.SimpleDraweeView;
//
//public class RecyclerViewHolder extends RecyclerView.ViewHolder {
//    // 使用data binding
//    private ViewDataBinding mBinding;
//    // 集合类，layout里包含的View,以view的id作为key，value是view对象
//    private SparseArray<View> mViews;
//    // 上下文对象
//    private Context mContext;
//
//    public ViewDataBinding getBinding() {
//        return mBinding;
//    }
//
//    public void setBinding(ViewDataBinding binding) {
//        this.mBinding = binding;
//    }
//
//    public RecyclerViewHolder(Context ctx, View itemView) {
//        super(itemView);
//        mContext = ctx;
//        mViews = new SparseArray<>();
//    }
//
//    private <T extends View> T findViewById(int viewId) {
//        View view = mViews.get(viewId);
//        if (view == null) {
//            view = itemView.findViewById(viewId);
//            mViews.put(viewId, view);
//        }
//        return (T) view;
//    }
//
//    public View getViewRoot() {
//        return itemView;
//    }
//
//    public SimpleDraweeView getDraweeView(int viewId) {
//        return (SimpleDraweeView) getView(viewId);
//    }
//
//    public View getView(int viewId) {
//        return findViewById(viewId);
//    }
//
//    public TextView getTextView(int viewId) {
//        return (TextView) getView(viewId);
//    }
//
//    public Button getButton(int viewId) {
//        return (Button) getView(viewId);
//    }
//
//    public ImageView getImageView(int viewId) {
//        return (ImageView) getView(viewId);
//    }
//
//    public ImageButton getImageButton(int viewId) {
//        return (ImageButton) getView(viewId);
//    }
//
//    public EditText getEditText(int viewId) {
//        return (EditText) getView(viewId);
//    }
//
//    public RecyclerViewHolder setText(int viewId, String value) {
//        TextView view = findViewById(viewId);
//        view.setText(value);
//        return this;
//    }
//
//    public RecyclerViewHolder setBackground(int viewId, int resId) {
//        View view = findViewById(viewId);
//        view.setBackgroundResource(resId);
//        return this;
//    }
//
//    public RecyclerViewHolder setClickListener(int viewId, View.OnClickListener listener) {
//        View view = findViewById(viewId);
//        view.setOnClickListener(listener);
//        return this;
//    }
//
//}
