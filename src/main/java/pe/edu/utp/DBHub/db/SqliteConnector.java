package pe.edu.utp.DBHub.db;

import pe.edu.utp.DBHub.config.DatabaseConnector;

public class SqliteConnector implements DatabaseConnector {
    @Override
    public boolean connect(String server, String port, String database, String user, String password) {
        return false;
    }

    @Override
    public boolean disconnect() {
        return false;
    }

    @Override
    public boolean isConnected() {
        return false;
    }

    @Override
    public String getErrorMessage() {
        return "";
    }

    @Override
    public void setErrorMessage(String errorMessage) {

    }
}
