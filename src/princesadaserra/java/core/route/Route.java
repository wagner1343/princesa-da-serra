package princesadaserra.java.core.route;

import java.util.ArrayList;

public abstract class Route {

	private int id;
	private String name;
	private ArrayList<Segment> segments;

	public Route(){
	    segments = new ArrayList<>();
    }

    public boolean addSegment(Segment segment){
	    boolean result;

	    if(result = isEmpty())
	        segments.add(segment);
        else if(result = getLastSegment().isConnected(segment))
            segments.add(segment);

        return result;
    }

    public boolean removeLastSegment(){
        if(!isEmpty()){
            segments.remove(segments.size() - 1);
            return true;
        }
        else
            return false;
    }

    public boolean isEmpty(){
	    return segments.isEmpty();
    }

    public Segment getLastSegment() {
        if(!isEmpty())
            return segments.get(segments.size() - 1);
        else
            return null;
    }

    public City[] getCities(){
	    City[] cities = new City[getCityCount()];

	    if(!isEmpty()){
	        cities[0] = getFirstCity();

	        int x = 1;
	        for(Segment s : segments){
	            cities[x] = s.getCityDestination();
	            x++;
            }
        }

        return cities;
    }

    public int getCityCount(){
	    int count = 0;

	    if(!isEmpty())
	        count = segments.size() + 1;

        return count;
    }

    public City getLastCity(){
	    if(!isEmpty())
	        return getLastSegment().getCityDestination();
	    else
	        return null;
    }

    public City getFirstCity() {
        if(!isEmpty()) return segments.get(0).getCityOrigin();
        else return null;
    }

    // Getter & Setter
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ArrayList<Segment> getSegments() {
        return segments;
    }

    public void setSegments(ArrayList<Segment> segments) {
        this.segments = segments;
    }
}
