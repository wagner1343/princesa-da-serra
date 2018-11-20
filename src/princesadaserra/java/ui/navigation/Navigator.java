package princesadaserra.java.ui.navigation;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import princesadaserra.java.ui.controller.ScenesTypes;
import princesadaserra.java.util.context.AppContext;

import java.io.IOException;
import java.util.ResourceBundle;

public class Navigator {
    private Stage stage;
    private Scene rootScene;
    private FXMLLoader fxmlLoader;
    private AppContext context;
    private ScenesTypes lastScenetype;
    private Object lastController;

    public Navigator(Stage stage, AppContext context) {
        this.stage = stage;
        this.context = context;
        fxmlLoader = new FXMLLoader();
    }

    public void reload(){
        navigateTo(lastScenetype, lastController);
    }

    public void navigateTo(ScenesTypes sceneType){
        navigateTo(sceneType, null);
    }
    public void navigateTo(ScenesTypes sceneType, Object controller) {

        System.out.println("Navigator.navigateTo:" + "sceneType = [" + sceneType + "]");
        Parent nextRoot;
        try {
            fxmlLoader = new FXMLLoader(getClass().getResource(sceneType.getPath()), context.getResourceBundle());
            fxmlLoader.setController(controller);
            nextRoot = fxmlLoader.load();

            if (rootScene == null) {
                rootScene = new Scene(nextRoot, 1024, 768);
                stage.setScene(rootScene);
            }
            else rootScene.setRoot(nextRoot);

            if (!stage.isShowing()) stage.show();

            lastScenetype = sceneType;
            lastController = controller;
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
