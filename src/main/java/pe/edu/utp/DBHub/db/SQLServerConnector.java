package pe.edu.utp.DBHub.db;

import pe.edu.utp.DBHub.config.DatabaseConnector;
import pe.edu.utp.DBHub.config.DatabaseQuerys;

import java.sql.*;

/**
 * Implementation of the DatabaseConnector interface for SQL Server databases.
 * Provides methods to connect, disconnect, check connection status,
 * and handle error messages specific to SQL Server.
 */
public class SQLServerConnector implements DatabaseConnector, DatabaseQuerys {
    private Connection connection;
    private String errorMessage;

    /**
     * Establishes a connection to the SQL Server database.
     *
     * @param server the server address.
     * @param port the port number.
     * @param database the database name.
     * @param user the username.
     * @param password the password.
     * @return true if the connection was successful, false otherwise.
     */
    @Override
    public boolean connect(String server, String port, String database, String user, String password) {
        String JDBC_URL = String.format(
                "jdbc:sqlserver://%s:%s;databaseName=%s;encrypt=true;trustServerCertificate=true;",
                server, port, database);
        try {
            connection = DriverManager.getConnection(JDBC_URL, user, password);
            return true;
        } catch (SQLException e) {
            setErrorMessage("Error connecting to SQL Server: " + e.getMessage());
            return false;
        }
    }

    /**
     * Closes the connection to the SQL Server database.
     *
     * @return true if the disconnection was successful, false otherwise.
     */
    @Override
    public boolean disconnect() {
        if (connection != null) {
            try {
                connection.close();
                return true;
            } catch (SQLException e) {
                setErrorMessage("Error disconnecting from SQL Server: " + e.getMessage());
                return false;
            }
        }
        setErrorMessage("No SQL Server connection to close.");
        return false;
    }

    /**
     * Checks if there is an active connection to the SQL Server database.
     *
     * @return true if connected, false otherwise.
     */
    @Override
    public boolean isConnected() {
        try {
            return (connection != null && !connection.isClosed());
        } catch (SQLException e) {
            setErrorMessage("Error checking SQL Server connection status: " + e.getMessage());
            return false;
        }
    }

    /**
     * Retrieves the error message if any operation fails.
     *
     * @return the error message as a String.
     */
    @Override
    public String getErrorMessage() {
        return errorMessage != null ? errorMessage : "No errors";
    }

    /**
     * Sets the error message for the connector.
     *
     * @param errorMessage the error message to set.
     */
    @Override
    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
    @Override
    public ResultSet executeQuery(String name_table, String name_column, String condition) {
        String sql = "SELECT * FROM "+name_table+" WHERE "+name_column +" = ?";
        if (connection != null) {
            try{
                PreparedStatement ps = connection.prepareStatement(sql);
                ps.setString(1, condition);
                if(connection !=null){
                    return ps.executeQuery();
                }
            }catch (SQLException e){
                throw new RuntimeException(e.getMessage());
            }
        } else {
            setErrorMessage("Database connection is null");
            throw new IllegalStateException(getErrorMessage());
        }
        return null;
    }

    @Override
    public ResultSet executeQuery(String name_table, String name_column) {
        String sql = "SELECT "+name_column+" FROM "+name_table;
        if (connection != null) {
            try{
                PreparedStatement ps = connection.prepareStatement(sql);
                if(connection !=null){
                    return ps.executeQuery();
                }
            }catch (SQLException e){
                throw new RuntimeException(e.getMessage());
            }
        } else {
            setErrorMessage("Database connection is null");
            throw new IllegalStateException(getErrorMessage());
        }
        return null;
    }

    @Override
    public ResultSet executeQuery(String name_table) {
        String sql = "SELECT * FROM "+name_table;
        if (connection != null) {
            try{
                PreparedStatement ps = connection.prepareStatement(sql);
                if(connection !=null){
                    return ps.executeQuery();
                }
            }catch (SQLException e){
                throw new RuntimeException(e.getMessage());
            }
        } else {
            setErrorMessage("Database connection is null");
            throw new IllegalStateException(getErrorMessage());
        }
        return null;
    }


    @Override
    public int executeUpdate(String name_table, String name_column, String value_insert,String column_condition, String condition) {
        String sql = "UPDATE "+name_table+" SET "+name_column+" = ? where "+column_condition+" = ?";
        if (connection != null) {
            try{
                PreparedStatement ps = connection.prepareStatement(sql);
                ps.setString(1, value_insert);
                ps.setString(2, condition);
                if(connection !=null){
                    return ps.executeUpdate();
                }

            }catch (SQLException e){
                throw new RuntimeException(e.getMessage());
            }
        } else {
            setErrorMessage("Database connection is null");
            throw new IllegalStateException(getErrorMessage());
        }
        return 0;
    }

    @Override
    public void executeProcedure(String name_procedure, Object[] parameter_list) {
        StringBuilder sql = new StringBuilder("{call ").append(name_procedure).append("(");
        for (int i = 0; i < parameter_list.length; i++) {
            sql.append("?");
            if(i <parameter_list.length-1){
                sql.append(",");
            }
        }
        sql.append(")}");
        try{
            CallableStatement callableStatement = connection.prepareCall(sql.toString());
            if(connection !=null){
                for(int i = 0; i<parameter_list.length; i++){
                    if(parameter_list[i] instanceof String){
                        callableStatement.setString(i+1, (String)parameter_list[i]);
                    } else if (parameter_list[i] instanceof Integer) {
                        callableStatement.setInt(i+1, (Integer)parameter_list[i]);
                    } else if (parameter_list[i] instanceof Double) {
                        callableStatement.setDouble(i+1, (Double)parameter_list[i]);
                    }else if (parameter_list[i] instanceof Boolean) {
                        callableStatement.setBoolean(i+1, (Boolean)parameter_list[i]);
                    } else if (parameter_list[i] instanceof Date) {
                        callableStatement.setDate(i+1, (Date)parameter_list[i]);
                    }
                }
                callableStatement.execute();
                System.out.println("Procedure executed successfully");

            }
        }catch (SQLException e){
            throw new RuntimeException(e.getMessage());
        }
    }

}