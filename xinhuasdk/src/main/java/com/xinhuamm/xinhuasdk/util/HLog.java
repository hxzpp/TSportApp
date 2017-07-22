package com.xinhuamm.xinhuasdk.util;

import android.util.Log;

import com.xinhuamm.xinhuasdk.BuildConfig;

/**
 * 简单的分装日志打印
 * 打印日志统一都用这个，方便管理
 */
public class HLog {
    private static final String LOG_TAG = "XinhuaSDKLog";
    private static boolean DEBUG = BuildConfig.DEBUG;

    private HLog() {
    }

    public static void error(String log) {
        if (DEBUG) Log.e(LOG_TAG, "" + log);
    }

    public static void log(String log) {
        if (DEBUG) Log.i(LOG_TAG, log);
    }

    public static void log(String tag, String log) {
        if (DEBUG) Log.i(tag, log);
    }

    public static void d(String tag, String log) {
        if (DEBUG) Log.d(tag, log);
    }

    public static void e(String tag, String log) {
        if (DEBUG) Log.e(tag, log);
    }

    public static void i(String tag, String log) {
        if (DEBUG) Log.i(tag, log);
    }
}
