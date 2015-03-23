package com.epam.alexey_shuvalov.java.lesson3.task1;

import com.epam.alexey_shuvalov.java.lesson3.task1.model.Trackable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

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
        }
        return null;
    }

    public static Long convertStringToTimeLong(String string) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(SIMPLE_TIME_FORMAT);
        try {
            Date date = simpleDateFormat.parse(string);
            return date.getTime();
        } catch (ParseException e) {
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
        }
        return null;
    }

    public static long getDateDiff(Date date1, Date date2) {
        return date2.getTime() - date1.getTime();
    }

    public static void printDateDiff(long difference) {
        long secondsInMilli = 1000;
        long minutesInMilli = secondsInMilli * 60;
        long hoursInMilli = minutesInMilli * 60;
        long daysInMilli = hoursInMilli * 24;

        long days = Math.abs(difference / daysInMilli);
        difference = difference % daysInMilli;

        long hours = Math.abs(difference / hoursInMilli);
        difference = difference % hoursInMilli;

        long minutes = Math.abs(difference / minutesInMilli);

        System.out.printf("%d day(s), %d hour(s), %d minute(s)%n",
                days, hours, minutes);
    }

    public static void generateShortReport(Trackable[] statistics) {
        Calendar current = Calendar.getInstance();
        for (Trackable stat : statistics) {
            if (stat != null) {
                if (stat.getEndDateCalendar().before(current)) {
                    System.out.print(stat.getStudentName() + " (" + stat.getProgramName()
                            + ") - Education program is completed. The time has passed since completion: ");
                    EducationUtils.printDateDiff(getDateDiff(current.getTime(), stat.getEndDateCalendar().getTime()));
                } else {
                    System.out.print(stat.getStudentName() + " (" + stat.getProgramName()
                            + ") - Education program is not completed. The time remaining until completion: ");
                    EducationUtils.printDateDiff(getDateDiff(current.getTime(), stat.getEndDateCalendar().getTime()));
                }
            }
        }
    }

    public static void generateFullReport(Trackable[] statistics) {
        Calendar current = Calendar.getInstance();
        for (Trackable stat : statistics) {
            if (stat != null) {
                if (stat.getEndDateCalendar().before(current)) {
                    String completedOutput = new StringBuilder()
                            .append("\n")
                            .append(stat.getStudentName())
                            .append(" has completed '")
                            .append(stat.getProgramName())
                            .append("' education program. Education hours were on weekdays from ")
                            .append(EducationCenter.STARTING_HOUR_OF_EDUCATION_DAY)
                            .append(" to ")
                            .append(EducationCenter.ENDING_HOUR_OF_EDUCATION_DAY)
                            .append(".\nThe total length of education program is ")
                            .append(stat.getProgramLength())
                            .append(" hours. Start date is ")
                            .append(stat.getStartDate())
                            .append(", and end date is ")
                            .append(stat.getEndDate())
                            .append(".\nThe time has passed since completion: ")
                            .toString();
                    System.out.print(completedOutput);
                    printDateDiff(getDateDiff(current.getTime(), stat.getEndDateCalendar().getTime()));
                } else {
                    System.out.format("%n%s is studying '%s' education program. Education hours are on weekdays from %s to %s.%n"
                            + "The total length of education program is %s hours. Start date is %s, and end date is %s.%n"
                            + "The time remaining until completion: ",
                            stat.getStudentName(), stat.getProgramName(),
                            EducationCenter.STARTING_HOUR_OF_EDUCATION_DAY, EducationCenter.ENDING_HOUR_OF_EDUCATION_DAY,
                            stat.getProgramLength(), stat.getStartDate(),
                            stat.getEndDate());
                    printDateDiff(getDateDiff(current.getTime(), stat.getEndDateCalendar().getTime()));
                }
            }
        }
    }

}
