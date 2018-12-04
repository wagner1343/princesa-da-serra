package princesadaserra.java.core.route;

import java.sql.Time;

public class Segment {

    private Long id;
    private City cityOrigin;
    private City cityDestination;
    private Time time;
    private double value;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
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

    public boolean connectsTo(Segment other) {
        return getCityDestination().getId() == other.getCityOrigin().getId();
    }
}
