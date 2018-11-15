package princesadaserra.java.ui.controller;

public enum ScenesTypes {
    LOGIN,
    DASHBOARD;

    public String getPath(){
        switch (this) {
            case DASHBOARD:
                return "/view/dashboard/Dashboard.fxml";
            case LOGIN:
                return "/view/login/Login.fxml";
            default:
                return null;
        }
    }
}
