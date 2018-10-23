package princesadaserra.java.util.context;

import princesadaserra.java.core.user.User;
import princesadaserra.java.ui.navigation.Navigator;
import princesadaserra.java.util.config.Config;
import princesadaserra.java.util.config.ConfigLoader;

import java.util.Locale;
import java.util.ResourceBundle;

public class AppContext {
    private static final String CONFIG_PATH = "/config.txt";

    private Navigator navigator;
    private User currentUser;
    private Config config;

    public AppContext(){
        config = new Config(ConfigLoader.load(getClass().getResource(CONFIG_PATH)));
    }

    public Navigator getNavigator() {
        return navigator;
    }

    public void setNavigator(Navigator navigator) {
        this.navigator = navigator;
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
}
