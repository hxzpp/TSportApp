//package com.xinhuamm.xinhuasdk.base.fragment;
//
//import android.animation.Animator;
//import android.animation.AnimatorListenerAdapter;
//import android.os.Handler;
//import android.os.Message;
//import android.support.v7.widget.LinearLayoutManager;
//import android.support.v7.widget.RecyclerView;
//import android.view.View;
//import android.widget.RelativeLayout;
//import android.widget.TextView;
//
//import com.xinhuamm.xinhuasdk.R;
//import com.xinhuamm.xinhuasdk.fresco.HFresco;
//import com.xinhuamm.xinhuasdk.widget.refresh_recyclerview.DividerItemDecoration;
//import com.xinhuamm.xinhuasdk.widget.refresh_recyclerview.RecyclerMode;
//import com.xinhuamm.xinhuasdk.widget.refresh_recyclerview.RecyclerViewManager;
//import com.xinhuamm.xinhuasdk.widget.refresh_recyclerview.RefreshRecyclerAdapterManager;
//import com.xinhuamm.xinhuasdk.widget.refresh_recyclerview.RefreshRecyclerView;
//import com.xinhuamm.xinhuasdk.widget.refresh_recyclerview.adapter.BaseRecyclerAdapter;
//import com.xinhuamm.xinhuasdk.widget.refresh_recyclerview.listener.OnBothRefreshListener;
//import com.xinhuamm.xinhuasdk.widget.refresh_recyclerview.listener.StickHeaderRecyclerListener;
//
//
///**
// * 现在重点在强化这个基类的功能，其他基类还没同步，后续再添加。。。。。。。
// * 基本列表类,
// * 默认是类型是TYPE.BOTH(下拉刷新和上拉加载)
// * 并且带有标题，默认标题是不显示的
// */
//public abstract class HBaseRecyclerViewFragment<T> extends HBaseTitleFragment implements OnBothRefreshListener,
//        BaseRecyclerAdapter.OnItemClickListener<T> {
//
//    public final static int KEY_HIDE_RECOMMEND = 1;
//
//    protected TextView mStickyHeaderView;
//    protected TextView mStickyMoreView;
//    protected View mStickyContainer;
//
//    protected BaseRecyclerAdapter<T> mAdapter;
//    protected RefreshRecyclerView mRefreshView;
//
//    protected String CACHE_NAME = getClass().getName();
//    protected RefreshRecyclerAdapterManager mAdapterMgr;
//
//    protected int mPage = 1;//当前的页码
//
//    protected boolean isFromPullDown = true;//默认是下拉刷新数据
//    private TextView mRecommemndTips;
//    private RelativeLayout mRecommemndBar;
//
//
//    protected Handler mHandle = new Handler() {
//
//        @Override
//        public void handleMessage(Message msg) {
//            super.handleMessage(msg);
//            if (msg.what == KEY_HIDE_RECOMMEND) {
//                disappearRecommendBar();
//            }
//        }
//    };
//
//
//    @Override
//    public int getContentLayoutId() {
//        return R.layout.fragment_base_recycler_view;
//    }
//
//    @Override
//    protected void initWidget() {
//        super.initWidget();
//
//        mTitleBar.setVisibility(View.GONE);//默认标题不显示
//        mViewDivider.setVisibility(View.GONE);//默认不显示顶部阴影
//        mRefreshView = (RefreshRecyclerView)findViewById(R.id.refreshRecyclerView);
//        mStickyHeaderView = (TextView) findViewById(R.id.tv_sticky_header_view);
//        mStickyContainer = (View)findViewById(R.id.sticky_container);
//        mStickyMoreView = (TextView) findViewById(R.id.tv_sticky_header_more);
//
//        mRecommemndTips = (TextView) findViewById(R.id.tvRecommendTips);
//        mRecommemndBar = (RelativeLayout) findViewById(R.id.llRecommendBar);
//    }
//
//
//    @Override
//    public void initData() {
//        super.initData();
//        mAdapter = getRecyclerAdapter();
//        mAdapterMgr = RecyclerViewManager.with(mAdapter, getLayoutManager());
//        mAdapterMgr.addItemDecoration(getDividerItemDecoration())
//                .setMode(getMode())
//                .setOnBothRefreshListener(getOnBothRefreshListener())
//                .setOnItemClickListener(this)
//                .enableStickHeader(mStickyContainer, mStickyHeaderView, mStickyMoreView, isHasMoreInStickHead(),
//                        getStickHeaderChangerListener())
//                .into(mRefreshView, mContext);
//
//        mRefreshView.addOnScrollListener(new RecyclerView.OnScrollListener() {
//            @Override
//            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
//
//                switch (newState) {
//                    case RecyclerView.SCROLL_STATE_SETTLING:
//                    case RecyclerView.SCROLL_STATE_DRAGGING:
//                        if (!HFresco.isPaused()) {
//                            HFresco.pause();
//                        }
//                        break;
//                    case RecyclerView.SCROLL_STATE_IDLE:
//                        if (HFresco.isPaused()) {
//                            HFresco.resume();
//                        }
//                        break;
//                }
//            }
//        });
//
//    }
//
//    protected RecyclerView.LayoutManager getLayoutManager() {
//        return new LinearLayoutManager(mContext);
//    }
//
//    protected DividerItemDecoration getDividerItemDecoration() {
//        return new DividerItemDecoration(mContext, LinearLayoutManager.VERTICAL,
//                mContext.getResources().getColor(R.color.dialog_line));
//    }
//
//    protected RecyclerMode getMode() {
//        return RecyclerMode.BOTH;
//    }
//
//
//    protected StickHeaderRecyclerListener.StickHeaderChangerListener getStickHeaderChangerListener() {
//        return null;
//    }
//
//    protected boolean isHasMoreInStickHead() {
//        return false;
//    }
//
//    public OnBothRefreshListener getOnBothRefreshListener() {
//        return this;
//    }
//
//    protected abstract BaseRecyclerAdapter<T> getRecyclerAdapter();
//
//    @Override
//    public void onPullDown() {
//        isFromPullDown = true;
//        mPage = 1;
//
//    }
//
//    @Override
//    public void onLoadMore() {
//        isFromPullDown = false;
//        mPage++;
//    }
//
//    @Override
//    protected void onClickEmptyLayout() {
//        onPullDown();
//    }
//
//    @Override
//    public void onItemClick(int position, T item) {
//
//    }
//
//
//    /**
//     * 推荐提示栏消失
//     */
//    private void disappearRecommendBar() {
//
//        mRecommemndBar.animate()
//                .translationY(-mRecommemndTips.getHeight())
//                .setDuration(380)
//                .setListener(new AnimatorListenerAdapter() {
//                    @Override
//                    public void onAnimationEnd(Animator animation) {
//                        super.onAnimationEnd(animation);
//                        mRecommemndBar.setTranslationY(0);
//                        mRecommemndTips.setVisibility(View.GONE);
//
//                    }
//                });
//
//    }
//
//    /**
//     * 推荐提示栏出现
//     *
//     * @param str
//     */
//    public void showRecommendBar(String str) {
//        mRecommemndTips.setText(str);
//        mRecommemndTips.setVisibility(View.VISIBLE);
//        mHandle.sendEmptyMessageDelayed(KEY_HIDE_RECOMMEND,1000);
//    }
//}
