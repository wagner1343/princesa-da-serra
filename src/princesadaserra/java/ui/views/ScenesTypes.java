package princesadaserra.java.ui.views;

public enum ScenesTypes {
    LOGIN,
    DASHBOARD;

    public String getPath(){
        switch (this) {
            case DASHBOARD:
                return "/view/templates/DashboardTemplate.fxml";
            case LOGIN:
                return "/view/login/LoginScreen.fxml";
            default:
                return null;
        }
    }
}
