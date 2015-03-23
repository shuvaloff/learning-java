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
        Statistics statistics = new Statistics(educationProgram, student, startDate, endDate, startDateCalendar, endDateCalendar);
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
        skipNonEducationHours(endDateCalendar); // clear() endDateCalendar from non education hours
        while (programDuration > 0) { // while total program duration in minutes greater than zero do
            Calendar educationEndTime = Calendar.getInstance(); // create new instance of calendar 
            educationEndTime.setTime(endDateCalendar.getTime()); // set time to the same with endDateCalendar
            educationEndTime.set(Calendar.HOUR_OF_DAY, ENDING_HOUR_OF_EDUCATION_DAY); // set HOUR_OF_DAY to 18
            educationEndTime.clear(Calendar.MINUTE); // set all the time (minutes) to undefined -- cannot be used in time calculations
            long diffMillis = educationEndTime.getTimeInMillis() - endDateCalendar.getTimeInMillis(); // calculating difference between 10 and 18 hours in millis
            int diffMinutes = (int) (diffMillis / (1000 * 60)); // converting difference to minutes
            if (programDuration < diffMinutes) { // if total program duratin is less then working hours of the day
                endDateCalendar.add(Calendar.MINUTE, programDuration); // then add whole programDuration and exit loop
                break;
            } else {
                endDateCalendar.add(Calendar.MINUTE, diffMinutes); // else add working hours
                skipNonEducationHours(endDateCalendar); // clear() endDateCalendar from non education hours
                programDuration -= diffMinutes; // decrease total program duration by working hours of the 'current' day
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
