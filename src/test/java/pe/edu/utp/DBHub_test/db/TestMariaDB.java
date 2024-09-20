package pe.edu.utp.DBHub_test.db;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pe.edu.utp.DBHub.db.MariaDBConnector;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

/**
 * Test class for MariaDBConnector.
 * Contains unit tests to verify the connection and disconnection functionality.
 */
public class TestMariaDB {

    private MariaDBConnector connector;

    /**
     * Sets up the test environment before each test.
     * Initializes the MariaDBConnector instance.
     */
    @BeforeEach
    public void setUp() {
        connector = new MariaDBConnector();
    }

    /**
     * Cleans up the test environment after each test.
     * Disconnects the MariaDBConnector instance.
     */
    @AfterEach
    public void tearDown() {
        connector.disconnect();
    }

    /**
     * Tests the connection to the MariaDB database.
     * Verifies that the connection is successful.
     */
    @Test
    public void testConnection() {
        boolean isConnected = connector.connect("localhost", "3306", "test_db", "root", "password");
        assertTrue(isConnected, "The connection should be successful.");
    }

    /**
     * Tests the disconnection from the MariaDB database.
     * Verifies that the connection is closed successfully.
     */
    @Test
    public void testDisconnection() {
        connector.connect("localhost", "3306", "test_db", "root", "password");
        connector.disconnect();
        assertFalse(connector.isConnected(), "The connection should be closed.");
    }
}