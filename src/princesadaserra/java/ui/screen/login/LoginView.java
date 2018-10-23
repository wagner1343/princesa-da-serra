package princesadaserra.java.ui.screen.login;

import javafx.beans.property.ObjectProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import princesadaserra.java.ui.screen.View;
import princesadaserra.java.ui.screen.templates.LoginScreenBase;

public class LoginView extends LoginScreenBase{
    private LoginViewController controller;

    public LoginView() {
        super();
        controller = new LoginViewController(this);

        init();
    }

    private void init(){
        this.loginOnClick().set((event) -> {
            controller.loginOnClick(event);
        });
    }

}
