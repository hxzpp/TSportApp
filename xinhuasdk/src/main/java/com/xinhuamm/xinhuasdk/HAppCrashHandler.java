//package com.xinhuamm.xinhuasdk;
//
//import android.content.Context;
//import android.os.Looper;
//import android.widget.Toast;
//
///**
// * 捕获程序未知异常，使程序报错时，交互更加友好
// */
//
//public class HAppCrashHandler implements Thread.UncaughtExceptionHandler {
//    public static final String TAG = "CrashHandler";
//
//    private Thread.UncaughtExceptionHandler mDefaultHandler;
//    private static HAppCrashHandler INSTANCE = new HAppCrashHandler();
//    private Context mContext;
//
//    private HAppCrashHandler() {
//    }
//
//    public static HAppCrashHandler getInstance() {
//        return INSTANCE;
//    }
//
//    public void init(Context context) {
//        mContext = context;
//        mDefaultHandler = Thread.getDefaultUncaughtExceptionHandler();
//        Thread.setDefaultUncaughtExceptionHandler(this);
//    }
//
//    @Override
//    public void uncaughtException(Thread thread, Throwable ex) {
//        if (mDefaultHandler != null && (BuildConfig.DEBUG || (!handleException(ex)))) {
//            mDefaultHandler.uncaughtException(thread, ex);
//        } else {
//            android.os.Process.killProcess(android.os.Process.myPid());
//            System.exit(1);
//        }
//    }
//
//    private boolean handleException(Throwable ex) {
//        if (ex == null) {
//            return false;
//        }
//        ex.printStackTrace();
//
//
//        new Thread() {
//            @Override
//            public void run() {
//                Looper.prepare();
//                Toast.makeText(mContext, "程序异常；正准备重启！！", Toast.LENGTH_LONG).show();
//                Looper.loop();
//            }
//        }.start();
//
//        try {
//            Thread.sleep(500);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//
//        return true;
//    }
//}
