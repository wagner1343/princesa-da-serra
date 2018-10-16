package princesadaserra.java;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
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
        int screenX = 800;
        int screenY = 600;


        DrawerStackExample stackExample = new DrawerStackExample();


        Parent p = new MainAppTemplate(new TripsContentExample(), "Viagens");

        AnchorPane root = new AnchorPane();
        root.setStyle("-fx-background-color: grey");


        p.setStyle("-fx-background-color: yellow");
        //stackExample.setStyle("-fx-background-color: cyan");

        AnchorPane.setLeftAnchor(p, 0d);
        AnchorPane.setRightAnchor(p, 0d);
        AnchorPane.setTopAnchor(p, 0d);
        AnchorPane.setBottomAnchor(p, 0d);

        AnchorPane.setLeftAnchor(stackExample, 0d);
        AnchorPane.setRightAnchor(stackExample, 0d);
        AnchorPane.setTopAnchor(stackExample, 0d);
        AnchorPane.setBottomAnchor(stackExample, 0d);

        root.getChildren().add(p);
        root.getChildren().add(stackExample);

        AnchorPane.setLeftAnchor(stackExample, 0d);
        AnchorPane.setTopAnchor(stackExample, 0d);

        AnchorPane.setLeftAnchor(p, 0d);
        AnchorPane.setRightAnchor(p, 0d);


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
