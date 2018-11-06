package princesadaserra.java.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public abstract class AuthenticatedDbRepository<K, I> extends AuthenticatedRepository<K, I> {
    private String connectionUrl;

    protected AuthenticatedDbRepository(String connectionUrl, String userName, String password) {
        super(userName, password);
        this.connectionUrl = connectionUrl;
    }

    protected String getConnectionUrl() {
        return connectionUrl;
    }

    protected void setConnectionUrl(String connectionUrl) {
        this.connectionUrl = connectionUrl;
    }

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(connectionUrl , getUserName(), getPassword());
    }
}
