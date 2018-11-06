package princesadaserra.java.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class AuthenticatedConnectionProvider extends ConnectionProvider{
    private String username;
    private String password;

    public AuthenticatedConnectionProvider(String connectionUrl, String username, String password) {
        super(connectionUrl);
        this.username = username;
        this.password = password;
    }

    protected String getUsername() {
        return username;
    }

    protected String getPassword() {
        return password;
    }

    @Override
    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(getConnectionUrl(), getUsername(), getPassword());
    }
}
