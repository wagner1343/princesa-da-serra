package princesadaserra.java;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {

    @Override
    public void start(Stage state) throws Exception {

        Parent p = FXMLLoader.load(getClass().getResource("/view/loginview/LoginScreen.fxml"));
        Scene scene = new Scene(p, 800, 600);
        state.setTitle("Princesa da Serra");
        state.setScene(scene);
        state.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
