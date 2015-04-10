package com.epam.alexey_shuvalov.java.lesson7.task1.connection;

/**
 * @author Alexey Shuvalov
 * 
 */
public interface DBConnection {
    void process() throws DBConnectionException;
    void solveTask() throws DBConnectionException;
    void close() throws DBConnectionException;
}

