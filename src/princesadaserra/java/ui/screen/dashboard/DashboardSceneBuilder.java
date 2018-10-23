package princesadaserra.java.ui.screen.dashboard;

import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import princesadaserra.java.ui.screen.SceneBuilder;
import princesadaserra.java.ui.screen.templates.SellerSidePane;
import princesadaserra.java.ui.screen.trips.TripsViewSeller;
import princesadaserra.java.ui.screen.util.UnauthorizedView;
import princesadaserra.java.util.context.AppContext;

public class DashboardSceneBuilder extends SceneBuilder{
    public DashboardSceneBuilder(AppContext context) {
        super(context);
    }

    @Override
    public Scene build() {
        Scene scene;
        if(getContext().getCurrentUser() != null) {
            DashboardView dashboardView;

            switch (getContext().getCurrentUser().getRole()){
                case SELLER:
                    scene = new Scene(new DashboardView(new SellerSidePane(), new TripsViewSeller()));
                    break;

                default:
                    scene = new Scene(new UnauthorizedView());

            }

            return scene;
        }
        else{
            scene = new Scene(new UnauthorizedView());
        }
        return scene;
    }
}
