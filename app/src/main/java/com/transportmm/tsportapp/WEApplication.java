package com.transportmm.tsportapp;

import com.squareup.leakcanary.LeakCanary;
import com.xinhuamm.xinhuasdk.base.HBaseApplication;

import timber.log.Timber;

/**
 * Created by bill on 17/7/20.
 */

public class WEApplication extends HBaseApplication {

    @Override
    public void onCreate() {
        super.onCreate();
        if (BuildConfig.LOG_DEBUG) {//Timber日志打印
            Timber.plant(new Timber.DebugTree());
        }
        if (BuildConfig.USE_CANARY) {//leakCanary内存泄露检查
            LeakCanary.install(this);
        }
    }
}