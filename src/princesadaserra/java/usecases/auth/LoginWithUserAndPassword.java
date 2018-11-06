package princesadaserra.java.usecases.auth;

import princesadaserra.java.core.user.User;
import princesadaserra.java.service.AuthService;
import princesadaserra.java.util.context.AppContext;
import princesadaserra.java.util.threading.Task;

public class LoginWithUserAndPassword extends Task<AppContext, User, Integer> {
    private final String password;
    private final String userName;

    public LoginWithUserAndPassword(String userName, String password, AppContext appContext){
        this.userName = userName;
        this.password = password;
    }

    @Override
    protected User execute(AppContext context) {
        System.out.println("LoginWithUserAndPassword.execute");
        
        if(AuthService.authenticate(userName, password)){

        }
    }
}
