//package com.xinhuamm.xinhuasdk.widget.refresh_recyclerview;
//
//import android.content.Context;
//import android.graphics.Canvas;
//import android.graphics.Paint;
//import android.graphics.Rect;
//import android.support.v7.widget.LinearLayoutManager;
//import android.support.v7.widget.RecyclerView;
//import android.util.TypedValue;
//import android.view.View;
//
//public class DividerItemDecoration extends RecyclerView.ItemDecoration {
//
//    private int mOrientation = LinearLayoutManager.VERTICAL ;
//
//    // item之间分割线的size，默认为1
//    private static final float DEFAULT_DIP_SIZE=0.5f;//dp为单位
//    private int mDividerSize;
//
//    private Paint mPaint ;
//    private Context context;
//
//    // 构造方法传入布局方向，不可不传
//    public DividerItemDecoration(Context context, int orientation, int color) {
//        this(context,orientation,color,DEFAULT_DIP_SIZE);
//    }
//
//    // 构造方法传入布局方向，不可不传
//    public DividerItemDecoration(Context context, int orientation, int color, int dipSize) {
//        this(context,orientation,color,(float) dipSize);
//    }
//
//    // 构造方法传入布局方向，不可不传
//    public DividerItemDecoration(Context context, int orientation, int color, float dipSize) {
//        this.mOrientation = orientation;
//        this.context=context;
//        if(orientation != LinearLayoutManager.VERTICAL && orientation != LinearLayoutManager.HORIZONTAL){
//            throw new IllegalArgumentException("IllegalArgument") ;
//        }
//        mDividerSize = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,dipSize,context.getResources().getDisplayMetrics());
//        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG) ;
//        mPaint.setColor(color);
//        // 设置填充
//        mPaint.setStyle(Paint.Style.FILL);
//    }
//
//    public void setDividerSize(int dividerSize){
//        mDividerSize = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_PX,dividerSize,context.getResources().getDisplayMetrics());
//    }
//
//    @Override
//    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
//        if(mOrientation == LinearLayoutManager.VERTICAL){
//            drawVertical(c,parent) ;
//        }else {
//            drawHorizontal(c,parent) ;
//        }
//    }
//
//    // 绘制纵向 item 分割线
//    private void drawVertical(Canvas canvas,RecyclerView parent){
//        final int left = parent.getPaddingLeft() ;
//        final int right = parent.getMeasuredWidth() - parent.getPaddingRight() ;
//        final int childSize = parent.getChildCount() ;
//        for(int i = 0 ; i < childSize ; i ++){
//            final View child = parent.getChildAt( i ) ;
//            RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) child.getLayoutParams();
//            final int top = child.getBottom() + layoutParams.bottomMargin ;
//            final int bottom = top + mDividerSize;
//            canvas.drawRect(left,top,right,bottom,mPaint);
//        }
//    }
//
//    // 绘制横向 item 分割线
//    private void drawHorizontal(Canvas canvas,RecyclerView parent){
//        final int top = parent.getPaddingTop() ;
//        final int bottom = parent.getMeasuredHeight() - parent.getPaddingBottom() ;
//        final int childSize = parent.getChildCount() ;
//        for(int i = 0 ; i < childSize ; i ++){
//            final View child = parent.getChildAt( i ) ;
//            RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) child.getLayoutParams();
//            final int left = child.getRight() + layoutParams.rightMargin ;
//            final int right = left + mDividerSize;
//            canvas.drawRect(left,top,right,bottom,mPaint);
//        }
//    }
//
//    // 设置item分割线的size
//    @Override
//    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
//        if(mOrientation == LinearLayoutManager.VERTICAL){
//            outRect.set(0,0,0, mDividerSize);
//        }else {
//            outRect.set(0,0, mDividerSize,0);
//        }
//    }
//
//}
