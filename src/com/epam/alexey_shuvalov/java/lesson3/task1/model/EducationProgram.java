package com.epam.alexey_shuvalov.java.lesson3.task1.model;

import com.epam.alexey_shuvalov.java.lesson3.task1.EducationUtils;

import java.util.Calendar;
import java.util.Date;

/**
 * @author Alexey Shuvalov
 *
 */
public class EducationProgram implements Trackable {
    public static final int STARTING_HOUR_OF_EDUCATION_DAY = 10;
    public static final int ENDING_HOUR_OF_EDUCATION_DAY = 18;
    public String studentName;
    public String programName;
    public Course[] courseScope;
    public Calendar educationCalendar;
    public Calendar completionDate;

    public Calendar getEducationCalendar() {
        return educationCalendar;
    }

    public void setEducationCalendar(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        this.educationCalendar = calendar;
        setEndDate();
    }

    @Override
    public Calendar getCompletionDate() {
        return completionDate;
    }

    public EducationProgram(String studentName) {
        this.studentName = studentName;
    }

    public EducationProgram(String studentName, String programName, Course[] courseScope) {
        this.studentName = studentName;
        this.programName = programName;
        this.courseScope = courseScope;
    }

    public EducationProgram(Student student) {
        this.studentName = student.toString();
    }

    @Override
    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    @Override
    public String getProgramName() {
        return programName;
    }

    @Override
    public String[] getEducationHours() {
        String[] educationHours = new String[2];
        educationHours[0] = Integer.toString(STARTING_HOUR_OF_EDUCATION_DAY);
        educationHours[1] = Integer.toString(ENDING_HOUR_OF_EDUCATION_DAY);
        return educationHours;
    }

    public void setProgramName(String programName) {
        this.programName = programName;
    }

    public Course[] getCourseScope() {
        return courseScope;
    }

    public void setCourseScope(Course[] courseScope) {
        this.courseScope = courseScope;
    }

    @Override
    public long getProgramLength() {
        long programLength = 0;
        for (Course aCourseScope : courseScope) {
            if (aCourseScope != null) {
                programLength += aCourseScope.getCourseDuration();
            }
        }
        return programLength;
    }

    @Override
    public String getStartDate() {
        return EducationUtils.convertDateToString(educationCalendar.getTime());
    }

    @Override
    public String getEndDate() {
        return EducationUtils.convertDateToString(completionDate.getTime());
    }

    public void setEndDate() {
        Calendar endDateCalendar = Calendar.getInstance();
        endDateCalendar.setTime(getEducationCalendar().getTime());
        this.completionDate = calculateEndDate(endDateCalendar);
    }

    public Calendar calculateEndDate(Calendar endDateCalendar) {
        /**
         * Calculates endDateCalendar by looping through programDuration (in minutes) 
         * over 'dead' calendar (where all fields are undefined). Adds minutes 
         * within STARTING_HOUR_OF_EDUCATION_DAY .. ENDING_HOUR_OF_EDUCATION_DAY.
         * 
         * @param endDateCalendar is a copy of calendar with Start Date specified
         * @return Calendar with education hours
         */
        int programDuration = (int)getProgramLength() * 60;
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
         * STARTING_HOUR_OF_EDUCATION_DAY..ENDING_HOUR_OF_EDUCATION_DAY range to undefined.
         * 
         * This means that isSet() will return false for all the educationCalendar fields,
         * and the date and time calculations will treat the fields as if they had never been set.
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
