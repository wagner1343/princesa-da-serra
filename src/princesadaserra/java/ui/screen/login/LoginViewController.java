package princesadaserra.java.ui.screen.login;

import javafx.event.ActionEvent;
import princesadaserra.java.ui.navigation.Navigator;
import princesadaserra.java.ui.screen.Controller;
import princesadaserra.java.ui.screen.SceneBuilder;
import princesadaserra.java.usecases.auth.LoginWithUserAndPassword;

public class LoginViewController extends Controller<LoginView> {

    public LoginViewController(LoginView view) {
        super(view);
    }

    @Override
    public void bind(LoginView loginView) {
        getView().loginOnClick().set(this::loginOnClick);
    }

    public void loginOnClick(ActionEvent actionEvent){
        LoginWithUserAndPassword task = new LoginWithUserAndPassword(getView().getUser(),getView().getPassword());

        task.addOnSuccessCallback((result) ->
        {
            if(result)
                Navigator.getInstance().navigateTo(SceneBuilder.ScenesTypes.DASHBOARD, getView().getScene().getWindow());
            else
                getView().incorrectUserOrPassword();
        }
        );

        task.start();
        System.out.println("LoginOnClick finished");
    }
}
