package pe.edu.utp.DBHub_test.db;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pe.edu.utp.DBHub.db.SQLServerConnector;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

/**
 * Test class for SQLServerConnector.
 * Contains unit tests to verify the connection and disconnection functionality.
 */
public class TestSQLServer {

    private SQLServerConnector connector;

    /**
     * Sets up the test environment before each test.
     * Initializes the SQLServerConnector instance and connects to the SQL Server database.
     */
    @BeforeEach
    public void setUp() {
        connector = new SQLServerConnector();
        connector.connect("localhost", "1433", "yourDatabase", "yourUser", "yourPassword");
    }

    /**
     * Cleans up the test environment after each test.
     * Disconnects the SQLServerConnector instance.
     */
    @AfterEach
    public void tearDown() {
        connector.disconnect();
    }

    /**
     * Tests the connection to the SQL Server database.
     * Verifies that the connection is successful.
     */
    @Test
    public void testConnection() {
        assertTrue(connector.isConnected(), "La conexión a SQL Server debería ser exitosa.");
    }

    /**
     * Tests the disconnection from the SQL Server database.
     * Verifies that the connection is closed successfully.
     */
    @Test
    public void testDisconnection() {
        connector.disconnect();
        assertFalse(connector.isConnected(), "La conexión debería estar cerrada.");
    }
}