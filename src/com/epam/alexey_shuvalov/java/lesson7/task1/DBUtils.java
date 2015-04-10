package com.epam.alexey_shuvalov.java.lesson7.task1;

import com.epam.alexey_shuvalov.java.lesson7.task1.connection.DBConnectionException;
import static java.lang.System.out;
import java.sql.SQLException;

/**
 * @author Alexey Shuvalov
 * 
 */
public class DBUtils {
    public static void printDBExceptions(SQLException ex) {
        out.println("SQLException caught!");
        while ( ex != null ){
            out.println("Message \t: " + ex.getMessage());
            out.println("SQLState \t: " + ex.getSQLState());
            out.println("ErrorCode \t: " + ex.getErrorCode());
            for (StackTraceElement element : ex.getStackTrace()) {
                System.err.println(element.toString());
            }
            ex = ex.getNextException();
        }
    }
    
    public static void printException(Exception ex) {
        out.println("An exception caught!");
        out.println("Message \t: " + ex.getMessage());
        out.println("Cause \t: " + ex.getCause());
        for (StackTraceElement element : ex.getStackTrace()) {
                System.err.println(element.toString());
        }
    }
}
