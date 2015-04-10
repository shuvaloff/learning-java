package com.epam.alexey_shuvalov.java.lesson7.task1.connection.factory;

import com.epam.alexey_shuvalov.java.lesson7.task1.connection.ConnectionType;
import com.epam.alexey_shuvalov.java.lesson7.task1.connection.DerbyConnection;
import com.epam.alexey_shuvalov.java.lesson7.task1.connection.OracleConnection;
import com.epam.alexey_shuvalov.java.lesson7.task1.connection.DBConnection;
import com.epam.alexey_shuvalov.java.lesson7.task1.connection.DBConnectionException;

/**
 * @author Alexey Shuvalov
 * 
 */
public class ConnectionFactory {
    
    public static DBConnection getConnection(ConnectionType connectionType) throws DBConnectionException {
        switch (connectionType) {
            case ORACLE:
                return new OracleConnection();
            case DERBY:                
                return new DerbyConnection();
            default:
                throw new RuntimeException("Unsupported type of connection!");
        }
    }

}
