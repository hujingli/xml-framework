package com.xmlframework.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * 时间工具类
 */
public class TimeUtil {

    /**
     * 获取当前日期
     * @return
     */
    public static String getDate() {
        Calendar calendar = Calendar.getInstance();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

        return sdf.format(calendar.getTime());
    }

    /**
     * 获取当前时间
     * @return
     */
    public static String getTime() {
        Calendar calendar = Calendar.getInstance();

        SimpleDateFormat sdf = new SimpleDateFormat("HHmmss");

        return sdf.format(calendar.getTime());
    }

}
