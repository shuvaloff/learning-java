package com.epam.alexey_shuvalov.java.lesson3.task1.model;

import java.util.Calendar;
import java.util.Date;

/**
 * @author Alexey Shuvalov
 *         Created on 20.03.2015.
 */
public interface Traceable {
    String getStudentName();
    String getProgramName();
    String getStartDate();
    String getEndDate();
    String[] getEducationHours();
    long getProgramLength();
    Calendar getCompletionDate();
}
