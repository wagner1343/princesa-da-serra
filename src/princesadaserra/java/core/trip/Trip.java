package princesadaserra.java.core.trip;

import princesadaserra.java.core.route.Route;
import princesadaserra.java.core.user.User;
import princesadaserra.java.core.vehicle.Bus;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

public class Trip {
    private Long id;
    private Route route;
    private Time dateStart;
    private Time dateFinish;
    private Time dateExpected;
    private User driver;
    private Bus bus;
    private List<TripSale> sells = null;

    public Trip(){
        sells = new ArrayList<>();
    }

    public User getDriver() {
        return driver;
    }

    public void setDriver(User driver) {
        this.driver = driver;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Route getRoute() {
        return route;
    }

    public void setRoute(Route route) {
        this.route = route;
    }

    public Time getTimeStart() {
        return dateStart;
    }

    public void setTimeStart(Time dateStart) {
        this.dateStart = dateStart;
    }

    public Time getTimeFinish() {
        return dateFinish;
    }

    public void setTimeFinish(Time dateFinish) {
        this.dateFinish = dateFinish;
    }

    public Time getTimeExpected() {
        return dateExpected;
    }

    public void setTimeExpected(Time dateExpected) {
        this.dateExpected = dateExpected;
    }

    public Bus getBus() {
        return bus;
    }

    public void setBus(Bus bus) {
        this.bus = bus;
    }

}
