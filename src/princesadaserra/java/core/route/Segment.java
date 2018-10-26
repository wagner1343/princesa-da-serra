package princesadaserra.java.core.route;

public class Segment {

	private int id;

	public Segment(City cityOrigin, City cityDestination, int time, double value) {
		this.cityOrigin = cityOrigin;
		this.cityDestination = cityDestination;
		this.time = time;
		this.value = value;
	}

	private City cityOrigin;
	private City cityDestination;
	private int time;
	private double value;

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

	public boolean connectsTo(Segment other){
	    return getCityDestination() == other.getCityOrigin();
    }
}
