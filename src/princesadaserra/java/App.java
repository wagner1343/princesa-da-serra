package princesadaserra.java;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import princesadaserra.java.ui.screen.login.LoginScreenController;

public class App extends Application {

    @Override
    public void start(Stage stage) throws Exception {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/loginview/LoginScreen.fxml"));
        loader.setController(new LoginScreenController() {
            @Override
            @FXML
            public void loginOnClick() {
                System.out.println("LoginOnCLick");
            }
        });

        Parent p = (Parent) loader.load();
        Scene scene = new Scene(p, 800, 600);
        stage.setTitle("Princesa da Serra");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
