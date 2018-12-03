package princesadaserra.java.core.vehicle;

import java.sql.Time;

public class Bus {

    private Long id;
    private Model model;
    private Time lastMaintenance;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Model getModel() {
        return model;
    }

    public void setModel(Model model) {
        this.model = model;
    }

    public Time getLastMaintenance() {
        return lastMaintenance;
    }

    public void setLastMaintenance(Time lastMaintenance) {
        this.lastMaintenance = lastMaintenance;
    }
}
