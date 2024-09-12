package pe.edu.utp.DBHub.db;

import pe.edu.utp.DBHub.config.DatabaseConnector;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Implementation of the DatabaseConnector interface for MySQL databases.
 * Provides methods to connect, disconnect, check connection status,
 * and handle error messages specific to MySQL.
 */
public class MySQLConnector implements DatabaseConnector {
    private Connection connection;
    private String errorMessage;

    /**
     * Establishes a connection to the MySQL database.
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
        String JDBC_URL = String.format("jdbc:mysql://%s:%s/%s?useSSL=false", server, port, database);
        try {
            connection = DriverManager.getConnection(JDBC_URL, user, password);
            return true;
        } catch (SQLException e) {
            setErrorMessage("Error connecting to MySQL: " + e.getMessage());
            return false;
        }
    }

    /**
     * Closes the connection to the MySQL database.
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
                setErrorMessage("Error disconnecting from MySQL: " + e.getMessage());
                return false;
            }
        }
        setErrorMessage("No MySQL connection to close.");
        return false;
    }

    /**
     * Checks if there is an active connection to the MySQL database.
     *
     * @return true if connected, false otherwise.
     */
    @Override
    public boolean isConnected() {
        try {
            return (connection != null && !connection.isClosed());
        } catch (SQLException e) {
            setErrorMessage("Error checking MySQL connection status: " + e.getMessage());
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
}