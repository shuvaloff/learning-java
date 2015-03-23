package com.epam.alexey_shuvalov.java.lesson3.task1;

import com.epam.alexey_shuvalov.java.lesson3.task1.model.EducationProgram;
import com.epam.alexey_shuvalov.java.lesson3.task1.model.Statistics;
import com.epam.alexey_shuvalov.java.lesson3.task1.model.Student;
import java.util.Calendar;
import java.util.Date;

/**
 * @author Alexey Shuvalov
 * 
 */
public class EducationCenter {
    
    public static final int STARTING_HOUR_OF_EDUCATION_DAY = 10;
    public static final int ENDING_HOUR_OF_EDUCATION_DAY = 18;
    
    public static Statistics getStatistics(EducationProgram educationProgram, Student student, Date startDate) {
        Calendar startDateCalendar = Calendar.getInstance();
        startDateCalendar.setTime(startDate);
        int programDuration = (int) educationProgram.getProgramLength() * 60;
        Calendar endDateCalendar = Calendar.getInstance();
        endDateCalendar.setTime(startDateCalendar.getTime());
        endDateCalendar = calculateEndDate(endDateCalendar, programDuration);
        Date endDate = endDateCalendar.getTime();
        Statistics statistics = new Statistics(educationProgram, student, startDate, endDate);
        return statistics;
    }
    
        private static Calendar calculateEndDate(Calendar endDateCalendar, int programDuration) {
        /**
         * Calculates endDateCalendar by looping through programDuration (in
         * minutes) over 'dead' calendar (where all fields are undefined). Adds
         * minutes within STARTING_HOUR_OF_EDUCATION_DAY ..
         * ENDING_HOUR_OF_EDUCATION_DAY.
         *
         * @param endDateCalendar is a copy of calendar with Start Date
         * specified
         * @return Calendar with education hours
         */
        skipNonEducationHours(endDateCalendar);
        while (programDuration > 0) {
            Calendar educationEndTime = Calendar.getInstance();
            educationEndTime.setTime(endDateCalendar.getTime());
            educationEndTime.set(Calendar.HOUR_OF_DAY, ENDING_HOUR_OF_EDUCATION_DAY);
            educationEndTime.clear(Calendar.MINUTE);
            long diffMillis = educationEndTime.getTimeInMillis() - endDateCalendar.getTimeInMillis();
            int diffMinutes = (int) (diffMillis / (1000 * 60));
            if (programDuration < diffMinutes) {
                endDateCalendar.add(Calendar.MINUTE, programDuration);
                break;
            } else {
                endDateCalendar.add(Calendar.MINUTE, diffMinutes);
                skipNonEducationHours(endDateCalendar);
                programDuration -= diffMinutes;
            }
        }
        return endDateCalendar;
    }

    private static void skipNonEducationHours(Calendar deadCalendar) {
        /**
         * Sets all the educationCalendar field values (minutes) outside of
         * STARTING_HOUR_OF_EDUCATION_DAY..ENDING_HOUR_OF_EDUCATION_DAY range to
         * undefined.
         *
         * This means that isSet() will return false for all the
         * educationCalendar fields, and the date and time calculations will
         * treat the fields as if they had never been set.
         *
         * @param deadCalendar
         */
        int currentHour = deadCalendar.get(Calendar.HOUR_OF_DAY);
        if (currentHour < STARTING_HOUR_OF_EDUCATION_DAY) {
            deadCalendar.set(Calendar.HOUR_OF_DAY, STARTING_HOUR_OF_EDUCATION_DAY);
            deadCalendar.clear(Calendar.MINUTE);
        }
        if (currentHour >= ENDING_HOUR_OF_EDUCATION_DAY) {
            deadCalendar.add(Calendar.DATE, 1);
            deadCalendar.set(Calendar.HOUR_OF_DAY, STARTING_HOUR_OF_EDUCATION_DAY);
            deadCalendar.clear(Calendar.MINUTE);
        }
    }
    
}