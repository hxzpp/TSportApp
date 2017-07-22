//package com.xinhuamm.xinhuasdk;
//
//
//import android.content.Context;
//import android.content.SharedPreferences;
//import android.content.pm.PackageInfo;
//import android.content.pm.PackageManager;
//import android.support.v4.content.SharedPreferencesCompat;
//
//import com.facebook.stetho.Stetho;
//import com.tencent.bugly.crashreport.CrashReport;
//import com.xinhuamm.xinhuasdk.bilibili.BoxingFrescoLoader;
//import com.xinhuamm.xinhuasdk.bilibili.BoxingUcrop;
//import com.xinhuamm.xinhuasdk.bilibili.boxing.BoxingCrop;
//import com.xinhuamm.xinhuasdk.bilibili.boxing.BoxingMediaLoader;
//import com.xinhuamm.xinhuasdk.fresco.HFresco;
//import com.xinhuamm.xinhuasdk.util.HDevice;
//import com.xinhuamm.xinhuasdk.util.HToast;
//
///**
// * 使用这个sdk的同志们，请必须继承这个类，要不会有意向不到的效果
// * 全局应用程序类
// * 用于保存和调用全局应用配置及访问网络数据
// */
//public class HAppContext extends android.support.multidex.MultiDexApplication {
//
//    private static final String PREF_NAME = "setting.pref";
//    private static final String NET_PROCESS = ":cozyDataManager";
//
//
//    private static HAppContext INSTANCE;
//    private boolean hasOpenApp = false;
//
//    @Override
//    public void onCreate() {
//        super.onCreate();
//        INSTANCE = this;
//        init();
//    }
//
//
//    private void init() {
//        // 多进程导致多次初始化Application,这里只初始化App主进程的Application
//        String curProcessName = HDevice.getProcessName(this, android.os.Process.myPid());
//        if (curProcessName.equals(getPackageName())) {//主进程
//            //初始化Fresco
//            HFresco.init(this);
//            //初始化配置信息（图片，音频，视频等存储位置）
//            HAppConfig.getInstance().init(this);
//            //初始化图片选择器
//            BoxingMediaLoader.getInstance().init(new BoxingFrescoLoader());
//            BoxingCrop.getInstance().init(new BoxingUcrop());
//            //初始化吐司
//            HToast.init(getApplicationContext());
//            // 初始化 友盟
////            UMShareAPI.get(this);
////            PlatformConfig.setWeixin(BuildConfig.WEIXIN_APPID, BuildConfig.WEIXIN_APPSECRET);
////            //新浪微博 appkey appsecret
////            PlatformConfig.setSinaWeibo(BuildConfig.WEIBO_APPKEY, BuildConfig.WEIBO_APPSECRET, BuildConfig.WEIBO_CALLBACK);
////            // QQ和Qzone appid appkey
////            PlatformConfig.setQQZone(BuildConfig.QQ_APPID, BuildConfig.QQ_APPKEY);
//        }
//
//        Stetho.initializeWithDefaults(this);
//        //腾讯Bugly初始化
//        CrashReport.initCrashReport(this, BuildConfig.BUGLY_APPID, false);
//
//        //QbSdk.initX5Environment(getApplicationContext(),  null);
//        // 初始化账户基础信息
//        //HAccountHelper.init(this);
//        // 初始化网络请求
//        //ApiHttpClient.init(this);
//        //初始化百度地图
//        //SDKInitializer.initialize(this);
//    }
//
//
//    /**
//     * 获得当前app运行的AppContext
//     *
//     * @return AppContext
//     */
//    public static HAppContext getInstance() {
//        return INSTANCE;
//    }
//
//    /**
//     * 获取在AndroidManifest.xml中配置的版本号
//     *
//     * @return
//     */
//    public PackageInfo getPackageInfo() {
//        android.content.pm.PackageInfo pi = null;
//        try {
//            PackageManager pm = getPackageManager();
//            pi = pm.getPackageInfo(getPackageName(), 0);
//        } catch (PackageManager.NameNotFoundException e) {
//        }
//        return pi;
//    }
//
//
//    public String getMetaData(String tag) {
//        String ret = "";
//        try {
//            ret = getPackageManager().getApplicationInfo(
//                    getPackageName(), PackageManager.GET_META_DATA).metaData.getString(tag);
//        } catch (PackageManager.NameNotFoundException e) {
//            e.printStackTrace();
//        }
//        return ret;
//    }
//
//    public void setHasOpenApp(boolean hasOpenApp) {
//        this.hasOpenApp = hasOpenApp;
//    }
//
//    public boolean isHasOpenApp() {
//        return hasOpenApp;
//    }
//
//    @Override
//    public void onTerminate() {
//        super.onTerminate();
//        hasOpenApp = false;
//    }
//
//    /**
//     * 判断当前版本是否兼容目标版本的方法
//     *
//     * @param VersionCode
//     * @return
//     */
//    public static boolean isMethodsCompat(int VersionCode) {
//        int currentVersion = android.os.Build.VERSION.SDK_INT;
//        return currentVersion >= VersionCode;
//    }
//
//    public void set(String key, int value) {
//        SharedPreferences.Editor editor = getPreferences().edit();
//        editor.putInt(key, value);
//        SharedPreferencesCompat.EditorCompat.getInstance().apply(editor);
//
//    }
//
//    public void remove(String key) {
//        SharedPreferences.Editor editor = getPreferences().edit();
//        editor.remove(key);
//        SharedPreferencesCompat.EditorCompat.getInstance().apply(editor);
//    }
//
//    public void set(String key, boolean value) {
//        SharedPreferences.Editor editor = getPreferences().edit();
//        editor.putBoolean(key, value);
//        SharedPreferencesCompat.EditorCompat.getInstance().apply(editor);
//    }
//
//    public void set(String key, String value) {
//        SharedPreferences.Editor editor = getPreferences().edit();
//        editor.putString(key, value);
//        SharedPreferencesCompat.EditorCompat.getInstance().apply(editor);
//    }
//
//    public boolean get(String key, boolean defValue) {
//        return getPreferences().getBoolean(key, defValue);
//    }
//
//    public String get(String key, String defValue) {
//        return getPreferences().getString(key, defValue);
//    }
//
//    public int get(String key, int defValue) {
//        return getPreferences().getInt(key, defValue);
//    }
//
//    public long get(String key, long defValue) {
//        return getPreferences().getLong(key, defValue);
//    }
//
//    public float get(String key, float defValue) {
//        return getPreferences().getFloat(key, defValue);
//    }
//
//    public SharedPreferences getPreferences() {
//        return getApplicationContext().getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
//    }
//
//    /**
//     * 图片模式，文字模式，默认是图片模式
//     *
//     * @return
//     */
//    public boolean isPicMode() {
//        return get(HAppConfig.KEY_PIC_MODE, true);
//    }
//
//    /**
//     * true代表图片模式，false代表文字模式
//     *
//     * @param mode
//     * @return
//     */
//    public void setPicMode(boolean mode) {
//        set(HAppConfig.KEY_PIC_MODE, mode);
//    }
//
//}