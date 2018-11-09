package princesadaserra.java.usecases.auth;

import javafx.util.Pair;
import princesadaserra.java.core.user.User;
import princesadaserra.java.persistence.repository.user.UserRepository;
import princesadaserra.java.util.context.AppContext;
import princesadaserra.java.util.threading.Task;

public class LoginWithUserAndPassword extends Task<Pair<String, String>, User, Integer>{
    private AppContext context;

    public LoginWithUserAndPassword(AppContext context){
        this.context = context;
    }

    @Override
    protected User execute(Pair<String, String> userAndPassword) {
        System.out.println("LoginWithUserAndPassword.execute");
        UserRepository userRepository = new UserRepository(userAndPassword.getKey(), userAndPassword.getValue());
        User user = userRepository.find(userAndPassword.getKey());

        if(user == null)
            setFailed();
        else
            context.setCurrentUser(user);

        return user;
    }
}
