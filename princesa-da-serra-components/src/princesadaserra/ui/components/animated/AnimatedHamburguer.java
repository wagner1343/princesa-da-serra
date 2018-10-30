package princesadaserra.ui.components.animated;

import com.jfoenix.controls.JFXHamburger;
import javafx.animation.RotateTransition;
import javafx.scene.input.MouseEvent;
import javafx.util.Duration;

public class AnimatedHamburguer extends JFXHamburger {
    RotateTransition transition;

    public AnimatedHamburguer(){
        transition = new RotateTransition(new Duration(300),this);
        transition.setFromAngle(0);
        transition.setToAngle(360);

        this.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> transition.play());
    }
}
