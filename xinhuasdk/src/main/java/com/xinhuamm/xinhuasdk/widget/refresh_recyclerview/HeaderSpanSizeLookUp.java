//package com.xinhuamm.xinhuasdk.widget.refresh_recyclerview;
//
//import android.support.v7.widget.GridLayoutManager;
//
//import com.xinhuamm.xinhuasdk.widget.refresh_recyclerview.adapter.RefreshRecyclerViewAdapter;
//
//
//public class HeaderSpanSizeLookUp extends GridLayoutManager.SpanSizeLookup {
//
//    private RefreshRecyclerViewAdapter mAdapter;
//    private int mSpanSize;
//
//    public HeaderSpanSizeLookUp(RefreshRecyclerViewAdapter adapter, int spanSize){
//        this.mAdapter = adapter;
//        this.mSpanSize = spanSize;
//    }
//
//    @Override
//    public int getSpanSize(int position) {
//        boolean isHeaderOrFooter = mAdapter.isHeader(position) || mAdapter.isFooter(position);
//        return isHeaderOrFooter ? mSpanSize : 1;
//    }
//}