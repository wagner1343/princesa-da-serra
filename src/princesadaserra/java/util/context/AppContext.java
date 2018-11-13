package princesadaserra.java.util.context;

import javafx.stage.Stage;
import princesadaserra.java.ui.navigation.Navigator;

import java.util.Locale;
import java.util.ResourceBundle;

public class AppContext {
    private Navigator navigator;
    private ResourceBundle resourceBundle;

    public AppContext(Stage stage){
        resourceBundle = ResourceBundle.getBundle("locale.strings", new Locale("en", "US"));
        navigator = new Navigator(stage, resourceBundle);
    }

    public ResourceBundle getResourceBundle() {
        return resourceBundle;
    }

    public Navigator getNavigator() {
        return navigator;
    }

    public void setNavigator(Navigator navigator) {
        this.navigator = navigator;
    }
}
