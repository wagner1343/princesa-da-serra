package princesadaserra.java.ui.navigation;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import princesadaserra.java.ui.controller.ScenesTypes;
import princesadaserra.java.util.context.ResourcesHolder;

import java.io.IOException;

public class Navigator {
    private Stage stage;
    private Scene rootScene;
    private FXMLLoader fxmlLoader;

    public Navigator(Stage stage) {
        this.stage = stage;
        fxmlLoader = new FXMLLoader();
    }

    public void navigateTo(ScenesTypes sceneType){
        navigateTo(sceneType, null);
    }
    public void navigateTo(ScenesTypes sceneType, Object controller) {
        System.out.println("Navigator.navigateTo:" + "sceneType = [" + sceneType + "]");
        Parent nextRoot;
        try {
            fxmlLoader = new FXMLLoader(getClass().getResource(sceneType.getPath()), ResourcesHolder.getResourceBundle());
            fxmlLoader.setController(controller);
            nextRoot = fxmlLoader.load();

            if (rootScene == null) {
                rootScene = new Scene(nextRoot);
                stage.setScene(rootScene);
            }
            else rootScene.setRoot(nextRoot);

            if (!stage.isShowing()) stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
