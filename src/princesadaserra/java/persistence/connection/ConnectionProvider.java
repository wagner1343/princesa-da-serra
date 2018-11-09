package princesadaserra.java.persistence.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionProvider {
    private String connectionUrl;

    public ConnectionProvider(String connectionUrl) {
        this.connectionUrl = connectionUrl;
    }

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(connectionUrl);
    }

    protected String getConnectionUrl() {
        return connectionUrl;
    }
}
