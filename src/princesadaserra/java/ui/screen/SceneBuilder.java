package princesadaserra.java.ui.screen;

import javafx.scene.Scene;
import princesadaserra.java.util.context.AppContext;

public abstract class SceneBuilder<SceneView, SceneController> {
    private AppContext context;

    public SceneBuilder(AppContext context){
        this.context = context;
    }

    public abstract Scene build();
}
