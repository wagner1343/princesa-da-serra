package princesadaserra.java.ui.navigation;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import princesadaserra.java.ui.controller.View;
import princesadaserra.java.ui.controller.login.LoginViewController;
import princesadaserra.java.util.context.AppContext;

import java.io.IOException;
import java.util.ResourceBundle;

public class Navigator {
    private Scene rootScene;
    private FXMLLoader fxmlLoader;
    private AppContext context;
    private View lastScenetype;
    private Object lastController;

    public Navigator(Stage stage, AppContext context) {
        this.context = context;
        fxmlLoader = new FXMLLoader();

        rootScene = new Scene(new AnchorPane(), 1024, 768);
        stage.setScene(rootScene);
        if (!stage.isShowing()) stage.show();
    }

    public void reload(){
        navigateTo(lastScenetype, lastController);
    }

    public void navigateTo(View sceneType){
        navigateTo(sceneType, null);
    }

    public Parent loadView(View view, Object controller, AppContext context) throws IOException {
        fxmlLoader = new FXMLLoader(getClass().getResource(view.getPath()), context.getLocaleBundle());
        fxmlLoader.setController(controller);
        System.out.println("context == null = " + context == null);
        return  fxmlLoader.load();
    }
    public void navigateTo(View sceneType, Object controller) {

        System.out.println("Navigator.navigateTo:" + "sceneType = [" + sceneType + "]");
        try {
            rootScene.setRoot(loadView(sceneType, controller, context));
            lastScenetype = sceneType;
            lastController = controller;
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
