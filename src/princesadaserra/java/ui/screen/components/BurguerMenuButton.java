package princesadaserra.java.ui.screen.components;

import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.transitions.hamburger.HamburgerBasicCloseTransition;
import com.jfoenix.transitions.hamburger.HamburgerNextArrowBasicTransition;
import javafx.animation.Transition;
import javafx.scene.input.MouseEvent;

public class BurguerMenuButton extends JFXHamburger {
    HamburgerNextArrowBasicTransition nextArrowBasicTransition;
    HamburgerBasicCloseTransition basicCloseTransition;
    boolean isChangingTransition;
    boolean isMouseHover;

    Transition transition;
    Transition nextTransition;
    private double transitionRate = 3;

    public BurguerMenuButton(){
        nextArrowBasicTransition = new HamburgerNextArrowBasicTransition(this);
        basicCloseTransition = new HamburgerBasicCloseTransition(this);
        transition = nextArrowBasicTransition;
        isChangingTransition = false;
        isMouseHover = isHover();

        this.addEventHandler(MouseEvent.MOUSE_ENTERED, event -> {
            if(!isChangingTransition) {
                System.out.println("mouse entered burguer");
                transition.setRate(transitionRate);
                transition.play();
            }
            isMouseHover = true;
        });
        this.addEventHandler(MouseEvent.MOUSE_EXITED, event -> {
            if(!isChangingTransition) {
                System.out.println("Mouse exited burguer");
                transition.setRate(-transitionRate);
                transition.play();
            }
            isMouseHover = false;
        });

        this.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {

            if (nextTransition == basicCloseTransition) {
                System.out.println("Changing to arrowTransition");
                nextTransition = nextArrowBasicTransition;
            } else {
                System.out.println("Changing to close transition");
                nextTransition = basicCloseTransition;
            }

            if(!isChangingTransition) {
                isChangingTransition = true;
                transition.jumpTo(transition.getTotalDuration());

                transition.setRate(-transitionRate * 2);

                // transition.setRate(-transitionRate * 2);
                transition.setOnFinished(e -> {
                    isChangingTransition = false;
                    transition.setRate(transitionRate);
                    transition = nextTransition;
                    transition.setOnFinished(ev -> {
                    });
                    if(isMouseHover) transition.play();

                });

                transition.play();
            }
        });
    }
}
