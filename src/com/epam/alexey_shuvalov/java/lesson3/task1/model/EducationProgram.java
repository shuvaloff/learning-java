package com.epam.alexey_shuvalov.java.lesson3.task1.model;

/**
 * @author Alexey Shuvalov
 *
 */
public class EducationProgram {
    private String programName;
    private Course[] courseScope;

    public String getProgramName() {
        return programName;
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

    public EducationProgram(String programName, Course[] courseScope) {
        this.programName = programName;
        this.courseScope = courseScope;
    }
}
