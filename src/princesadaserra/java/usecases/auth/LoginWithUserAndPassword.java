package princesadaserra.java.usecases.auth;

import javafx.util.Pair;
import princesadaserra.java.core.user.User;
import princesadaserra.java.service.AuthService;
import princesadaserra.java.util.context.AppContext;
import princesadaserra.java.util.threading.Task;

public class LoginWithUserAndPassword extends Task<Pair<String,String>, User, Integer> {
    private final String password;

    public String getPassword() {
        return password;
    }

    public String getUser() {
        return user;
    }

    private final String user;

    public LoginWithUserAndPassword(String user, String password){
        this.user = user;
        this.password = password;
    }
    @Override
    protected User execute(Pair<String, String> argument) {
        String user;
        String password;

        if(argument == null){
            user = this.getUser();
            password = this.getPassword();
        } else{
            user = argument.getKey();
            password = argument.getValue();
        }
        User u = AuthService.getInstance().authenticate(user, password);

        return u;
    }
}
