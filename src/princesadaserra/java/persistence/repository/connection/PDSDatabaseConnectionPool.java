package princesadaserra.java.persistence.repository.connection;

import org.postgresql.ds.PGConnectionPoolDataSource;

public class PDSDatabaseConnectionPool extends PGConnectionPoolDataSource {
    private static final String SERVER_NAME = "localhost";
    private static final int PORT_NUMBER = 5432;
    private static final String DATABASE = "princesa_da_serra";

    public PDSDatabaseConnectionPool(String user, String password){
        super();
        setServerName(SERVER_NAME);
        setPortNumber(PORT_NUMBER);
        setDatabaseName(DATABASE);
        super.setUser(user);
        super.setPassword(password);
    }
}
