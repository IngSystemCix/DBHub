package pe.edu.utp.DBHub_test.db;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pe.edu.utp.DBHub.db.H2Connector;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

/**
 * Test class for H2Connector.
 * Contains unit tests to verify the connection and disconnection functionality.
 */
public class TestDBH2 {

    private H2Connector connector;

    /**
     * Sets up the test environment before each test.
     * Initializes the H2Connector instance.
     */
    @BeforeEach
    public void setUp() {
        connector = new H2Connector();
    }

    /**
     * Cleans up the test environment after each test.
     * Disconnects the H2Connector instance.
     */
    @AfterEach
    public void tearDown() {
        connector.disconnect();
    }

    /**
     * Tests the connection to the H2 database.
     * Verifies that the connection is successful.
     */
    @Test
    public void testConnection() {
        boolean isConnected = connector.connect("localhost", "9092", "test", "sa", "");
        assertTrue(isConnected, "The connection should be successful.");
    }

    /**
     * Tests the disconnection from the H2 database.
     * Verifies that the connection is closed successfully.
     */
    @Test
    public void testDisconnection() {
        connector.connect("localhost", "9092", "test", "sa", "");
        connector.disconnect();
        assertFalse(connector.isConnected(), "The connection should be closed.");
    }
}