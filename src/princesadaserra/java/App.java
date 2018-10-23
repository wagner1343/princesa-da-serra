package princesadaserra.java;

import javafx.application.Application;
import javafx.stage.Stage;
import princesadaserra.java.ui.navigation.Navigator;
import princesadaserra.java.ui.screen.SceneBuilder;
import princesadaserra.java.util.context.AppContext;

import java.util.Locale;
import java.util.ResourceBundle;

public class App extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        AppContext appContext = new AppContext();
        Navigator navigator = new Navigator(stage, appContext);
        appContext.setNavigator(navigator);

        navigator.navigateTo(SceneBuilder.ScenesTypes.LOGIN);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
