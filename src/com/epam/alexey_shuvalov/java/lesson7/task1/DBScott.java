
package com.epam.alexey_shuvalov.java.lesson7.task1;

import com.epam.alexey_shuvalov.java.lesson7.task1.connection.ConnectionType;
import com.epam.alexey_shuvalov.java.lesson7.task1.connection.DBConnection;
import com.epam.alexey_shuvalov.java.lesson7.task1.connection.DBConnectionException;
import com.epam.alexey_shuvalov.java.lesson7.task1.connection.factory.ConnectionFactory;

/** 
 * @author Alexey Shuvalov
 * 
 */
public class DBScott {
    
    public static void main(String[] args) {
        DBScott app = new DBScott();
        try {
            app.startApplication();
        } catch (DBConnectionException ex) {
            DBUtils.printException(ex);
        }
        
    }

    private void startApplication() throws DBConnectionException {
            ConnectionType derby = ConnectionType.DERBY;
            ConnectionType oracle = ConnectionType.ORACLE;

            DBConnection derbyConnection = ConnectionFactory.getConnection(derby);

            derbyConnection.process();
            derbyConnection.solveTask();
            derbyConnection.close();

            DBConnection oracleConnection = ConnectionFactory.getConnection(oracle);
            oracleConnection.process();
            oracleConnection.solveTask();
            oracleConnection.close();
    }
}
