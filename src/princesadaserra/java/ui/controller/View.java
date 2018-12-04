package princesadaserra.java.ui.controller;

public enum View {
    USER_REGISTER_PANE,
    TRIPS_CONTENT,
    USERS_CONTENT,
    VEHICLES_CONTENT,
    LOGIN,
    DASHBOARD;

    public String getPath(){
        switch (this) {
            case DASHBOARD:
                return "/view/dashboard/Dashboard.fxml";
            case LOGIN:
                return "/view/login/Login.fxml";
            case TRIPS_CONTENT:
                return "/view/dashboard/content/trips/TripsContent.fxml";
            case USERS_CONTENT:
                return "/view/dashboard/content/users/UsersContent.fxml";
            case VEHICLES_CONTENT:
                return "/view/dashboard/content/vehicles/VehiclesContent.fxml";
            case USER_REGISTER_PANE:
                return "/view/dashboard/content/users/UserRegisterPane.fxml";
            default:
                return null;
        }
    }
}
