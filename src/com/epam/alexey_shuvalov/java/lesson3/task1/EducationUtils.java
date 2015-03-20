package com.epam.alexey_shuvalov.java.lesson3.task1;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author Alexey Shuvalov
 */
public class EducationUtils {
    public static final String SIMPLE_DATE_TIME_FORMAT = "dd.MM.yyyy HH:mm:ss";
    public static final String SIMPLE_DATE_FORMAT = "dd.MM.yyyy";
    public static final String SIMPLE_TIME_FORMAT = "HH:mm:ss";

    public static Date convertStringToDateTime(String string) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(SIMPLE_DATE_TIME_FORMAT);
        try {
            return simpleDateFormat.parse(string);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Date convertStringToTime(String string) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(SIMPLE_TIME_FORMAT);
        try {
            Date date = simpleDateFormat.parse(string);
            date.getTime();
            return date;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Long convertStringToTimeLong(String string) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(SIMPLE_TIME_FORMAT);
        try {
            Date date = simpleDateFormat.parse(string);
            return date.getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String convertTimeToString(Date date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(SIMPLE_TIME_FORMAT);
        return simpleDateFormat.format(date);
    }

    public static String convertDateToString(Date date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(SIMPLE_DATE_FORMAT);
        return simpleDateFormat.format(date);
    }

    public static String convertDateTimeToString(Date date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(SIMPLE_DATE_TIME_FORMAT);
        return simpleDateFormat.format(date);
    }

    public static Long convertStringToDateTimeLong(String string) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(SIMPLE_DATE_TIME_FORMAT);
        try {
            Date date = simpleDateFormat.parse(string);
            return date.getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static long getDateDiff(Date date1, Date date2, TimeUnit timeUnit) {
        long diffInMillies = date2.getTime() - date1.getTime();
        return timeUnit.convert(diffInMillies, TimeUnit.MILLISECONDS);
    }
    
    public static void printDateDiff(long difference){
        long secondsInMilli = 1000;
        long minutesInMilli = secondsInMilli * 60;
        long hoursInMilli = minutesInMilli * 60;
        long daysInMilli = hoursInMilli * 24;

        long days = difference / daysInMilli;
        difference = difference % daysInMilli;

        long hours = difference / hoursInMilli;
        difference = difference % hoursInMilli;

        long minutes = difference / minutesInMilli;

        System.out.printf("%d day(s), %d hour(s), %d minute(s)%n",
            days, hours, minutes);
    }
}
