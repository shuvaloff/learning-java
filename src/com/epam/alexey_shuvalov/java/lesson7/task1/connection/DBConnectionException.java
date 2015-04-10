/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epam.alexey_shuvalov.java.lesson7.task1.connection;

/**
 *
 * @author Alexey_Shuvalov
 */
public class DBConnectionException extends Exception {

    public DBConnectionException(String message) {
        super(message);
    }

    public DBConnectionException(Throwable cause) {
        super(cause);
    }

    public DBConnectionException(String message, Throwable cause) {
        super(message, cause);
    }
}
