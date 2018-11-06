package princesadaserra.java.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionProvider {
    public ConnectionProvider(String connectionUrl) {
        this.connectionUrl = connectionUrl;
    }

    protected String getConnectionUrl() {
        return connectionUrl;
    }

    private String connectionUrl;
    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(connectionUrl);
    }
}
