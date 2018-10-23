package princesadaserra.java.ui.screen.login;

import princesadaserra.java.ui.screen.components.LoginScreen;
import princesadaserra.java.util.context.AppContext;

public class LoginView extends LoginScreen {
    private LoginViewController controller;
    private AppContext context;

    public LoginView(AppContext context) {
        super(context);

        this.context = context;
        controller = new LoginViewController(this);

        init();
    }

    private void init(){
        this.loginOnClick().set((event) -> {
            controller.loginOnClick(event);
        });
    }

}
