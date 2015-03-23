package com.epam.alexey_shuvalov.java.lesson3.task1.model;

import java.util.Calendar;
import java.util.Date;

/**
 * @author Alexey Shuvalov
 *         Created on 20.03.2015.
 */
public interface Trackable {
    String getStudentName();
    String getProgramName();
    String getStartDateAsString();
    String getEndDateAsString();
    Date getEndDate();
    long getProgramLength();
}