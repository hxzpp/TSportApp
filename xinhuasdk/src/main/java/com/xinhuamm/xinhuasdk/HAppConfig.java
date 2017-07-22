//package com.xinhuamm.xinhuasdk;
//
//import android.content.Context;
//import android.os.Environment;
//
//import java.io.File;
//
///**
// * 应用程序配置类
// * 用于保存用户相关信息及设置
// */
//public class HAppConfig {
//
//
//    private final static String APP_CONFIG = "config";
//
//    //默认是图片模式，点击关闭变成文字模式
//    public static final String KEY_PIC_MODE = "KEY_PIC_MODE";
//
//    //字体大小信息0-5
//    public static final String KEY_FONT_MODE = "KEY_FONT_MODE";
//    //字体大小表示的汉字
//    public static final String KEY_FONT_KEY_MODE = "KEY_FONT_KEY_MODE";
//
//    public static final String KEY_LOAD_IMAGE = "KEY_LOAD_IMAGE";
//    public static final String KEY_NOTIFICATION_DISABLE_WHEN_EXIT = "KEY_NOTIFICATION_DISABLE_WHEN_EXIT";
//    public static final String KEY_CHECK_UPDATE = "KEY_CHECK_UPDATE";
//    public static final String KEY_DOUBLE_CLICK_EXIT = "KEY_DOUBLE_CLICK_EXIT";
//
//    //app的名字
//    private String APP_NAME;
//    // 默认存放图片的路径
//    private String DEFAULT_SAVE_IMAGE_PATH;
//
//    // 默认存放文件下载的路径
//    private String DEFAULT_SAVE_FILE_PATH;
//
//    // 默认存放视频的路径
//    private String DEFAULT_SAVE_VIDEO_PATH;
//
//    // 默认存放语音的路径
//    private String DEFAULT_SAVE_AUDIO_PATH;
//
//    //头像存放路径
//    private String DEFAULT_SAVE_HEAD_PATH;
//
//    //apk下载保存路径
//    private String DEFAULT_SAVE_APK_PATH;
//
//
//    private static HAppConfig INSTANCE = new HAppConfig();
//    private Context mContext;
//
//    private HAppConfig() {
//    }
//
//    public static HAppConfig getInstance() {
//        return INSTANCE;
//    }
//
//    public void init(Context context) {
//        mContext = context;
//        APP_NAME = context.getString(R.string.app_name);
//
//        DEFAULT_SAVE_IMAGE_PATH = Environment
//                .getExternalStorageDirectory()
//                + File.separator
//                + APP_NAME
//                + File.separator + "image" + File.separator;
//
//        DEFAULT_SAVE_FILE_PATH = Environment
//                .getExternalStorageDirectory()
//                + File.separator
//                + APP_NAME
//                + File.separator + "download" + File.separator;
//
//        DEFAULT_SAVE_VIDEO_PATH = Environment
//                .getExternalStorageDirectory()
//                + File.separator
//                + APP_NAME
//                + File.separator + "video" + File.separator;
//
//        DEFAULT_SAVE_AUDIO_PATH = Environment
//                .getExternalStorageDirectory()
//                + File.separator
//                + APP_NAME
//                + File.separator + "audio" + File.separator;
//
//        DEFAULT_SAVE_HEAD_PATH = Environment
//                .getExternalStorageDirectory()
//                + File.separator
//                + APP_NAME
//                + File.separator + "head" + File.separator;
//
//        DEFAULT_SAVE_APK_PATH=Environment
//                .getExternalStorageDirectory()
//                + File.separator
//                + APP_NAME
//                + File.separator + "apk"+ File.separator;
//    }
//
//    /**
//     *  默认存放图片的路径
//     * @return
//     */
//    public String getDefaultSaveImagePath() {
//        return DEFAULT_SAVE_IMAGE_PATH;
//    }
//
//    /**
//     * 默认存放文件下载的路径
//     * @return
//     */
//    public String getDefaultSaveFilePath() {
//        return DEFAULT_SAVE_FILE_PATH;
//    }
//
//    /**
//     * 默认存放视频的路径
//     * @return
//     */
//    public String getDefaultSaveVideoPath() {
//        return DEFAULT_SAVE_VIDEO_PATH;
//    }
//
//    /**
//     * 默认存放语音的路径
//     * @return
//     */
//    public String getDefaultSaveAudioPath() {
//        return DEFAULT_SAVE_AUDIO_PATH;
//    }
//
//
//    public String getDefaultSaveHeadPath() {
//        return DEFAULT_SAVE_HEAD_PATH;
//    }
//
//    public String getDefaultSaveApkPath(){
//        return DEFAULT_SAVE_APK_PATH;
//    }
//}
