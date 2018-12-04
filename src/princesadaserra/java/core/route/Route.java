package princesadaserra.java.core.route;

import java.util.ArrayList;
import java.util.List;

public class Route {

    private Long id;
    private String name;
    private List<Segment> segments;

    public Route(){
        segments = new ArrayList<>();
    }

    public int findCityStart(City start) { //retorna o indice do vetor de segmentos que representa a posição do banco, para respeitar a ordem dos segmentos

        for (int x = 0; x < segments.size(); x++)
            if (segments.get(x).getCityOrigin().getId() == start.getId())
                return x;

        return -1;
    }

    public int findCityFinish(City finish) { //retorna o indice do vetor de segmentos que representa a posição do banco, para respeitar a ordem dos segmentos

        for (int x = 0; x < segments.size(); x++)
            if (segments.get(x).getCityDestination().getId() == finish.getId())
                return x;

        return -1;
    }

    public boolean addSegment(Segment segment) {

        return segments.add(segment);
    }

    public long getTime() {
        long sum = 0;
        for (Segment s : segments) {
            sum += s.getTime().getTime();
        }

        return sum;
    }

    public boolean removeLastSegment() {
        if (!isEmpty()) {
            segments.remove(segments.size() - 1);
            return true;
        } else
            return false;
    }

    public boolean isEmpty() {
        return segments.isEmpty();
    }

    public Segment getLastSegment() {
        if (!isEmpty())
            return segments.get(segments.size() - 1);
        else
            return null;
    }

    public double getValue(){
        double sum = 0;

        for(Segment s : segments){
            sum += s.getValue();
        }

        return sum;
    }

    public City[] getCities() {
        City[] cities = new City[getCityCount()];

        if (!isEmpty()) {
            cities[0] = getFirstCity();

            int x = 1;
            for (Segment s : segments) {
                cities[x] = s.getCityDestination();
                x++;
            }
        }

        return cities;
    }

    public int getCityCount() {
        int count = 0;

        if (!isEmpty())
            count = segments.size() + 1;

        return count;
    }

    public City getLastCity() {
        if (!isEmpty())
            return getLastSegment().getCityDestination();
        else
            return null;
    }

    public City getFirstCity() {
        if (!isEmpty()) return segments.get(0).getCityOrigin();
        else return null;
    }

    // Getter & Setter
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Segment> getSegments() {
        return segments;
    }

    public void setSegments(List<Segment> segments) {
        this.segments = segments;
    }

    @Override
    public String toString() {
        return name;
    }
}
