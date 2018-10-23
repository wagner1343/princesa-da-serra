package princesadaserra.java.ui.screen.components;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.beans.property.ObjectProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import princesadaserra.java.ui.SharedVisualProperties;
import princesadaserra.java.util.context.ResourcesHolder;

public class LoginPane extends AnchorPane {
        private JFXTextField userTextField;
        private JFXPasswordField passwordTextField;
        private JFXButton loginButton;

        public ObjectProperty<EventHandler<ActionEvent>> loginOnClick(){
            return loginButton.onActionProperty();
        }

        public String getUser(){
            return userTextField.getText();
        }

        public String getPassword(){
            return passwordTextField.getText();
        }


        public LoginPane(){
            userTextField = new JFXTextField();
            passwordTextField = new JFXPasswordField();
            loginButton = new JFXButton();

            userTextField.setCacheHint(javafx.scene.CacheHint.SPEED);
            userTextField.setPrefWidth(180);
            userTextField.setPrefHeight(25);
            userTextField.setLayoutX(37.0);
            userTextField.setLayoutY(16.0);
            userTextField.setFocusColor(Color.web("#757575"));
            userTextField.setUnFocusColor(Color.web("#00000061"));
            userTextField.setPromptText(ResourcesHolder.getResourceBundle().getString("user.textfield.prompt"));


            passwordTextField.setCacheHint(javafx.scene.CacheHint.SPEED);
            passwordTextField.setPrefWidth(180);
            passwordTextField.setPrefHeight(25);
            passwordTextField.setLayoutX(37.0);
            passwordTextField.setLayoutY(48.0);
            passwordTextField.setFocusColor(Color.web("#757575"));
            passwordTextField.setUnFocusColor(Color.web("#00000061"));
            passwordTextField.setPromptText(ResourcesHolder.getResourceBundle().getString("password.textfield.prompt"));

            loginButton.setCacheHint(javafx.scene.CacheHint.SPEED);
            loginButton.setLayoutX(20.0);
            loginButton.setLayoutY(93.0);
            loginButton.setPrefWidth(208);
            loginButton.setPrefHeight(30);
            loginButton.setStyle("-fx-background-color: #C4FF39; -fx-background-radius: 5;");
            loginButton.setFont(new Font(14.0));
            loginButton.setTextFill(Color.web("white"));
            loginButton.setText(ResourcesHolder.getResourceBundle().getString("login.button.text"));


            setCacheHint(javafx.scene.CacheHint.SPEED);
            setLayoutX(81.0);
            setLayoutY(48.0);
            setMaxHeight(USE_PREF_SIZE);
            setMaxWidth(USE_PREF_SIZE);
            setMinHeight(USE_PREF_SIZE);
            setMinWidth(USE_PREF_SIZE);
            setPrefHeight(144.0);
            setPrefWidth(251.0);
            setStyle("-fx-background-radius: 5; -fx-background-color: white;");

            getChildren().add(userTextField);
            getChildren().add(passwordTextField);
            getChildren().add(loginButton);

            setEffect(SharedVisualProperties.getDropShadow());
        }


}
