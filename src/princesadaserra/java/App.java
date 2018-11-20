package princesadaserra.java;

import javafx.application.Application;
import javafx.stage.Stage;
import princesadaserra.java.ui.controller.Views;
import princesadaserra.java.ui.controller.login.LoginViewController;
import princesadaserra.java.util.context.AppContext;

public class App extends Application {

    @Override
    public void start(Stage stage) {
        AppContext appContext = new AppContext(stage);
        appContext.getNavigator().navigateTo(Views.LOGIN, new LoginViewController(appContext));
    }

    public static void main(String[] args) {
        launch(args);
    }
}
