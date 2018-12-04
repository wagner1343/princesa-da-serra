package princesadaserra.java.usecases.user;

import princesadaserra.java.core.role.Role;
import princesadaserra.java.persistence.repository.user.UserRepository;
import princesadaserra.java.util.threading.Task;

import java.util.List;

public class GetAllRoles extends Task<String, List<Role>, Integer> {
    private UserRepository userRepository;
    public GetAllRoles(UserRepository userRepository){
        this.userRepository = userRepository;
    }
    @Override
    protected List<Role> execute(String argument) {
        List<Role> roles = userRepository.findAllRoles();

        return roles;
    }
}
