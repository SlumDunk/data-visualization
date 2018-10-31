package com.uncc.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * @Author: zerongliu
 * @Date: 10/31/18 09:21
 * @Description:
 */
public class DateUtil {

    public static final String DEFAULT_DATE_PATTERN = "MMM d, yyyy";

    /**
     * transfer a string to date
     *
     * @param datePattern
     * @param dateStr
     * @return
     */
    public static Date parseStr2Date(String datePattern, String dateStr) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(datePattern, Locale.ENGLISH);
        try {
            return dateFormat.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * transfer a string to date
     *
     * @param datePattern
     * @param date
     * @return
     */
    public static String parseDate2Str(String datePattern, Date date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(datePattern, Locale.ENGLISH);
        return dateFormat.format(date);
    }
}
