package princesadaserra.java.ui.navigation;

import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.Window;
import princesadaserra.java.ui.screen.SceneBuilder;
import princesadaserra.java.ui.screen.dashboard.DashboardSceneBuilder;
import princesadaserra.java.ui.screen.login.LoginSceneBuilder;
import princesadaserra.java.util.context.AppContext;

import java.util.HashMap;
import java.util.Map;

public class Navigator {
    private Map<SceneBuilder.ScenesTypes, SceneBuilder> sceneMap;
    private AppContext context;
    private Stage stage;

    public Navigator(Stage stage, AppContext context){
        this.stage = stage;
        this.context = context;
        sceneMap = new HashMap<>(SceneBuilder.ScenesTypes.values().length, 1);
    }

    public void navigateTo(SceneBuilder.ScenesTypes sceneType){
        Scene nextScene = getSceneBuilder(sceneType).build();

        stage.setScene(nextScene);
        stage.show();

        System.out.println("Navigator.navigateTo");
        System.out.println("sceneType = [" + sceneType + "]");
        System.out.println("nextScene = [" + nextScene + "]");
    }


    private SceneBuilder getSceneBuilder(SceneBuilder.ScenesTypes scene){
        if(!sceneMap.containsKey(scene))
            sceneMap.put(scene, createSceneBuilder(scene));

        return sceneMap.get(scene);
    }


    private SceneBuilder createSceneBuilder(SceneBuilder.ScenesTypes scene){
        switch (scene){
            case LOGIN:
                return new LoginSceneBuilder(context);
            case DASHBOARD:
                return new DashboardSceneBuilder(context);

        }

        return null;
    }
}
