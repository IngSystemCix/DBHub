package pe.edu.utp.DBHub_test.db;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pe.edu.utp.DBHub.db.RedisConnector;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

/**
 * Test class for RedisConnector.
 * Contains unit tests to verify the connection and disconnection functionality.
 */
public class TestRedis {

    private RedisConnector connector;

    /**
     * Sets up the test environment before each test.
     * Initializes the RedisConnector instance and connects to the Redis database.
     */
    @BeforeEach
    public void setUp() {
        connector = new RedisConnector();
        connector.connect("localhost", "6379", "db_test", "admin", "");
    }

    /**
     * Cleans up the test environment after each test.
     * Disconnects the RedisConnector instance.
     */
    @AfterEach
    public void tearDown() {
        connector.disconnect();
    }

    /**
     * Tests the connection to the Redis database.
     * Verifies that the connection is successful.
     */
    @Test
    public void testConnection() {
        assertTrue(connector.isConnected(), "The connection should be successful.");
    }

    /**
     * Tests the disconnection from the Redis database.
     * Verifies that the connection is closed successfully.
     */
    @Test
    public void testDisconnection() {
        connector.disconnect();
        assertFalse(connector.isConnected(), "The connection should be closed.");
    }
}