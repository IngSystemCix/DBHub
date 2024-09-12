package pe.edu.utp.DBHub.db;

import pe.edu.utp.DBHub.config.DatabaseConnector;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Implementation of the DatabaseConnector interface for MariaDB databases.
 * Provides methods to connect, disconnect, check connection status,
 * and handle error messages specific to MariaDB.
 */
public class MariDBConnector implements DatabaseConnector {
    private Connection connection;
    private String errorMessage;

    /**
     * Establishes a connection to the MariaDB database.
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
        String JDBC_URL = String.format("jdbc:mariadb://%s:%s/%s", server, port, database);
        try {
            connection = DriverManager.getConnection(JDBC_URL, user, password);
            return true;
        } catch (SQLException e) {
            setErrorMessage("Error connecting to MariaDB: " + e.getMessage());
            closeConnection();
            return false;
        }
    }

    /**
     * Closes the connection to the MariaDB database.
     *
     * @return true if the disconnection was successful, false otherwise.
     */
    @Override
    public boolean disconnect() {
        return closeConnection();
    }

    /**
     * Checks if there is an active connection to the MariaDB database.
     *
     * @return true if connected, false otherwise.
     */
    @Override
    public boolean isConnected() {
        try {
            return (connection != null && !connection.isClosed());
        } catch (SQLException e) {
            setErrorMessage("Error checking MariaDB connection status: " + e.getMessage());
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

    /**
     * Closes the connection to the MariaDB database.
     *
     * @return true if the disconnection was successful, false otherwise.
     */
    private boolean closeConnection() {
        if (connection != null) {
            try {
                connection.close();
                return true;
            } catch (SQLException e) {
                setErrorMessage("Error disconnecting from MariaDB: " + e.getMessage());
                return false;
            }
        }
        setErrorMessage("No MariaDB connection to close.");
        return false;
    }
}