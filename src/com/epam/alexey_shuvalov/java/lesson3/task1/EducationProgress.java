package com.epam.alexey_shuvalov.java.lesson3.task1;

import com.epam.alexey_shuvalov.java.lesson3.task1.model.Course;
import com.epam.alexey_shuvalov.java.lesson3.task1.model.EducationProgram;
import com.epam.alexey_shuvalov.java.lesson3.task1.model.Student;
import com.epam.alexey_shuvalov.java.lesson3.task1.model.Trackable;

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
        EducationUtils.generateShortReport(educationPrograms);
    }

    public void fullReport(Trackable[] educationPrograms) {
        EducationUtils.generateFullReport(educationPrograms);
    }

    public Trackable[] startApplication() {
        Student firstStudent = new Student("Ivan", "Ivanov");
        Student secondStudent = new Student("Petr", "Petrov");

        Course[] firstCourseScope = new Course[2];
        firstCourseScope[0] = new Course("Java Servlets Technology", 16);
        firstCourseScope[1] = new Course("Struts Framework", 24);

        EducationProgram firstEP = new EducationProgram(firstStudent);
        firstEP.setCourseScope(firstCourseScope);
        firstEP.setProgramName("J2EE Developer");
        firstEP.setEducationCalendar(EducationUtils.convertStringToDateTime("17.03.2015 10:00:00"));

        Course[] secondCourseScope = new Course[4];
        secondCourseScope[0] = new Course("Java Overview", 8);
        secondCourseScope[1] = new Course("JFC/Swing Library", 16);
        secondCourseScope[2] = null;
        secondCourseScope[3] = new Course("JDBC Technology", 16);

        EducationProgram secondEP = new EducationProgram(secondStudent);
        secondEP.setCourseScope(secondCourseScope);
        secondEP.setProgramName("Java Developer");
        secondEP.setEducationCalendar(EducationUtils.convertStringToDateTime("15.03.2015 10:00:00"));

        Trackable[] educationPrograms = new EducationProgram[3];
        educationPrograms[0] = firstEP;
        educationPrograms[1] = null;
        educationPrograms[2] = secondEP;
        return educationPrograms;
    }

}

