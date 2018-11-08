package princesadaserra.java.ui.navigation;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import princesadaserra.java.ui.controller.ScenesTypes;
import princesadaserra.java.util.context.ResourcesHolder;

import java.io.IOException;
import java.net.URL;

public class Navigator {
    private Stage stage;
    private Scene rootScene;
    private FXMLLoader fxmlLoader;
    private Preloader<URL, Parent> preloader;

    public Navigator(Stage stage) {
        this.stage = stage;
        fxmlLoader = new FXMLLoader();
        preloader = new Preloader<>((url) -> {
            try {
                return FXMLLoader.load(url, ResourcesHolder.getResourceBundle());
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        });

        for (ScenesTypes scene : ScenesTypes.values())
            preloader.preload(getClass().getResource(scene.getPath()));
    }

    public void navigateTo(ScenesTypes sceneType) {
        System.out.println("Navigator.navigateTo:" + "sceneType = [" + sceneType + "]");
        Parent nextRoot = preloader.load(getClass().getResource(sceneType.getPath()));

        if (rootScene == null) {
            rootScene = new Scene(nextRoot);
            stage.setScene(rootScene);
        } else
            rootScene.setRoot(nextRoot);

        if (!stage.isShowing()) stage.show();
    }
}
