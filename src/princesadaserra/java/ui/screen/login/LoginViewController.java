package princesadaserra.java.ui.screen.login;

import javafx.event.ActionEvent;
import princesadaserra.java.ui.navigation.Navigator;
import princesadaserra.java.ui.screen.SceneBuilder;
import princesadaserra.java.usecases.auth.LoginWithUserAndPassword;
import princesadaserra.java.util.context.AppContext;

public class LoginViewController {
    private LoginView view;
    private AppContext context;

    public LoginViewController(AppContext context,LoginView view){
        this.view = view;
        this.context = context;
    }

    public void loginOnClick(ActionEvent event){
        LoginWithUserAndPassword task = new LoginWithUserAndPassword(view.getUser(), view.getPassword());

        task.addOnSuccessCallback((result) ->
        {
            if(result != null) {
                context.setCurrentUser(result);
                context.getNavigator().navigateTo(SceneBuilder.ScenesTypes.DASHBOARD);
            } else
                view.incorrectUserOrPassword();
        }
        );

        task.start();
        System.out.println("LoginOnClick finished");
    }
}
