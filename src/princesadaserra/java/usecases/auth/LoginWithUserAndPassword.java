package princesadaserra.java.usecases.auth;

import princesadaserra.java.core.user.User;
import princesadaserra.java.service.AuthService;
import princesadaserra.java.util.context.AppContext;
import princesadaserra.java.util.threading.Task;

public class LoginWithUserAndPassword extends Task<AppContext, User, Integer> {
    private final String password;
    private final String user;

    public LoginWithUserAndPassword(String user, String password){
        this.user = user;
        this.password = password;
    }

    @Override
    protected User execute(AppContext context) {
        User user = AuthService.getInstance().authenticate(this.user, password);

        if(user == null)
            setFailed();
        else {
            context.setCurrentUser(user);
            setSuccess();
        }

        return user;
    }
}
