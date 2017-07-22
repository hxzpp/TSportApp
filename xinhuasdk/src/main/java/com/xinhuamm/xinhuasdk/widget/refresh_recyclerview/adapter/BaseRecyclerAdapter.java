//package com.xinhuamm.xinhuasdk.widget.refresh_recyclerview.adapter;
//
//import android.content.Context;
//import android.databinding.DataBindingUtil;
//import android.databinding.ViewDataBinding;
//import android.support.v7.widget.RecyclerView;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public abstract class BaseRecyclerAdapter<T> extends RecyclerView.Adapter<RecyclerViewHolder> {
//
//    public static final int FIRST_STICKY_VIEW = 1;
//    public static final int HAS_STICKY_VIEW = 2;
//    public static final int NONE_STICKY_VIEW = 3;
//
//    protected final List<T> mData;
//    protected final Context mContext;
//    protected LayoutInflater mInflater;
//    private OnItemClickListener onItemClickListener;
//    private OnItemLongClickListener mOnItemLongClickListener;
//
//    abstract public int getItemLayoutId(int viewType);
//
//    abstract public void bindData(RecyclerViewHolder holder, int position, T item);
//
//    public BaseRecyclerAdapter(Context ctx, List<T> list) {
//        mData = (list != null) ? list : new ArrayList<T>();
//        mContext = ctx;
//        mInflater = LayoutInflater.from(ctx);
//    }
//
//    public BaseRecyclerAdapter(Context ctx) {
//        mData = new ArrayList<T>();
//        mContext = ctx;
//        mInflater = LayoutInflater.from(ctx);
//    }
//
//    @Override
//    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        ViewDataBinding binding = DataBindingUtil.inflate(
//                LayoutInflater.from(mContext), getItemLayoutId(viewType), parent, false);
//
//        final RecyclerViewHolder holder = new RecyclerViewHolder(mContext, binding.getRoot());
//        holder.setBinding(binding);
//
//        return holder;
//    }
//
//    @Override
//    public void onBindViewHolder(RecyclerViewHolder holder, final int position) {
//        bindData(holder, position, mData.get(position));
//        if (onItemClickListener != null) {
//            holder.getViewRoot().setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    onItemClickListener.onItemClick(position, mData.get(position));
//
//                }
//            });
//        }
//        if (mOnItemLongClickListener != null) {
//            holder.getViewRoot().setOnLongClickListener(new View.OnLongClickListener() {
//                @Override
//                public boolean onLongClick(View v) {
//                    return mOnItemLongClickListener.onItemLongCLick(position, mData.get(position));
//
//                }
//            });
//        }
//
//    }
//
//    @Override
//    public int getItemCount() {
//        return mData == null ? 0 : mData.size();
//    }
//
//    /**
//     * 提取制定位置的元素, add by lyy
//     *
//     * @param position
//     * @return
//     */
//    public T getItemAtPosition(int position) {
//        if (position < 0 || position >= mData.size()) {
//            return null;
//        } else {
//            return mData.get(position);
//        }
//    }
//
//    public void add(int pos, T item) {
//        mData.add(pos, item);
//        notifyItemInserted(pos);
//    }
//
//    public void delete(int pos) {
//        mData.remove(pos);
//        notifyItemRemoved(pos);
//    }
//
//    public void addList(boolean isRefresh, List<T> list) {
//        if (isRefresh) {
//            mData.clear();
//        }
//        if (list != null && list.size() > 0) {
//            mData.addAll(list);
//            notifyDataSetChanged();
//        }
//    }
//
//    public void addList(int pos, List<T> list) {
//        if (list != null && list.size() > 0) {
//            mData.addAll(pos, list);
//            notifyDataSetChanged();
//        }
//    }
//
//    public void addList(List<T> list) {
//        if (list != null && list.size() > 0) {
//            int startPos = mData.size();
//            mData.addAll(list);
//            notifyItemRangeInserted(startPos, list.size());
//        }
//    }
//
//    public void clear() {
//        if (mData != null) {
//            mData.clear();
//            notifyDataSetChanged();
//        }
//    }
//
//    public List<T> getDataList() {
//        return mData;
//    }
//
//    public interface OnItemClickListener<T> {
//        void onItemClick(int position, T item);
//    }
//
//    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
//        this.onItemClickListener = onItemClickListener;
//    }
//
//    public interface OnItemLongClickListener<T> {
//        boolean onItemLongCLick(int position, T item);
//
//    }
//
//    public void setOnItemLongClickListener(OnItemLongClickListener onItemLongClickListener) {
//        this.mOnItemLongClickListener = onItemLongClickListener;
//    }
//
//}