package princesadaserra.java.core.route;

import java.util.Date;

public class Segment {

	private int id;
	private City cityOrigin;
	private City cityDestination;
	private Date time;
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

	public boolean isConnected(Segment other){
	    return getCityDestination() == other.getCityOrigin();
    }
}
