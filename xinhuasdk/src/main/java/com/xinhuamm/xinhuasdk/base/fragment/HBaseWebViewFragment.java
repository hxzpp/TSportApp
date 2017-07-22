package com.xinhuamm.xinhuasdk.base.fragment;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageButton;
import android.widget.ProgressBar;

import com.xinhuamm.xinhuasdk.R;
import com.xinhuamm.xinhuasdk.util.HDevice;
import com.xinhuamm.xinhuasdk.widget.webview.HAdvancedWebView;

/**
 * 带有腾讯X5Webview的fragment
 */
public abstract class HBaseWebViewFragment extends HBaseTitleFragment implements HAdvancedWebView.Listener, View.OnClickListener {

    private final static String USER_AGENT = " jjrb/";

    public HAdvancedWebView mWebView;
    private boolean mIsWebViewAvailable;
    protected ImageButton mBack;
    protected ImageButton mRefresh;
    protected View mBottomView;
    private Animation mAnimbottomOut;
    private Animation mAnimBottomIn;

    private ProgressBar mPageLoadingProgressBar = null;

    private boolean mEnableShow = true;
    private boolean isShow = false;
    private OnTitleReceiveLisenter mOnTitleReceiveLisenter;

    @Override
    protected int getContentLayoutId() {
        return R.layout.fragment_base_webview;
    }

    @Override
    protected void onBindViewBefore(View root) {
        super.onBindViewBefore(root);
        // on before onBindViewBefore call
    }

    @Override
    protected void initWidget(Bundle savedInstanceState) {
        super.initWidget(savedInstanceState);

        mTitleBar.setVisibility(View.GONE);//默认标题不显示
        mViewDivider.setVisibility(View.GONE);//默认不显示顶部阴影
        mIsWebViewAvailable = true;

        mBack = (ImageButton) findViewById(R.id.wapBack);
        mBack.setOnClickListener(this);

        mRefresh = (ImageButton) findViewById(R.id.wapRefresh);
        mRefresh.setOnClickListener(this);

        mBottomView = findViewById(R.id.bottomView);

        mWebView = (HAdvancedWebView)findViewById(R.id.webView);
        mWebView.setListener((Activity) mContext, this);
        mWebView.clearPermittedHostnames();
        mWebView.getSettings().setUserAgentString(mWebView.getSettings().getUserAgentString() + USER_AGENT+ HDevice.getVersionName(mContext));
        mWebView.setWebChromeClient(new WebChromeClient() {

            @Override
            public boolean onCreateWindow(WebView view, boolean isDialog, boolean isUserGesture, Message resultMsg) {
                HAdvancedWebView newWebView = new HAdvancedWebView(mContext);
                // myParentLayout.addView(newWebView, LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
                WebView.WebViewTransport transport = (WebView.WebViewTransport) resultMsg.obj;
                transport.setWebView(newWebView);
                resultMsg.sendToTarget();

                return true;
            }

        });


        mWebView.setWebViewClient(new WebViewClient() {

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {

                if (TextUtils.isEmpty(url))
                    return true;

                // 电话、短信、邮箱
                if (url.startsWith(WebView.SCHEME_TEL)
                        || url.startsWith("sms:")
                        || url.startsWith(WebView.SCHEME_MAILTO)) {
                    try {
                        Intent intent = new Intent(Intent.ACTION_VIEW);
                        intent.setData(Uri.parse(url));
                        mContext.startActivity(intent);
                    } catch (Exception ignored) {
                    }
                    return true;
                }

                return false;
            }

        });

        mWebView.setIScrollChangeListener(new HAdvancedWebView.IScrollChangeListener() {
            @Override
            public void onScrollChanged(int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                if (mEnableShow) {
                    if (oldScrollY > scrollY && oldScrollY - scrollY > 40) {// 向下
                        if (isShow)
                            mBottomView.startAnimation(mAnimBottomIn);
                    } else if (oldScrollY < scrollY && scrollY - oldScrollY > 40) {// 向上
                        if (!isShow)
                            mBottomView.startAnimation(mAnimbottomOut);
                    }
                }
            }
        });


        setShowBottom(false);
        initProgressBar();
    }


    private void initProgressBar() {
        mPageLoadingProgressBar = (ProgressBar) findViewById(R.id.progressBarWeb);// new
        // ProgressBar(getApplicationContext(),
        // null,
        // android.R.attr.progressBarStyleHorizontal);
        mPageLoadingProgressBar.setMax(100);
        mPageLoadingProgressBar.setProgressDrawable(this.getResources()
                .getDrawable(R.drawable.color_progressbar));
        mPageLoadingProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        super.initData(savedInstanceState);

        //底部栏出去动
        mAnimbottomOut = AnimationUtils.loadAnimation(getContext(), R.anim.bottom_out);
        //动画不恢复原
        mAnimbottomOut.setFillAfter(true);
        mAnimbottomOut.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                isShow = true;
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                mBottomView.setVisibility(View.GONE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        //底部进入动画
        mAnimBottomIn = AnimationUtils.loadAnimation(getContext(), R.anim.bottom_in);
        //动画不恢复原
        mAnimBottomIn.setFillAfter(true);
        mAnimBottomIn.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                isShow = false;
                mBottomView.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationEnd(Animation animation) {
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        });

    }

    @Override
    public void onResume() {
        super.onResume();
        mWebView.onResume();
    }

    @Override
    public void onPause() {
        mWebView.onPause();
        super.onPause();
    }


    @Override
    public void onDestroyView() {
        mIsWebViewAvailable = false;
        super.onDestroyView();
    }


    @Override
    public void onDestroy() {
        if (mWebView != null) {
            mWebView.onDestroy();
            mWebView = null;
        }
        super.onDestroy();
    }

    /**
     * Gets the WebView.
     */
    public HAdvancedWebView getWebView() {
        return mIsWebViewAvailable ? mWebView : null;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        mWebView.onActivityResult(requestCode, resultCode, intent);
    }


    @Override
    public void onReceivedTitle(String title) {
        if (mOnTitleReceiveLisenter != null)
            mOnTitleReceiveLisenter.OnTitleReceive(title);
    }

    @Override
    public void onPageStarted(String url, Bitmap favicon) {

    }

    @Override
    public void onPageFinished(String url) {

    }

    @Override
    public void onPageError(int errorCode, String description, String failingUrl) {

    }

    @Override
    public void onDownloadRequested(String url, String suggestedFilename, String mimeType, long contentLength, String contentDisposition, String userAgent) {

        HAdvancedWebView.Browsers.openUrl((Activity) mContext, url);

    }

    @Override
    public void onExternalPageRequest(String url) {

    }

    @Override
    public void onProgressChanged(WebView view, int newProgress) {
        mPageLoadingProgressBar.setProgress(newProgress);
        if(newProgress==100)
            mPageLoadingProgressBar.setVisibility(View.GONE);
    }

    @Override
    public void onClick(View v) {
        if (v == mBack) {
            if (mWebView != null && mWebView.canGoBack())
                mWebView.goBack();
        } else if (v == mRefresh) {
            if (mWebView != null)
                mWebView.reload();
        }
    }

    public void setShowBottom(boolean b) {
        this.mEnableShow = b;
        if (mEnableShow) {
            mBottomView.setVisibility(View.VISIBLE);
        } else
            mBottomView.setVisibility(View.GONE);
    }

    public interface OnTitleReceiveLisenter {
         void OnTitleReceive(String title);
    }

    public void setOnTitleReceiveLisenter(
            OnTitleReceiveLisenter lisenter) {
        this.mOnTitleReceiveLisenter = lisenter;
    }

}
