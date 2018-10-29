package princesadaserra.java;

import javafx.application.Application;
import javafx.stage.Stage;
import princesadaserra.java.ui.navigation.Navigator;
import princesadaserra.java.ui.views.ScenesTypes;
import princesadaserra.java.util.context.AppContext;

import java.util.Scanner;

public class App extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        AppContext.getInstance().setNavigator(new Navigator(stage));
        AppContext.getInstance().getNavigator().navigateTo(ScenesTypes.LOGIN);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
