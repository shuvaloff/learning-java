package com.epam.alexey_shuvalov.java.lesson3.task1;

import com.epam.alexey_shuvalov.java.lesson3.task1.model.*;

import java.util.Date;

/**
 * @author Alexey Shuvalov
 * Task #1.
 */
public class EducationProgress {
    public static void main(String[] args) {
        EducationProgress app = new EducationProgress();
        switch (args.length) {
            case 0:
                app.shortReport(app.startApplication());
                break;
            default:
                int number = -1;
                try {
                    number = Integer.parseInt(args[0]);
                } catch (NumberFormatException ex) {
                    System.out.println("We only accept 0 (zero) as an argument. " + 
                            args[0] + " doesn't look like zero from here.");
                }
                if (number == 0) {
                    app.shortReport(app.startApplication());
                } else {
                    app.fullReport(app.startApplication());
                }
                break;
        }
    }

    public void shortReport(Trackable[] educationPrograms) {
        Date today = new Date();
        EducationUtils.generateShortReport(educationPrograms, today);
    }

    public void fullReport(Trackable[] educationPrograms) {
        Date today = new Date();
        EducationUtils.generateFullReport(educationPrograms, today);
    }

    public Trackable[] startApplication() {
        Student firstStudent = new Student("Ivan", "Ivanov");
        Student secondStudent = new Student("Petr", "Petrov");

        Course[] firstCourseScope = new Course[2];
        firstCourseScope[0] = new Course("Java Servlets Technology", 16);
        firstCourseScope[1] = new Course("Struts Framework", 24);

        EducationProgram firstEP = new EducationProgram("J2EE Developer", firstCourseScope);

        Course[] secondCourseScope = new Course[4];
        secondCourseScope[0] = new Course("Java Overview", 8);
        secondCourseScope[1] = new Course("JFC/Swing Library", 16);
        secondCourseScope[2] = null;
        secondCourseScope[3] = new Course("JDBC Technology", 16);

        EducationProgram secondEP = new EducationProgram("Java Developer", secondCourseScope);
        
        Date firstStartDate = EducationUtils.convertStringToDateTime("19.03.2015 10:30:00");
        Statistics firstStudentStats = EducationCenter.getStatistics(firstEP, firstStudent, firstStartDate);
        
        Date secondStartDate = EducationUtils.convertStringToDateTime("18.03.2015 10:30:00");
        Statistics secondStudentStats = EducationCenter.getStatistics(secondEP, secondStudent, secondStartDate);
        
        Trackable[] statistics = new Statistics[3];
        statistics[0] = firstStudentStats;
        statistics[1] = null;
        statistics[2] = secondStudentStats;
        return statistics;
    }

}