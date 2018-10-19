package princesadaserra.java;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import princesadaserra.java.ui.navigation.Navigator;
import princesadaserra.java.ui.screen.SceneBuilder;
import princesadaserra.java.ui.screen.login.LoginSceneBuilder;
import princesadaserra.java.ui.screen.templates.AppBarTemplate;
import princesadaserra.java.ui.screen.templates.DrawerStackExample;
import princesadaserra.java.ui.screen.templates.MainAppTemplate;
import princesadaserra.java.ui.screen.trips.TripsContentExample;
import princesadaserra.java.util.context.AppContext;

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
