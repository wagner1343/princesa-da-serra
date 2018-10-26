package princesadaserra.java.util.context;

import princesadaserra.java.core.user.User;
import princesadaserra.java.ui.navigation.Navigator;

public class AppContext {
    private static final String CONFIG_PATH = "/config.txt";
    private static AppContext instance;
    private Navigator navigator;
    private User currentUser;

    public static AppContext getInstance() {
        if(instance == null)
            instance = new AppContext();
        return instance;
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
}
