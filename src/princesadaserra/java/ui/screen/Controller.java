package princesadaserra.java.ui.screen;

public abstract class Controller<ViewType> {
    public ViewType getView() {
        return view;
    }

    private ViewType view;

    public Controller(ViewType view){
        this.view = view;
        bind(view);
    }
    public abstract void bind(ViewType viewType);


}
