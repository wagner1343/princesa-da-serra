package princesadaserra.java.usecases.user;

import princesadaserra.java.core.user.User;
import princesadaserra.java.persistence.repository.user.UserRepository;
import princesadaserra.java.util.threading.Task;

public class CreateNewUser extends Task<String, User, Integer> {
    private UserRepository userRepository;
    private User user;
    public CreateNewUser(UserRepository userRepository, User user){
        this.userRepository = userRepository;
        this.user = user;
    }

    @Override
    protected User execute(String argument) {
        User resultUser = userRepository.add(user);
        if(resultUser == null)
            setFailed();
        return resultUser;
    }
}
