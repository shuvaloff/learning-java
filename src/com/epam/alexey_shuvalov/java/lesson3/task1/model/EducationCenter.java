package com.epam.alexey_shuvalov.java.lesson3.task1.model;

import java.util.Date;

/**
 * @author Alexey Shuvalov
 * 
 */
public class EducationCenter {
    
    public static Statistics getStatistics(EducationProgram educationProgram, Student student, Date startDate) {
        Statistics statistics = new Statistics(educationProgram, student, startDate);
        return statistics;
    }
    
}
