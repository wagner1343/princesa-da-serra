package princesadaserra.java.ui.screen.login;

import javafx.event.ActionEvent;
import princesadaserra.java.tasks.auth.LoginWithUserAndPassword;

public class LoginViewControllerGuest implements LoginViewController {

    @Override
    public void loginOnClick(String user, String password) {
        LoginWithUserAndPassword loginTask = new LoginWithUserAndPassword(user, password);
        loginTask.addOnFinishCallback(()->{
            System.out.println("Login " + loginTask.getSTATUS() + "!" + " for " + loginTask.getUser() + ":" + loginTask.getPassword());
        });
        loginTask.start();
    }
}
