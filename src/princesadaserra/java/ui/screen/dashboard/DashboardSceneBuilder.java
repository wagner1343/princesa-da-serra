package princesadaserra.java.ui.screen.dashboard;

import javafx.scene.Scene;
import princesadaserra.java.ui.screen.SceneBuilder;
import princesadaserra.java.ui.screen.components.SellerSidePane;
import princesadaserra.java.ui.screen.trips.TripsViewSales;
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
                    scene = new Scene(new DashboardView(new SellerSidePane(), new TripsViewSales()));
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
