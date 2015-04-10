package com.epam.alexey_shuvalov.java.lesson7.task1.connection;

import com.epam.alexey_shuvalov.java.lesson7.task1.DBUtils;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;

/**
 * @author Alexey Shuvalov
 * 
 */
public class OracleConnection implements DBConnection {
    private static final String DB_CONNECTION_STRING = "jdbc:oracle:thin:scott/tiger@localhost:1521:XE";
    private static final String TABLE_NAME = "EMP";
    private static final int TIMEOUT = 5;
    private Connection connection;
    
    public OracleConnection() throws DBConnectionException {
        try {
            Class.forName("oracle.jdbc.OracleDriver").newInstance();
            this.connection = DriverManager.getConnection(DB_CONNECTION_STRING);
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException ex) {
            throw new DBConnectionException(ex);
        }
    }
    
    @Override
    public void process() throws DBConnectionException {
        try {
            if (connection != null && connection.isValid(TIMEOUT)) {
                System.out.println("A connection with Oracle database has been successfully established.");
            }
        } catch (Exception ex) {
            throw new DBConnectionException(ex);
        }
    }

    @Override
    public void close() throws DBConnectionException {
        try {
            if (connection != null) {
                connection.close();
                System.out.println("A connection with Oracle database has been successfully closed.");
            }
        } catch (SQLException ex) {
            throw new DBConnectionException(ex);
        }
    }

    @Override
    public void solveTask() {
        try {
            try (Statement statement = connection.createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY)) {
                try (ResultSet records = statement.executeQuery("select EMPNO, ENAME, JOB, MGR, HIREDATE, SAL, COMM, DEPTNO from " + TABLE_NAME)) {
                    if (getRowCount(records) >= 150 ) {
                        System.out.println("PLS, NO!");
                    } else {
                        connection.setAutoCommit(false);
                        for (int i = 0; i < 10; i++) {
                            while (records.next()) {
                                int mgrNULL, commNULL;
                                int empID = getLastPKValue();
                                StringBuilder query = new StringBuilder();
                                query.append("INSERT INTO APP.EMP ");
                                query.append("(EMPNO, ENAME, JOB, MGR, HIREDATE, SAL, COMM, DEPTNO) \n");
                                query.append("	VALUES (?, ?, ?, ?, ?, ?, ?, ?)");
                                PreparedStatement preparedStatement = connection.prepareStatement(query.toString());
                                preparedStatement.setInt(1, ++empID);
                                preparedStatement.setString(2, records.getString(2));
                                preparedStatement.setString(3, records.getString(3));
                                mgrNULL = records.getInt(4);
                                if (records.wasNull()) {
                                    preparedStatement.setNull(4, Types.INTEGER);
                                } else {
                                    preparedStatement.setInt(4, mgrNULL);
                                }
                                preparedStatement.setDate(5, records.getDate(5));
                                preparedStatement.setInt(6, records.getInt(6));
                                commNULL = records.getInt(7);
                                if (records.wasNull()) {
                                    preparedStatement.setNull(7, Types.INTEGER);
                                } else {
                                    preparedStatement.setInt(7, commNULL);
                                }
                                preparedStatement.setInt(8, records.getInt(8));
                                preparedStatement.executeUpdate();
                            }
                            records.beforeFirst();
                        }
                    }
                }
                connection.commit();
            }
        } catch (SQLException ex) {
            DBUtils.printDBExceptions(ex);
            if (connection != null) {
                try {
                    System.err.println("Transaction is being rolled back...");
                    connection.rollback();
                } catch (SQLException exc) {
                    DBUtils.printDBExceptions(exc);
                }
            }
        } 
    }

    private int getLastPKValue() {
        try {
            try (Statement statement = connection.createStatement();
                 ResultSet resultSet = statement.executeQuery("select EMPNO from EMP ORDER BY EMPNO DESC")) 
            {
                while (resultSet.next()) {
                    int id = resultSet.getInt("EMPNO");
                    return id;
                }
            }
        } catch (SQLException ex) {
            DBUtils.printDBExceptions(ex);
        }
        return 0;
    }
    
    private int getRowCount(ResultSet resultSet) throws SQLException {
        int rowCount;
        int currentRow = resultSet.getRow();            
        rowCount = resultSet.last() ? resultSet.getRow() : 0;
        if (currentRow == 0) {
            resultSet.beforeFirst(); 
        } else {
            resultSet.absolute(currentRow);
        }
        return rowCount;
    }
    
}
