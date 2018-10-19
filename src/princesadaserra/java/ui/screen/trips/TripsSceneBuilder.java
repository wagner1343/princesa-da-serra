package princesadaserra.java.ui.screen.trips;

import com.sun.org.apache.regexp.internal.RE;
import javafx.scene.Node;
import javafx.scene.Scene;
import princesadaserra.java.core.user.User;
import princesadaserra.java.ui.SharedVisualProperties;
import princesadaserra.java.ui.screen.SceneBuilder;
import princesadaserra.java.ui.screen.util.UnauthorizedView;
import princesadaserra.java.ui.screen.util.UnauthorizedViewController;
import princesadaserra.java.util.config.ConfigKeys;
import princesadaserra.java.util.context.AppContext;

public class TripsSceneBuilder extends SceneBuilder {
    public TripsSceneBuilder(AppContext context) {
        super(context);
    }

    @Override
    public Scene build() {
        User user = getContext().getCurrentUser();

        if(user != null){
            switch (user.getRole()){
                case GUEST:
                    UnauthorizedView unauthorizedView = new UnauthorizedView();
                    UnauthorizedViewController unauthorizedViewController = new UnauthorizedViewController(unauthorizedView);

                    return new Scene(unauthorizedView);

                case SELLER:
                    return new Scene(new TripsViewSeller());
            }
        }else{
            return new Scene(new UnauthorizedView());
        }

        return new Scene(new UnauthorizedView());
    }
}
