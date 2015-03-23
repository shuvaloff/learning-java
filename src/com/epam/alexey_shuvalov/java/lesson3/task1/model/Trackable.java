package com.epam.alexey_shuvalov.java.lesson3.task1.model;

import java.util.Calendar;

/**
 * @author Alexey Shuvalov
 *         Created on 20.03.2015.
 */
public interface Trackable {
    String getStudentName();
    String getProgramName();
    String getStartDate();
    String getEndDate();
    long getProgramLength();
    Calendar getEndDateCalendar();
}
