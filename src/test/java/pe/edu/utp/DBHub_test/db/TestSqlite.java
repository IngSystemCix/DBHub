package pe.edu.utp.DBHub_test.db;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pe.edu.utp.DBHub.db.SqliteConnector;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

/**
 * Test class for SqliteConnector.
 * Contains unit tests to verify the connection and disconnection functionality.
 */
public class TestSqlite {

    private SqliteConnector connector;

    /**
     * Sets up the test environment before each test.
     * Initializes the SqliteConnector instance and connects to the SQLite database.
     */
    @BeforeEach
    public void setUp() {
        connector = new SqliteConnector();
        connector.connect("localhost", "", "db_test", "", "");
    }

    /**
     * Cleans up the test environment after each test.
     * Disconnects the SqliteConnector instance.
     */
    @AfterEach
    public void tearDown() {
        connector.disconnect();
    }

    /**
     * Tests the connection to the SQLite database.
     * Verifies that the connection is successful.
     */
    @Test
    public void testConnection() {
        assertTrue(connector.isConnected(), "The connection should be successful.");
    }

    /**
     * Tests the disconnection from the SQLite database.
     * Verifies that the connection is closed successfully.
     */
    @Test
    public void testDisconnection() {
        connector.disconnect();
        assertFalse(connector.isConnected(), "The connection should be closed.");
    }
}