package princesadaserra.java.usecases.user;

import princesadaserra.java.core.user.User;
import princesadaserra.java.persistence.repository.user.UserRepository;
import princesadaserra.java.util.threading.Task;

import java.util.List;
import java.util.function.Predicate;

public class GetUserBySpecification extends Task<String, List<User>, Integer> {
    private Predicate<User>[] specifications;
    private UserRepository userRepository;

    public GetUserBySpecification(UserRepository userRepository, Predicate<User>... specifications){
        this.userRepository = userRepository;
        this.specifications = specifications;
    }
    @Override
    protected List<User> execute(String argument) {
        List<User> list = userRepository.list();
        for(Predicate p : specifications)
            list.removeIf(p);

        for(User u : list){
            System.out.println(u + " found");
        }
        return list;
    }
}
