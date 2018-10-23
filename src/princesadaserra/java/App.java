package princesadaserra.java;

import javafx.application.Application;
import javafx.stage.Stage;
import princesadaserra.java.ui.navigation.Navigator;
import princesadaserra.java.ui.screen.SceneBuilder;

public class App extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Navigator navigator = Navigator.getInstance();

        navigator.navigateToNewWindow(SceneBuilder.ScenesTypes.LOGIN);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
