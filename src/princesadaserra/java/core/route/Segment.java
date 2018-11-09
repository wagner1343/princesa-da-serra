package princesadaserra.java.core.route;

public class Segment {

    private int id;
    private City cityOrigin;
    private City cityDestination;
    private int time;
    private double value;

    public Segment(City cityOrigin, City cityDestination, int time, double value) {
        this.cityOrigin = cityOrigin;
        this.cityDestination = cityDestination;
        this.time = time;
        this.value = value;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
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
        return getCityDestination() == other.getCityOrigin();
    }
}
