package princesadaserra.java.util.context;

import javafx.stage.Stage;
import princesadaserra.java.ui.navigation.Navigator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Locale;
import java.util.ResourceBundle;

public class AppContext {
    private Navigator navigator;

    public ArrayList<String> getLanguageList() {
        return languageList;
    }

    public void setLanguageList(ArrayList<String> languageList) {
        this.languageList = languageList;
    }

    private ArrayList<String> languageList;

    public void setResourceBundle(ResourceBundle resourceBundle) {
        this.resourceBundle = resourceBundle;
    }

    private ResourceBundle resourceBundle;

    public AppContext(Stage stage){
        resourceBundle = ResourceBundle.getBundle("locale.strings", new Locale("en_US"));
        navigator = new Navigator(stage, this);
        languageList = new ArrayList<String>(Arrays.asList("pt_BR", "en_US"));
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
