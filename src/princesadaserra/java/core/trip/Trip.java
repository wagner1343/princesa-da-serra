package princesadaserra.java.core.trip;

import princesadaserra.java.core.route.City;
import princesadaserra.java.core.route.Route;
import princesadaserra.java.core.route.Segment;
import princesadaserra.java.core.user.User;
import princesadaserra.java.core.vehicle.Bus;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class Trip {
    private Long id;
    private Route route;
    private Timestamp dateStart;
    private Timestamp dateFinish;
    private Timestamp dateExpected;
    private User driver;
    private Bus bus;
    private List<TripSale> sells = null;
    private int[][] poltronasegmento = null;
    private int seats = 0;
    private int segm = 0;

    public void inicializaMatriz() {

        int seats = bus.getModel().getSeatAmount();
        int segm = route.getSegments().size();
        poltronasegmento = new int[seats][segm];

        for (int x = 0; x < seats; x++)
            for (int y = 0; y < segm; y++)
                poltronasegmento[x][y] = 0;

        for (TripSale a : sells)
            setBuy(a.getCityOrigin(), a.getCityDestination(), a.getSeatNumber());
    }

    public void setBuy(City start, City finish, int numberSeat) {

        for (int x = route.findCityStart(start); x <= route.findCityFinish(finish); x++)
            poltronasegmento[numberSeat][x] = 1;

    }

    public List<Integer> emptySeats(City start, City finish) {

        List<Integer> emptys = new ArrayList<>();

        for (int x = 0; x <= seats; x++) //passo todos os seats pra funcao que verifica se o seat está vazio naquela range
            if (isAvailable(start, finish, x))
                emptys.add(x);

        return emptys;
    }

    public boolean isAvailable(City start, City finish, int numberSeat) {

        for (int x = route.findCityStart(start); x <= route.findCityFinish(finish); x++) {
            if (poltronasegmento[numberSeat][x] == 1)
                return false;
        }
        return true;
    }

    public void setSells(List<TripSale> sells) {

        this.sells = sells;
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

    public Timestamp getTimeStart() {
        return dateStart;
    }

    public void setTimeStart(Timestamp dateStart) {
        this.dateStart = dateStart;
    }

    public Timestamp getTimeFinish() {
        return dateFinish;
    }

    public void setTimeFinish(Timestamp dateFinish) {
        this.dateFinish = dateFinish;
    }

    public Timestamp getTimeExpected() {
        return new Timestamp(dateStart.getTime() + route.getTime());
    }

    public void setTimeExpected(Timestamp dateExpected) {
        this.dateExpected = dateExpected;
    }

    public Bus getBus() {
        return bus;
    }

    public void setBus(Bus bus) {
        this.bus = bus;
    }

}
