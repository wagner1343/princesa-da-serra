package princesadaserra.java.ui.screen;

import javafx.scene.Scene;
import princesadaserra.java.util.context.AppContext;

public abstract class SceneBuilder {
    public enum ScenesTypes {
        LOGIN,
        DASHBOARD
    }
    public AppContext getContext() {
        return context;
    }

    private AppContext context;

    public SceneBuilder(AppContext context){
        this.context = context;
    }

    public abstract Scene build();
}
