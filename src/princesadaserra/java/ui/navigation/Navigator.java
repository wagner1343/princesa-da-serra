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
    private Map<Window, Stage> stageOwnershipMap;
    private AppContext context;
    private static Navigator instance;

    public static Navigator getInstance() {
        if(instance == null){
            instance = new Navigator();
        }

        return instance;
    }

    private Navigator(){
        context = AppContext.getInstance();
        sceneMap = new HashMap<>(SceneBuilder.ScenesTypes.values().length, 1);
        stageOwnershipMap = new HashMap<>();
    }

    public void navigateToNewWindow(SceneBuilder.ScenesTypes scene){
        Stage newStage = new Stage();
        Scene nextScene = getSceneBuilder(scene).build();


        newStage.setScene(nextScene);
        newStage.show();
        stageOwnershipMap.put(nextScene.getWindow(), newStage);

        System.out.println("Navigator.navigateToNewWindow");
        System.out.println("scene = [" + scene + "]");
        System.out.println("window = [" + nextScene.getWindow() + "]");
        System.out.println("nextScene = [" + nextScene + "]");
    }

    public void navigateTo(SceneBuilder.ScenesTypes sceneType, Window window){
        Stage targetStage = stageOwnershipMap.get(window);
        Scene nextScene = getSceneBuilder(sceneType).build();

        targetStage.setScene(nextScene);
        stageOwnershipMap.put(window, targetStage);

        targetStage.show();

        System.out.println("Navigator.navigateTo");
        System.out.println("sceneType = [" + sceneType + "], window = [" + window + "]");
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
