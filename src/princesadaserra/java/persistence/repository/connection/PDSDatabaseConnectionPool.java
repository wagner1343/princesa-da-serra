package princesadaserra.java.persistence.repository.connection;

import org.postgresql.ds.PGConnectionPoolDataSource;

import javax.sql.ConnectionPoolDataSource;

public class PDSDatabaseConnectionPool extends PGConnectionPoolDataSource {
    private static final String CONNECTION_URL = "jdbc:postgresql://localhost:5432/princesa_da_serra";

    public PDSDatabaseConnectionPool(String user, String password){
        super();
        super.setURL(CONNECTION_URL);
        super.setUser(user);
        super.setPassword(password);
    }
}
