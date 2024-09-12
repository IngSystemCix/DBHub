package pe.edu.utp.DBHub.db;

import pe.edu.utp.DBHub.config.DatabaseConnector;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * Implementation of the DatabaseConnector interface for Redis databases.
 * Provides methods to connect, disconnect, check connection status,
 * and handle error messages specific to Redis.
 */
public class RedisConnector implements DatabaseConnector {
    private static JedisPool jedisPool;
    private String errorMessage;

    /**
     * Establishes a connection to the Redis database.
     *
     * @param server the server address.
     * @param port the port number.
     * @param database the database name (not used in Redis).
     * @param user the username (not used in Redis).
     * @param password the password (not used in Redis).
     * @return true if the connection was successful, false otherwise.
     */
    @Override
    public boolean connect(String server, String port, String database, String user, String password) {
        try {
            jedisPool = new JedisPool(new JedisPoolConfig(), server, Integer.parseInt(port));
            try (Jedis jedis = jedisPool.getResource()) {
                // Test connection by doing a simple ping
                jedis.ping();
            }
            return true;
        } catch (Exception e) {
            setErrorMessage("Error connecting to Redis: " + e.getMessage());
            return false;
        }
    }

    /**
     * Closes the connection to the Redis database.
     *
     * @return true if the disconnection was successful, false otherwise.
     */
    @Override
    public boolean disconnect() {
        if (jedisPool != null) {
            try {
                jedisPool.close();
                return true;
            } catch (Exception e) {
                setErrorMessage("Error disconnecting from Redis: " + e.getMessage());
                return false;
            }
        }
        setErrorMessage("No Redis connection to close.");
        return false;
    }

    /**
     * Checks if there is an active connection to the Redis database.
     *
     * @return true if connected, false otherwise.
     */
    @Override
    public boolean isConnected() {
        try {
            return (jedisPool != null && !jedisPool.isClosed());
        } catch (Exception e) {
            setErrorMessage("Error checking Redis connection status: " + e.getMessage());
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