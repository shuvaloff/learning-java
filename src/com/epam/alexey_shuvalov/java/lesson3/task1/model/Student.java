package com.epam.alexey_shuvalov.java.lesson3.task1.model;

/**
 *
 * @author Alexey_Shuvalov
 */
public class Student {
    private String firstName;
    private String lastName;
    
    public Student() {
    }

    public Student(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }
    
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        return firstName + " " + lastName;
    }
    
}
