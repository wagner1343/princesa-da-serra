package princesadaserra.java.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class AuthenticatedConnectionProvider extends ConnectionProvider{
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

    private String username;
    private String password;

    @Override
    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(getConnectionUrl(), getUsername(), getPassword());
    }
}
