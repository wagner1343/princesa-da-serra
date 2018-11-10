package princesadaserra.java.core.vehicle;

import java.util.Date;

public class Bus {

    private Long id;
    private Long id_model;
    private Date lastMaintenance;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId_model() {
        return id_model;
    }

    public void setId_model(Long id_model) {
        this.id_model = id_model;
    }

    public Date getLastMaintenance() {
        return lastMaintenance;
    }

    public void setLastMaintenance(Date lastMaintenance) {
        this.lastMaintenance = lastMaintenance;
    }
}
