package princesadaserra.java;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import princesadaserra.java.ui.screen.login.LoginScreenBase;
import princesadaserra.java.ui.screen.login.LoginScreenController;
import princesadaserra.java.ui.screen.templates.DrawerStackExample;
import princesadaserra.java.ui.screen.templates.MainAppTemplate;
import princesadaserra.java.ui.screen.trips.TripsContentExample;

public class App extends Application {

    @Override
    public void start(Stage stage) throws Exception {

        DrawerStackExample stackExample = new DrawerStackExample();
        stackExample.setMinWidth(200);



        Parent p = new MainAppTemplate(new TripsContentExample(), "Viagens");

        HBox root = new HBox();
        ((MainAppTemplate) p).setMinWidth(200);
        root.getChildren().add(stackExample);
        root.getChildren().add(p);

        root.setOnMouseClicked((event -> {
            stackExample.getDrawer().toggle();
            System.out.println("Toggle");
        }));

        Scene scene = new Scene(root, 800, 600);

        stage.setTitle("Princesa da Serra");
        stage.setScene(scene);

        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
