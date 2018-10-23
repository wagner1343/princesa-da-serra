package princesadaserra.java.ui.screen.login;

import princesadaserra.java.ui.screen.components.LoginScreen;

public class LoginView extends LoginScreen {
    private LoginViewController controller;

    public LoginView() {
        super();
        controller = new LoginViewController(this);

        init();
    }

    private void init(){
        this.loginOnClick().set((event) -> {
            controller.loginOnClick(event);
        });
    }

}
