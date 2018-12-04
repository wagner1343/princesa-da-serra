package princesadaserra.java.usecases.user;

import princesadaserra.java.core.user.User;
import princesadaserra.java.persistence.repository.user.UserRepository;
import princesadaserra.java.util.threading.Task;

import java.util.List;

public class GetAllUsers extends Task<String, List<User>, Integer> {
    private String username;
    private UserRepository userRepository;

    public GetAllUsers(UserRepository userRepository, String username) {
        this.userRepository = userRepository;
        this.username = username;
    }

    @Override
    protected List<User> execute(String s) {
        List<User> userList = userRepository.list();

        return userList;
    }
}
