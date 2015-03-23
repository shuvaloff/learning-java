package com.epam.alexey_shuvalov.java.lesson3.task1;

import com.epam.alexey_shuvalov.java.lesson3.task1.model.Trackable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
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

    public static void printDateDiff(long difference) {
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

    public static void generateShortReport(Trackable[] statistics) {
        Calendar current = Calendar.getInstance();
        for (Trackable educationProgram : statistics) {
            if (educationProgram != null) {
                if (educationProgram.getEndDateCalendar().before(current)) {
                    long diff = getDateDiff(educationProgram.getEndDateCalendar().getTime(), current.getTime(), TimeUnit.MILLISECONDS);
                    System.out.print(educationProgram.getStudentName() + " (" + educationProgram.getProgramName()
                            + ") - Education program is completed. The time has passed since completion: ");
                    EducationUtils.printDateDiff(diff);
                } else {
                    long diff = getDateDiff(current.getTime(), educationProgram.getEndDateCalendar().getTime(), TimeUnit.MILLISECONDS);
                    System.out.print(educationProgram.getStudentName() + " (" + educationProgram.getProgramName()
                            + ") - Education program is not completed. The time remaining until completion: ");
                    EducationUtils.printDateDiff(diff);
                }
            }
        }
    }

    public static void generateFullReport(Trackable[] statistics) {
        Calendar current = Calendar.getInstance();
        for (Trackable educationProgram : statistics) {
            if (educationProgram != null) {
                if (educationProgram.getEndDateCalendar().before(current)) {
                    long diff = getDateDiff(educationProgram.getEndDateCalendar().getTime(), current.getTime(), TimeUnit.MILLISECONDS);
                    String completedOutput = new StringBuilder()
                            .append("\n")
                            .append(educationProgram.getStudentName())
                            .append(" has completed '")
                            .append(educationProgram.getProgramName())
                            .append("' education program. Education hours were on weekdays from ")
                            .append(educationProgram.getEducationHours()[0])
                            .append(" to ")
                            .append(educationProgram.getEducationHours()[1])
                            .append(".\nThe total length of education program is ")
                            .append(educationProgram.getProgramLength())
                            .append(" hours. Start date is ")
                            .append(educationProgram.getStartDate())
                            .append(", and end date is ")
                            .append(educationProgram.getEndDate())
                            .append(".\nThe time has passed since completion: ")
                            .toString();
                    System.out.print(completedOutput);
                    printDateDiff(diff);
                } else {
                    long diff = getDateDiff(current.getTime(), educationProgram.getEndDateCalendar().getTime(), TimeUnit.MILLISECONDS);
                    System.out.format("%n%s is studying '%s' education program. Education hours are on weekdays from %s to %s.%n"
                            + "The total length of education program is %s hours. Start date is %s, and end date is %s.%n"
                            + "The time remaining until completion: ",
                            educationProgram.getStudentName(), educationProgram.getProgramName(),
                            educationProgram.getEducationHours()[0], educationProgram.getEducationHours()[1],
                            educationProgram.getProgramLength(), educationProgram.getStartDate(),
                            educationProgram.getEndDate());
                    printDateDiff(diff);
                }
            }
        }
    }

}
