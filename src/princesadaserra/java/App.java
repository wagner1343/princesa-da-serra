package princesadaserra.java;

import javafx.application.Application;
import javafx.stage.Stage;
import princesadaserra.java.ui.controller.View;
import princesadaserra.java.ui.controller.login.LoginViewController;
import princesadaserra.java.util.context.AppContext;

import java.io.IOException;

public class App extends Application {

    @Override
    public void start(Stage stage) {
        AppContext appContext = new AppContext(stage);
        appContext.getNavigator().navigateTo(View.LOGIN, new LoginViewController(appContext));
    }

    public static void main(String[] args) {
        launch(args);
    }
}
