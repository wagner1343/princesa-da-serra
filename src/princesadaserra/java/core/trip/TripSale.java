package princesadaserra.java.core.trip;

import princesadaserra.java.core.route.City;
import princesadaserra.java.core.user.User;

public class TripSale {
    private Long id;
    private User passenger;
    private User employee;
    private Trip trip;
    private City cityOrigin;
    private City cityDestination;
    private double TotalValue;
    private int seatNumber;

    public User getEmployee(){ return employee; }

    public void setEmployee(User employee){ this.employee = employee; }

    public User getPassenger() {
        return passenger;
    }

    public void setPassenger(User passenger) {
        this.passenger = passenger;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Trip getTrip() {
        return trip;
    }

    public void setTrip(Trip trip) {
        this.trip = trip;
    }

    public City getCityOrigin() {
        return cityOrigin;
    }

    public void setCityOrigin(City cityOrigin) {
        this.cityOrigin = cityOrigin;
    }

    public City getCityDestination() {
        return cityDestination;
    }

    public void setCityDestination(City cityDestination) {
        this.cityDestination = cityDestination;
    }

    public double getTotalValue() {
        return TotalValue;
    }

    public void setTotalValue(double totalValue) {
        TotalValue = totalValue;
    }

    public int getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(int seatNumber) {
        this.seatNumber = seatNumber;
    }
}
