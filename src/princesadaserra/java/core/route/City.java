package princesadaserra.java.core.route;

import java.util.Objects;

public class City {

    private Long id;
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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
        return Objects.equals(otherCity.getId(), this.getId()) && Objects.equals(otherCity.getName(), this.getName());

    }

    @Override
    public String toString() {
        return name;
    }
}
