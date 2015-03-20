package com.epam.alexey_shuvalov.java.lesson3.task1;

import com.epam.alexey_shuvalov.java.lesson3.task1.model.Course;
import com.epam.alexey_shuvalov.java.lesson3.task1.model.EducationProgram;
import com.epam.alexey_shuvalov.java.lesson3.task1.model.Student;
import com.epam.alexey_shuvalov.java.lesson3.task1.model.Traceable;

import java.util.Calendar;
import java.util.concurrent.TimeUnit;

/**
 * @author Alexey Shuvalov
 *
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

    public void shortReport(Traceable[] educationPrograms) {
        Calendar current = Calendar.getInstance();
        for (Traceable educationProgram : educationPrograms) {
            if (educationProgram != null) {
                if (educationProgram.getCompletionDate().before(current)) {
                    long diff = EducationUtils.getDateDiff(educationProgram.getCompletionDate().getTime(), current.getTime(), TimeUnit.MILLISECONDS);
                    System.out.print(educationProgram.getStudentName() + " (" + educationProgram.getProgramName() +
                            ") - Education program is completed. The time has passed since completion: ");
                    EducationUtils.printDateDiff(diff);
                } else {
                    long diff = EducationUtils.getDateDiff(current.getTime(), educationProgram.getCompletionDate().getTime(), TimeUnit.MILLISECONDS);
                    System.out.print(educationProgram.getStudentName() + " (" + educationProgram.getProgramName() +
                            ") - Education program is not completed. The time remaining until completion: ");
                    EducationUtils.printDateDiff(diff);
                }
            }
        }
    }

    public void fullReport(Traceable[] educationPrograms) {
        Calendar current = Calendar.getInstance();
        for (Traceable educationProgram : educationPrograms) {
            if (educationProgram != null) {
                if (educationProgram.getCompletionDate().before(current)) {
                    long diff = EducationUtils.getDateDiff(educationProgram.getCompletionDate().getTime(), current.getTime(), TimeUnit.MILLISECONDS);
                    System.out.format("\n%s has completed '%s' education program. Education hours were on weekdays from %s to %s.\n" +
                                    "The total length of education program is %s hours. Start date is %s, and end date is %s.\n" +
                                    "The time has passed since completion: ",
                            educationProgram.getStudentName(), educationProgram.getProgramName(),
                            educationProgram.getEducationHours()[0], educationProgram.getEducationHours()[1],
                            educationProgram.getProgramLength(), educationProgram.getStartDate(),
                            educationProgram.getEndDate());
                    EducationUtils.printDateDiff(diff);
                } else {
                    long diff = EducationUtils.getDateDiff(current.getTime(), educationProgram.getCompletionDate().getTime(), TimeUnit.MILLISECONDS);
                    System.out.format("\n%s is studying '%s' education program. Education hours are on weekdays from %s to %s.\n" +
                                    "The total length of education program is %s hours. Start date is %s, and end date is %s.\n" +
                                    "The time remaining until completion: ",
                            educationProgram.getStudentName(), educationProgram.getProgramName(),
                            educationProgram.getEducationHours()[0], educationProgram.getEducationHours()[1],
                            educationProgram.getProgramLength(), educationProgram.getStartDate(),
                            educationProgram.getEndDate());
                    EducationUtils.printDateDiff(diff);
                }
            }
        }
    }

    public Traceable[] startApplication() {
        Student firstStudent = new Student("Ivan", "Ivanov");
        Student secondStudent = new Student("Petr", "Petrov");

        Course[] firstCourses = new Course[2];
        firstCourses[0] = new Course("Java Servlets Technology", 16);
        firstCourses[1] = new Course("Struts Framework", 24);

        EducationProgram firstEP = new EducationProgram(firstStudent);
        firstEP.setCourseScope(firstCourses);
        firstEP.setProgramName("J2EE Developer");
        firstEP.setEducationCalendar(EducationUtils.convertStringToDateTime("16.03.2015 10:00:00"));

        Course[] secondCourses = new Course[4];
        secondCourses[0] = new Course("Java Overview", 8);
        secondCourses[1] = new Course("JFC/Swing Library", 16);
        secondCourses[2] = null;
        secondCourses[3] = new Course("JDBC Technology", 16);

        EducationProgram secondEP = new EducationProgram(secondStudent);
        secondEP.setCourseScope(secondCourses);
        secondEP.setProgramName("Java Developer");
        secondEP.setEducationCalendar(EducationUtils.convertStringToDateTime("14.03.2015 10:00:00"));

        Traceable[] educationPrograms = new EducationProgram[3];
        educationPrograms[0] = firstEP;
        educationPrograms[1] = null;
        educationPrograms[2] = secondEP;
        return educationPrograms;
    }

}

