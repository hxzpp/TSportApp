//package com.transportmm.tsportapp.welcome;
//
//import android.Manifest;
//import android.content.Context;
//import android.content.Intent;
//import android.net.Uri;
//import android.os.CountDownTimer;
//import android.support.annotation.NonNull;
//import android.text.TextUtils;
//import android.view.View;
//import android.widget.ImageView;
//import android.widget.TextView;
//
//import com.dova.dac.JSonUtils;
//import com.facebook.drawee.view.SimpleDraweeView;
//import com.transportmm.tsportapp.BuildConfig;
//import com.transportmm.tsportapp.MainActivity;
//import com.transportmm.tsportapp.R;
//import com.xinhuamm.edpaper.news.WapDetailActivity;
//import com.xinhuamm.edpaper.push.GetuiPushManager;
//import com.xinhuamm.edpaper.util.StartADResultHelper;
//import com.xinhuamm.edpaper.util.statistics.StatisticsDelegator;
//import com.xinhuamm.edpaper.util.statistics.StatisticsHelper;
//import com.xinhuamm.xinhuasdk.base.activity.HBaseActivity;
//import com.xinhuamm.xinhuasdk.fresco.HFresco;
//import com.xinhuamm.xinhuasdk.util.HToast;
//import com.xinhuamm.xinhuasdk.util.SharedPreferencesUtil;
//
//import java.util.List;
//import java.util.UUID;
//
//import butterknife.BindView;
//import butterknife.OnClick;
//import pub.devrel.easypermissions.EasyPermissions;
//
//
//public class WelcomeActivity extends HBaseActivity implements EasyPermissions.PermissionCallbacks {
//
//    private final static int REQUEST_CODE_WAP_VIEW = 100;
//
//    private static final int RC_PHONE_STORAGE_PERM = 124;
//
//    private static final String[] PERMS = {Manifest.permission.READ_PHONE_STATE,
//            Manifest.permission.READ_EXTERNAL_STORAGE};
//
//    @BindView(R.id.ad_content)
//    SimpleDraweeView adContent;
//    @BindView(R.id.tvSkip)
//    TextView tvSkip;
//    @BindView(R.id.view_splash_bottom)
//    ImageView view;
//
//    private boolean isFirst = false;
//    private WelcomeWrapper.Presenter mPresenter;
//    private CountDownTimer mCountDownTimer;
//    private StartADData mStartADData;
//    private SharedPreferencesUtils mSharedPreferences;
//
//    @Override
//    protected int getContentView() {
//        return R.layout.activity_welcome;
//    }
//
//    public static void show(Context context) {
//        Intent intent = new Intent(context, WelcomeActivity.class);
//        context.startActivity(intent);
//    }
//
//    @Override
//    protected void initData() {
//
//        setBackEnable(false);
//        mPresenter = new WelcomePresenter(this, this);
//        mSharedPreferences = new SharedPreferencesUtils(WelcomeActivity.this, BuildConfig.APP_CONFIG_NAME);
//        isFirst = mSharedPreferences.getBoolean(BuildConfig.SP_CONFIG_ISFIRSTOPENAPP, true);
//        phoneAndStorageTask();
//    }
//
//    public void phoneAndStorageTask() {
//        if (!EasyPermissions.hasPermissions(this, PERMS)) {
//            EasyPermissions.requestPermissions(this,
//                    "请求读取手机信息和sd卡权限", RC_PHONE_STORAGE_PERM, PERMS);
//        } else {
//            init();
//        }
//    }
//
//    private void init() {
//
//        GetuiPushManager.getInstance().init(this);
//        setSharedPreferencesIMEI();
//        mPresenter.init();
//        if (!isFirst) {
//            showSplash();
//        }
//    }
//
//    private void setSharedPreferencesIMEI() {
//        String imei = mSharedPreferences.get(BuildConfig.SP_CONFIG_IMEI_ID);
//        if (TextUtils.isEmpty(imei)) {
//            imei = PhoneUtils.getPhoneIMEI(this);
//            if (isErrorImei(imei)) {
//                imei = UUID.randomUUID().toString();
//            }
//            mSharedPreferences.add(BuildConfig.SP_CONFIG_IMEI_ID, imei);
//        }
//    }
//
//    private boolean isErrorImei(String imei) {
//        if (TextUtils.isEmpty(imei) || imei.equals("null") || imei.startsWith("00000")) {
//            return true;
//        }
//        return false;
//    }
//
//    private void showSplash() {
//
//        mStartADData = StartADResultHelper.getInstance(this).getStartADData();
//        if (mStartADData != null && !TextUtils.isEmpty(mStartADData.imgUrl)) {
//            HFresco.with(adContent).loadXX(mStartADData.imgUrl);
//            tvSkip.setVisibility(View.VISIBLE);
//            if (mStartADData.isFullScreen())
//                view.setVisibility(View.GONE);
//            else
//                view.setVisibility(View.VISIBLE);
//            //由于CountDownTimer有一定的延迟，所以这里设置加了200毫秒
//            mCountDownTimer = new CountDownTimer(mStartADData.staysecond * 1000 + 200, 1000) {
//                @Override
//                public void onTick(long millisUntilFinished) {
//                    tvSkip.setText("跳过(" + millisUntilFinished / 1000 + "s)");
//                }
//
//                @Override
//                public void onFinish() {
//                    tvSkip.setText("跳过(" + 0 + "s)");
//                    gotoMainActivity();
//                }
//            };
//            mCountDownTimer.start();
//            StatisticsHelper.jjrbAdsView(this, String.valueOf(mStartADData.id), "start");
//
//        } else {
//            tvSkip.setVisibility(View.INVISIBLE);
//            tvSkip.postDelayed(new Runnable() {
//                @Override
//                public void run() {
//                    gotoMainActivity();
//                }
//            }, 1000);
//        }
//    }
//
//    @OnClick({R.id.ad_content, R.id.tvSkip})
//    public void onViewClicked(View view) {
//        switch (view.getId()) {
//            case R.id.ad_content:
//                gotoWebActivity();
//                break;
//            case R.id.tvSkip:
//                gotoMainActivity();
//                break;
//        }
//    }
//
//    private void gotoWebActivity() {
//
//        if (mStartADData != null && !TextUtils.isEmpty(mStartADData.hrefUrl)) {
//            if (mStartADData.isOutUrl()) {//外链
//                try {
//                    Intent intent = new Intent();
//                    intent.setAction(Intent.ACTION_VIEW);
//                    Uri content_url = Uri.parse(mStartADData.hrefUrl);
//                    intent.setData(content_url);
//                    startActivity(intent);
//                } catch (Exception ignore) {
//
//                }
//            } else {
//                if (mCountDownTimer != null)
//                    mCountDownTimer.cancel();
//                WapDetailActivity.show(WelcomeActivity.this, mStartADData.hrefUrl, REQUEST_CODE_WAP_VIEW, true);
//            }
//
//            StatisticsHelper.jjrbAdsClick(WelcomeActivity.this, String.valueOf(mStartADData.id), "start");
//        }
//
//    }
//
//    private void gotoMainActivity() {
//        if (mCountDownTimer != null)
//            mCountDownTimer.cancel();
//        SharedPreferencesUtils sp = new SharedPreferencesUtils(WelcomeActivity.this, BuildConfig.APP_CONFIG_NAME);
//        boolean isFirst = sp.getBoolean(BuildConfig.SP_CONFIG_ISFIRSTOPENAPP, true);
//        if (isFirst) {
//            startActivity(new Intent(WelcomeActivity.this, GuideActivity.class));
//        } else {
//            startActivity(new Intent(WelcomeActivity.this, MainActivity.class));
//        }
//        sp.addBoolean(BuildConfig.SP_CONFIG_ISFIRSTOPENAPP, false);
//        finish();
//    }
//
//    @Override
//    public void onPermissionsDenied(int requestCode, List<String> perms) {
//        HToast.showShort("关闭权限会导致部分功能无法使用");
//        init();
////        if (EasyPermissions.somePermissionPermanentlyDenied(this, perms)) {
////            new AppSettingsDialog.Builder(this).build().show();
////        }
//    }
//
//    @Override
//    public void onPermissionsGranted(int requestCode, List<String> perms) {
//        if (perms.size() == PERMS.length)
//            init();
//    }
//
//
//    @Override
//    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
//        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
//    }
//
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if (requestCode == REQUEST_CODE_WAP_VIEW) {
//            gotoMainActivity();
//        }
//    }
//
//    @Override
//    public void handleInitResult(InitResult ret) {
//        if (ret != null && ret.initData != null) {
//            InitDataHelper.getInstance(this).updateInitDataCache(ret.initData);
//            mPresenter.getAliKey();
//            mPresenter.getNav();
//            mPresenter.getStartAD();
//            (new StatisticsDelegator(this)).insertStatistics("app_start", 10000, "启动", "0");
//        } else {
//            if (isFirst) {
//                HToast.showShort("请求超时，请检查网络");
//                finish();
//            }
//        }
//    }
//
//
//    @Override
//    public void handleStartADResult(final StartADResult ret) {
//
//        if (ret != null && ret.isSuccState()) {
//            if (ret.data != null && ret.data.length > 0) {
//                StartADResultHelper.getInstance(this).updateStartADDataCache(ret.data[0]);
//            } else {
//                StartADResultHelper.getInstance(this).clearStartADDataCache();
//            }
//        }
//    }
//
//    @Override
//    public void handleNavResult(NavResult ret) {
//        if (ret != null && ret.data != null && ret.isSuccState()) {
//            NavItem[] order = ret.data.order_data;//订阅的数据
//            NavItem[] unOrder = ret.data.data;//未订阅的数据
//
//            if (order != null) {
//                FileOutputStream in = null;
//                try {
//                    in = EDApp.getInstance().openFileOutput(EDConfigApp.SUB_ACTIVE_TAB_JSON,
//                            Context.MODE_PRIVATE);
//                    JSonUtils.toJson(in, order);
//                } catch (Exception e) {
//                    e.printStackTrace();
//                } finally {
//                    FileUtils.closeStream(in);
//                }
//            }
//
//            if (unOrder != null) {
//                FileOutputStream out = null;
//                try {
//                    out = EDApp.getInstance().openFileOutput(EDConfigApp.SUB_UN_ACTIVE_TAB_JSON,
//                            Context.MODE_PRIVATE);
//                    JSonUtils.toJson(out, unOrder);
//                } catch (Exception e) {
//                    e.printStackTrace();
//                } finally {
//                    FileUtils.closeStream(out);
//                }
//            }
//            if (isFirst) {
//                tvSkip.postDelayed(new Runnable() {
//                    @Override
//                    public void run() {
//                        gotoMainActivity();
//                    }
//                }, 1000);
//            }
//        } else {
//            if (isFirst) {
//                HToast.showShort("请求超时，请重试");
//                finish();
//            }
//        }
//    }
//
//    @Override
//    public void handleAliKey(AliyunKeyResult ret) {
//        SharedPreferencesUtil sp = new SharedPreferencesUtil(this, BuildConfig.APP_CONFIG_NAME);
//        if (ret != null && ret.isSucc() && ret.data != null) {
//            sp.put(BuildConfig.SP_CONFIG_ALIKEYID, ret.data.ossKey);
//            sp.put(BuildConfig.SP_CONFIG_ALISECRETNAME, ret.data.ossSecret);
//            sp.put(BuildConfig.SP_CONFIG_ALIKEYID_USER, ret.data.key);
//            sp.put(BuildConfig.SP_CONFIG_ALISECRETNAME_USER, ret.data.secret);
//        }
//    }
//
//    @Override
//    public void handleCheckUpdateResult(CheckUpdateResult ret) {
//        if (ret != null) {
//        }
//    }
//
//    @Override
//    public void handleConfigsResult(ConfigsResult ret) {
//        if (ret != null) {
//        }
//    }
//
//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//        if (mCountDownTimer != null)
//            mCountDownTimer.cancel();
//        if (mPresenter != null)
//            mPresenter.stop();
//    }
//}
