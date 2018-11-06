package princesadaserra.java.usecases.auth;

import princesadaserra.java.core.user.User;
import princesadaserra.java.persistence.user.UserRepository;
import princesadaserra.java.service.AuthService;
import princesadaserra.java.util.context.AppContext;
import princesadaserra.java.util.threading.Task;

public class LoginWithUserAndPassword extends Task<AppContext, User, Integer> {
    private final String password;
    private final String userName;
    private UserRepository userRepository;

    public LoginWithUserAndPassword(String userName, String password){
        this.userName = userName;
        this.password = password;
        userRepository = new UserRepository(userName, password);
    }

    @Override
    protected User execute(AppContext context) {
        System.out.println("LoginWithUserAndPassword.execute");
        
        if(AuthService.authenticate(userName, password)){
            context.getCurrentUser().setUsername(userName);
            context.getCurrentUser().setPassword(password);

            context.setCurrentUser(userRepository.find(userName));
        }

        return null;
    }
}
