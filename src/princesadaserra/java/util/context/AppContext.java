package princesadaserra.java.util.context;

import princesadaserra.java.core.user.User;
import princesadaserra.java.ui.navigation.Navigator;
import princesadaserra.java.util.config.Config;
import princesadaserra.java.util.config.ConfigLoader;

import java.util.Locale;
import java.util.ResourceBundle;

public class AppContext {
    static Locale defaultLocale = new Locale("pt", "BR");
    public static final String CONFIG_PATH = "/config.txt";

    public ResourceBundle getStringsResource() {
        return stringsResource;
    }

    public void setStringsResource(ResourceBundle stringsResource) {
        this.stringsResource = stringsResource;
    }

    private ResourceBundle stringsResource;

    public Navigator getNavigator() {
        return navigator;
    }

    public void setNavigator(Navigator navigator) {
        this.navigator = navigator;
    }

    Navigator navigator;

    public AppContext(){
        stringsResource = ResourceBundle.getBundle("/locale/strings", defaultLocale);
        config = new Config(ConfigLoader.load(getClass().getResource(CONFIG_PATH)));
    }


    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }

    public Config getConfig() {
        return config;
    }

    public void setConfig(Config config) {
        this.config = config;
    }

    private User currentUser;
    private Config config;
}
