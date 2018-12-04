package princesadaserra.java.usecases.user;

import princesadaserra.java.core.user.User;
import princesadaserra.java.persistence.repository.user.UserRepository;
import princesadaserra.java.util.threading.Task;

import javax.sql.ConnectionPoolDataSource;

public class GetUserByUsername extends Task<String, User, Integer> {
    private String username;
    private UserRepository userRepository;

    public GetUserByUsername(UserRepository userRepository, String username) {
        this.userRepository = userRepository;
        this.username = username;
    }

    @Override
    protected User execute(String s) {
        User user = userRepository.find(username);
        if(user == null){
            setFailed();
        }
        else{
            setSuccess();
        }
        return user;
    }
}
