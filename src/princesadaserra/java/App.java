package princesadaserra.java;

import javafx.application.Application;
import javafx.stage.Stage;
import princesadaserra.java.ui.controller.login.LoginViewController;
import princesadaserra.java.ui.navigation.Navigator;
import princesadaserra.java.ui.controller.ScenesTypes;
import princesadaserra.java.util.context.AppContext;

public class App extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        AppContext appContext = new AppContext(stage);
        appContext.getNavigator().navigateTo(ScenesTypes.LOGIN, new LoginViewController(appContext));
    }

    public static void main(String[] args) {
        launch(args);
    }
}
