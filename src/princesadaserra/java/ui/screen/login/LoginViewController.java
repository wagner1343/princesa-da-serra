package princesadaserra.java.ui.screen.login;

import javafx.event.ActionEvent;
import princesadaserra.java.ui.navigation.Navigator;
import princesadaserra.java.ui.screen.SceneBuilder;
import princesadaserra.java.usecases.auth.LoginWithUserAndPassword;

public class LoginViewController {
    private LoginView view;

    public LoginViewController(LoginView view){
        this.view = view;
    }

    public void loginOnClick(ActionEvent event){
        LoginWithUserAndPassword task = new LoginWithUserAndPassword(view.getUser(), view.getPassword());

        task.addOnSuccessCallback((result) ->
        {
            if(result != null) {
                view.getContext().setCurrentUser(result);
                view.getContext().getNavigator().navigateTo(SceneBuilder.ScenesTypes.DASHBOARD);
            } else
                view.incorrectUserOrPassword();
        }
        );

        task.start();
        System.out.println("LoginOnClick finished");
    }
}
