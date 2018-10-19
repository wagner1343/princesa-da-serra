package princesadaserra.java.ui.screen.login;

import javafx.scene.Scene;
import princesadaserra.java.ui.screen.SceneBuilder;
import princesadaserra.java.util.context.AppContext;

public class LoginSceneBuilder extends SceneBuilder{

    public LoginSceneBuilder(AppContext context) {
        super(context);
    }

    @Override
    public Scene build() {
        LoginViewController loginViewController = new LoginViewController(new LoginView());

        Scene scene = new Scene(loginViewController.getView());
        return scene;
    }
}
