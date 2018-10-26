package princesadaserra.java.usecases.auth;

import javafx.util.Pair;
import princesadaserra.java.core.user.User;
import princesadaserra.java.service.AuthService;
import princesadaserra.java.util.context.AppContext;
import princesadaserra.java.util.threading.Task;

public class Logout extends Task<AppContext, Pair<Boolean, String>, Integer>{
    @Override
    protected Pair<Boolean, String> execute(AppContext context) {
        User user = context.getCurrentUser();
        if (user == null){
            setFailed();
            return new Pair<>(false, "User logged out already");
        }


        if(AuthService.getInstance().logout(user)){
            setSuccess();
            return new Pair<>(true, "Logged out with success");
        }
        else {
            setFailed();
            return new Pair<>(false, "Logout failed");
        }
    }
}
