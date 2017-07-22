package com.xinhuamm.xinhuasdk.util;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.text.TextUtils;

import java.util.List;

/**
 * @author Zhoujin
 * @说明 应用包工具类
 * @时间 2014-5-1
 */
public class PackageUtil {
    /**
     * 获取在AndroidManifest.xml中配置的版本号
     *
     * @return
     */
    private static PackageInfo getPackageInfo(Context context) {
        android.content.pm.PackageInfo pi = null;
        try {
            PackageManager pm = context.getPackageManager();
            pi = pm.getPackageInfo(context.getPackageName(), 0);
        } catch (NameNotFoundException e) {
        }
        return pi;
    }

    /**
     * 读取mainfest文件中的变量值
     *
     * @param context
     * @param key
     * @return
     */
    public static String getMataValue(Context context, String key) {
        if (key == null) {
            return null;
        }
        try {
            ApplicationInfo appInfo = context.getPackageManager()
                    .getApplicationInfo(getPackageName(context),
                            PackageManager.GET_META_DATA);
            return appInfo.metaData.getString(key);
        } catch (NameNotFoundException e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * 获取应用包名
     *
     * @return
     */
    public static String getPackageName(Context context) {
        PackageInfo pkgInfo = getPackageInfo(context);
        return pkgInfo.packageName;
    }

    /**
     * 获取版本号
     *
     * @return
     */
    public static String getVersionName(Context context) {
        PackageInfo pkgInfo = getPackageInfo(context);
        return pkgInfo.versionName;
    }

    public static int getVersionCode(Context context) {
        PackageInfo pkgInfo = getPackageInfo(context);
        return pkgInfo.versionCode;
    }

    /**
     * 判断客户端是否安装了某个APP
     * @param context
     * @param targetPackageName   要判定的APP的名字
     * @return
     */
    public static boolean installedApp(Context context,String targetPackageName)
    {
        if(TextUtils.isEmpty(targetPackageName))
        {
            return false;
        }
        // 获取packagemanager
        final PackageManager packageManager = context.getPackageManager();
        // 获取所有已安装程序的包信息
        List<PackageInfo> packageInfos = packageManager.getInstalledPackages(0);
        // 从pinfo中将包名字逐一取出，压入pName list中
        if (packageInfos != null) {
            for (int i = 0; i < packageInfos.size(); i++) {
                String packName = packageInfos.get(i).packageName;
                if(packName!=null&&packName.equals(targetPackageName))
                {
                    return true;
                }
            }
        }
        // 判断packageNames中是否有目标程序的包名，有TRUE，没有FALSE
        return false;
    }

}
