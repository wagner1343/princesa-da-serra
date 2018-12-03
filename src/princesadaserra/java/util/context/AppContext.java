package princesadaserra.java.util.context;

import javafx.stage.Stage;
import princesadaserra.java.ui.navigation.Navigator;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Locale;
import java.util.ResourceBundle;

public class AppContext {
    private Navigator navigator;
    private ArrayList<String> languageList;
    private ResourceBundle localeBundle;

    public AppContext(Stage stage){
        localeBundle = ResourceBundle.getBundle("locale.strings", new Locale("en_US"));
        navigator = new Navigator(stage, this);
        languageList = new ArrayList<>(Arrays.asList("pt_BR", "en_US"));
    }

    public ArrayList<String> getLanguageList() {
        return languageList;
    }

    public ResourceBundle getLocaleBundle() {
        return localeBundle;
    }

    public Navigator getNavigator() {
        return navigator;
    }

    public void setLocale(Locale locale) {
        localeBundle = ResourceBundle.getBundle("locale.strings", locale);
    }
}
