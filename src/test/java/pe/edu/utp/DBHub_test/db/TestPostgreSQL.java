package pe.edu.utp.DBHub_test.db;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pe.edu.utp.DBHub.db.PostgreSQLConnector;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

/**
 * Test class for PostgreSQLConnector.
 * Contains unit tests to verify the connection and disconnection functionality.
 */
public class TestPostgreSQL {

    private PostgreSQLConnector connector;

    /**
     * Sets up the test environment before each test.
     * Initializes the PostgreSQLConnector instance.
     */
    @BeforeEach
    public void setUp() {
        connector = new PostgreSQLConnector();
    }

    /**
     * Cleans up the test environment after each test.
     * Disconnects the PostgreSQLConnector instance.
     */
    @AfterEach
    public void tearDown() {
        connector.disconnect();
    }

    /**
     * Tests the connection to the PostgreSQL database.
     * Verifies that the connection is successful.
     */
    @Test
    public void testConnection() {
        boolean isConnected = connector.connect("localhost", "5432", "test_db", "user", "password");
        assertTrue(isConnected, "The connection should be successful.");
    }

    /**
     * Tests the disconnection from the PostgreSQL database.
     * Verifies that the connection is closed successfully.
     */
    @Test
    public void testDisconnection() {
        connector.connect("localhost", "5432", "test_db", "user", "password");
        connector.disconnect();
        assertFalse(connector.isConnected(), "The connection should be closed.");
    }
}