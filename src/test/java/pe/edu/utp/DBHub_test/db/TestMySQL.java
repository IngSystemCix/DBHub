package pe.edu.utp.DBHub_test.db;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pe.edu.utp.DBHub.db.MySQLConnector;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

/**
 * Test class for MySQLConnector.
 * Contains unit tests to verify the connection and disconnection functionality.
 */
public class TestMySQL {

    private MySQLConnector connector;

    /**
     * Sets up the test environment before each test.
     * Initializes the MySQLConnector instance.
     */
    @BeforeEach
    public void setUp() {
        connector = new MySQLConnector();
    }

    /**
     * Cleans up the test environment after each test.
     * Disconnects the MySQLConnector instance.
     */
    @AfterEach
    public void tearDown() {
        connector.disconnect();
    }

    /**
     * Tests the connection to the MySQL database.
     * Verifies that the connection is successful.
     */
    @Test
    public void testConnection() {
        boolean isConnected = connector.connect("localhost", "3306", "crud_spring", "root", "");
        assertTrue(isConnected, "The connection should be successful.");
    }

    /**
     * Tests the disconnection from the MySQL database.
     * Verifies that the connection is closed successfully.
     */
    @Test
    public void testDisconnection() {
        connector.connect("localhost", "3306", "test_db", "root", "");
        connector.disconnect();
        assertFalse(connector.isConnected(), "The connection should be closed.");
    }
}