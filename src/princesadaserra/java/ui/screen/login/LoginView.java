package princesadaserra.java.ui.screen.login;

import javafx.beans.property.ObjectProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import princesadaserra.java.ui.screen.View;

public interface LoginView<LoginViewControllerType> extends View<LoginViewControllerType>{
    public abstract String getLogin();
    public abstract String getPassword();
    public abstract ObjectProperty<EventHandler<ActionEvent>> getLoginOnClick();
}
