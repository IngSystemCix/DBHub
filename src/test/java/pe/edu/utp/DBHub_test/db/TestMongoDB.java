package pe.edu.utp.DBHub_test.db;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pe.edu.utp.DBHub.db.MongoDBConnector;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class TestMongoDB {

    private MongoDBConnector connector;

    @BeforeEach
    public void setUp() {
        connector = new MongoDBConnector();
    }

    @AfterEach
    public void tearDown() {
        connector.disconnect();
    }

    @Test
    public void testConnection() {

        boolean isConnected = connector.connect("localhost", "27017", "test_db", "root", "password");

        assertTrue(isConnected, "he connection should be successful.");
    }

    @Test
    public void testDisconnection() {

        connector.connect("localhost", "27017", "test_db", "root", "password");

        connector.disconnect();

        assertFalse(connector.isConnected(), "The connection should be closed.");
    }
}
