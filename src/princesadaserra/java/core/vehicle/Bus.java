package princesadaserra.java.core.vehicle;

import java.sql.Date;
import java.sql.Time;

public class Bus {

    private Long id;
    private Model model;
    private Date lastMaintenance;

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

    public Date getLastMaintenance() {
        return lastMaintenance;
    }

    public void setLastMaintenance(Date lastMaintenance) {
        this.lastMaintenance = lastMaintenance;
    }

    @Override
    public String toString() {
        return String.format("%d %s %s", id, model.toString(), lastMaintenance.toString());
    }

    @Override
    public boolean equals(Object other) {
        System.out.println("Bus.equals");
        if (other == null) {
            return false;
        }

        Bus b1 = (Bus) other;

        return b1.getId() == this.getId();

    }
}
