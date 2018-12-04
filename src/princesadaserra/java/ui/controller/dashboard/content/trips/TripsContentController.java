package princesadaserra.java.ui.controller.dashboard.content.trips;

// TODO implement TripsContentController

import javax.sql.ConnectionPoolDataSource;

public class TripsContentController {
    private ConnectionPoolDataSource dataSource;
    private String username;

    public TripsContentController(ConnectionPoolDataSource dataSource, String username) {
        this.dataSource = dataSource;
        this.username = username;
    }
}
