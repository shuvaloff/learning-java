package com.epam.alexey_shuvalov.java.lesson3.task1.model;

/**
 *
 * @author Alexey_Shuvalov
 */
public class Course {
    private String courseName;
    private long courseDuration;

    public Course() {
    }


    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public long getCourseDuration() {
        return courseDuration;
    }

    public void setCourseDuration(long courseDuration) {
        this.courseDuration = courseDuration;
    }

    public Course(String courseName, long courseDuration) {
        this.courseName = courseName;
        this.courseDuration = courseDuration;
    }
    
    @Override
    public String toString() {
        return "Course{" + "courseName=" + courseName + ", courseDuration=" + courseDuration + '}';
    }
}
