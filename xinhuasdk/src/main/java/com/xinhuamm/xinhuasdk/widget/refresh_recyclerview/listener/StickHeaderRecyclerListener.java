//package com.xinhuamm.xinhuasdk.widget.refresh_recyclerview.listener;
//
//import android.support.v7.widget.RecyclerView;
//import android.view.View;
//import android.widget.TextView;
//
//import com.xinhuamm.xinhuasdk.widget.refresh_recyclerview.adapter.BaseRecyclerAdapter;
//
//
//public class StickHeaderRecyclerListener extends RecyclerView.OnScrollListener{
//
//    public interface StickHeaderChangerListener{
//        void onHeaderChanger(String title);
//    }
//
//    private View mContainer;
//    private TextView mStickHeaderView;
//    private TextView mStickHeaderMoreView;
//    private boolean mShowMore;
//
//    private StickHeaderChangerListener mListener;
//
//    public StickHeaderRecyclerListener(View container, TextView stickHeaderView, TextView stickHeaderMore,
//                                       boolean showMore, StickHeaderChangerListener listener) {
//
//        mContainer = container;
//        mContainer.setVisibility(View.INVISIBLE);
//        mStickHeaderMoreView = stickHeaderMore;
//        mStickHeaderView = stickHeaderView;
//        mListener = listener;
//        mShowMore = showMore;
//    }
//
//    @Override
//    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
//        super.onScrolled(recyclerView, dx, dy);
//
//        // Get the sticky information from the topmost view of the screen.
//        View stickyInfoView = recyclerView.getChildAt(0);
////                recyclerView.findChildViewUnder(mContainer.getMeasuredWidth() / 2, 5);
//        if (stickyInfoView != null && stickyInfoView.getContentDescription() != null) {
//            mContainer.setVisibility(View.VISIBLE);
//            String title = String.valueOf(stickyInfoView.getContentDescription());
//            mStickHeaderView.setVisibility(View.VISIBLE);
//            mStickHeaderView.setText(title);
//
//            if (mShowMore) {
//                mStickHeaderMoreView.setVisibility(View.VISIBLE);
//            } else {
//                mStickHeaderMoreView.setVisibility(View.GONE);
//            }
//
//            if ( mListener != null ) {
//                mListener.onHeaderChanger(title);
//            }
//
//        } else {
//            mContainer.setVisibility(View.INVISIBLE);
//        }
//
//        // Get the sticky view's translationY by the first view below the sticky's height.
//        View transInfoView = recyclerView.findChildViewUnder(
//                mContainer.getMeasuredWidth() / 2, mContainer.getMeasuredHeight() + 1);
//
//        if (transInfoView != null && transInfoView.getTag() != null) {
//            int transViewStatus = (int) transInfoView.getTag();
//            int dealtY = transInfoView.getTop() - mContainer.getMeasuredHeight();
//            if (transViewStatus == BaseRecyclerAdapter.HAS_STICKY_VIEW) {
//                // If the first view below the sticky's height scroll off the screen,
//                // then recovery the sticky view's translationY.
//                if (transInfoView.getTop() > 0) {
//                    mContainer.setTranslationY(dealtY);
//                } else {
//                    mContainer.setTranslationY(0);
//                }
//            } else if (transViewStatus == BaseRecyclerAdapter.NONE_STICKY_VIEW) {
//                mContainer.setTranslationY(0);
//            }
//        }
//    }
//
//    public boolean isShowMore() {
//        return mShowMore;
//    }
//
//    public void setShowMore(boolean mShowMore) {
//        this.mShowMore = mShowMore;
//    }
//}
//
