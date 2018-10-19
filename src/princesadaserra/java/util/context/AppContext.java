package princesadaserra.java.util.context;

import princesadaserra.java.core.user.User;
import princesadaserra.java.util.config.Config;
import princesadaserra.java.util.config.ConfigLoader;

public class AppContext {
    private static AppContext instance;

    public static final String CONFIG_PATH = "/config.txt";

    private AppContext(){
        config = new Config(ConfigLoader.load(getClass().getResource(CONFIG_PATH)));
    }

    public static AppContext getInstance(){
        if(instance == null){
            instance = new AppContext();
        }

        return instance;
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
