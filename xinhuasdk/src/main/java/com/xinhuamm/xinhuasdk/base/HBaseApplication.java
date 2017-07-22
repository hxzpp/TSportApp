package com.xinhuamm.xinhuasdk.base;

import android.content.Context;

import com.facebook.stetho.Stetho;
import com.tencent.bugly.crashreport.CrashReport;
import com.xinhuamm.xinhuasdk.BuildConfig;
import com.xinhuamm.xinhuasdk.base.delegate.AppDelegate;
import com.xinhuamm.xinhuasdk.base.delegate.AppLifecycles;
import com.xinhuamm.xinhuasdk.di.component.AppComponent;
import com.xinhuamm.xinhuasdk.util.HToast;
import com.xinhuamm.xinhuasdk.utils.DeviceUtils;

/**
 * 大家都要继承这个application，
 * 本项目由
 * mvp
 * +dagger2
 * +retrofit
 * +rxjava
 * +androideventbus
 * +butterknife组成
 * 请配合官方wiki文档https://github.com/JessYanCoding/MVPArms/wiki,学习本框架
 */
public class HBaseApplication extends android.support.multidex.MultiDexApplication implements App {

    private static HBaseApplication INSTANCE;

    private AppLifecycles mAppDelegate;

    public static HBaseApplication getInstance() {
        return INSTANCE;
    }

    /**
     * 这里会在 {@link HBaseApplication#onCreate} 之前被调用,可以做一些较早的初始化
     * 常用于 MultiDex 以及插件化框架的初始化
     *
     * @param base
     */
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        this.mAppDelegate = new AppDelegate(base);
        this.mAppDelegate.attachBaseContext(base);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        this.mAppDelegate.onCreate(this);
        INSTANCE = this;
        init();
    }

    private void init() {
        // 多进程导致多次初始化Application,这里只初始化App主进程的Application
        String curProcessName = DeviceUtils.getProcessName(this, android.os.Process.myPid());
        if (curProcessName.equals(getPackageName())) {//主进程
            //初始化图片选择器
//            BoxingMediaLoader.getInstance().init(new BoxingFrescoLoader());
//            BoxingCrop.getInstance().init(new BoxingUcrop());
            //初始化吐司
            HToast.init(getApplicationContext());
        }

        Stetho.initializeWithDefaults(this);
        //腾讯Bugly初始化
        CrashReport.initCrashReport(this, BuildConfig.BUGLY_APPID, false);
    }


    /**
     * 在模拟环境中程序终止时会被调用
     */
    @Override
    public void onTerminate() {
        super.onTerminate();
        if (mAppDelegate != null)
            this.mAppDelegate.onTerminate(this);
    }

    /**
     * 将AppComponent返回出去,供其它地方使用, AppComponent接口中声明的方法返回的实例,在getAppComponent()拿到对象后都可以直接使用
     *
     * @return
     */
    @Override
    public AppComponent getAppComponent() {
        return ((App) mAppDelegate).getAppComponent();
    }

}
