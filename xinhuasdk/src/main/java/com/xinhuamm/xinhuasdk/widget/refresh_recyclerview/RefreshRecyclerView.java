//package com.xinhuamm.xinhuasdk.widget.refresh_recyclerview;
//
//import android.content.Context;
//import android.support.v7.widget.RecyclerView;
//import android.util.AttributeSet;
//import android.view.GestureDetector;
//import android.view.MotionEvent;
//import android.view.View;
//
//import com.xinhuamm.xinhuasdk.widget.ptr.PtrDefaultHandler;
//import com.xinhuamm.xinhuasdk.widget.ptr.PtrFrameLayout;
//import com.xinhuamm.xinhuasdk.widget.ptr.PtrHandler;
//import com.xinhuamm.xinhuasdk.widget.refresh_recyclerview.listener.LoadMoreRecyclerListener;
//import com.xinhuamm.xinhuasdk.widget.refresh_recyclerview.listener.OnBothRefreshListener;
//import com.xinhuamm.xinhuasdk.widget.refresh_recyclerview.view.RefreshHeader;
//import com.xinhuamm.xinhuasdk.widget.refresh_recyclerview.view.RefreshHeaderGif;
//
//public class RefreshRecyclerView extends PtrFrameLayout {
//    private final int LODING_ICON_MODE_NORMAL=1;
//    private final int LODING_ICON_MODE_GIF=2;
//
//    private Context mContext;
//    private RecyclerView mRecyclerView;
//    private PtrFrameLayout.LayoutParams params;
//    private LoadMoreRecyclerListener mOnScrollListener;
//    private RecyclerMode mode;
//    private RefreshHeader mHeaderView;
//    private RefreshHeaderGif mHeaderViewGif;
//    private float mDownY;
//
//    private boolean mIsHorizontalMode = false;
//    private boolean isFirst = true;
//    private GestureDetector mDetector;
//    private boolean mIsDisallowIntercept = false;
//    private int loadingMode=LODING_ICON_MODE_GIF;
//
//    public RefreshRecyclerView(Context context) {
//        super(context);
//        mContext = context;
//        init();
//    }
//
//    public RefreshRecyclerView(Context context, AttributeSet attrs, int defStyle) {
//        super(context, attrs, defStyle);
//        mContext = context;
//        init();
//    }
//
//    public RefreshRecyclerView(Context context, AttributeSet attrs) {
//        super(context, attrs);
//        mContext = context;
//        init();
//    }
//
//    private void initGesture() {
//        mDetector = new GestureDetector(getContext(),gestureListener);
//    }
//
//    private void init() {
//        mRecyclerView = new RecyclerView(mContext);
//        params = new PtrFrameLayout.LayoutParams(
//                PtrFrameLayout.LayoutParams.MATCH_PARENT, PtrFrameLayout.LayoutParams.MATCH_PARENT);
//        mRecyclerView.setLayoutParams(params);
//        addView(mRecyclerView);
//
//        setResistance(1.7f);
//        setRatioOfHeaderHeightToRefresh(1.2f);
//        setDurationToClose(100);
//        setDurationToCloseHeader(200);
//        setKeepHeaderWhenRefresh(true);
//        setPullToRefresh(false);
//
//        //ViewPager滑动冲突
//        disableWhenHorizontalMove(true);
//        initGesture();
//
//        if(loadingMode==LODING_ICON_MODE_GIF){
//            mHeaderViewGif=new RefreshHeaderGif(mContext);
//            setHeaderView(mHeaderViewGif);
//            addPtrUIHandler(mHeaderViewGif);
//        }else {
//            mHeaderView = new RefreshHeader(mContext);
//            setHeaderView(mHeaderView);
//            addPtrUIHandler(mHeaderView);
//        }
//    }
//
//    public void setAdapter(RecyclerView.Adapter adapter){
//        if (null == adapter){
//            throw new NullPointerException("adapter cannot be null");
//        }
//        mRecyclerView.setAdapter(adapter);
//    }
//
//    public void setLayoutManager(RecyclerView.LayoutManager layoutManager){
//        mRecyclerView.setLayoutManager(layoutManager);
//    }
//
//    public void setItemAnimator(RecyclerView.ItemAnimator itemAnimator){
//        if (null == itemAnimator){
//            return;
//        }
//        mRecyclerView.setItemAnimator(itemAnimator);
//    }
//
//    public void setMode(RecyclerMode mode){
//        this.mode = mode;
//        if (RecyclerMode.NONE == mode || RecyclerMode.BOTTOM == mode){
//
//            setEnabled(false);
//        } else {
//            setEnabled(true);
//        }
//
//        if(null != mOnScrollListener){
//            mOnScrollListener.setMode(mode);
//        }
//    }
//
//    public void addOnScrollListener(RecyclerView.OnScrollListener listener){
//        if (null == listener){
//            return;
//        }
//        if (listener instanceof LoadMoreRecyclerListener){
//            mOnScrollListener = (LoadMoreRecyclerListener) listener;
//            mRecyclerView.addOnScrollListener(mOnScrollListener);
//        } else {
//            mRecyclerView.addOnScrollListener(listener);
//        }
//    }
//
//    public RecyclerView.LayoutManager getLayoutManager(){
//        return mRecyclerView.getLayoutManager();
//    }
//
//    public void addItemDecoration(RecyclerView.ItemDecoration decor){
//        if (null == decor){
//            return;
//        }
//        mRecyclerView.addItemDecoration(decor);
//    }
//
//    public void setOnBothRefreshListener(final OnBothRefreshListener listener){
//        if (RecyclerMode.NONE == mode || null == listener){
//            return;
//        }
//
//        if (RecyclerMode.BOTH == mode || RecyclerMode.TOP == mode){
//            //当前允许下拉刷新
//
//            setPtrHandler(new PtrHandler() {
//                @Override
//                public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {
//
//                    return PtrDefaultHandler.checkContentCanBePulledDown(frame, content, header);
//                }
//
//                @Override
//                public void onRefreshBegin(PtrFrameLayout frame) {
//                    listener.onPullDown();
////                    if(!NetWork.getNetworkStatus(getContext())) {
////                        onRefreshCompleted();
////                        Toast.makeText(getContext(), "网络未连接，请检查网络设置", Toast.LENGTH_SHORT).show();
////                    }
//                }
//            });
//        }
//
//        if (RecyclerMode.BOTH == mode || RecyclerMode.BOTTOM == mode){
//            if (null != mOnScrollListener){
//                mOnScrollListener.setOnBothRefreshListener(listener);
//            }
//        }
//    }
//
//    public RecyclerView real(){
//        return mRecyclerView;
//    }
//
//    public void onRefreshCompleted(){
//        if (RecyclerMode.BOTH == mode || RecyclerMode.TOP == mode){
//            refreshComplete();
//        }
//        if (RecyclerMode.BOTH == mode || RecyclerMode.BOTTOM == mode){
//            if (null != mOnScrollListener){
//                mOnScrollListener.onRefreshComplete();
//            }
//        }
//    }
//
//    private GestureDetector.OnGestureListener gestureListener = new GestureDetector.OnGestureListener() {
//        @Override
//        public boolean onDown(MotionEvent e) {
//            return true;
//        }
//
//        @Override
//        public void onShowPress(MotionEvent e) {
//        }
//
//        @Override
//        public boolean onSingleTapUp(MotionEvent e) {
//            return false;
//        }
//
//        @Override
//        public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
//            float disX, disY;
//            if(distanceX < 0) {
//                disX = -distanceX;
//            } else {
//                disX = distanceX;
//            }
//            if(distanceY < 0) {
//                disY = -distanceY;
//            } else {
//                disY = distanceY;
//            }
//
//            if (disX > 5) {
//                if (isFirst) {
//                    mIsHorizontalMode = true;
//                    isFirst = false;
//                }
//            } else {
//                if (isFirst) {
//                    mIsHorizontalMode = false;
//                    isFirst = false;
//                }
//                return false;//垂直滑动会返回false
//            }
//
//            return true;//水平滑动会返回true
//        }
//
//        @Override
//        public void onLongPress(MotionEvent e) {
//
//        }
//
//        @Override
//        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
//            return false;
//        }
//    };
//
//    @Override
//    public void requestDisallowInterceptTouchEvent(boolean disallowIntercept) {
//        this.mIsDisallowIntercept = disallowIntercept;
//        super.requestDisallowInterceptTouchEvent(disallowIntercept);
//    }
//
//    @Override
//    public boolean dispatchTouchEvent(MotionEvent e) {
//
//        if (mIsDisallowIntercept && mIsHorizontalMode) {
//            return dispatchTouchEventSupper(e);
//        }
//
////        if (!isEnabled()) {
////            switch (e.getAction()){
////                case MotionEvent.ACTION_DOWN:
////                    mDownY = e.getY();
////                    break;
////                case MotionEvent.ACTION_UP:
////                    float currentY = e.getY();
////                    if ((currentY - mDownY) > 0) {
////                        //手指向下
////                        mOnScrollListener.isLoadingMoreEnabled = false;
////                    }
////                    else {
////                        //手指向上
////                        mOnScrollListener.isLoadingMoreEnabled = true;
////                    }
////
////                    break;
////            }
////            return super.dispatchTouchEvent(e);
////        }
//
////        if (mDetector.onTouchEvent(e) && mIsDisallowIntercept && mIsHorizontalMode){
////            return dispatchTouchEventSupper(e);
////        }
//
//
//        return super.dispatchTouchEvent(e);
//    }
//
//
//
//}
