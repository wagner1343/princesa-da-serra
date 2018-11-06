package princesadaserra.java.usecases.auth;

import javafx.util.Pair;
import princesadaserra.java.core.user.User;
import princesadaserra.java.service.AuthService;
import princesadaserra.java.util.context.AppContext;
import princesadaserra.java.util.threading.Task;

public class Logout extends Task<AppContext, Boolean, Integer>{
    @Override
    protected Boolean execute(AppContext context) {
        AuthService.logout();

        return true;
    }
}
