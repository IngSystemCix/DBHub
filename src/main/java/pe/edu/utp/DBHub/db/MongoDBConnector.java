package pe.edu.utp.DBHub.db;

import pe.edu.utp.DBHub.config.DatabaseConnector;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoDatabase;
import com.mongodb.MongoClientSettings;
import com.mongodb.ConnectionString;

/**
 * Implementation of the DatabaseConnector interface for MongoDB databases.
 * Provides methods to connect, disconnect, check connection status,
 * and handle error messages specific to MongoDB.
 */
public class MongoDBConnector implements DatabaseConnector {
    private MongoClient mongoClient;
    private MongoDatabase mongoDatabase;
    private String errorMessage;

    /**
     * Establishes a connection to the MongoDB database.
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
        String connectionString = String.format("mongodb://%s:%s@%s:%s/%s", user, password, server, port, database);
        try {
            ConnectionString connString = new ConnectionString(connectionString);
            mongoClient = MongoClients.create(connString);
            mongoDatabase = mongoClient.getDatabase(database);
            return true;
        } catch (Exception e) {
            setErrorMessage("Error connecting to MongoDB: " + e.getMessage());
            return false;
        }
    }

    /**
     * Closes the connection to the MongoDB database.
     *
     * @return true if the disconnection was successful, false otherwise.
     */
    @Override
    public boolean disconnect() {
        if (mongoClient != null) {
            try {
                mongoClient.close();
                mongoDatabase = null; // Clear the database reference on disconnect
                return true;
            } catch (Exception e) {
                setErrorMessage("Error disconnecting from MongoDB: " + e.getMessage());
                return false;
            }
        }
        setErrorMessage("No MongoDB connection to close.");
        return false;
    }

    /**
     * Checks if there is an active connection to the MongoDB database.
     *
     * @return true if connected, false otherwise.
     */
    @Override
    public boolean isConnected() {
        try {
            return mongoClient != null && mongoDatabase != null; // Check if both client and database are set
        } catch (Exception e) {
            setErrorMessage("Error checking MongoDB connection status: " + e.getMessage());
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
     * Retrieves the MongoDatabase instance.
     *
     * @return the MongoDatabase instance.
     */
    public MongoDatabase getMongoDatabase() {
        return mongoDatabase; // Additional method to access the MongoDatabase instance
    }
}