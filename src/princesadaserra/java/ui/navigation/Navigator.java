package princesadaserra.java.ui.navigation;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import princesadaserra.java.ui.views.ScenesTypes;
import princesadaserra.java.ui.views.dashboard.DashboardViewController;
import princesadaserra.java.ui.views.login.LoginViewController;
import princesadaserra.java.util.context.ResourcesHolder;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Navigator {
    private Map<ScenesTypes, Parent> sceneMap;
    private Stage stage;
    private FXMLLoader fxmlLoader;
    private FXMLPreloader preloader;

    public Navigator(Stage stage) {
        this.stage = stage;
        fxmlLoader = new FXMLLoader();
        preloader = new FXMLPreloader(this);
        sceneMap = new HashMap<>(ScenesTypes.values().length, 1);

        fxmlLoader.setResources(ResourcesHolder.getResourceBundle());

        for (ScenesTypes scene : ScenesTypes.values()) preloader.load(scene);
    }

    public void navigateTo(ScenesTypes sceneType) {
        System.out.println("Navigator.navigateTo");
        System.out.println("sceneType = [" + sceneType + "]");

        stage.setScene(getScene(sceneType));
        if (!stage.isShowing()) stage.show();
    }


    private Scene getScene(ScenesTypes scene) {
        return new Scene(isPreloaded(scene) ? pickFromPool(scene) : loadFxml(scene));
    }

    public boolean isPreloaded(ScenesTypes scene) {
        return sceneMap.containsKey(scene);
    }

    public void addToPool(ScenesTypes scene, Parent p) {
        sceneMap.put(scene, p);
    }

    private Parent pickFromPool(ScenesTypes scene) {
        Parent p = sceneMap.remove(scene);
        preloader.load(scene);
        return p;
    }

    public synchronized Parent loadFxml(ScenesTypes scene) {
        switch (scene) {
            case LOGIN:
                try {
                    fxmlLoader.setController(new LoginViewController());
                    return fxmlLoader.load(getClass().getResource("/view/login/LoginScreen.fxml"));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            case DASHBOARD:
                try {
                    fxmlLoader.setController(new DashboardViewController());
                    return fxmlLoader.load(getClass().getResource("/view/templates/DashBoardTemplate.fxml"));
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }
        return null;
    }
}
