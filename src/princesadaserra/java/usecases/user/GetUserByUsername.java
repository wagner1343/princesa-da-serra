package princesadaserra.java.usecases.user;

import princesadaserra.java.core.user.User;
import princesadaserra.java.persistence.repository.user.UserRepository;
import princesadaserra.java.util.callback.Callback;
import princesadaserra.java.util.threading.Task;

import javax.sql.ConnectionPoolDataSource;
import javax.sql.DataSource;

public class GetUserByUsername extends Task<String, User, Integer> {
    private ConnectionPoolDataSource dataSource;
    private String username;

    public GetUserByUsername(ConnectionPoolDataSource dataSource, String username) {
        this.dataSource = dataSource;
        this.username = username;
    }

    @Override
    protected User execute(String s) {
        UserRepository repository = new UserRepository(dataSource);
        User user = repository.find(username);
        if(user == null){
            setFailed();
        }
        else{
            setSuccess();
        }
        return user;
    }
}
