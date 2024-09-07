package pe.edu.utp.DBHub.config;

/**
 * Interface representing a connector to a database.
 * Provides methods to connect, disconnect, check connection status,
 * and handle error messages.
 */
public interface DatabaseConnector {

    /**
     * Establishes a connection to the database.
     *
     * @param server the server address.
     * @param port the port number.
     * @param database the database name.
     * @param user the username.
     * @param password the password.
     * @return true if the connection was successful, false otherwise.
     */
    public boolean connect(String server, String port, String database, String user, String password);

    /**
     * Closes the connection to the database.
     *
     * @return true if the disconnection was successful, false otherwise.
     */
    public boolean disconnect();

    /**
     * Checks if there is an active connection to the database.
     *
     * @return true if connected, false otherwise.
     */
    public boolean isConnected();

    /**
     * Retrieves the error message if any operation fails.
     *
     * @return the error message as a String.
     */
    public String getErrorMessage();

    /**
     * Sets the error message for the connector.
     *
     * @param errorMessage the error message to set.
     */
    public void setErrorMessage(String errorMessage);
}