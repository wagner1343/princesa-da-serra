package princesadaserra.java.core.route;

public class City {

    private int id;
    private String name;

    public City(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object other) {
        if (other == null) return false;
        if (other == this) return true;
        if (!(other instanceof City)) return false;

        City otherCity = (City) other;
        if (otherCity.getId() == this.getId() && otherCity.getName() == this.getName()) {
            return true;
        } else {
            return false;
        }

    }
}
