package princesadaserra.java.ui.controller;

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
