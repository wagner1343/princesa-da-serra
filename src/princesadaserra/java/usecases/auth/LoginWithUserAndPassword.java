package princesadaserra.java.usecases.auth;

import javafx.util.Pair;
import org.postgresql.ds.PGConnectionPoolDataSource;
import princesadaserra.java.core.user.User;
import princesadaserra.java.persistence.repository.connection.PDSDatabaseConnectionPool;
import princesadaserra.java.persistence.repository.user.UserRepository;
import princesadaserra.java.util.context.AppContext;
import princesadaserra.java.util.threading.Task;

import java.sql.SQLException;

public class LoginWithUserAndPassword extends Task<Object, PDSDatabaseConnectionPool, Integer>{
    private String user;
    private String password;

    public LoginWithUserAndPassword(String user, String password) {
        this.user = user;
        this.password = password;
    }

    @Override
    protected PDSDatabaseConnectionPool execute(Object obj) {
        System.out.println("LoginWithUserAndPassword.execute");
        PDSDatabaseConnectionPool connectionPool = new PDSDatabaseConnectionPool(user, password);

        try {
            connectionPool.getConnection();
            setSuccess();
        } catch (SQLException e) {
            e.printStackTrace();
            setFailed();
        }
        return connectionPool;
    }
}
