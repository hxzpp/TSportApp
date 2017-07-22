package com.xinhuamm.xinhuasdk.util;

import android.text.TextUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class SimpleDate {
    /**
     * 获取当前日期
     * yyyy-MM-dd hh:mm:ss 表示12小时制
     * yyyy-MM-dd HH:mm:ss 表示24小时制
     *
     * @return
     */
    public static String getCurrentDate() {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()).toString();
    }

    public static Date getCurrentDate(String time) {
        try {
            return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String getCurrentMonthDay() {
        return new SimpleDateFormat("MM月dd日").format(new Date()).toString();
    }
//
//    public static String getTomrrowDay(int offset) {
//        return AbDateUtil.getCurrentDateByOffset("MM月dd日", Calendar.DAY_OF_MONTH, offset);
//    }

    /**
     * 获取短日期
     *
     * @return
     */
    public static String getShortDate() {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm").format(new Date()).toString();
    }

    /**
     * 获取短日期
     *
     * @return
     */
    public static String getShortMonthDate(String date) {
        Date time = getCurrentDate(date);
        String formatDate = "";
        if (null != time) {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM-dd HH:mm");
            formatDate = simpleDateFormat.format(time).toString();
        } else {
            return date;
        }
        return formatDate;
    }


    public static String formatSimpleDate(String date) {
        if (TextUtils.isEmpty(date)) return "";
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM-dd");
            Date simpleDate = simpleDateFormat.parse(date);
            simpleDateFormat = new SimpleDateFormat("MM月dd日");
            date = simpleDateFormat.format(simpleDate);
        } catch (ParseException e) {
        }
        return date;
    }

    /**
     * @return
     */
    public static String getSplitDate() {
        String date = "";
        Calendar c = Calendar.getInstance();
        //获取当前年份
        date += c.get(Calendar.YEAR);
        //获取当前月份
        date += (c.get(Calendar.MONTH) + 1);
        //获取当前月份的日期号码
        date += c.get(Calendar.DAY_OF_MONTH);
        //获取当前的小时数
        date += c.get(Calendar.HOUR_OF_DAY);
        //获取当前的分钟数
        date += c.get(Calendar.MINUTE);
        //秒数
        date += c.get(Calendar.SECOND);
        return date;
    }

    /**
     * year/month/day hour:minute
     *
     * @return
     */
    public static String getDate() {
        String date = "";
        Calendar c = Calendar.getInstance();
        //获取当前年份
        date += c.get(Calendar.YEAR) + "/";
        //获取当前月份
        date += (c.get(Calendar.MONTH) + 1) + "/";
        //获取当前月份的日期号码
        date += c.get(Calendar.DAY_OF_MONTH) + " ";
        //获取当前的小时数
        String day = c.get(Calendar.HOUR_OF_DAY) + "";
        day = formatDate(day);
        date += day + ":";
        //获取当前的分钟数
        day = c.get(Calendar.MINUTE) + "";
        day = formatDate(day);
        date += day;
        return date;
    }


    /**
     * month-day hour:minute
     *
     * @return
     */
    public static String getMonthDayDate() {
        String date = "";
        Calendar c = Calendar.getInstance();
        //获取当前月份
        date += formatDate((c.get(Calendar.MONTH) + 1) + "") + "-";
        //获取当前月份的日期号码
        date += formatDate(c.get(Calendar.DAY_OF_MONTH) + "") + "  ";

        //获取当前的小时数
        String day = c.get(Calendar.HOUR_OF_DAY) + "";
        day = formatDate(day);
        date += day + ":";
        //获取当前的分钟数
        day = c.get(Calendar.MINUTE) + "";
        day = formatDate(day);
        date += day;
        return date;
    }

    public static String getSimpleDate() {
        String date = "";
        Calendar c = Calendar.getInstance();
        date += formatDate((c.get(Calendar.MONTH) + 1) + "") + "月";
        //获取当前月份的日期号码
        date += formatDate((c.get(Calendar.DAY_OF_MONTH)) + "") + "日";
        return date;
    }

    public static String formatDate(String date) {
        if (TextUtils.isEmpty(date)) {
            return date;
        }
        if (date.length() == 1) {
            return "0" + date;
        }
        return date;
    }

    /**
     * 获取当前的小时和分
     *
     * @return
     */
    public static String getCurrentHourMinute() {
        String date = "";
        //获取当前的小时数
        Calendar c = Calendar.getInstance();
        String day = c.get(Calendar.HOUR_OF_DAY) + "";
        day = formatDate(day);
        date += day + " : ";
        //获取当前的分钟数
        day = c.get(Calendar.MINUTE) + "";
        day = formatDate(day);
        date += day;
        return date;
    }

    public static boolean isCurrentTimeInBetween(String startTime, String endTime) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm");
        try {
            Date startDate = simpleDateFormat.parse(startTime);
            long startTimeLong = startDate.getTime();

            Date endDate = simpleDateFormat.parse(endTime);
            long endTimeLong = endDate.getTime();

            Date currDate = new Date(System.currentTimeMillis());
            String currTime = simpleDateFormat.format(currDate);
            currDate = simpleDateFormat.parse(currTime);
            long currentTime = currDate.getTime();
            return currentTime >= startTimeLong && currentTime < endTimeLong;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean isOutCurrentTime(String endTime) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm");
        try {
            Date endDate = simpleDateFormat.parse(endTime);
            long endTimeLong = endDate.getTime();

            Date currDate = new Date(System.currentTimeMillis());
            String currTime = simpleDateFormat.format(currDate);
            currDate = simpleDateFormat.parse(currTime);
            long currentTime = currDate.getTime();

            return currentTime > endTimeLong;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean timeCompare(String beforeTime, String endTime) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm");
        try {
            Date beforeDate = simpleDateFormat.parse(beforeTime);
            long beforeTimeLong = beforeDate.getTime();

            Date endDate = simpleDateFormat.parse(endTime);
            long endTimeLong = endDate.getTime();

            return beforeTimeLong > endTimeLong;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return false;
    }


    /**
     * 获取周
     *
     * @return
     */
    public static int getWeek() {
        Calendar c = Calendar.getInstance();
        //获取当前的小时数
        int weekIndex = c.get(Calendar.DAY_OF_WEEK);
        int week = 1;
        switch (weekIndex) {
            case 1:
                week = 7;
                break;
            case 2:
                week = 1;
                break;
            case 3:
                week = 2;
                break;
            case 4:
                week = 3;
                break;
            case 5:
                week = 4;
                break;
            case 6:
                week = 5;
                break;
            case 7:
                week = 6;
                break;
        }
        return week;
    }


    /**
     * 根据时间戳，得到HH:MM:ss的播放时间格式
     */
    public static String getTime(int time) {
        int ss = 1000;
        int mi = ss * 60;
        int hh = mi * 60;
        long hour = (time) / hh;
        long minute = (time - hour * hh) / mi;
        long second = (time - hour * hh - minute * mi) / ss;

        String strHour = hour < 10 ? "0" + hour : "" + hour;
        String strMinute = minute < 10 ? "0" + minute : "" + minute;
        String strSecond = second < 10 ? "0" + second : "" + second;
        if (hour > 0) {
            return strHour + ":" + strMinute + ":" + strSecond;
        } else {
            return strMinute + ":" + strSecond;
        }
    }

    /**
     * @param ms 毫秒
     * @return
     */
    public static String getMSTime(int ms) {
        int s = ms / 1000;
        if (s < 60) {
            String second = s < 10 ? "0" + s : "" + s;
            return "00:" + second;
        } else {
            int m = s / 60;
            s = s - m * 60;
            String strM = m < 10 ? "0" + m : "" + m;
            String strS = s < 10 ? "0" + s : "" + s;
            return strM + ":" + strS;
        }

    }

    public static String getYMTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
        return sdf.format(new java.util.Date());
    }

    public static String getYMDTime_() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(new java.util.Date());
    }

    public static String getYMDateTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("yy-MM-dd-HH-mm-ss");
        return sdf.format(new java.util.Date());

    }

    public static String getShowTime(long milliseconds) {
        // 获取日历函数
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(milliseconds);
        SimpleDateFormat dateFormat = null;
        // 判断是否大于60分钟，如果大于就显示小时。设置日期格式
        if (milliseconds / 60000 > 60) {
            dateFormat = new SimpleDateFormat("hh:mm:ss");
        } else {
            dateFormat = new SimpleDateFormat("00:mm:ss");
        }
        return dateFormat.format(calendar.getTime());
    }

    /**
     * 初始化生日对应的calender对象，方便提取年月日的信息
     *
     * @param birthday yyyy-MM-dd格式的字符串
     * @return
     */
    public static Calendar initBirthdayDate(String birthday) {
        if (birthday == null || birthday.equals("")) {
            birthday = "1990-01-01";
        }

        Date date = null;
        try {
            date = new SimpleDateFormat("yyyy-MM-dd").parse(birthday);
        } catch (ParseException e) {
            e.printStackTrace();
            date = new Date(1990, 01, 01);
        }
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return c;
    }
}
