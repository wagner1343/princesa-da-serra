package princesadaserra.java.ui.screen.login;

import javafx.event.ActionEvent;
import princesadaserra.java.ui.navigation.Navigator;
import princesadaserra.java.ui.screen.Controller;
import princesadaserra.java.ui.screen.SceneBuilder;

public class LoginViewController extends Controller<LoginView> {

    public LoginViewController(LoginView view) {
        super(view);
    }

    @Override
    public void bind(LoginView loginView) {
        getView().loginOnClick().set((action) -> loginOnClick(action));
    }

    public void loginOnClick(ActionEvent actionEvent){
        Navigator.getInstance().navigateTo(SceneBuilder.ScenesTypes.DASHBOARD, getView().getScene().getWindow());
    }
}
