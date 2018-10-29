package princesadaserra.java.util.context;

import princesadaserra.java.core.user.User;
import princesadaserra.java.ui.navigation.Navigator;
import princesadaserra.java.util.threading.CallbackWithArgument;
import princesadaserra.java.util.threading.CallbackWithArgumentList;

public class AppContext {
    private static final String CONFIG_PATH = "/config.txt";
    private static AppContext instance;
    private Navigator navigator;
    private User currentUser;
    private CallbackWithArgumentList<User> onUserChanged;

    public AppContext(){
        onUserChanged = new CallbackWithArgumentList<>();
    }

    public static AppContext getInstance() {
        if(instance == null)
            instance = new AppContext();
        return instance;
    }

    public void addOnUserChanged(CallbackWithArgument<User> callback) {
        onUserChanged.addCallback(callback);
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
        onUserChanged.executeAll(currentUser);
    }
}
