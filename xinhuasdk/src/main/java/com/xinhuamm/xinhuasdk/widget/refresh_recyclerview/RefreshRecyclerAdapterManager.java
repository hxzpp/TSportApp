//package com.xinhuamm.xinhuasdk.widget.refresh_recyclerview;
//
//import android.content.Context;
//import android.support.v7.widget.GridLayoutManager;
//import android.support.v7.widget.RecyclerView;
//import android.view.View;
//import android.widget.TextView;
//
//import com.xinhuamm.xinhuasdk.widget.refresh_recyclerview.adapter.BaseRecyclerAdapter;
//import com.xinhuamm.xinhuasdk.widget.refresh_recyclerview.adapter.RefreshRecyclerViewAdapter;
//import com.xinhuamm.xinhuasdk.widget.refresh_recyclerview.listener.LoadMoreRecyclerListener;
//import com.xinhuamm.xinhuasdk.widget.refresh_recyclerview.listener.OnBothRefreshListener;
//import com.xinhuamm.xinhuasdk.widget.refresh_recyclerview.listener.StickHeaderRecyclerListener;
//
//import java.util.ArrayList;
//import java.util.List;
//
//
///**
// * 这是一个recylerview的管理类，主要是在第一次初始化的时候使用，并调用into方法，
// * 在运行中途需要调用recylerview的某个方法，最好不要用这个管理类，要不会不起作用，
// * 以后在优化它。
// */
//public class RefreshRecyclerAdapterManager {
//
//    private RefreshRecyclerView recyclerView;
//    private RefreshRecyclerViewAdapter mAdapter;
//    private RecyclerView.LayoutManager mLayoutManager;
//    private RecyclerMode mode;
//
//    private OnBothRefreshListener mOnBothRefreshListener;
//    private List<RecyclerView.ItemDecoration> mDecor;
//    private LoadMoreRecyclerListener loadMoreRecyclerListener;
//    private RecyclerView.ItemAnimator mItemAnimator;
//
//    private View mContainer;
//    private TextView mStickHeader;
//    private TextView mStickMore;
//    private boolean mShowMore;
//
//    private StickHeaderRecyclerListener.StickHeaderChangerListener mListener;
//    private boolean mEnableStick = false;
//    private BaseRecyclerAdapter.OnItemClickListener mOnItemClickListener;
//    private BaseRecyclerAdapter.OnItemLongClickListener mOnItemLongClickListener;
//    private StickHeaderRecyclerListener mStickHeaderRecyclerListener;
//
//    public RefreshRecyclerAdapterManager(
//            RecyclerView.Adapter adapter, RecyclerView.LayoutManager layoutManager) {
//        mAdapter = new RefreshRecyclerViewAdapter(adapter);
//
//        if (null == layoutManager) {
//            throw new NullPointerException("Couldn't resolve a null object reference of LayoutManager");
//        }
//        mLayoutManager = layoutManager;
//        if (layoutManager instanceof GridLayoutManager) {
//            //如果是header或footer，设置其充满整列
//            ((GridLayoutManager) layoutManager).setSpanSizeLookup(
//                    new HeaderSpanSizeLookUp(mAdapter, ((GridLayoutManager) layoutManager).getSpanCount()));
//        }
//        mLayoutManager = layoutManager;
//        mDecor = new ArrayList<>();
//    }
//
//    private RefreshRecyclerAdapterManager getInstance() {
//        return RefreshRecyclerAdapterManager.this;
//    }
//
//    public RefreshRecyclerAdapterManager addHeaderView(View v) {
//        if (v != null) {
//            mAdapter.addHeaderView(v);
//        }
//        return getInstance();
//    }
//
//    public RefreshRecyclerAdapterManager addHeaderView(View v, int position) {
//        if (v != null) {
//            mAdapter.addHeaderView(v, position);
//        }
//        return getInstance();
//    }
//
//    public RefreshRecyclerAdapterManager addFooterView(View v) {
//        if (v != null) {
//            mAdapter.addFooterView(v);
//        }
//        return getInstance();
//    }
//
//    public RefreshRecyclerAdapterManager removeHeaderView(View v) {
//        mAdapter.removeHeader(v);
//        return getInstance();
//    }
//
//    public RefreshRecyclerViewAdapter getAdapter() {
//        return mAdapter;
//    }
//
//    public RefreshRecyclerAdapterManager setOnBothRefreshListener(OnBothRefreshListener onBothRefreshListener) {
//        this.mOnBothRefreshListener = onBothRefreshListener;
//        return getInstance();
//    }
//
//
//    public RefreshRecyclerAdapterManager removeFooterView(View v) {
//        mAdapter.removeFooter(v);
//        return getInstance();
//    }
//
//    public RefreshRecyclerAdapterManager setMode(RecyclerMode mode) {
//        this.mode = mode;
//        return getInstance();
//    }
//
////    public RefreshRecyclerAdapterManager setLayoutManager(RecyclerView.LayoutManager layoutManager){
////        if (null == layoutManager){
////            throw new NullPointerException("Couldn't resolve a null object reference of LayoutManager");
////        }
////        this.mLayoutManager = layoutManager;
////        if (layoutManager instanceof GridLayoutManager){
////            //如果是header或footer，设置其充满整列
////            ((GridLayoutManager)layoutManager).setSpanSizeLookup(
////                    new HeaderSpanSizeLookUp(mAdapter, ((GridLayoutManager) layoutManager).getSpanCount()));
////        }
////        return getInstance();
////    }
//
//    public RefreshRecyclerAdapterManager setOnItemClickListener
//            (BaseRecyclerAdapter.OnItemClickListener onItemClickListener) {
//        this.mOnItemClickListener = onItemClickListener;
//        return getInstance();
//    }
//
//    public RefreshRecyclerAdapterManager setOnItemLongClickListener(
//            BaseRecyclerAdapter.OnItemLongClickListener onItemLongClickListener) {
//        this.mOnItemLongClickListener = onItemLongClickListener;
//        return getInstance();
//    }
//
//    public void onRefreshCompleted() {
//        if (null == recyclerView) {
//            throw new NullPointerException("recyclerView is null");
//        }
//        if (null == mAdapter) {
//            throw new NullPointerException("adapter is null");
//        }
//        if (RecyclerMode.BOTH == mode || RecyclerMode.TOP == mode) {
//            recyclerView.refreshComplete();
//        }
//        if (RecyclerMode.BOTH == mode || RecyclerMode.BOTTOM == mode) {
//            if (null != loadMoreRecyclerListener) {
//                loadMoreRecyclerListener.onRefreshComplete();
//            }
//        }
//
//    }
//
//    public RefreshRecyclerView getRecyclerView() {
//        if (null == recyclerView) {
//            throw new NullPointerException("Couldn't resolve a null object reference of RefreshRecyclerView");
//        }
//        return recyclerView;
//    }
//
//    public RefreshRecyclerAdapterManager setItemAnimator(RecyclerView.ItemAnimator itemAnimator) {
//        this.mItemAnimator = itemAnimator;
//        return getInstance();
//    }
//
//    public RefreshRecyclerAdapterManager addItemDecoration(RecyclerView.ItemDecoration decor) {
//        mDecor.add(decor);
//        return getInstance();
//    }
//
//    public RefreshRecyclerAdapterManager addItemDecoration(RecyclerView.ItemDecoration decor, int pos) {
//        mDecor.add(pos, decor);
//        return getInstance();
//    }
//
//    public RefreshRecyclerAdapterManager enableStickHeader(View container, TextView stickHeader, TextView stickMore,
//                                                           boolean showMore, StickHeaderRecyclerListener.StickHeaderChangerListener listener) {
//        if (listener != null) {
//            mContainer = container;
//            mStickHeader = stickHeader;
//            mStickMore = stickMore;
//            mShowMore = showMore;
//
//            mListener = listener;
//            mEnableStick = true;
//        }
//        return getInstance();
//    }
//
//
//    public void into(RefreshRecyclerView recyclerView, Context context) {
//        if (null == recyclerView) {
//            throw new NullPointerException("Couldn't resolve a null object reference of RefreshRecyclerView");
//        }
//
//        mAdapter.putLayoutManager(mLayoutManager);
//        recyclerView.setAdapter(mAdapter);
//
//        recyclerView.setMode(mode);
//        //为RefreshRecyclerView添加滚动监听
//        loadMoreRecyclerListener = new LoadMoreRecyclerListener(context, mode);
//        recyclerView.addOnScrollListener(loadMoreRecyclerListener);
//
//        if (mEnableStick) {
//            mStickHeaderRecyclerListener = new StickHeaderRecyclerListener(mContainer, mStickHeader,
//                    mStickMore, mShowMore, mListener);
//            recyclerView.addOnScrollListener(mStickHeaderRecyclerListener);
//        }
//
//        if (null != mOnBothRefreshListener)
//            recyclerView.setOnBothRefreshListener(mOnBothRefreshListener);
//
//        for (RecyclerView.ItemDecoration decoration : mDecor) {
//            recyclerView.addItemDecoration(decoration);
//        }
//
//        recyclerView.setItemAnimator(mItemAnimator);
//
//        mAdapter.setOnItemClickListener(mOnItemClickListener);
//        mAdapter.setOnItemLongClickListener(mOnItemLongClickListener);
//        recyclerView.setLayoutManager(mLayoutManager);
//        this.recyclerView = recyclerView;
//    }
//
//    public void setShowMore(boolean showMore) {
//        if (mStickHeaderRecyclerListener != null)
//            mStickHeaderRecyclerListener.setShowMore(showMore);
//    }
//
//
//}
