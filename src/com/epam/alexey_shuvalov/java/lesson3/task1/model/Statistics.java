package com.epam.alexey_shuvalov.java.lesson3.task1.model;

import com.epam.alexey_shuvalov.java.lesson3.task1.EducationUtils;
import java.util.Calendar;
import java.util.Date;

/**
 * @author Alexey Shuvalov
 * 
 */
public class Statistics implements Trackable {

    private static final int STARTING_HOUR_OF_EDUCATION_DAY = 10;
    private static final int ENDING_HOUR_OF_EDUCATION_DAY = 18;
    private EducationProgram educationProgram;
    private Student student;
    private final Date startDate;
    private Date endDate;
    private Calendar startDateCalendar;
    private Calendar endDateCalendar;

    public Statistics(EducationProgram educationProgram, Student student, Date startDate) {
        this.educationProgram = educationProgram;
        this.student = student;
        this.startDate = startDate;
        setStartDate();
    }

    public EducationProgram getEducationProgram() {
        return educationProgram;
    }

    public void setEducationProgram(EducationProgram educationProgram) {
        this.educationProgram = educationProgram;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    @Override
    public String getStartDate() {
        return EducationUtils.convertDateToString(startDateCalendar.getTime());
    }

    private void setStartDate() {
        Calendar startDateCalendar = Calendar.getInstance();
        startDateCalendar.setTime(startDate);
        this.startDateCalendar = startDateCalendar;
        setEndDate();
    }

    @Override
    public String getEndDate() {
        return EducationUtils.convertDateToString(endDate);
    }

    private void setEndDate() {
        Calendar endDateCalendar = Calendar.getInstance();
        endDateCalendar.setTime(getStartDateCalendar().getTime());
        this.endDateCalendar = calculateEndDate(endDateCalendar);
        this.endDate = this.endDateCalendar.getTime();
    }

    @Override
    public String getStudentName() {
        return student.toString();
    }

    @Override
    public String getProgramName() {
        return educationProgram.getProgramName();
    }

    @Override
    public String[] getEducationHours() {
        String[] educationHours = new String[2];
        educationHours[0] = Integer.toString(STARTING_HOUR_OF_EDUCATION_DAY);
        educationHours[1] = Integer.toString(ENDING_HOUR_OF_EDUCATION_DAY);
        return educationHours;
    }

    @Override
    public long getProgramLength() {
        long programLength = 0;
        for (Course aCourseScope : educationProgram.getCourseScope()) {
            if (aCourseScope != null) {
                programLength += aCourseScope.getCourseDuration();
            }
        }
        return programLength;
    }

    @Override
    public Calendar getEndDateCalendar() {
        return endDateCalendar;
    }

    private Calendar calculateEndDate(Calendar endDateCalendar) {
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
        int programDuration = (int) getProgramLength() * 60; // total program duration in minutes
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

    private void skipNonEducationHours(Calendar deadCalendar) {
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

    private Calendar getStartDateCalendar() {
        return startDateCalendar;
    }

}
