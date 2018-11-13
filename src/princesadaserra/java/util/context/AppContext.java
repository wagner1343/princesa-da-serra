package princesadaserra.java.util.context;

import javafx.stage.Stage;
import princesadaserra.java.core.user.User;
import princesadaserra.java.ui.navigation.Navigator;
import princesadaserra.java.util.callback.CallbackWithArgument;
import princesadaserra.java.util.callback.CallbackWithArgumentList;

import java.util.ResourceBundle;

public class AppContext {
    private Navigator navigator;
    private ResourceBundle resourceBundle;

    public AppContext(Stage stage){
        navigator = new Navigator(stage);
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
