package com.epam.alexey_shuvalov.java.lesson3.task1.model;

import com.epam.alexey_shuvalov.java.lesson3.task1.EducationUtils;
import java.util.Calendar;
import java.util.Date;

/**
 * @author Alexey Shuvalov
 * 
 */
public class Statistics implements Trackable {
    
    private EducationProgram educationProgram;
    private Student student;
    private final Date startDate;
    private final Date endDate;
    private final Calendar startDateCalendar;
    private final Calendar endDateCalendar;
    
    public Statistics(EducationProgram educationProgram, Student student, 
                      Date startDate, Date endDate, 
                      Calendar startDateCalendar, Calendar endDateCalendar) 
    {
        this.educationProgram = educationProgram;
        this.student = student;
        this.startDate = startDate;
        this.endDate = endDate;
        this.startDateCalendar = startDateCalendar;
        this.endDateCalendar = endDateCalendar;
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
        return EducationUtils.convertDateToString(startDate);
    }

    @Override
    public String getEndDate() {
        return EducationUtils.convertDateToString(endDate);
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
    public long getProgramLength() {
        return educationProgram.getProgramLength();
    }

    @Override
    public Calendar getEndDateCalendar() {
        return endDateCalendar;
    }

    public Calendar getStartDateCalendar() {
        return startDateCalendar;
    }

}
